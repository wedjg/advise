package com.wedjg.advice.dto;

import lombok.Data;

/**
 * 邮件消息实体
 * @author Liao Jiajian
 * @date 2019-05-21
 */
@Data
public class MailDto extends MessageDto {

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

    @Override
    public String toString() {
        return "发往【" + getTo() + "】的邮件：" + getSubject() + "==>";
    }
}
