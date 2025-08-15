package com.skyco2;

import freemarker.template.*;
import java.io.*;
import java.util.*;

public class DocGenerator {

    public static void main(String[] args) throws Exception {
        // 1. 配置 FreeMarker
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_32);
        cfg.setClassForTemplateLoading(DocGenerator.class, "/templates");
        cfg.setDefaultEncoding("UTF-8");

        // 2. 准备数据模型
        Map<String, Object> data = new HashMap<>();
        data.put("title", "项目报告");
        data.put("date", "2023-10-01");
        data.put("items", Arrays.asList("需求分析", "系统设计", "代码实现"));

        // 3. 加载模板并生成内容
        Template template = cfg.getTemplate("report.ftl");
        StringWriter writer = new StringWriter();
        template.process(data, writer);
        String xmlContent = writer.toString();

        // 4. 输出为 doc 文件
        try (FileOutputStream fos = new FileOutputStream("output.doc")) {
            fos.write(xmlContent.getBytes("UTF-8"));
        }
        System.out.println("doc 文档生成成功!");

        // 4. 输出为 docx 文件
        try (FileOutputStream fos = new FileOutputStream("output.docx")) {
            fos.write(xmlContent.getBytes("UTF-8"));
        }
        System.out.println("docx 文档生成成功!");
    }
}
