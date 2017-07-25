package org.cooze.test;

import org.cooze.clazz.mapping.*;
import org.cooze.clazz.util.XMLBeanUtil;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

/**
 * @author cooze
 * @version 1.0.0
 * @desc
 * @date 2017/6/29
 */
public class TestMain {


    @Test
    public void javaXML() throws IOException, SAXException {
        File file = new File(System.getProperty("user.dir") + "/src/main/resources/ExampleTemplate.xml");

        JavaXML javaXML = XMLBeanUtil.parsingXML(file);

    }


}
