package com.qd.utils;

import cn.hutool.core.util.ObjectUtil;
import com.qd.exception.BadRequestException;
import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;

/**
 * 验证工具
 */
public class ValidationUtil {

    /**
     * 验证空
     *
     * @param obj
     * @param entity
     * @param parameter
     * @param value
     */
    public static void isNull(Object obj, String entity, String parameter, Object value) {
        if (ObjectUtil.isNull(obj)) {
            String msg = entity + " 不存在：" + parameter + " is " + value;
            throw new BadRequestException(msg);
        }
    }

    /**
     * 验证是否为邮箱
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        return new EmailValidator().isValid(email, null);
    }
}
