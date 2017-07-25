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
    public  void javaXML() throws IOException, SAXException {
      File file =  new File("/Volumes/NETAC/soft/ideaWorkSpace/project/spring-cloud/clazz-generator/java-compiler/src/main/resources/ExampleTemplate.xml");

       JavaXML javaXML = XMLBeanUtil.parsingXML(file);

    }


    @Test
    public void testfield(){
//        System.out.println("\n\n\n\n");
//        for (int i=0; i<32;i++ ){
//            System.out.print(256+".");
//        }
//        System.out.println("\n\n\n\n");
//256.256.256.256.256.256.256.256.256.256.256.256.256.256.256.256.256.256.256.256.256.256.256.256.256.256.256.256.256.256.256.256
        System.out.println(Integer.parseInt("100",16));//256

//        System.out.println();

    }




}
