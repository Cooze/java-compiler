package org.cooze.clazz.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.cooze.clazz.charset.Charset;

import java.io.*;
import java.util.Map;

/**
 * @author cooze
 * @version 1.0.0
 * @desc
 * @date 2017/6/27
 */
public final class TemplateUtil{

    private TemplateUtil(){}

    /**
     *
     * @param templateFileDir
     *         模板文件路径
     * @param charset
     *          模板字符集，默认时utf-8
     * @return
     * @throws IOException
     */
    private static Configuration loadConfigure(File templateFileDir, String charset) throws IOException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
        cfg.setDirectoryForTemplateLoading(templateFileDir);
        cfg.setDefaultEncoding(charset == null || "".equals(charset) ? Charset.UTF_8 : charset);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        return cfg;
    }

    /**
     * 解析FreeMarke模板，根据生成对应的java类
     * @param templateFileDir
     *          模板文件路径
     * @param templateFileName
     *          模板文件名称
     * @param model
     *          解析模板时，传入的参数模型
     * @param charset
     *          模板字符集，默认时utf-8
     * @return
     *      返回生成的java类字符串
     * @throws IOException
     *          IO异常信息
     *
     * @throws TemplateException
     *          模版异常信息
     */
    public static String parseTemplate(File templateFileDir, String templateFileName, Map<String, Object> model, String charset) throws IOException, TemplateException {
        Configuration cfg = loadConfigure(templateFileDir, charset);
        Template temp = cfg.getTemplate(templateFileName);

        StringWriter stringWriter = new StringWriter();
        BufferedWriter bw = new BufferedWriter(stringWriter);
        temp.process(model, bw);
        //生成类字符串
        String clazzStr = stringWriter.toString();
        bw.close();
        return clazzStr;
    }


    /**
     * 解析FreeMarke模板，根据生成对应的java类
     * @param templateFileDir
     *          模板文件路径
     * @param templateFileName
     *          模板文件名称
     * @param model
     *          解析模板时，传入的参数模型
     * @param outPutDir
     *          根据模板生成对应文件的输出路径
     * @param outPutFileName
     *          生成的模板的文件名
     * @param charset
     *          字符集，默认时utf-8
     * @throws IOException
     *          IO异常
     * @throws TemplateException
     *          模板异常
     */
    public static void parseTemplate(File templateFileDir, String templateFileName, Map<String, Object> model,File outPutDir,String outPutFileName, String charset) throws IOException, TemplateException {
        Configuration cfg = loadConfigure(templateFileDir, charset);
        Template temp = cfg.getTemplate(templateFileName);

        if (!outPutDir.exists()) {
            outPutDir.mkdirs();
        }

        //java文件的生成目录
        OutputStream fos = new FileOutputStream(new File(outPutDir, outPutFileName));
        Writer out = new OutputStreamWriter(fos);
        temp.process(model, out);
        fos.flush();
        fos.close();
    }

}
