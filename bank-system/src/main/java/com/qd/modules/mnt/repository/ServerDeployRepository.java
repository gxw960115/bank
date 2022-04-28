package com.qd.modules.mnt.repository;

import com.qd.modules.mnt.domain.ServerDeploy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ServerDeployRepository extends JpaRepository<ServerDeploy,Long>, JpaSpecificationExecutor<ServerDeploy> {

    /**
     * 根据ip查询
     */

    ServerDeploy findByIp(String ip);
}
