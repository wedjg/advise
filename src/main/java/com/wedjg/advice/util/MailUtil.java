package com.wedjg.advice.util;

import org.springframework.util.ResourceUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

/**
 * 邮件工具类
 * @author wedjg
 * @date 2019-05-22
 */
public final class MailUtil {

    public static final String ROOT_PATH = "classpath:templates";
    public static final String SUFFIX_HTML = ".html";
    public static final String SLASH = "/";


    public static String getHtml(String fileName, Context context) {
        File file = null;
        String html = "";
        String filePath = new StringBuilder(ROOT_PATH).append(SLASH).append(fileName)
                .append(SUFFIX_HTML).toString();
        try {
            file = ResourceUtils.getFile(filePath);
            html = new String(Files.readAllBytes(file.toPath()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new TemplateEngine().process(html, context);
    }
}
