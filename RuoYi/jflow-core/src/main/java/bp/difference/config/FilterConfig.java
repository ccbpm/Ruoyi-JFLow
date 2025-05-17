package bp.difference.config;

import bp.difference.context.CookieBackupFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

/**
 * 注册过滤器的配置类
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<CookieBackupFilter> registryCookieBackupFilter() {
        FilterRegistrationBean<CookieBackupFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CookieBackupFilter());
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);  // 确保最先执行
        return registrationBean;
    }
}
