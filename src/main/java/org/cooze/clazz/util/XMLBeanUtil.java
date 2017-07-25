package org.cooze.clazz.util;

import org.apache.commons.digester3.Digester;
import org.cooze.clazz.mapping.*;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
/**
 * @author cooze
 * @version 1.0.0
 * @desc
 * @date 2017/6/29
 */
public final class XMLBeanUtil {


    /**
     * XML模板解析方法
     * @param xmlFile xml文件位置
     * @return 返回XML方法类
     */
    public static JavaXML parsingXML(File xmlFile) throws IOException, SAXException {

        Digester digester = new Digester();
        digester.setValidating(false);
        digester.addObjectCreate("JavaXML", JavaXML.class);
        digester.addBeanPropertySetter("JavaXML/className", "className");
        digester.addBeanPropertySetter("JavaXML/packageName", "packageName");
        digester.addBeanPropertySetter("JavaXML/modifier", "modifier");
        digester.addBeanPropertySetter("JavaXML/extends", "superClass");

        digester.addObjectCreate("JavaXML/annotations/annotation", Annotation.class);
        digester.addBeanPropertySetter("JavaXML/annotations/annotation", "annotation");

        digester.addObjectCreate("JavaXML/importPackages/packageName", ImportPackages.class);
        digester.addBeanPropertySetter("JavaXML/importPackages/packageName", "packageName");



        digester.addObjectCreate("JavaXML/implements/interface", Interface.class);
        digester.addBeanPropertySetter("JavaXML/implements/interface", "interfaces");

        digester.addObjectCreate("JavaXML/attributes/attribute", Attribute.class);
        digester.addSetProperties("JavaXML/attributes/attribute");
        digester.addBeanPropertySetter("JavaXML/attributes/attribute/name", "name");
        digester.addBeanPropertySetter("JavaXML/attributes/attribute/type", "type");
        digester.addBeanPropertySetter("JavaXML/attributes/attribute/modifier", "modifier");

        digester.addObjectCreate("JavaXML/attributes/attribute/annotations/annotation", AttrAnnotation.class);
        digester.addBeanPropertySetter("JavaXML/attributes/attribute/annotations/annotation", "annotation");

        digester.addObjectCreate("JavaXML/methods/method", Method.class);
        digester.addBeanPropertySetter("JavaXML/methods/method/name", "name");
        digester.addBeanPropertySetter("JavaXML/methods/method/returnType", "returnType");
        digester.addBeanPropertySetter("JavaXML/methods/method/methodBody", "methodBody");
        digester.addBeanPropertySetter("JavaXML/methods/method/modifier", "modifier");
        digester.addBeanPropertySetter("JavaXML/methods/method/throwsExceptions", "throwsExceptions");

        digester.addObjectCreate("JavaXML/methods/method/annotations/annotation", MethodAnnotation.class);
        digester.addBeanPropertySetter("JavaXML/methods/method/annotations/annotation", "annotation");


        digester.addObjectCreate("JavaXML/methods/method/methodParameters/parameter", Parameter.class);
        digester.addBeanPropertySetter("JavaXML/methods/method/methodParameters/parameter/type", "type");
        digester.addBeanPropertySetter("JavaXML/methods/method/methodParameters/parameter/name", "name");
        digester.addBeanPropertySetter("JavaXML/methods/method/methodParameters/parameter/modifier", "modifier");

        digester.addSetNext("JavaXML/importPackages/packageName", "addImportPackage");
        digester.addSetNext("JavaXML/implements/interface", "addInterface");
        digester.addSetNext("JavaXML/annotations/annotation", "addAnnotation");
        digester.addSetNext("JavaXML/attributes/attribute", "addAttribute");
        digester.addSetNext("JavaXML/methods/method", "addMethod");
        digester.addSetNext("JavaXML/methods/method/methodParameters/parameter", "addMethodParameter");
        digester.addSetNext("JavaXML/attributes/attribute/annotations/annotation", "addAnnotation");
        digester.addSetNext("JavaXML/methods/method/annotations/annotation", "addMethodAnnotation");

        Object obj = digester.parse(xmlFile);
        if (obj != null && obj instanceof JavaXML) {
            return (JavaXML)obj;
        }
        return null;
    }


}
