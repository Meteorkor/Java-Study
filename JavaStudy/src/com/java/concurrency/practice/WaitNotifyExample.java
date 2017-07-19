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

package com.java.concurrency.practice;

import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import java.util.LinkedList;

/**
 * @author unseok.kim
 * @since  2017. 7. 19.
 */
public class WaitNotifyExample {
    
    @Test
    public void 폴링() throws InterruptedException{
        Queue<String> q = new LinkedList<>();
        q.add("coffee");
        q.add("coffee");
        q.add("coffee");
        
        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(takeCoffee(q));
        service.submit(takeCoffee(q));
        service.submit(takeCoffee(q));
        service.submit(makeCoffee(q));
        service.submit(takeCoffee(q));
        
        service.shutdown();
        service.awaitTermination(10, TimeUnit.SECONDS);
        
    }
    
    private Runnable takeCoffee(Queue<String> q){
        return new Runnable() {
            @Override
            public void run() {
                    String coffee = null;
                    while (true) {
                        synchronized (q) {
                            coffee = q.poll();
                        }
                        if (coffee == null) {
                            System.out.println("wait coffee");
                        } else {
                            break;
                        }

                    }
                    System.out.println("take coffee");
            }
        };
    }
    private Runnable makeCoffee(Queue<String> q){
        return new Runnable() {
            
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                synchronized (q) {
                    q.add("coffee");
                }
                
            }
        };
    }
    
    

    @Test
    public void waitNotify() throws InterruptedException{
        Queue<String> q = new LinkedList<>();
        q.add("coffee");
        q.add("coffee");
        q.add("coffee");
        
        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(takeWaitCoffee(q));
        service.submit(takeWaitCoffee(q));
        service.submit(takeWaitCoffee(q));
        service.submit(makeNotifyCoffee(q));
        service.submit(takeWaitCoffee(q));
        
        service.shutdown();
        service.awaitTermination(10, TimeUnit.SECONDS);
        
    }
    
    private Runnable takeWaitCoffee(Queue<String> q){
        return new Runnable() {
            @Override
            public void run() {
                    String coffee = null;
                    while (true) {
                        synchronized (q) {
                            coffee = q.poll();
                            if (coffee == null) {
                                System.out.println("wait coffee");
                            } else {
                                break;
                            }
                            try {
                                q.wait();
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }

                    }
                    System.out.println("take coffee");
            }
        };
    }
    
    private Runnable makeNotifyCoffee(Queue<String> q){
        return new Runnable() {
            
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                synchronized (q) {
                    q.add("coffee");
                    q.notify();
                }
                
            }
        };
    }
    
    @Test
    public void blockQueueTest() throws InterruptedException{
        LinkedBlockingQueue<String> q = new LinkedBlockingQueue<>();
        q.add("coffee");
        q.add("coffee");
        q.add("coffee");
        
        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(blockingTakeCoffee(q));
        service.submit(blockingTakeCoffee(q));
        service.submit(blockingTakeCoffee(q));
        service.submit(blockingmakeCoffee(q));
        service.submit(blockingTakeCoffee(q));
        
        service.shutdown();
        service.awaitTermination(10, TimeUnit.SECONDS);
        
    }
    
    private Runnable blockingTakeCoffee(LinkedBlockingQueue<String> q){
        return new Runnable() {
            @Override
            public void run() {
                    String coffee = null;
                    while (true) {
                        
                            try {
                                coffee = q.take();
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                            }
                            if (coffee == null) {
                                System.out.println("wait coffee");
                            } else {
                                break;
                            }
                        
                    }
                    System.out.println("take coffee");
            }
        };
    }
    private Runnable blockingmakeCoffee(LinkedBlockingQueue<String> q){
        return new Runnable() {
            
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                try {
                    q.put("coffee");
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                    
                
            }
        };
    }
    
}
