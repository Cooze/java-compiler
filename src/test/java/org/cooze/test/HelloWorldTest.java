package org.cooze.test;

import com.google.gson.Gson;
import org.cooze.clazz.factory.ClassNameXMLPath;
import org.cooze.clazz.factory.JavaBuilder;
import org.cooze.clazz.factory.ObjOpt;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cooze
 * @version 1.0.0
 * @desc
 * @date 2017/6/30
 */
public class HelloWorldTest {

    @Test
    public void personSaySomeThingTest() throws Exception {
        String baseDir = (System.getProperty("user.dir") + "/src/main/resources");

        List<ClassNameXMLPath> classNameXMLPaths = new ArrayList<>();
        classNameXMLPaths.add(new ClassNameXMLPath("test.Person", baseDir + "/Person.xml"));
        classNameXMLPaths.add(new ClassNameXMLPath("org.cooze.hello.HelloWorld", baseDir + "/HelloWorld.xml"));
        classNameXMLPaths.add(new ClassNameXMLPath("org.cooze.main.MyMain", baseDir + "/main.xml"));

        //编译
        JavaBuilder javaBuilder = JavaBuilder.builder(baseDir, "JavaTemplate.ftl")
                .setCompileClassesAndXmlPaths(classNameXMLPaths)
                .build();

        ObjOpt person = javaBuilder.load("test.Person");
        ObjOpt helloworld = javaBuilder.load("org.cooze.hello.HelloWorld");
        ObjOpt main = javaBuilder.load("org.cooze.main.MyMain");

        Object zhangsan = person.getObj();

        person.runMethod("setName", new Object[]{"张三"}, "java.lang.String");
        person.runMethod("setMessage", new Object[]{"你好！"}, "java.lang.String");

        System.out.println(new Gson().toJson(zhangsan));

        //使用动态编译的类作为参数
        helloworld.runMethod("sayHello", new Object[]{zhangsan}, "test.Person");
        helloworld.runMethod("sayHello", new Object[]{zhangsan}, "test.Person");

        //测试执行带有main函数的类
        main.runMethod("main",new Object[]{new String[]{}},String[].class);

//        //报错，
//        Class<?> cl = Class.forName("org.cooze.hello.HelloWorld");

    }
}
