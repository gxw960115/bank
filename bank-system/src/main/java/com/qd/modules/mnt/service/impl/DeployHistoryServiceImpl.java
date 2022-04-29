package com.qd.modules.mnt.service.impl;

import cn.hutool.core.util.IdUtil;
import com.qd.modules.mnt.domain.DeployHistory;
import com.qd.modules.mnt.repository.DeployHistoryRepository;
import com.qd.modules.mnt.service.DeployHistoryService;
import com.qd.modules.mnt.service.dto.DeployHistoryDto;
import com.qd.modules.mnt.service.dto.DeployHistoryQueryCriteria;
import com.qd.modules.mnt.service.mapstruct.DeployHistoryMapper;
import com.qd.utils.FileUtil;
import com.qd.utils.PageUtil;
import com.qd.utils.QueryHelp;
import com.qd.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @BelongsProject: bank
 * @BelongsPackage: com.qd.modules.mnt.service.impl
 * @Author: GXW
 * @CreateTime: 2022-04-29  16:15
 * @Description: TODO
 * @Version: 1.0
 */
@Service
@RequiredArgsConstructor
public class DeployHistoryServiceImpl implements DeployHistoryService {

    private final DeployHistoryRepository deployHistoryRepository;
    private final DeployHistoryMapper deployHistoryMapper;

    @Override
    public Object queryAll(DeployHistoryQueryCriteria criteria, Pageable pageable) {
        Page<DeployHistory> page = deployHistoryRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(deployHistoryMapper::toDto));
    }

    @Override
    public List<DeployHistoryDto> queryAll(DeployHistoryQueryCriteria criteria) {
        return deployHistoryMapper.toDto(deployHistoryRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder)));
    }

    @Override
    public DeployHistoryDto findById(String id) {
        DeployHistory deployHistory = deployHistoryRepository.findById(id).orElseGet(DeployHistory::new);
        ValidationUtil.isNull(deployHistory.getId(), "DeployHistory", "id", id);
        return deployHistoryMapper.toDto(deployHistory);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(DeployHistory resources) {
        resources.setId(IdUtil.simpleUUID());
        deployHistoryRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Set<String> ids) {
        for (String id : ids) {
            deployHistoryRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<DeployHistoryDto> queryAll, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (DeployHistoryDto deployHistoryDto : queryAll) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("部署编号", deployHistoryDto.getDeployId());
            map.put("应用名称", deployHistoryDto.getAppName());
            map.put("部署IP", deployHistoryDto.getIp());
            map.put("部署时间", deployHistoryDto.getDeployDate());
            map.put("部署人员", deployHistoryDto.getDeployUser());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
