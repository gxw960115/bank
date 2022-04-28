package com.qd.modules.mnt.repository;

import com.qd.modules.mnt.domain.DeployHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DeployHistoryRepository extends JpaRepository<DeployHistory, String>, JpaSpecificationExecutor<DeployHistory> {
}
