package com.wedjg.advice.service;

import com.wedjg.advice.dto.MailDto;
import org.springframework.stereotype.Service;

/**
 * 邮件消息服务
 * @author wedjg
 * @date 2019-05-21
 */
@Service
public interface MailService{

     /**
      * 发送简单邮件
      * @param mail 邮件实体
      */
     void sendSimpleMail(MailDto mail);

     /**
      * 发送带有html的邮件
      * @param mail 邮件实体
      */
     void sendHtmlMail(MailDto mail);

     /**
      * 发送带有附件的邮件
      * @param mail 邮件实体
      */
     void sendAttachmentsMail(MailDto mail);

     /**
      * 发送带有静态资源的邮件
      * @param mail 邮件实体
      */
     void sendInlineResourceMail(MailDto mail);
}