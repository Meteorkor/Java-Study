
package com.effective.java.concurrent;

import org.junit.Test;

/**
 * 
 * @author unseok.kim
 * @since  2017. 6. 1.
 */
public class ThreadStopProblemExample {
    
    public class Toilet{
        private int tissue = 1;
        public void use(){
            while(tissue<1){
                System.out.println("tissue..not..exist");
            }
            if(tissue>0){
                tissue--;
            }
            
        }
        public void tissueFill(){
            if(tissue<1){
                tissue = 1;
            }
        }
        public int getTissue() {
            return tissue;
        }
        
    }
    
    public class ToiletTask implements Runnable{
        private Toilet toilet;
        public ToiletTask(Toilet toilet){
            this.toilet = toilet;
        }
        
        /* (non-Javadoc)
         * @see java.lang.Runnable#run()
         */
        @Override
        public void run() {
            synchronized (this.toilet) {
                toilet.use();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                toilet.tissueFill();

            }     
        }
        
    }
    
    @Test
    public void problemExamTest(){
        Toilet toilet = new Toilet();
        Thread th = null;
        for(int idx=0;idx<10000;idx++){
            th = new Thread(new ToiletTask(toilet));
            th.start();
            th.stop();
        }
        
        System.out.println("aaaa");
        try {
            th.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("toilet.getTissue() : " + toilet.getTissue());
    }
    
}
