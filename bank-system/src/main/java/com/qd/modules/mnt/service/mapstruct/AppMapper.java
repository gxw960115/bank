package com.qd.modules.mnt.service.mapstruct;

import com.qd.base.BaseMapper;
import com.qd.modules.mnt.domain.App;
import com.qd.modules.mnt.service.dto.AppDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @BelongsProject: bank
 * @BelongsPackage: com.qd.modules.mnt.service.mapstruct
 * @Author: GXW
 * @CreateTime: 2022-04-29  13:49
 * @Description: TODO
 * @Version: 1.0
 */
@Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AppMapper extends BaseMapper<AppDto, App> {
}
