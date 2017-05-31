

package com.effective.java.concurrent;

import java.util.concurrent.TimeUnit;
/**
 * while (!stopRequested){
 *  i++;
 * }
 * 을 
 * if(!stopRequested){
 *  while(true)
 *      i++;
 * }
 * 로 바꾸는 호이스팅(hoisting)이 일어날수 있다.
 * 
 * @author unseok.kim
 * @since  2017. 5. 31.
 */
public class StopThread {
//    private volatile static boolean stopRequested;
    private static boolean stopRequested;

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