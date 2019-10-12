/*
 * The MIT License
 *
 * Copyright 2019 tjclancy.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.tjc.common.unittest.perf;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import static org.tjc.common.unittest.UnitTestSupport.methodName;
import static org.tjc.common.unittest.UnitTestSupport.writeBanner;

/**
 *
 * @author tjclancy
 */
public class StopWatchTest {

    public StopWatchTest() {
    }

    /**
     * Test of create method, of class StopWatch.
     */
    @Test
    public void testCreate() {
        writeBanner(methodName());
        StopWatch sw = StopWatch.create();
        assertTrue(sw.isStopped());
        assertFalse(sw.isRunning());
    }

    /**
     * Test of createStarted method, of class StopWatch.
     */
    @Test
    public void testCreateStarted() {
        writeBanner(methodName());
        StopWatch sw = StopWatch.createStarted();
        assertTrue(sw.isRunning());
        assertFalse(sw.isStopped());
    }

    /**
     * Test of start method, of class StopWatch.
     */
    @Test
    public void testStart() {
        writeBanner(methodName());
        StopWatch sw = StopWatch.create();
        assertTrue(sw.isStopped());
        assertFalse(sw.isRunning());

        sw.start();
        assertTrue(sw.isRunning());
        assertFalse(sw.isStopped());

    }

    /**
     * Test of stop method, of class StopWatch.
     */
    @Test
    public void testStop() {
        writeBanner(methodName());
        StopWatch sw = StopWatch.create();
        try {
            sw.stop();
            fail("Should have throw an IllegalStateException...Can't stop a stopped watch.");
        } catch (IllegalStateException ex) {
            // An exception is expected.
        }

        sw = StopWatch.createStarted();
        assertTrue(sw.isRunning());
        assertFalse(sw.isStopped());
        sw.stop();
        assertTrue(sw.isStopped());
        assertFalse(sw.isRunning());
    }

    /**
     * Test of reset method, of class StopWatch.
     */
    @Test
    public void testReset() {
        writeBanner(methodName());
        StopWatch sw = StopWatch.createStarted();
        try {
            Thread.sleep(250);
        } catch (InterruptedException ex) {
            return;
        }
        sw.stop();
        assertTrue(sw.getTimeStarted() > 0);
        assertTrue(sw.getTimeStopped() > 0);
        assertTrue(sw.isStopped());

        sw.reset();
        assertEquals(0, sw.getTimeStarted());
        assertEquals(0, sw.getTimeStopped());
        assertTrue(sw.isStopped());
    }

    /**
     * Test of elapsedTime method, of class StopWatch.
     */
    @Test
    public void testElapsedTime() {
        writeBanner(methodName());
        StopWatch sw = StopWatch.createStarted();
        assertTrue(sw.isRunning());
        try {
            Thread.sleep(150);
        } catch (InterruptedException ex) {
            return;
        }
        sw.stop();
        assertTrue(sw.isStopped());
        assertTrue(sw.getTimeStarted() > 0);
        assertTrue(sw.getTimeStopped() > sw.getTimeStarted());

        long elapsed = sw.elapsedTime();
        assertTrue(elapsed > 0);
        assertEquals(elapsed, sw.getTimeStopped() - sw.getTimeStarted());
    }

    /**
     * Test of isRunning method, of class StopWatch.
     */
    @Test
    public void testIsRunning() {
        writeBanner(methodName());
        StopWatch sw = StopWatch.createStarted();
        assertTrue(sw.isRunning());
        assertFalse(sw.isStopped());
    }

    /**
     * Test of isStopped method, of class StopWatch.
     */
    @Test
    public void testIsStopped() {
        writeBanner(methodName());
        StopWatch sw = StopWatch.create();
        assertTrue(sw.isStopped());
        assertFalse(sw.isRunning());
    }

    /**
     * Test of getTimeStarted method, of class StopWatch.
     */
    @Test
    public void testGetTimeStarted() {
        writeBanner(methodName());
    }

    /**
     * Test of getTimeStopped method, of class StopWatch.
     */
    @Test
    public void testGetTimeStopped() {
        writeBanner(methodName());
    }

    /**
     * Test of getTime method, of class StopWatch.
     */
    @Test
    public void testGetTime_0args() {
        writeBanner(methodName());
    }

    /**
     * Test of getTime method, of class StopWatch.
     */
    @Test
    public void testGetTime_TimeUnit() {
        writeBanner(methodName());
    }

    /**
     * Test of getNanoTime method, of class StopWatch.
     */
    @Test
    public void testGetNanoTime() {
        writeBanner(methodName());
    }

    /**
     * Test of toString method, of class StopWatch.
     */
    @Test
    public void testToString() {
        writeBanner(methodName());
    }

}
