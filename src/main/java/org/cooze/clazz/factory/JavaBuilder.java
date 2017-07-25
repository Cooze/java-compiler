package org.cooze.clazz.factory;


import freemarker.template.TemplateException;
import org.cooze.clazz.charset.Charset;
import org.cooze.clazz.compiler.JCompiler;
import org.cooze.clazz.mapping.JavaXML;
import org.cooze.clazz.util.TemplateUtil;
import org.cooze.clazz.util.XMLBeanUtil;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author cooze
 * @version 1.0.0
 * @desc
 * @date 2017/6/29
 */
public class JavaBuilder {

    private String javaTemplateDir;
    private String javaTemplateName;

    private List<ClassNameXMLPath> classNameXMLPathList;

    private static JavaBuilder javaBuilder;

    private static JCompiler jCompiler = new JCompiler();

    private Map<String, byte[]> CACHE_CLASS = new HashMap<>();

    private JavaBuilder() {
    }

    private JavaBuilder(String javaTemplateDir, String javaTemplateName) {
        this.javaTemplateDir = javaTemplateDir;
        this.javaTemplateName = javaTemplateName;
    }


    public static JavaBuilder builder(String javaTemplatePath, String javaTemplateName) {
        if (javaBuilder == null) {
            javaBuilder = new JavaBuilder(javaTemplatePath, javaTemplateName);
        }
        return javaBuilder;
    }

    public JavaBuilder setCompileClassesAndXmlPaths(List<ClassNameXMLPath> classNameXMLPathList) {
        this.classNameXMLPathList = classNameXMLPathList;
        return this;
    }

    /**
     * 生成java源码文本
     *
     * @return 返回类名对应的java源码字符串
     * @throws IOException
     * @throws RuntimeException
     * @throws TemplateException
     * @throws SAXException
     */
    private List<KeyValue> buildJavaSourceCode() throws IOException, RuntimeException, TemplateException, SAXException {
        List<ClassNameXMLPath> classNameXMLPaths = classNameXMLPathList;
        List<KeyValue> keyValues = new ArrayList<>();
        for (int i = 0, len = classNameXMLPaths.size(); i < len; i++) {
            ClassNameXMLPath classNameXMLPath = classNameXMLPaths.get(i);
            JavaXML javaXML = XMLBeanUtil.parsingXML(new File(classNameXMLPath.getXmlPath()));
            Map<String, Object> model = buidModel(javaXML);
            //生成java源码
            String javaSourceCode = TemplateUtil.parseTemplate(new File(this.javaTemplateDir), this.javaTemplateName, model, Charset.UTF_8);
            javaSourceCode = javaSourceCode.replaceAll("#lt", "<").replaceAll("#gt", ">");
            keyValues.add(new KeyValue(classNameXMLPath.getClassName(), javaSourceCode));
        }
        return keyValues;
    }

    /**
     * 构建编译java源码
     *
     * @return
     * @throws Exception
     */
    public JavaBuilder build() throws Exception {
        List<KeyValue> javaNameAndSourceCode = buildJavaSourceCode();

        List<String> javaNames = new ArrayList<>();
        List<String> sourceCodes = new ArrayList<>();

        for (int i = 0, len = javaNameAndSourceCode.size(); i < len; i++) {
            KeyValue keyValue = javaNameAndSourceCode.get(i);
            javaNames.add(getJavaName(keyValue.getKey()));
            sourceCodes.add(keyValue.getValue());
        }
        //编译java源码，生成class字节码
        Map<String, byte[]> map = jCompiler.compile(javaNames.toArray(new String[]{}), sourceCodes.toArray(new String[]{}));
        CACHE_CLASS.putAll(map);
        return this;
    }


    public ObjOpt load(String fullClassName) throws Exception {
        Class<?> clazz = jCompiler.loadClass(fullClassName, this.CACHE_CLASS);
        Object obj = clazz.newInstance();
        return new ObjOpt(obj, clazz, this.CACHE_CLASS, jCompiler);
    }

    /**
     * 构建freemarker模型对象
     *
     * @param javaXML
     * @return
     */
    private Map<String, Object> buidModel(JavaXML javaXML) {
        if (javaXML == null) {
            return null;
        }
        Map<String, Object> model = new HashMap<>();
        model.put("packageName", javaXML.getPackageName());
        model.put("className", javaXML.getClassName());
        model.put("modifier", javaXML.getModifier());
        model.put("superClass", javaXML.getSuperClass());
        model.put("importPackages", javaXML.getImportPackages());
        model.put("annotations", javaXML.getAnnotations());
        model.put("interfaces", javaXML.getInterfaces());
        model.put("attributes", javaXML.getAttributes());
        model.put("methods", javaXML.getMethods());
        return model;
    }

    private String getJavaName(String fullClassName) {
        return fullClassName.substring(fullClassName.lastIndexOf(".") + 1, fullClassName.length()) + ".java";
    }


}
