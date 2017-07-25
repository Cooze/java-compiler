package org.cooze.test.main;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author xianzhe_song
 * @version 1.0.0
 * @desc
 * @date 2017/7/2
 */
public class Main1 {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        System.out.println("main1");
        ClassLoader classLoader = Main1.class.getClassLoader();
        Class<?> loadClass = classLoader.loadClass("org.cooze.test.main.Main2");
        Method method = loadClass.getMethod("main", String[].class);
        method.invoke(null, new Object[] { new String[] {} });
    }
}
