package com.wedjg.advice.service.impl;

import com.wedjg.advice.dto.MailDto;
import com.wedjg.advice.service.MailService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceImplTest {

    @Resource
    private MailService mailService;

    private MailDto mail;

    @Before
    public void setUp() throws Exception {
        mail = new MailDto();
        mail.setTo("125490772@qq.com");
        mail.setSubject("advise的邮件");
        mail.setContent(LocalDateTime.now().toString());
    }

    @Test
    public void sendSimpleMail() {
        mailService.sendHtmlMail(this.mail);
    }
}