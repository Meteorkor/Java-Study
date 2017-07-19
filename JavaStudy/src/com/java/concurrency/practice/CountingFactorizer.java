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

import java.util.concurrent.atomic.AtomicLong;

public class CountingFactorizer {
    private final AtomicLong count = new AtomicLong(0);

    public AtomicLong getCount() {
        return count;
    }
    

}
