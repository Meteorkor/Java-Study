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
import java.util.Collections;
import java.util.List;

public class ListHelper<E> {
    public List<E> list = Collections.synchronizedList(new ArrayList<E>());

    // ...

    public synchronized boolean putIfAbsend(E x) {
        boolean absent = !list.contains(x);
        if (absent) {
            list.add(x);
            
        }
        return absent;
    }
    /*//동기화 후    
    public boolean putIfAbsend(E x) {
        synchronized (list) {
            boolean absent = !list.contains(x);
            if (absent) {
                list.add(x);
                
            }
            return absent;
        }        
    }
    */
}
