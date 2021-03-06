package com.qd.modules.mnt.service.dto;

import com.qd.annotation.Query;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class ServerDeployQueryCriteria {
    /**
     * 模糊
     */
    @Query(blurry = "name,ip,account")
    private String blurry;

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;
}
