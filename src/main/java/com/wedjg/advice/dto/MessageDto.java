package com.wedjg.advice.dto;

import lombok.Data;

/**
 * 消息实体
 * @author wedjg
 * @date 2019-05-21
 */
@Data
public class MessageDto {

    /**
     * 消息接收方
     */
    private String to;

    /**
     * 消息主题
     */
    private String subject;

    /**
     * 消息正文
     */
    private String content;
}
