package com.qd.repository;


import com.qd.domain.QiniuConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface QiNiuConfigRepository extends JpaRepository<QiniuConfig,Long> {

    /**
     * 编辑类型
     */
    @Modifying
    @Query(value="update QiniuConfig set type =?1")
    void update(String type);

}
