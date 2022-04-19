package com.qd.service.dto;

import com.qd.annotation.Query;
import lombok.Data;

@Data
public class PictureQueryCriteria {

    @Query(type = Query.Type.INNER_LIKE)
    private String filename;

    @Query(type = Query.Type.INNER_LIKE)
    private String username;

    @Query(type = Query.Type.BETWEEN)
    private String createTime;
}
