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

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class SafePoint {
    private int x,y;
    private SafePoint(int[] a){
        this(a[0],a[1]);
    }
    public SafePoint(SafePoint p){
        this(p.get());
    }
    public SafePoint(int x, int y){
        this.set(x,y);
    }
    public synchronized int[] get(){
        return new int[]{x,y};
    } 
    
    public synchronized void set(int x, int y){
        this.x = x;
        this.y = y;
    }
}
