package com.qd.modules.mnt.service.mapstruct;

import com.qd.base.BaseMapper;
import com.qd.modules.mnt.domain.Database;
import com.qd.modules.mnt.service.dto.DatabaseDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @BelongsProject: bank
 * @BelongsPackage: com.qd.modules.mnt.service.mapstruct
 * @Author: GXW
 * @CreateTime: 2022-04-29  13:51
 * @Description: TODO
 * @Version: 1.0
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DatabaseMapper extends BaseMapper<DatabaseDto, Database> {
}
