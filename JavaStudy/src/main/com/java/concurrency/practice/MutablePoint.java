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

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class MutablePoint {
    public int x,y;
    public MutablePoint() {x=0;y=0;}
    public MutablePoint(MutablePoint p){
        this.x = p.x;
        this.y = p.y;
    }

}
