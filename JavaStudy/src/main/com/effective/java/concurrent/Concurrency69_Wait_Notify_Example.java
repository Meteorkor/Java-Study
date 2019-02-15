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

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 * @author unseok.kim
 * @since  2017. 6. 2.
 */
public class Concurrency69_Wait_Notify_Example {

    private static final ConcurrentMap<String,String> map = new ConcurrentHashMap<>();
    /*
    public static String intern(String s){
        String previousValue = map.putIfAbsent(s, s);
        return previousValue== null ? s :previousValue;
    }
    */
    
    public static String intern(String s){
        String result = map.get(s);
        if(result == null){
            result = map.putIfAbsent(s, s);
            if(result ==null){
                return result;
            }
        }
        return result;
    }
    Object lock  = new Object();
    
    boolean state = false;
    
    @Test
    public void waitNotiTest() throws InterruptedException{
        ExecutorService exService = Executors.newFixedThreadPool(5);
        
        exService.submit(new Runnable() {
            
            @Override
            public void run() {
                synchronized (lock) {
                    try {
                        while(!state)
                        lock.wait();
                        System.out.println("wake");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
//                        state = false;
                    }    
                }
            }
        });
        synchronized (lock) {
            lock.notify();
//            lock.notifyAll();
            state = true;
        }
                
        exService.shutdown();
        exService.awaitTermination(1000, TimeUnit.SECONDS);
    }
}
