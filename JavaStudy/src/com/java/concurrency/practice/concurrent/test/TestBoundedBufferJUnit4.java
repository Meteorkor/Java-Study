
package com.java.concurrency.practice.concurrent.test;

import java.lang.management.ManagementFactory;

import org.junit.Assert;

//import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author unseok.kim
 * @since  2017. 9. 11.
 */
public class TestBoundedBufferJUnit4 {
   
    private static final long LOCKUP_DETECT_TIMEOUT = 1000;
    private static final int CAPACITY = 10000;
    private static final int THRESHOLD = 10000;
    
    @Test
    public void testIsEmptyWhenConstructed() {
        SemaphoreBoundedBuffer<Integer> bb = new SemaphoreBoundedBuffer<Integer>(10);
        
        Assert.assertTrue(bb.isEmpty());
        Assert.assertFalse(bb.isFull());
    }
    @Test
    public void testIsFullAfterPuts() throws InterruptedException {
        SemaphoreBoundedBuffer<Integer> bb = new SemaphoreBoundedBuffer<Integer>(10);
        for (int i = 0; i < 10; i++)
            bb.put(i);
        Assert.assertTrue(bb.isFull());
        Assert.assertFalse(bb.isEmpty());
    }

    @Test
    public void testTakeBlocksWhenEmpty() {
        final SemaphoreBoundedBuffer<Integer> bb = new SemaphoreBoundedBuffer<Integer>(10);
        Thread taker = new Thread() {
            public void run() {
                try {
                    int unused = bb.take();
                    Assert.fail(); // if we get here, it's an error
                } catch (InterruptedException success) {
                }
            }
        };
        try {
            taker.start();
            Thread.sleep(LOCKUP_DETECT_TIMEOUT);
            System.out.println("taker.getState() : " + taker.getState());
            taker.interrupt();
            taker.join(LOCKUP_DETECT_TIMEOUT);
            Assert.assertFalse(taker.isAlive());
        } catch (Exception unexpected) {
            Assert.fail();
        }
    }

    class Big {
        double[] data = new double[100000];
    }

    @Test
    public void testLeak() throws InterruptedException {
        SemaphoreBoundedBuffer<Big> bb = new SemaphoreBoundedBuffer<Big>(CAPACITY);
        int heapSize1 = snapshotHeap();
        for (int i = 0; i < CAPACITY; i++)
            bb.put(new Big());
        for (int i = 0; i < CAPACITY; i++)
            bb.take();
        int heapSize2 = snapshotHeap();
        Assert.assertTrue(Math.abs(heapSize1 - heapSize2) < THRESHOLD);
    }

    
    private int snapshotHeap() {
//        System.out.println("ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().toString() : " + ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().toString());
//init = 402653184(393216K) used = 10066432(9830K) committed = 385875968(376832K) max = 5713690624(5579776K)
        
        /* Snapshot heap and return heap size */
        return 0;
    }

}
