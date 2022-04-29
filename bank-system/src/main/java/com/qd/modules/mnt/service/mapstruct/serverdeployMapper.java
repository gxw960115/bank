package com.qd.modules.mnt.service.mapstruct;

import com.qd.base.BaseMapper;
import com.qd.modules.mnt.domain.ServerDeploy;
import com.qd.modules.mnt.service.dto.ServerDeployDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @BelongsProject: bank
 * @BelongsPackage: com.qd.modules.mnt.service.mapstruct
 * @Author: GXW
 * @CreateTime: 2022-04-29  13:57
 * @Description: TODO
 * @Version: 1.0
 */
@Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface serverdeployMapper extends BaseMapper<ServerDeployDto, ServerDeploy> {
}
