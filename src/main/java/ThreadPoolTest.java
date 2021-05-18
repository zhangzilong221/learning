import com.sun.corba.se.spi.orbutil.threadpool.ThreadPool;

import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolTest {
    private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
    private static final int COUNT_BITS = 8 - 3;
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;

    private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int SHUTDOWN   =  0 << COUNT_BITS;
    private static final int STOP       =  1 << COUNT_BITS;
    private static final int TIDYING    =  2 << COUNT_BITS;
    private static final int TERMINATED =  3 << COUNT_BITS;

    private static int runStateOf(int c)     { return c & ~CAPACITY; }
    private static int workerCountOf(int c)  { return c & CAPACITY; }
    private static int ctlOf(int rs, int wc) { return rs | wc; }
    public static void main(String[] args) {
        ThreadPoolTest test = new ThreadPoolTest();
        System.out.println("运行中\t"+RUNNING);
        System.out.println("关闭掉\t"+SHUTDOWN);
        System.out.println("停止吧\t"+STOP);
        System.out.println("整理中\t"+TIDYING);
        System.out.println("已终止\t"+TERMINATED);

        System.out.println("计数位\t"+COUNT_BITS);
        System.out.println("容量呀\t"+CAPACITY);

        int c = test.ctl.get();
        System.out.println("当前C\t"+c);
        System.out.println("运行状态\t"+runStateOf(c));
        System.out.println("worker统计\t"+workerCountOf(c));


        System.out.println("满足运行状态>=关闭掉 ");



    }
}