package org.cooze.clazz.proxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author cooze
 * @version 1.0.0
 * @desc
 * @date 2017/6/27
 */
public class DynamicProxy implements MethodInterceptor {
    @Override
    public Object intercept(Object obj, Method method, Object[] params, MethodProxy methodProxy) throws InvocationTargetException, IllegalAccessException {
        return method.invoke(obj, params);
    }
}
