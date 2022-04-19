package com.qd.service.mapstruct;

import com.qd.base.BaseMapper;
import com.qd.domain.LocalStorage;
import com.qd.service.dto.LocalStorageDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface LocalStorageMapper extends BaseMapper<LocalStorageDto, LocalStorage> {
}
