package org.cooze.clazz.compiler;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

/**
 * 注意：
 * 自定义类加载器，单例全局唯一，保证全局使用同一个类加载器，从而使得class文件编译出来同一个类
 * <p>
 * 原因：
 * Java 虚拟机不仅要看类的全名是否相同，还要看加载此类的类加载器是否一样。
 * 只有两者都相同的情况，才认为两个类是相同的。
 * 即便是同样的字节代码，被不同的类加载器加载之后所得到的类，也是不同的。
 * <p>
 * 例如：有一个class文件Hello.class
 * 使用两个不同的类加载器ClassLoader1和ClassLoader2同时加载同一个Hello.class文件，并创建实例最后得出这两个实例不是同一个类。
 *
 * @author cooze
 * @version 1.0.0
 * @desc
 * @date 2017/6/26
 */
public class OverrideClassLoader extends URLClassLoader {

    private Map<String, byte[]> classBytes = new HashMap<>();

    private static OverrideClassLoader memoryClassLoader;

    private OverrideClassLoader(Map<String, byte[]> classBytes) {
        super(new URL[0], OverrideClassLoader.class.getClassLoader());
        this.classBytes.putAll(classBytes);
    }

    public static synchronized OverrideClassLoader load(Map<String, byte[]> classBytes) {
        if (memoryClassLoader == null) {
            memoryClassLoader = new OverrideClassLoader(classBytes);
        }
        return memoryClassLoader;
    }
    public static OverrideClassLoader getClassLoader(){
        return memoryClassLoader;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] buf = classBytes.get(name);
        if (buf == null) {
            return super.findClass(name);
        }
        classBytes.remove(name);
        //查看jvm是否已经加载过此类，如果已经加载则直接返回
        Class<?> clazz = findLoadedClass(name);
        if (clazz != null) {
            return clazz;
        }
        //根据"包名.类名"，以及class文件的字节流数组获取类
        return defineClass(name, buf, 0, buf.length);
    }
}
