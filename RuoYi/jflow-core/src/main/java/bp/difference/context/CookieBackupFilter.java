package bp.difference.context;

import bp.difference.ContextHolderUtils;
import org.springframework.stereotype.Component;
import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class CookieBackupFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            // 备份cookies到ThreadLocal
            if (request instanceof HttpServletRequest) {
                HttpServletRequest httpRequest = (HttpServletRequest) request;
                Cookie[] cookies = httpRequest.getCookies();
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if (cookie != null && cookie.getName() != null) {
                            // 将cookie保存到ThreadLocal
                            ContextHolderUtils.setThreadLocalCookie(cookie.getName(), cookie.getValue());
                        }
                    }
                }
            }
            // 继续处理请求
            chain.doFilter(request, response);
        } finally {
            // 清理ThreadLocal资源，防止内存泄漏
            ContextHolderUtils.clearThreadLocalCookies();
        }
    }
}
