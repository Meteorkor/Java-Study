/*
 * Copyright (c) 2016 by TmaxSoft co., Ltd.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of TmaxSoft co., Ltd("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with TmaxSoft co., Ltd.
 */

package com.effective.java.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 * @author unseok.kim
 * @since 2017. 5. 31.
 */
public class EffectiveJavaConcurrentExample {

    private static volatile int nextSerialNumber = 0;

    public static int generateSerialNumber() {
        return nextSerialNumber++;
    }

    @Test
    public void 단일스레드테스트() {
        for (int forIdx = 0; forIdx < 10000; forIdx++) {
            int idx = generateSerialNumber();
            System.out.println("idx : " + idx);
        }
        
        int idx = generateSerialNumber();
        System.out.println("Lastidx : " + idx);

    }

    @Test
    public void 멀티스레드테스트() throws InterruptedException {
        ExecutorService exService = Executors.newFixedThreadPool(1000);
        for (int forIdx = 0; forIdx < 10000; forIdx++) {
            
            exService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    int idx = generateSerialNumber();
                    System.out.println("idx : " + idx);
                }
            });
            /*
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    
                    int idx = generateSerialNumber();
                    System.out.println("idx : " + idx);
                }
            }).start();
            */
        }
        exService.shutdown();
        exService.awaitTermination(10, TimeUnit.SECONDS);
//        exService.shutdown();
        int idx = generateSerialNumber();
        Thread.sleep(500);
        System.out.println("Lastidx : " + idx);

    }
}
