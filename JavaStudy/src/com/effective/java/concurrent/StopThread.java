

package com.effective.java.concurrent;

import java.util.concurrent.TimeUnit;
/**
 * 필드 값을 변경하는 메소드와 읽는 메소드 모두 동기화 해야한다.
 * boolean은 값 변경 동작이 원자적(상호 배타 불필요)이기 때문에 synchronized가 불필요 하지만
 * 변수값을 확실하게 전달하기 위하여 synchronized를 사용한 부분이다.
 * 
 * @author unseok.kim
 * @since  2017. 5. 31.
 */
public class StopThread {
//    private volatile static boolean stopRequested;
    private static boolean stopRequested;

    public synchronized static boolean isStopRequested() {
        return stopRequested;
    }

    public synchronized static void setStopRequested(boolean stopRequested) {
        StopThread.stopRequested = stopRequested;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread backgroundThread = new Thread(new Runnable() {
            public void run() {
                int i = 0;
                while (!isStopRequested()){
                    i++;
                    System.out.println("I'm Run : " + i);
                }
                    
            }
        });
        backgroundThread.start();
        TimeUnit.SECONDS.sleep(1);
        setStopRequested(true);
        backgroundThread.join();
    }
}