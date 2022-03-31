package com.qd.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class RsaProperties {

    public static String privateKey;

    @Value("${rsa.private_key}")
    public static void setPrivateKey(String privateKey) {
        RsaProperties.privateKey = privateKey;
    }
}
