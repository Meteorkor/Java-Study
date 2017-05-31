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

package com.effective.java.concurrent;

/**
 * @author unseok.kim
 * @since 2017. 5. 31.
 */
public class EffectivelyImmutable {
    {//객체 참조를 클래스 초기화시에 static필드에 저장
        DataTestClz1 localVar = new DataTestClz1("kim",11);
        firstSolv = localVar;
    }
    private static DataTestClz1 firstSolv;

    public static DataTestClz1 getFirstSolv() {
        return firstSolv;
    }

    /**
     * volatile 필드에 저장
     * 
     * @since  2017. 5. 31.
     * @param name
     * @param number
     */
    private volatile DataTestClz1 dataClz1 = new DataTestClz1("default",0);
    public DataTestClz1 getDataClz2(){
        return this.dataClz1;
    } 
    
    public void setDataClz1(String name, int number){
        DataTestClz1 localdataClz1 = new DataTestClz1(name,number);
        dataClz1 = localdataClz1;
    }
}
