import com.sun.istack.internal.NotNull;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description:
 * @Author: 张子龙
 * @date 2021/5/18 22:11
 */
public class ThreadPoolExecutorUtil {
    /**
     * 获取可使用核心线程大小
     */
    private static int CORE_SIZE = Runtime.getRuntime().availableProcessors() / 2 + 1;
    /**
     * 自定义线程池
     */
    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(CORE_SIZE, CORE_SIZE, 0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(), new MrmThreadPoolFactory());

    /**
     * 提交任务 无返回值
     * 使用get方法
     *
     * @param task 线程接口
     */
    public static void executor(Runnable task) {
        threadPoolExecutor.execute(task);
    }

    /**
     * 提交任务 有返回值
     *
     * @param callable 带参数 线程接口
     * @return 任务执行结果
     */
    public static Future<?> submit(Callable<?> callable) {
        return threadPoolExecutor.submit(callable);
    }

    /**
     * 线程工厂
     */
    static class MrmThreadPoolFactory implements ThreadFactory {
        private ThreadGroup group;
        private String namePrefix;
        private AtomicInteger nextId = new AtomicInteger();

        MrmThreadPoolFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            namePrefix = "mrm-task-";
        }

        @Override
        public Thread newThread(@NotNull Runnable r) {
            Thread t = new Thread(group, r, namePrefix + nextId.getAndIncrement());
            t.setDaemon(false);
            return t;
        }
    }
}
