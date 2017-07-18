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

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;


@ThreadSafe
public class Counter {
   @GuardedBy("this") private long value = 0;
   
   public synchronized long getValue(){
       return value;
   }
   
   public synchronized long increment(){
       if(value==Long.MAX_VALUE)
           throw new IllegalStateException("counter overflow");
       return ++value;
   }
   
}
