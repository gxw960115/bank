package com.qd.modules.mnt.service.dto;

import com.qd.base.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
public class ServerDeployDto extends BaseDTO implements Serializable {
    private Long id;
    private String name;
    private String ip;
    private Integer port;
    private String account;
    private String password;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServerDeployDto that = (ServerDeployDto) o;
        return id.equals(that.id) && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
