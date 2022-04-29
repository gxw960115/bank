package com.qd.modules.mnt.service.mapstruct;

import com.alipay.api.domain.DepartmentDTO;
import com.qd.base.BaseMapper;
import com.qd.modules.mnt.domain.DeployHistory;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @BelongsProject: bank
 * @BelongsPackage: com.qd.modules.mnt.service.mapstruct
 * @Author: GXW
 * @CreateTime: 2022-04-29  13:53
 * @Description: TODO
 * @Version: 1.0
 */
@Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DeployHistoryMapper extends BaseMapper<DepartmentDTO, DeployHistory> {
}
