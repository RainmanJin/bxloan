package com.coamctech.bxloan.commons.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreemarkerUtils {
	private static Configuration configuration = null;
	private final static String TEMPLATE_ROOT_PATH="D:/temp/";
	private final static String ENCODING="UTF-8";
	static{
		configuration = new Configuration();
		configuration.setDefaultEncoding(ENCODING);
		File dir=new File(TEMPLATE_ROOT_PATH);
		if(!FileUtils.isExists(dir)){
			try {
				FileUtils.forceMkdir(dir);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * @param templateName
	 * @param outFileName
	 * @param dataMap
	 */
	public static void createDoc(String templateName,String outFileName,Object dataMap){
		configuration.setClassForTemplateLoading(FreemarkerUtils.class, "/template");
		Template t=null;
		try {
			//test.ftl为要装载的模板
			t = configuration.getTemplate(templateName,ENCODING);
		} catch (IOException e) {
			e.printStackTrace();
		}
		File outFile = new File(TEMPLATE_ROOT_PATH+outFileName);
		//输出文档路径及名称
		Writer out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		 
        try {
			t.process(dataMap, out);
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(out!=null)
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * @param templateName
	 * @param outputStream
	 * @param dataMap
	 */
	public static void createDoc(String templateName,OutputStream outputStream,Object dataMap){
		configuration.setClassForTemplateLoading(FreemarkerUtils.class, "/template");
		Template t=null;
		try {
			//test.ftl为要装载的模板
			t = configuration.getTemplate(templateName,ENCODING);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//输出文档路径及名称
		Writer out = null;
        try {
        	out = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
			t.process(dataMap, out);
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(out!=null)
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
