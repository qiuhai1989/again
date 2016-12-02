/**
 *
 */
package com.haqiu.framework.rpc;

import java.lang.annotation.*;

/**
 * @author GilesChu
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
//@Component
public @interface RemoteService {
    String value() default "";

    // RPC Service实现类的接口，如果只有一个，
    Class<?> serviceClass() default Object.class;

    String reference() default "";

}