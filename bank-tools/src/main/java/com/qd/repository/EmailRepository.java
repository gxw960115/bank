package com.qd.repository;

import com.qd.domain.EmailConfig;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmailRepository extends JpaRepository<EmailConfig, Long> {
}
