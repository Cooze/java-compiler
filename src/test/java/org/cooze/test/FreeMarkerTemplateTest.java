package org.cooze.test;

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
 * @date 2017/6/27
 */
public class FreeMarkerTemplateTest {

    @Test
    public void testSubStr() {
        String str = "org.cooze.clazz.Hello";
        System.out.println(str.substring(str.lastIndexOf(".") + 1, str.length()) + ".java");
    }

    @Test
    public void testJava001() throws Exception {
        String baseDir =( System.getProperty("user.dir")+"/src/main/resources");

        List<ClassNameXMLPath> classNameXMLPaths = new ArrayList<>();
        classNameXMLPaths.add(new ClassNameXMLPath("org.cooze.clazz.Hello",baseDir+"/Hello.xml"));
        JavaBuilder javaBuilder = JavaBuilder.builder(baseDir, "JavaTemplate.ftl")
                .setCompileClassesAndXmlPaths(classNameXMLPaths).build();
        ObjOpt objOpt = javaBuilder.load("org.cooze.clazz.Hello");

        objOpt.runMethod("setMessage", new String[]{"张三"}, new String[]{"java.lang.String"});
        objOpt.runMethod("sayHello");

    }

}
