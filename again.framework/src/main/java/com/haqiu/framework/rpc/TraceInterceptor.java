/*
 * TraceInterceptor.java 2014年8月26日 下午9:38:08
 *
 * Copyright© 2014 fuvang Technology, Inc. All rights reserved. 福旺公司版权所有，并保持所有权利。
 */
package com.haqiu.framework.rpc;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Class description goes here.
 *
 * @author GilesChu
 *
 * @version 3.0.0, 2014年8月26日 下午9:38:08
 *
 */
public class TraceInterceptor implements MethodInterceptor {
    public Object invoke(MethodInvocation invocation) throws Throwable {

        try {
            System.out.println("method start");
            return invocation.proceed();
        } catch (Exception e) {
            System.out.println(e);
            return "Exception";
        } finally {
            System.out.println("method end");
        }
    }
}
