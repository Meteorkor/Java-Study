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

import com.java.concurrency.practice.PersonSet.Person;

public class PrivateLock {

    private final Object myLock = new Object();
    @GuardedBy("myLock") Person person;
    
    public void someMethod(){
        synchronized (myLock) {
            //person 변수의 값을 읽거나 변경
        }
    }
    
}
