package com.effective.java.concurrent;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

/**
 * @author unseok.kim
 * @since  2017. 6. 1.
 */
public class LockTechTest {

    /**
     * 락 분할(대상 분할)
     * @author unseok.kim
     * @since  2017. 6. 1.
     */
    public class ServerStatus {
        private final Set<String> users;
        private final Set<String> queries;

        public ServerStatus() {
            users = new HashSet<String>();
            queries = new HashSet<String>();
        }
        
        public synchronized void addUser(String user){
            users.add(user);
        }
        public synchronized void addQuery(String query){
            queries.add(query);
        }
        /*
        public synchronized void addUser(String user){
            synchronized (users) {
                users.add(user);    
            }
        }
        public synchronized void addQuery(String query){
            synchronized (queries) {
                queries.add(query);    
            }
        }
        */
    }
    
    
        Map<String,String> map = new HashMap<String,String>();
        
//    Map<String,String> map = new ConcurrentHashMap<String,String>();
    
    @Before
    public void before(){
               
//        map.put("1", "1");
    }
    
    @Test
    public void synchronizedAndConcurrentHashMap() throws InterruptedException{
    
        ExecutorService executorService = Executors.newFixedThreadPool(1000);
        for(int idx=0; idx<100000;idx++){
            final int finalInt = idx;
            
                executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException ignore) {
                        }
                        synchronized(map){
                            map.put(String.valueOf(finalInt),String.valueOf(finalInt));    
                        }
                    }
                });    
            
            
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ignore) {
                    }
                    Object obj = null;
                    synchronized(map){
                        obj = map.get(String.valueOf(finalInt));    
                    }
                    
                    System.out.println("obj : " + obj);
                    
                }
            });    
        }
        
        executorService.shutdown();
        executorService.awaitTermination(1000, TimeUnit.SECONDS);
        
        System.out.println("map.size() : " + map.size());
    }
    

    final int LOCK_LEN = 10;
    Object[] locks = new Object[LOCK_LEN];
    {
        for(int idx=0; idx<LOCK_LEN; idx++){
            locks[idx] = new Object();    
        }
        
    }

}
