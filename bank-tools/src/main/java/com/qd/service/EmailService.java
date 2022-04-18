package com.qd.service;

import com.qd.domain.EmailConfig;
import com.qd.domain.vo.EmailVo;

public interface EmailService {

    /**
     * 更新邮件配置
     *
     * @param emailConfig 邮箱配置
     * @param old         /
     * @return /
     * @throws Exception /
     */
    EmailConfig config(EmailConfig emailConfig, EmailConfig old) throws Exception;

    /**
     * 查询配置
     *
     * @return EmailConfig 邮件配置
     */
    EmailConfig find();

    /**
     * 发送邮件
     *
     * @param emailVo     邮件发送内容
     * @param emailConfig 邮件配置
     */
    void send(EmailVo emailVo, EmailConfig emailConfig);
}
