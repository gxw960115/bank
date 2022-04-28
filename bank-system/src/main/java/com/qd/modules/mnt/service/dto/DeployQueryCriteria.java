package com.qd.modules.mnt.service.dto;

import com.qd.annotation.Query;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class DeployQueryCriteria {

    /**
     * 模糊
     */
    @Query(type = Query.Type.INNER_LIKE,propName = "name",joinName = "app")
    private  String appName;

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;
}
