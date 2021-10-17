package lq.yiqian.utils.threadPool;

import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 通用线程池
 *
 * @author LQ
 * @create 2021-10-17 19:04
 */
@Component
public class ThreadPoolUtils {

    /**
     * 核心线程数
     */
    private static final int CORE_POOL_SIZE = 10;
    /**
     * 最大线程数
     */
    private static final int MAX_POOL_SIZE = 20;

    /**
     * 队列大小
     */
    private static final int BLOCK_QUEUE_SIZE = 1024;

    /**
     * 将线程池定义到内部类中, 只有用到线程池时线程池才会被创建
     */
    private static class ThreadPoolHolder {

        /**
         * 公共线程池
         */
        private final static ExecutorService COMMON_EXECUTOR_SERVICE = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                5,
                TimeUnit.MINUTES,
                new LinkedBlockingQueue<>(BLOCK_QUEUE_SIZE),
                new CommonThreadFactory("mac-common-thread-pool"),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );

        public static ExecutorService getCommonExecutorService() {
            return COMMON_EXECUTOR_SERVICE;
        }
    }

    public static void execute(Runnable runnable) {
        ThreadPoolHolder.getCommonExecutorService().execute(runnable);
    }

    public static Future<?> submit(Runnable task) {
        return ThreadPoolHolder.getCommonExecutorService().submit(task);
    }

    public static <T> Future<T> submit(Callable<T> task) {
        return ThreadPoolHolder.getCommonExecutorService().submit(task);
    }

    /**
     * 获取通用的线程池
     *
     * @return ExecutorService
     */
    public static ExecutorService getCommonExecutorService() {
        return ThreadPoolHolder.getCommonExecutorService();
    }

    /**
     * 是否结束
     *
     * @return boolean
     */
    public static boolean isShutDown() {
        return ThreadPoolHolder.getCommonExecutorService().isShutdown();
    }

    @PreDestroy
    public void destroy() {
        getCommonExecutorService().shutdown();
    }
}
