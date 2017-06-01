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

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.junit.Test;

/**
 * [Concurrent] generateSerialNumber 'Atomic' 을 통한 상호배제 처리
 * @author unseok.kim
 * @since 2017. 5. 31.
 */
public class EffectiveJavaConcurrentExample {

    private static AtomicLong nextSerialNumber = new AtomicLong(0);

    public static long generateSerialNumber() {
        return nextSerialNumber.getAndIncrement();
    }
    /*
    private static volatile int nextSerialNumber = 0;

    public static int generateSerialNumber() {
        return nextSerialNumber++;
    }
    */
    @Test
    public void 단일스레드테스트() {
        for (int forIdx = 0; forIdx < 100000; forIdx++) {
            long idx = generateSerialNumber();
        }
        
        long idx = generateSerialNumber();
        System.out.println("Lastidx : " + idx);

    }

    @Test
    public void 멀티스레드테스트() throws InterruptedException {
        ExecutorService exService = Executors.newFixedThreadPool(1000);
        for (int forIdx = 0; forIdx < 100000; forIdx++) {
            
            exService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    long idx= generateSerialNumber();
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
        long idx = generateSerialNumber();
        Thread.sleep(500);
        System.out.println("Lastidx : " + idx);

    }
    
    @Test
    public void 항목67클라이언트메소드(){
        ObservableSet<Integer> obSet = new ObservableSet<Integer>();

        /**
         * ConcurrentModificationException
        
        obSet.addObserver(new SetObserver<Integer>() {
            public void added(ObservableSet<Integer> s, Integer e) {
                System.out.println(e);
                if (e == 23)
                    s.removeObserver(this);
            }
        });
        */ 
        /*
        데드락
        */
        obSet.addObserver(new SetObserver<Integer>() {
            public void added(final ObservableSet<Integer> s, Integer e) {
                System.out.println(e);
                if (e == 23) {
                    ExecutorService executor = Executors.newSingleThreadExecutor();
                    final SetObserver<Integer> observer = this;
                    try {
                        executor.submit(new Runnable() {
                            public void run() {
                                s.removeObserver(observer);
                            }
                        }).get();
                    } catch (ExecutionException ex) {
                        throw new AssertionError(ex.getCause());
                    } catch (InterruptedException ex) {
                        throw new AssertionError(ex.getCause());
                    } finally {
                        executor.shutdown();
                    }
                }
            }
        });
        
        for(int idx=0;idx<30;idx++){
            obSet.add(idx);    
        }
        
        
    }
}
