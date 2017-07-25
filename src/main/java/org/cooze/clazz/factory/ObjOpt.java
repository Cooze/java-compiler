package org.cooze.clazz.factory;

import org.cooze.clazz.compiler.JCompiler;
import org.cooze.clazz.compiler.OverrideClassLoader;
import org.cooze.clazz.proxy.DynamicProxy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author cooze
 * @version 1.0.0
 * @desc
 * @date 2017/6/30
 */
public class ObjOpt {
    private Object object;
    private Class<?> clazz;
    private Map<String, byte[]> CLASS_CACHE;
    private DynamicProxy dynamicProxy = new DynamicProxy();
    private JCompiler jCompiler;


    private ObjOpt() {
    }

    public ObjOpt(Object object, Class<?> clazz, Map<String, byte[]> classCache, JCompiler jCompiler) {
        this.object = object;
        this.clazz = clazz;
        this.CLASS_CACHE = classCache;
        this.jCompiler = jCompiler;
    }


    public Object runMethod(String methodName) throws Exception {
        return dynamicProxy.intercept(object, this.clazz.getMethod(methodName, null), null, null);
    }

    public Object runMethod(String methodName, Object[] params, String... paramTypes) throws Exception {
        Class<?>[] paramTypeArray = null;

        List<Class<?>> paramTypeList;
        if (paramTypes != null && paramTypes.length > 0) {
            paramTypeList = new ArrayList<>();
            for (String type : paramTypes) {
                paramTypeList.add(loadClass(type));
            }
            paramTypeArray = paramTypeList.toArray(new Class<?>[]{});
        }

        return dynamicProxy.intercept(object, this.clazz.getMethod(methodName, paramTypeArray), params, null);
    }


    public Object runMethod(String methodName, Object[] params, Class<?>... paramTypes) throws Exception {
        return dynamicProxy.intercept(object, this.clazz.getMethod(methodName, paramTypes), params, null);
    }

    public Object getObj() {
        return this.object;
    }

    private Class<?> loadClass(String name) {
        Class<?> clazz = null;
        try {
            //直接加载类
            clazz = Class.forName(name);
        } catch (ClassNotFoundException e) {
            //如果异常，则先从原有加载器中加载类
            OverrideClassLoader loader = OverrideClassLoader.getClassLoader();
            if (loader != null) {
                try {
                    clazz = Class.forName(name, false, loader);
                } catch (ClassNotFoundException e1) {
                    try {
                        //如果异常则重新从缓存中加载
                        clazz = this.jCompiler.loadClass(name, CLASS_CACHE);
                    } catch (ClassNotFoundException e2) {
                        e2.printStackTrace();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
        return clazz;
    }

}
