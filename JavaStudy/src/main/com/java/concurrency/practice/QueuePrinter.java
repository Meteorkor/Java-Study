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

import java.util.Queue;

/**
 * @author unseok.kim
 * @since  2017. 7. 19.
 */
public class QueuePrinter {

    private Queue<String> preQ;
    public void print(Queue<String> queue){
        this.preQ = queue;
        RePrint();
    }
    public void RePrint(){
        for(String out : preQ){
            System.out.println("out : " + out);
        }
    }
}
