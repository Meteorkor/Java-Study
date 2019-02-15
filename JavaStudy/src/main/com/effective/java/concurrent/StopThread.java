

package com.effective.java.concurrent;

import java.util.concurrent.TimeUnit;
/**
 * 더 좋은 방법은 volatile을 이용하여 동기화 생략하는 것
 * volatile은 상호 배타는 제공하지 않지만
 * 항상 최신 값을 읽을수 있도록 가시성을제공한다.
 * 
 * @author unseok.kim
 * @since  2017. 5. 31.
 */
public class StopThread {
    private volatile static boolean stopRequested;
//    private static boolean stopRequested;

    public static void main(String[] args) throws InterruptedException {
        Thread backgroundThread = new Thread(new Runnable() {
            public void run() {
                int i = 0;
                while (!stopRequested){
                    i++;
                    System.out.println("I'm Run : " + i);
                }
                    
            }
        });
        backgroundThread.start();
        TimeUnit.SECONDS.sleep(1);
        stopRequested = true;
        backgroundThread.join();
    }
}