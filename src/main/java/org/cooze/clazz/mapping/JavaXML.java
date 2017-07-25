package org.cooze.clazz.mapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cooze
 * @version 1.0.0
 * @desc
 * @date 2017/6/29
 */
public class JavaXML {
    private String packageName;

    private List<ImportPackages> importPackages;

    private String className;

    private List<Annotation> annotations;

    private String modifier;

    private String superClass;

    private List<Interface> interfaces;

    private List<Attribute> attributes;

    private List<Method> methods;

    public String getSuperClass() {
        return superClass;
    }

    public void setSuperClass(String superClass) {
        this.superClass = superClass;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<Annotation> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<Annotation> annotations) {
        this.annotations = annotations;
    }

    public void addAnnotation(Annotation annotation) {
        if (annotation == null) {
            return;
        }
        if (this.annotations == null) {
            this.annotations = new ArrayList<>();
        }
        this.annotations.add(annotation);
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public List<ImportPackages> getImportPackages() {
        return importPackages;
    }

    public void setImportPackages(List<ImportPackages> importPackages) {
        this.importPackages = importPackages;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public void addImportPackage(ImportPackages importPackages) {
        if (importPackages == null) {
            return;
        }
        if (this.importPackages == null) {
            this.importPackages = new ArrayList<>();
        }
        this.importPackages.add(importPackages);

    }

    public List<Interface> getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(List<Interface> interfaces) {
        this.interfaces = interfaces;
    }

    public void addInterface(Interface inteface) {
        if (inteface == null) {
            return;
        }
        if (this.interfaces == null) {
            this.interfaces = new ArrayList<>();
        }
        this.interfaces.add(inteface);
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public void addAttribute(Attribute attribute) {
        if (attribute == null) {
            return;
        }
        if (this.attributes == null) {
            this.attributes = new ArrayList<>();
        }
        this.attributes.add(attribute);
    }


    public List<Method> getMethods() {
        return methods;
    }

    public void setMethods(List<Method> methods) {
        this.methods = methods;
    }

    public void addMethod(Method method) {
        if (method == null) {
            return;
        }
        if (this.methods == null) {
            this.methods = new ArrayList<>();
        }
        this.methods.add(method);
    }
}
