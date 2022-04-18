package com.qd.repository;

import com.qd.domain.AlipayConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AliPayRepository extends JpaRepository<AlipayConfig, Long> {
}
