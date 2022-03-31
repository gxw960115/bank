package com.qd.config;

import lombok.Data;
import lombok.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class ElAdminProperties {

    public static Boolean ipLocal;

    @Value("${ip.local-parsing}")
    public void setIpLocal(Boolean ipLocal){
        ElAdminProperties.ipLocal = ipLocal;
    }
}
