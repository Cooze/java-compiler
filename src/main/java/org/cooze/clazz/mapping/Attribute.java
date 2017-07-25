package org.cooze.clazz.mapping;


import java.util.ArrayList;
import java.util.List;

/**
 * @author cooze
 * @version 1.0.0
 * @desc
 * @date 2017/6/29
 */
public class Attribute {
    private String type;
    private String gs;

    private String name;

    private List <AttrAnnotation> annotations;

    private String modifier;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGs() {
        return gs;
    }

    public void setGs(String gs) {
        this.gs = gs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public List<AttrAnnotation> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<AttrAnnotation> annotations) {
        this.annotations = annotations;
    }

    public void addAnnotation(AttrAnnotation annotation){
        if(annotation==null){
            return ;
        }

        if(this.annotations==null){
            this.annotations = new ArrayList<>();
        }

        this.annotations.add(annotation);

    }


}
