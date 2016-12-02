/*
 * TraceHessianProxyFactoryBean.java 2014年8月27日 上午7:39:44
 *
 * Copyright© 2014 fuvang Technology, Inc. All rights reserved. 福旺公司版权所有，并保持所有权利。
 */
package com.haqiu.framework.rpc;

import com.haqiu.framework.utils.EntityConveterUtils;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;

import java.util.Arrays;
import java.util.UUID;

/**
 * Class description goes here.
 *
 * @author GilesChu
 *
 * @version 3.0.0, 2014年8月27日 上午7:39:44
 *
 */
public class TraceHessianProxyFactoryBean extends HessianProxyFactoryBean {
    /**
     * Logger for this class
     */
    private static final Logger logger = LoggerFactory.getLogger(TraceHessianProxyFactoryBean.class);

    public Object invoke(MethodInvocation invocation) throws Throwable {
        String serviceAddr = super.getServiceUrl();
        String serviceMehtod = invocation.getMethod().toString();
        String excuteId = UUID.randomUUID().toString();
        logger.info("Execute Id:{}, remote service address is: {}, method: {},parametes:{}", excuteId, serviceAddr, serviceMehtod,
                Arrays.deepToString(invocation.getArguments()));
        Object rtnObject = super.invoke(invocation);
        if(logger.isInfoEnabled()){
            try {
                //new Gson().toJson(rtnObject) 不能有相同的字段
                logger.info("Execute Id:{},result:{}", excuteId, EntityConveterUtils.conveterEntityToJson(rtnObject));
            } catch (Exception e) {
                logger.error(e.getMessage(),e);
            }
        }
        return rtnObject;
    }

}
