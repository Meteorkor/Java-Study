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


public abstract class VehicleTracker {
    
    public static class Taxi extends VehicleTracker{

        /**
         * @param id
         */
        public Taxi() {
            super("Taxi");
            // TODO Auto-generated constructor stub
        }

        @Override
        public void move() {
            if(x>Integer.MAX_VALUE-100){
                x = 0;
            }
            if(y>Integer.MAX_VALUE-100){
                y = 0;
            }
            x = x+3;
            y = y+3;
        }
        
    }
    public static  class Cop extends VehicleTracker{

        /**
         * @param id
         */
        public Cop() {
            super("Cop");
            // TODO Auto-generated constructor stub
        }

        @Override
        public void move() {
            if(x>Integer.MAX_VALUE-100){
                x = 0;
            }
            if(y>Integer.MAX_VALUE-100){
                y = 0;
            }
            x = x+2;
            y = y+2;
        }
        
    }
    public static class Truck extends VehicleTracker{

        /**
         * @param id
         */
        public Truck() {
            super("Truck");
            // TODO Auto-generated constructor stub
        }

        @Override
        public void move() {
            if(x>Integer.MAX_VALUE-100){
                x = 0;
            }
            if(y>Integer.MAX_VALUE-100){
                y = 0;
            }
            x = x+1;
            y = y+1;
        }
        
    }
    
    
    public VehicleTracker(String id) {
        this.id = id;
    }
    /**
     * 차량 ID
     */
    private String id;
    protected int x=0;
    protected int y=0;
    
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public String getId() {
        return id;
    }
    
    public abstract void move();
    
    public String getLocation(){
        StringBuilder stb = new StringBuilder();
        stb.append("x : ");
        stb.append(x);
        stb.append(" , ");
        stb.append("y : ");
        stb.append(x);
        return stb.toString();
    }
    
}
