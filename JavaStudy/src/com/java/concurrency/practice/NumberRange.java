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

import java.util.concurrent.atomic.AtomicInteger;

public class NumberRange {
    //의존성 조건 : lower <= upper
    private final AtomicInteger lower = new AtomicInteger(0);
    private final AtomicInteger upper = new AtomicInteger(0);
    /* 초기 */
    public void setLower(int i){
        //주의 - 안전하지 않은 비교문
        if(i > upper.get()){
            throw new IllegalArgumentException("can't set lower to " + i + " > upper");
        }
        lower.set(i);
    }
    
    public void setUpper(int i){
        //주의 - 안전하지 않은 비교문
        if(i < lower.get()){
            throw new IllegalArgumentException("can't set upper to " + i + " > upper");
        }
        upper.set(i);
    }
    
    /* sync 
    public synchronized void setLower(int i){
        if(i > upper.get()){
            throw new IllegalArgumentException("can't set lower to " + i + " > upper");
        }
        lower.set(i);
    }
    
    public synchronized void setUpper(int i){
        if(i < lower.get()){
            throw new IllegalArgumentException("can't set upper to " + i + " > upper");
        }
        upper.set(i);
    }
    */
    
    
    public boolean isInRange(int i){
        return (i >= lower.get() && i <= upper.get());
    }

}
