package com.qd.modules.mnt.service.impl;

import com.qd.exception.BadRequestException;
import com.qd.modules.mnt.domain.App;
import com.qd.modules.mnt.repository.AppRepository;
import com.qd.modules.mnt.service.AppService;
import com.qd.modules.mnt.service.dto.AppDto;
import com.qd.modules.mnt.service.dto.AppQueryCriteria;
import com.qd.modules.mnt.service.mapstruct.AppMapper;
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
 * @CreateTime: 2022-04-29  14:01
 * @Description: TODO
 * @Version: 1.0
 */
@Service
@RequiredArgsConstructor
public class AppServiceImpl implements AppService {
    private final AppRepository appRepository;
    private final AppMapper appMapper;

    @Override
    public Object queryAll(AppQueryCriteria criteria, Pageable pageable) {
        Page<App> page = appRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(appMapper::toDto));
    }

    @Override
    public List<AppDto> queryAll(AppQueryCriteria criteria) {
        return appMapper.toDto(appRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder)));
    }

    @Override
    public AppDto findById(Long id) {
        App app = appRepository.findById(id).orElseGet(App::new);
        ValidationUtil.isNull(app.getId(), "app", "id", id);
        return appMapper.toDto(app);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(App resources) {
        verification(resources);
        appRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(App resources) {
        verification(resources);
        App app = appRepository.findById(resources.getId()).orElseGet(App::new);
        ValidationUtil.isNull(app.getId(), "App", "id", resources.getId());
        app.copy(resources);
        appRepository.save(app);
    }

    private void verification(App resources) {
        String opt = "/opt";
        String home = "/home";
        if (!(resources.getUploadPath().startsWith(opt) || resources.getUploadPath().startsWith(home))) {
            throw new BadRequestException("?????????????????????opt????????????home??????");
        }
        if (!(resources.getDeployPath().startsWith(opt) || resources.getDeployPath().startsWith(home))) {
            throw new BadRequestException("?????????????????????opt????????????home?????? ");
        }
        if (!(resources.getBackupPath().startsWith(opt) || resources.getBackupPath().startsWith(home))) {
            throw new BadRequestException("?????????????????????opt????????????home?????? ");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Set<Long> ids) {
        for (Long id : ids) {
            appRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<AppDto> queryAll, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (AppDto appDto : queryAll) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("????????????", appDto.getName());
            map.put("??????", appDto.getPort());
            map.put("????????????", appDto.getUploadPath());
            map.put("????????????", appDto.getDeployPath());
            map.put("????????????", appDto.getBackupPath());
            map.put("????????????", appDto.getStartScript());
            map.put("????????????", appDto.getDeployScript());
            map.put("????????????", appDto.getCreateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
