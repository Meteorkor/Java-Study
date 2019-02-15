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
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class DelegatingVehicleTracker {
    private final ConcurrentHashMap<String,Point> locations;
    private final Map<String, Point> unmodifinableMap;
    public DelegatingVehicleTracker(Map<String, Point> points){
        locations = new ConcurrentHashMap<>();
        unmodifinableMap = Collections.unmodifiableMap(locations);
    }
    public Map<String, Point> getLocations(){
        return unmodifinableMap;
    }
    public Point getLocation(String id){
        return locations.get(id);
    }
    public void setLocation(String id, int x, int y){
        
        
        if(locations.replace(id, new Point(x, y))==null){
            throw new IllegalArgumentException("invalid vehicle name : " + id);
        }
        
    }
    
    public Map<String, Point> getLocationsCopy(){
        return Collections.unmodifiableMap(new HashMap<String,Point>(locations));
    }

}
