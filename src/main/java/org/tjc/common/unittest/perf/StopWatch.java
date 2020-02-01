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

import java.util.concurrent.TimeUnit;

/**
 * A simple, not very intelligent, stop watch class used for timing methods calls.
 *
 * @author tjclancy
 */
public class StopWatch {

    private long timeStarted;
    private long timeStopped;
    private boolean running;

    public StopWatch() {
        this.running = false;
    }

    public static StopWatch create() {
        return new StopWatch();
    }

    public static StopWatch createStarted() {
        StopWatch sw = new StopWatch();
        sw.start();
        return sw;
    }

    public void start() {
        if (this.running == true) {
            throw new IllegalStateException("The stop watch is already running.");
        }
        this.running = true;
        this.timeStarted = System.nanoTime();
    }

    public void stop() {
        if (!running) {
            throw new IllegalStateException("The stop watch is not running.");
        }
        this.timeStopped = System.nanoTime();
        this.running = false;
    }

    public void reset() {
        running = false;
        timeStarted = timeStopped = 0L;
    }

    public long elapsedTime() {
        if (timeStopped > 0 && timeStarted > 0 && (timeStopped > timeStarted)) {
            return timeStopped - timeStarted;
        }
        throw new IllegalStateException(
                String.format(
                        "Couldn't calculate elapsed time. Current state: time started: %s, time stopped: %s",
                        Long.toString(timeStarted), Long.toString(timeStopped)));
    }

    public boolean isRunning() {
        return running;
    }

    public boolean isStopped() {
        return !running;
    }

    public long getTimeStarted() {
        return timeStarted;
    }

    public long getTimeStopped() {
        return timeStopped;
    }

    /**
     * Converts the internal nanosecond time to milliseconds.
     *
     * @return Returns the time as a long int.
     */
    public long getTime() {
        return convert(timeStarted, TimeUnit.MILLISECONDS);
    }

    /**
     * Converts internal nanosecond time to the specfied time unit.
     *
     * @param timeUnit The TimeUnit (e.g. Hours, Minutes, Seconds).
     *
     * @return Returnd the time as a long int.
     */
    public long getTime(TimeUnit timeUnit) {
        return convert(timeStarted, timeUnit);
    }

    public long getNanoTime() {
        long time;
        if (isRunning()) {
            time = timeStarted;
        } else {
            time = elapsedTime();
        }
        return time;
    }

    @Override
    public String toString() {
        return String.format("started: %tLns, stopped: %tLns, elapsed: %tLns", timeStarted,
                timeStopped,
                elapsedTime());
    }

    private static long convert(long time, TimeUnit timeUnit) {
        return timeUnit.convert(time, TimeUnit.NANOSECONDS);
    }

}
