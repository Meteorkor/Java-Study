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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class MonitorVehicleTracker {
    
    private final Map<String, MutablePoint> locations;
    
    public MonitorVehicleTracker(Map<String, MutablePoint> locations){
        this.locations = deepCopy(locations);
    }
    public synchronized Map<String, MutablePoint> getLocations(){
        return deepCopy(locations);
    }
    public synchronized MutablePoint getLocations(String id){
        MutablePoint loc = locations.get(id);
        return loc == null ? null : new MutablePoint(loc);
        
    }
    
    public synchronized void setLocation(String id, int x, int y){
        MutablePoint loc = locations.get(id);
        if(loc == null) throw new IllegalArgumentException("No such ID: " + id);
        loc.x = x;
    }
    
    
    private static Map<String, MutablePoint> deepCopy(Map<String, MutablePoint> m){
        Map<String, MutablePoint> result = new HashMap<String, MutablePoint>();
        for(String id : m.keySet()){
            result.put(id, new MutablePoint(m.get(id)));
        }
        return Collections.unmodifiableMap(result);
    }

}
