/*
 * LazySerializerFactory.java 2014年8月24日 下午3:17:41
 * 
 * Copyright© 2014 fuvang Technology, Inc. All rights reserved. 福旺公司版权所有，并保持所有权利。
 */
package com.haqiu.framework.rpc;

import com.caucho.hessian.io.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Class description goes here.
 *
 * @author GilesChu
 *
 * @version 3.0.0, 2014年8月24日 下午3:17:41
 *
 */
public class LazySerializerFactory extends SerializerFactory {

    public static String versionString = "";
    static {
        try {
            Class<?> clazz = Class.forName("org.hibernate.Version");
            Method getVersionString = org.springframework.util.ReflectionUtils.findMethod(Class.forName("org.hibernate.Version"),
                    "getVersionString");
            versionString = (String) org.springframework.util.ReflectionUtils.invokeMethod(getVersionString, clazz);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Serializer getSerializer(Class cls) throws HessianProtocolException {
        try {
            if (versionString.startsWith("3") && Class.forName("org.hibernate.collection.PersistentCollection").isAssignableFrom(cls)) {
                return new LazySerializerForHibernate3(cls);
            } else if (versionString.startsWith("4")
                    && Class.forName("org.hibernate.collection.spi.PersistentCollection").isAssignableFrom(cls)) {
                return new LazySerializerForHibernate4(cls);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return super.getSerializer(cls);
    }

}

class LazySerializerForHibernate3 extends JavaSerializer {
    static Method isInitializedMethod = null;
    static Class<?> PersistentSetClass;
    static Class<?> PersistentMapClass;
    static Class<?> PersistentListClass;
    static {
        try {
            PersistentSetClass = Class.forName("org.hibernate.collection.PersistentSet");
            PersistentMapClass = Class.forName("org.hibernate.collection.PersistentMap");
            PersistentListClass = Class.forName("org.hibernate.collection.PersistentList");
            isInitializedMethod = org.springframework.util.ReflectionUtils.findMethod(Class.forName("org.hibernate.Hibernate"),
                    "isInitialized");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("rawtypes")
    public LazySerializerForHibernate3(Class cls) {
        super(cls);
    }

    CollectionSerializer collectionSeiralizer = new CollectionSerializer();
    MapSerializer mapSerializer = new MapSerializer();

    /**
     * @Title: writeObject
     * @Description: TODO(override hessian write)
     * @param @param object
     * @param @param out
     * @param @throws IOException
     * @return void
     * @throws
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void writeObject(Object object, AbstractHessianOutput out) throws IOException {
        boolean ifHaveInit = (boolean) org.springframework.util.ReflectionUtils.invokeMethod(isInitializedMethod, object);
        Object obj = convertPersistent(object);
        if (ifHaveInit && object != null) {
            out.writeObject(obj);
            out.flush();
            return;
        }
        if (PersistentMapClass.isAssignableFrom(object.getClass())) {
            mapSerializer.writeObject(new HashMap(), out);
        } else {
            collectionSeiralizer.writeObject(new ArrayList(), out);
        }

    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static Object convertPersistent(Object obj) {

        if (obj.getClass().isAssignableFrom(PersistentSetClass)) {
            Set set = new HashSet();
            Set pset = (Set) obj;
            if (pset != null) {
                for (int i = 0; i < pset.size(); i++) {
                    set.add(pset.toArray()[i]);
                }
            }
            return set;
        } else if (obj.getClass().isAssignableFrom(PersistentListClass)) {
            List list = new ArrayList();
            List plist = (List) obj;
            if (plist != null) {
                for (int i = 0; i < plist.size(); i++) {
                    list.add(plist.get(i));
                }
            }
            return list;
        } else {
            return obj;
        }
    }
}

class LazySerializerForHibernate4 extends JavaSerializer {

    static Method isInitializedMethod = null;
    static {
        try {
            isInitializedMethod = org.springframework.util.ReflectionUtils.findMethod(Class.forName("org.hibernate.Hibernate"),
                    "isInitialized");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("rawtypes")
    public LazySerializerForHibernate4(Class cls) {
        super(cls);
    }

    CollectionSerializer collectionSeiralizer = new CollectionSerializer();

    MapSerializer mapSerializer = new MapSerializer();

    /**
     * @Title: writeObject
     * @Description: TODO(override hessian write)
     * @param @param object
     * @param @param out
     * @param @throws IOException
     * @return void
     * @throws
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void writeObject(Object object, AbstractHessianOutput out) throws IOException {

        boolean ifHaveInit = (boolean) org.springframework.util.ReflectionUtils.invokeMethod(isInitializedMethod, object);
        if (ifHaveInit) {
            out.writeObject(object);
            out.flush();
            return;
        }

        try {
            if (Class.forName("org.hibernate.collection.internal.PersistentMap").isAssignableFrom(object.getClass())) {
                mapSerializer.writeObject(new HashMap(), out);
            } else {
                collectionSeiralizer.writeObject(new ArrayList(), out);
            }
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}