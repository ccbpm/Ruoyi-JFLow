package bp.tools;

import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.annotation.Nonnull;
import java.util.Map;

public class ContextCopyingTaskDecorator implements TaskDecorator {
    @Nonnull
    public Runnable decorate(@Nonnull Runnable runnable) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        Map<String, String> mdcContextMap = MDC.getCopyOfContextMap(); // 捕获 MDC 上下文
        return () -> {
            Map<String, String> previousMdcContextMap = null;
            try {
                RequestContextHolder.setRequestAttributes(requestAttributes);
                if (mdcContextMap != null) {
                    previousMdcContextMap = MDC.getCopyOfContextMap();
                    MDC.setContextMap(mdcContextMap);
                }
                runnable.run();
            } finally {
                if (previousMdcContextMap != null) {
                    MDC.setContextMap(previousMdcContextMap); // 恢复旧的 MDC
                } else {
                    MDC.clear();
                }
                RequestContextHolder.resetRequestAttributes();
            }
        };
    }
}
