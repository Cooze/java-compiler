package org.cooze.clazz.compiler;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

/**
 * @author cooze
 * @version 1.0.0
 * @desc
 * @date 2017/6/26
 */
public class JCompiler {
    JavaCompiler compiler;
    StandardJavaFileManager stdManager;

    public JCompiler() {
        this.compiler = ToolProvider.getSystemJavaCompiler();
        this.stdManager = compiler.getStandardFileManager(null, null, null);
    }

    /**
     * 根据java源码字符串，编译java源码生成clazz字节流。
     *
     * @param javaFileName      java文件名,例如："Test.java"
     * @param javaSourceCodeStr java源码字符串
     * @return 编译的结果以key-value的形式返回，key是编译出的类名，如：org.cooze.bean.Person
     * value是class的二进制流。
     * @throws IOException 编译错误异常
     */
    public Map<String, byte[]> compile(String javaFileName, String javaSourceCodeStr) throws IOException {
        return compile(new String[]{javaFileName}, javaSourceCodeStr);
    }

    /**
     * 根据java源码字符串，编译java源码生成clazz字节流,传入的java文件名要和java源码需要对应上
     *
     * @param javaFileNames      java文件名数组
     * @param javaSourceCodeStrs java源码数组
     * @return 编译的结果以key-value的形式返回，key是编译出的类名，如：org.cooze.bean.Person
     * value是class的二进制流。
     * @throws IOException 编译错误异常
     */
    public Map<String, byte[]> compile(String[] javaFileNames, String... javaSourceCodeStrs) throws IOException {
        try (OverrideJavaFileManager manager = new OverrideJavaFileManager(stdManager)) {
            List<JavaFileObject> javaFileObjectList = new ArrayList<>();
            for (int i = 0, len = javaFileNames.length; i < len; i++) {
                javaFileObjectList.add(manager.makeStringSource(javaFileNames[i], javaSourceCodeStrs[i]));
            }
            JavaCompiler.CompilationTask task = compiler.getTask(null, manager, null, null, null, javaFileObjectList);
            Boolean result = task.call();
            if (result == null || !result.booleanValue()) {
                throw new RuntimeException("Compilation failed.");
            }
            return manager.getClassBytes();
        }
    }

    /**
     * 编译java源码
     *
     * @param javaFileName   java文件名
     * @param javaSourcePath java源码文件路径
     * @return 编译的结果以key-value的形式返回，key是编译出的类名，如：org.cooze.bean.Person
     * value是class的二进制流。
     * @throws IOException 编译错误异常
     */
    public Map<String, byte[]> compile(String javaFileName, File javaSourcePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(javaSourcePath)))) {
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            return compile(javaFileName, sb.toString());
        }
    }


    /**
     * Load class from compiled classes.
     * 从编译的class文件中获取指定的类
     *
     * @param name       全类名，org.cooze.Person
     * @param classBytes 编译的Map集合，以类名为key，类名的字节码为值
     * @return 返回类的示例Class
     * @throws ClassNotFoundException 没有找到类抛出异常
     * @throws IOException            加载异常.
     */
    public Class<?> loadClass(String name, Map<String, byte[]> classBytes) throws ClassNotFoundException, IOException {
       return OverrideClassLoader.load(classBytes).loadClass(name);
    }
}

