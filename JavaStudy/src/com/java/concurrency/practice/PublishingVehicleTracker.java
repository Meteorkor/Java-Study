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
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class PublishingVehicleTracker {
    private final Map<String, SafePoint> locations;
    private final Map<String, SafePoint> unmodifiableMap;
    
    public PublishingVehicleTracker(Map<String, SafePoint> locations){
        this.locations = new ConcurrentHashMap<>();
        this.unmodifiableMap = Collections.unmodifiableMap(locations);
    }
    public Map<String, SafePoint> getLocations(){
        return unmodifiableMap;
    }
    
    public SafePoint getLocation(String id){
        return locations.get(id);
    }
    
    public void setLocation(String id, int x, int y){
        if(!locations.containsKey(id)){
            throw new IllegalArgumentException("invalid vehicle name : " + id);
        }
        locations.get(id).set(x, y);
    }
        
    
    

}
