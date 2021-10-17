package lq.yiqian.utils.threadPool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义工厂类
 *
 * @author LQ
 * @create 2021-10-17 19:13
 */
public class CommonThreadFactory implements ThreadFactory {

    /**
     * please understanding by your Imagination
     */
    private static final AtomicInteger POOL_NUMBER = new AtomicInteger(1);

    /**
     * please understanding by your Imagination
     */
    private final AtomicInteger threadNumber = new AtomicInteger(1);


    /**
     * please understanding by your Imagination
     */
    private final String namePrefix;

    /**
     * please understanding by your Imagination
     */
    public CommonThreadFactory(String namePrefix) {
        this.namePrefix = namePrefix +"-pool-" + POOL_NUMBER.getAndIncrement() + "-thread-";
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, namePrefix + threadNumber.getAndIncrement());
        // 创建出来的线程不能是守护线程, 守护线程优先级太低, 容易在一个操作中间中断
        if (t.isDaemon()) {
            t.setDaemon(false);
        }
        // 设置线程优先级
        if (t.getPriority() != Thread.NORM_PRIORITY) {
            t.setPriority(Thread.NORM_PRIORITY);
        }
        return t;
    }
}
