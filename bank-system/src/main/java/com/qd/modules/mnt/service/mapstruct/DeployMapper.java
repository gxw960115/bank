package com.qd.modules.mnt.service.mapstruct;

import com.qd.base.BaseMapper;
import com.qd.modules.mnt.domain.Deploy;
import com.qd.modules.mnt.service.dto.DeployDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @BelongsProject: bank
 * @BelongsPackage: com.qd.modules.mnt.service.mapstruct
 * @Author: GXW
 * @CreateTime: 2022-04-29  13:56
 * @Description: TODO
 * @Version: 1.0
 */
@Mapper(componentModel = "spring", uses = {AppMapper.class, serverdeployMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DeployMapper extends BaseMapper<DeployDto, Deploy> {
}
