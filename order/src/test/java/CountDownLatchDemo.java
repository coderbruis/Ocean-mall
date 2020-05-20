import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * @author LuoHaiYang
 */
public class CountDownLatchDemo {
    // 线程任务
    class Worker implements Runnable {
        // 定义计数锁用来实现功能 1
        private final CountDownLatch startSignal;
        // 定义计数锁用来实现功能 2
        private final CountDownLatch doneSignal;

        Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
            this.startSignal = startSignal;
            this.doneSignal = doneSignal;
        }
        // 子线程做的事情
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName()+" begin");
                // await 时有两点需要注意：await 时 state 不会发生变化，2：startSignal 的state初始化是 1，所以所有子线程都是获取不到锁的，都需要到同步队列中去等待，达到先启动的子线程等待后面启动的子线程的结果
                startSignal.await();
                doWork();
                // countDown 每次会使 state 减一，doneSignal 初始化为 9，countDown 前 8 次执行都会返回 false (releaseShared 方法)，执行第 9 次时，state 递减为 0，会 countDown 成功，表示所有子线程都执行完了，会释放 await 在 doneSignal 上的主线程
                doneSignal.countDown();
                System.out.println(Thread.currentThread().getName()+" end");
            } catch (InterruptedException ex) {
            } // return;
        }

        void doWork() throws InterruptedException {
            System.out.println(Thread.currentThread().getName()+"sleep 5s …………");
            Thread.sleep(5000l);
        }
    }

    @Test
    public void test() throws InterruptedException {
        // state 初始化为 1 很关键，子线程是不断的 await，await 时 state 是不会变化的，并且发现 state 都是 1，所有线程都获取不到锁
        // 造成所有线程都到同步队列中去等待，当主线程执行 countDown 时，就会一起把等待的线程给释放掉
        CountDownLatch startSignal = new CountDownLatch(1);
        // state 初始化成 9，表示有 9 个子线程执行完成之后，会唤醒主线程
        CountDownLatch doneSignal = new CountDownLatch(9);

        for (int i = 0; i < 9; ++i) // create and start threads
        {
            new Thread(new Worker(startSignal, doneSignal)).start();
        }
        System.out.println("main thread begin");
        // 这行代码唤醒 9 个子线程，开始执行(因为 startSignal 锁的状态是 1，所以调用一次 countDown 方法就可以释放9个等待的子线程)
        startSignal.countDown();
        // 这行代码使主线程陷入沉睡，等待 9 个子线程执行完成之后才会继续执行(就是等待子线程执行 doneSignal.countDown())
        doneSignal.await();
        System.out.println("main thread end");
    }
}
