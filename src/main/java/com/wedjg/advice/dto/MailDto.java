package com.wedjg.advice.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * 邮件消息实体
 * 发HTML的必要参数为to,subject,htmlFileName,parameter
 * @author Liao Jiajian
 * @date 2019-05-21
 */
@Data
public class MailDto extends MessageDto{

    private static final long serialVersionUID = -2537938346097834178L;
    /**
     * 邮件发送方
     */
    private String from;

    /**
     * 附件路径
     */
    private String filePath;

    /**
     * 静态资源路径
     */
    private String rscPath;

    /**
     * 静态资源ID
     */
    private String rscId;
	
	/**
     * 邮件模板文件名
     */
    private String htmlFileName;
	
	/**
     * 邮件模板参数
     */
    private Map<String, String> parameter;
	
	
	

    @Override
    public String toString() {
        return "发往【" + getTo() + "】的邮件：" + getSubject() + "==>";
    }
}
