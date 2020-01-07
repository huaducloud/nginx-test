package com.huaducloud.nginxtest.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;
import java.util.Optional;

/**
 * @author huanglishun
 * @date 2020-01-07 9:32
 */
@Component
public class SpringSecurityAuditorAware implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        //如果为空设置为系统创建
        return Optional.of(getCurrentUserName().orElse("system"));
    }

    /**
     * 取出当前登录用户名
     * @return 用户名
     */
    public static Optional<String> getCurrentUserName() {
        return Optional.ofNullable("huadu");
    }

}
