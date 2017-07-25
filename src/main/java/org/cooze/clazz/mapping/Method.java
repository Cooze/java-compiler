package org.cooze.clazz.mapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cooze
 * @version 1.0.0
 * @desc
 * @date 2017/6/29
 */
public class Method {
    private String name;

    private String returnType;

    private String methodBody;

    private List<MethodAnnotation> annotations;

    private String modifier;

    private String throwsExceptions;


    private List<Parameter> methodParameters;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getMethodBody() {
        return methodBody;
    }

    public void setMethodBody(String methodBody) {
        this.methodBody = methodBody;
    }

    public List<MethodAnnotation>  getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<MethodAnnotation> annotations) {
        this.annotations = annotations;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getThrowsExceptions() {
        return throwsExceptions;
    }

    public void setThrowsExceptions(String throwsExceptions) {
        this.throwsExceptions = throwsExceptions;
    }

    public List<Parameter> getMethodParameters() {
        return methodParameters;
    }

    public void setMethodParameters(List<Parameter> methodParameters) {
        this.methodParameters = methodParameters;
    }

    public void addMethodParameter(Parameter parameter) {
        if (parameter == null) {
            return;
        }
        if (this.methodParameters == null) {
            this.methodParameters = new ArrayList<>();
        }
        this.methodParameters.add(parameter);
    }

    public void addMethodAnnotation(MethodAnnotation methodAnnotation){
        if(methodAnnotation==null){
            return;
        }
        if(this.annotations==null){
            this.annotations = new ArrayList<>();
        }
        this.annotations.add(methodAnnotation);
    }

}
