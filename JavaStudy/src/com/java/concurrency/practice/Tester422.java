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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 * @author unseok.kim
 * @since  2017. 7. 17.
 */
public class Tester422 {

    @Test
    public void test() throws InterruptedException{
        Map<String, VehicleTracker> map = makeVehicles();
     
        ExecutorService service = Executors.newCachedThreadPool();
        for(int idx=0;idx<1000;idx++){
            service.submit(getMover(map));    
        }
        /*
        service.submit(getMover(map));
        service.submit(getMover(map));
        service.submit(getMover(map));
        */
        service.shutdown();
        boolean ret = service.awaitTermination(5, TimeUnit.SECONDS);
        System.out.println("ret : " + ret);
        
//        service.sh;
        
        vehiclesPrint(map);
        
        
        
    }
    
    public Runnable getMover(Map map){
        return new Runnable() {
            
            @Override
            public void run() {
                vehicleMoved(map);
                
            }
        };
    }
    
    
    public void renderVehicle(String key, VehicleTracker tracker){
        System.out.println("key : " + key +" , trackeyLocation :" + tracker.getLocation());
    }
    public void vehiclesPrint(Map<String,VehicleTracker> map){
        for(Map.Entry<String, VehicleTracker> entry : map.entrySet()){
            renderVehicle(entry.getKey(),entry.getValue());
        }
    }
    
    public void vehicleMoved(Map<String,VehicleTracker> map){
        for(VehicleTracker tracker : map.values()){
            tracker.move();
        }
    }
    
    public Map<String, VehicleTracker> makeVehicles(){
        Map<String,VehicleTracker> map = new HashMap<>();
        
        VehicleTracker taxi = makeTaxi();
        VehicleTracker cop = makeCop();
        VehicleTracker truck = makeTruck();
        map.put(taxi.getId(), taxi);
        map.put(cop.getId(), cop);
        map.put(truck.getId(), truck);
        
        return map;
    }
    
    private VehicleTracker makeTaxi(){

        VehicleTracker tracker = new VehicleTracker.Taxi();
        return tracker;
    }
    private VehicleTracker makeCop(){
        VehicleTracker tracker = new VehicleTracker.Cop();
        return tracker;
    }
    private VehicleTracker makeTruck(){
        VehicleTracker tracker = new VehicleTracker.Truck();
        return tracker;
    }
    
    
}
