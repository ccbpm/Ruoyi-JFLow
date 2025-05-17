package bp.tools;

import bp.da.DataSet;
import bp.da.DataTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AsyncTaskHelper {
    private static final Logger logger = LoggerFactory.getLogger(AsyncTaskHelper.class);
    @FunctionalInterface
    public interface ThrowingSupplier<T> {
        T get() throws Exception;
    }

    public static boolean isHasTimeoutOrCancellation(DataSet myds, List<Future<DataTable>> futures, boolean hasTimeoutOrCancellation, Logger logger) throws InterruptedException {
        for (Future<DataTable> f : futures) {
            if (f.isCancelled()) {
                hasTimeoutOrCancellation = true;
                logger.warn("一个数据加载任务因超时被取消。");
                continue;
            }
            try {
                DataTable table = f.get();
                if (table != null) {
                    myds.Tables.add(table);
                }
            } catch (ExecutionException ee) {
                Throwable cause = ee.getCause() != null ? ee.getCause() : ee;
                logger.error("一个数据加载任务执行失败: {}", cause.getMessage(), cause);
            } catch (CancellationException ce) {
                hasTimeoutOrCancellation = true;
                logger.warn("一个数据加载任务被取消 (CancellationException)。");
            }
        }
        return hasTimeoutOrCancellation;
    }

    public static Callable<DataTable> createTrackedTask(
            final ThrowingSupplier<DataTable> supplier,
            final String taskName) {
        return () -> {
            long start = System.currentTimeMillis();
            String currentThreadName = Thread.currentThread().getName();
            DataTable table = null;
            try {
                table = supplier.get();
                if (table == null) {
                    logger.warn("警告: 任务 [{}] 返回了 null DataTable", taskName);
                } else if (table.TableName == null) {
                    logger.warn("警告: 任务 [{}] 返回的 DataTable 没有设置 TableName", taskName);
                }
                return table;
            } catch (Exception e) {
                logger.error("任务 [{}] 执行时发生异常", taskName, e);
                throw e;
            } finally {
                long duration = System.currentTimeMillis() - start;
                logger.info("任务 [{}] 在线程 [{}] 执行完毕, 耗时: {} ms", taskName, currentThreadName, duration);
            }
        };
    }
}
