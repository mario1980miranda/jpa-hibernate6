package com.code.truck.ecommerce.concurrence;

import org.junit.jupiter.api.Test;

public class ThreadsTests {

    private static void log(Object obj, Object... args) {
        System.out.println(String.format("LOG [" + System.currentTimeMillis() + "] :" + obj, args));
    }

    private static void wait(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException ignored) { }
    }

    @Test
    public void understandingThreads() {

        Runnable runnable1 = () -> {
            log("Runnable 01 will wait for 3 seconds");
            wait(3);
            log("Runnable 01 finished");
        };

        Runnable runnable2 = () -> {
            log("Runnable 02 will wait for 1 second");
            wait(1);
            log("Runnable 02 finished");
        };

        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        log("Thread test finished");

    }

}
