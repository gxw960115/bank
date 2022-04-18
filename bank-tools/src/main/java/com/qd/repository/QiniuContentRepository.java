package com.qd.repository;

import com.qd.domain.QiniuConfig;
import com.qd.domain.QiniuContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface QiniuContentRepository extends JpaRepository<QiniuContent, Long>, JpaSpecificationExecutor<QiniuConfig> {
    /**
     * 根据key查询
     *
     * @param key 文件名
     * @return
     */
    QiniuContent findByKey(String key);
}