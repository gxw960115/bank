package com.qd.service.dto;

import com.qd.base.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class LocalStorageDto extends BaseDTO implements Serializable {
    private Long id;

    private String realName;

    private String name;

    private String suffix;

    private String type;

    private String size;
}
