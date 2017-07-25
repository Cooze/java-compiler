package org.cooze.clazz.factory;

/**
 * @author cooze
 * @version 1.0.0
 * @desc
 * @date 2017/6/30
 */
public class ClassNameXMLPath {

    private String className;

    private String xmlPath;

    public ClassNameXMLPath() {
    }

    public ClassNameXMLPath(String className, String xmlPath) {
        this.className = className;
        this.xmlPath = xmlPath;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getXmlPath() {
        return xmlPath;
    }

    public void setXmlPath(String xmlPath) {
        this.xmlPath = xmlPath;
    }
}
