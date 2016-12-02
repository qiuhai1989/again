/**
 *
 */
package com.haqiu.framework.rpc;

import com.caucho.hessian.io.SerializerFactory;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.remoting.caucho.HessianServiceExporter;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author GilesChu
 *
 */
public class ClassPathMapperScanner extends ClassPathBeanDefinitionScanner {

    private final BeanDefinitionRegistry registry;

    private BeanNameGenerator beanNameGenerator = new AnnotationBeanNameGenerator();

    private ScopeMetadataResolver scopeMetadataResolver = new AnnotationScopeMetadataResolver();

    private ApplicationContext applicationContext;

    private Class<? extends Annotation> annotationClass = RemoteService.class;

    private Map<String, String> serverUrlMap;

    public ClassPathMapperScanner(BeanDefinitionRegistry registry, ApplicationContext applicationContext) {
        super(registry, false);
        this.registry = registry;
        this.applicationContext = applicationContext;
    }

    /**
     * Configures parent scanner to search for the right interfaces. It can search for all interfaces or just for those that extends a
     * markerInterface or/and those annotated with the annotationClass
     */
    public void registerFilters() {
        // if specified, use the given annotation and / or marker interface
        addIncludeFilter(new AnnotationTypeFilter(this.annotationClass));

        // exclude package-info.java
        addExcludeFilter(new TypeFilter() {
            public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
                String className = metadataReader.getClassMetadata().getClassName();
                return className.endsWith("package-info");
            }
        });
    }

    /**
     * Calls the parent search that will search and register all the candidates. Then the registered objects are post processed to set them
     * as MapperFactoryBeans
     */
    @Override
    public Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitions = new LinkedHashSet<BeanDefinitionHolder>();
        for (String basePackage : basePackages) {
            Set<BeanDefinition> candidates = findCandidateComponents(basePackage);
            for (BeanDefinition tmp : candidates) {
                ScannedGenericBeanDefinition candidate = (ScannedGenericBeanDefinition) tmp;
                ScopeMetadata scopeMetadata = this.scopeMetadataResolver.resolveScopeMetadata(candidate);
                candidate.setScope(scopeMetadata.getScopeName());

                String beanName;
                String referServiceName;
                Class<?> serviceInterface;
                Object referServiceInstance;
                AnnotationMetadata meatadata = candidate.getMetadata();
                Map<String, Object> annotationAttributes = meatadata.getAnnotationAttributes("com.haqiu.framework.rpc.RemoteService");
                String defineBeanName = (String) annotationAttributes.get("value");
                if (!"".equals(defineBeanName)) {
                    beanName = defineBeanName;
                } else {
                    beanName = "/" + this.beanNameGenerator.generateBeanName(candidate, this.registry);
                }
                String defineServiceName = (String) annotationAttributes.get("reference");
                if (!"".equals(defineServiceName)) {
                    referServiceName = defineServiceName;
                    referServiceInstance = new RuntimeBeanReference(referServiceName);
                    serviceInterface = applicationContext.getType(referServiceName);
                } else {
                    referServiceName = null;
                    referServiceInstance = null;
                    serviceInterface = null;
                }
                Class<?> defineServiceInterface = (Class<?>) annotationAttributes.get("serviceClass");
                if (Object.class != defineServiceInterface) {
                    serviceInterface = defineServiceInterface;
                    if (null == referServiceInstance) {
                        referServiceInstance = applicationContext.getBean(serviceInterface);
                    }
                } else {
                    if (null == serviceInterface) {
                        try {
                            serviceInterface = Class.forName(meatadata.getInterfaceNames()[0]);
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    if (null == referServiceInstance) {
                        referServiceInstance = applicationContext.getBean(serviceInterface);
                    }
                }

                candidate.setBeanClassName(HessianServiceExporter.class.getName());
                candidate.setBeanClass(HessianServiceExporter.class);

                if (null != referServiceName) {
                    candidate.setDependsOn(new String[] { referServiceName });
                }
                candidate.getPropertyValues().add("service", referServiceInstance);
                candidate.getPropertyValues().add("serviceInterface", serviceInterface);

                SerializerFactory factory = new SerializerFactory();
                factory.addFactory(LazySerializerFactory.createDefault());
                candidate.getPropertyValues().add("serializerFactory", new RuntimeBeanReference("lazySerializer"));
                if (candidate instanceof AbstractBeanDefinition) {
                    postProcessBeanDefinition((AbstractBeanDefinition) candidate, beanName);
                }
                if (candidate instanceof AnnotatedBeanDefinition) {
                    AnnotationConfigUtils.processCommonDefinitionAnnotations((AnnotatedBeanDefinition) candidate);
                }
                if (checkCandidate(beanName, candidate)) {
                    BeanDefinitionHolder definitionHolder = new BeanDefinitionHolder(candidate, beanName);
                    definitionHolder = applyScopedProxyMode(scopeMetadata, definitionHolder, this.registry);
                    beanDefinitions.add(definitionHolder);
                    registerBeanDefinition(definitionHolder, this.registry);
                }
            }
        }
        return beanDefinitions;
    }

    static BeanDefinitionHolder applyScopedProxyMode(ScopeMetadata metadata, BeanDefinitionHolder definition,
            BeanDefinitionRegistry registry) {

        ScopedProxyMode scopedProxyMode = metadata.getScopedProxyMode();
        if (scopedProxyMode.equals(ScopedProxyMode.NO)) {
            return definition;
        }
        boolean proxyTargetClass = scopedProxyMode.equals(ScopedProxyMode.TARGET_CLASS);
        return ScopedProxyUtils.createScopedProxy(definition, registry, proxyTargetClass);
    }
}
