package com.szu.juc.thread_pool;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 * @Date 2021/3/7 0:01
 */

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class L01_ThreadPoolTest {

    public static void main(String[] args) {
        ThreadFactory threadFactory = new MyThreadFactory();
        ExecutorService executorService = Executors.newCachedThreadPool();
        Runnable runnable = ()-> System.out.println("this is runnable");
        executorService.submit(runnable);

        ThreadPoolExecutor myExecutor = new ThreadPoolExecutor(4, 8,
                60, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(16),
                new MyThreadFactory(),
                new MyRejectPolicy());

        for (int i = 0; i < 20; i++) {
            myExecutor.submit(runnable);
        }

    }

}


/*
* 新建自定义的ThreadFactory
* */
class MyThreadFactory implements ThreadFactory {

    private final ThreadGroup group;
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;

    MyThreadFactory() {
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() :
                Thread.currentThread().getThreadGroup();
        namePrefix = "MyThreadFactory-thread-";
    }


    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(group, r,
                namePrefix + threadNumber.getAndIncrement(),
                0);
        return t;
    }
}
/*
* 自己的拒绝策略
* */
class MyRejectPolicy implements RejectedExecutionHandler{

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("Queue is full!");
    }
}
