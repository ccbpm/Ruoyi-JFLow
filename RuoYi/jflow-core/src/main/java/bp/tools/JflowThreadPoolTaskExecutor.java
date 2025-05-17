package bp.tools;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池任务执行器单例
 * 使用静态内部类实现单例模式
 */
public class JflowThreadPoolTaskExecutor {

    private JflowThreadPoolTaskExecutor() {
    }

    private static class SingletonHolder {
        private static final ThreadPoolTaskExecutor INSTANCE = createThreadPool();

        private static ThreadPoolTaskExecutor createThreadPool() {
            ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
            // 核心线程：核心线程一直存活
            executor.setCorePoolSize(20);
            // 队列容量：任务将队列塞满之后，扩展核心线程，线程总数最多不超过最大线程数
            executor.setQueueCapacity(200);
            // 最大线程
            executor.setMaxPoolSize(200);
            // 闲置线程存活时长
            executor.setKeepAliveSeconds(60);
            // 拒绝策略：任务超出线程池容量后，新任务交还主线程处理
            executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
            // 线程名前缀
            executor.setThreadNamePrefix("cc-task-worker-");
            // 使用TaskDecorator管理上下文
            executor.setTaskDecorator(new ContextCopyingTaskDecorator());
            // 初始化线程池
            executor.initialize();
            return executor;
        }
    }

    public static ThreadPoolTaskExecutor getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public static void shutdown() {
        ThreadPoolTaskExecutor pool = SingletonHolder.INSTANCE;
        pool.shutdown();
    }

    /**
     * 获取线程池当前使用情况
     * 用于监控和诊断
     */
    public static String getPoolStatus() {
        ThreadPoolTaskExecutor pool = SingletonHolder.INSTANCE;

        ThreadPoolExecutor executor = pool.getThreadPoolExecutor();
        return String.format(
                "线程池状态s: [Active: %d, Core: %d, Max: %d, LargestPoolSize: %d, " +
                        "TaskCount: %d, CompletedTasks: %d, QueueSize: %d]",
                executor.getActiveCount(),
                executor.getCorePoolSize(),
                executor.getMaximumPoolSize(),
                executor.getLargestPoolSize(),
                executor.getTaskCount(),
                executor.getCompletedTaskCount(),
                executor.getQueue().size()
        );
    }
}