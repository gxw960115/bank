package com.qd.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditorAware")
public class AuditorConfig implements AuditorAware<String> {
    /**
     * 返回操作员标志信息
     * @return
     */
    @Override
    public Optional<String> getCurrentAuditor() {
        try {
            // 这里是根据实际业务情况来获取具体信息
            return Optional.of(SecurityUtils.getCurrentUsername());
        } catch (Exception ignored) {
        }
        // 用户定时任务，或者无Token调用情况
        return Optional.of("System");
    }
}
