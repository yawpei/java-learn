package vip.itlearning.java.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author yaw
 * @version 1.0
 * @Date 2022/8/26
 */
public class ThreadTest {

    public static void main(String[] args) {
    /*for (int i = 0; i < 30; i++) {
            System.out.println(ThreadRun.currentThread().getName() + ":" + i);
            if (i == 20) {
                new ThreadRun().start();
            }
        }
        for (int i = 0; i < 30; i++) {
            System.out.println(ThreadRun.currentThread().getName() + ":" + i);
            if (i == 20) {
                RunnableRun run = new RunnableRun();
                new Thread(run, "新线程1").start();
                new Thread(run, "新线程2").start();
            }
        }*/

        CallableThreadCall call = new CallableThreadCall();
        FutureTask<Integer> ft = new FutureTask<>(call);
        for (int i = 0; i < 30; i++) {
            System.out.println(ThreadRun.currentThread().getName() + ":" + i);
            if (i == 20) {
                new Thread(ft, "有返回值的线程").start();
            }
        }
        try {
            System.out.println("子线程的返回值：" + ft.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 重写Thead的run方法
     */
    static class ThreadRun extends Thread {

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                System.out.println(getName() + ":" + i);
            }
        }
    }

    /**
     * 实现Runnable的run方法
     */
    static class RunnableRun implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }

    /**
     * 实现Callable的call方法
     */
    static class CallableThreadCall implements Callable<Integer> {

        @Override
        public Integer call() {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
            return 1;
        }
    }
}
