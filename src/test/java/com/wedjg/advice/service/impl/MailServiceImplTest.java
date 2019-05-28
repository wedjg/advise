package com.wedjg.advice.service.impl;

import com.wedjg.advice.dto.MailDto;
import com.wedjg.advice.service.MailService;
import com.wedjg.advice.util.DateUtil;
import com.wedjg.advice.util.MailUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import java.io.File;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceImplTest {

    @Resource
    private MailService mailService;

    private MailDto mail;

    TemplateEngine templateEngine;

    @Before
    public void setUp() throws Exception {
        mail = new MailDto();
        mail.setTo("125490772@qq.com");
        mail.setSubject("advise的邮件");
        mail.setContent(DateUtil.getStandardTime());
        templateEngine = new TemplateEngine();
    }

    @Test
    public void sendSimpleMail() {
        //mailService.sendHtmlMail(this.mail);
    }


    @Test
    public void sendTemplateMail() {
        Context context = new Context();
        context.setVariable("username", "wedjg");

        mail.setContent(MailUtil.getHtml("emailTemplate", context));
        //mailService.sendHtmlMail(mail);
    }


}