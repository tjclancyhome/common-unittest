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
package org.tjc.common.unittest;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.concurrent.TimeUnit;
import org.tjc.common.unittest.perf.StopWatch;

import static org.tjc.common.unittest.UnitTestSupport.writeln;

/**
 * Abstract class to be derived by other test classes that supplies some useful methods when working
 * with unittest.
 *
 * @author tjclancy
 * @version $Id: $Id
 */
public abstract class BaseUnitTest {
    private final Deque<Boolean> smallStack = new ArrayDeque<>(1);
    private final StdOutRedirector stdOutRed;

    /**
     * Constructor for BaseUnitTest.
     */
    public BaseUnitTest() {
        stdOutRed = StdOutRedirector.getInstance();
    }

    public void captureStdOut() {
        stdOutRed.startCapture();
    }

    public String releaseStdOut() {
        stdOutRed.stopCapture();
        return stdOutRed.toString();
    }

    /**
     * pushBool.
     *
     * @param value a boolean.
     */
    public void pushBool(boolean value) {
        smallStack.push(value);
    }

    /**
     * popBool.
     *
     * @return a boolean.
     */
    public boolean popBool() {
        boolean value = smallStack.pop();
        return value;
    }

    /**
     * showTimedResult.
     *
     * @param sw     a {@link org.tjc.common.unittest.perf.StopWatch} object.
     * @param result a T object.
     * @param <T>    a T object.
     */
    public <T> void showTimedResult(StopWatch sw, T result) {
        showElapsedTimes(sw);
        showResult(result);
    }

    /**
     * showResult.
     *
     * @param result a T object.
     * @param <T>    a T object.
     */
    public <T> void showResult(T result) {
        writeln("=======");
        writeln("Result:");
        writeln("=======");
        writeln("  {0}", result);
        writeln();
    }

    /**
     * showTimedResults.
     *
     * @param sw      a {@link org.tjc.common.unittest.perf.StopWatch} object.
     * @param results a {@link java.util.Collection} object.
     * @param <T>     a T object.
     */
    public <T> void showTimedResults(StopWatch sw, Collection<T> results) {
        showElapsedTimes(sw);
        writeln("========");
        writeln("Results:");
        writeln("========");
        results.forEach(result -> writeln("  {0}", result));
        writeln();
    }

    /**
     * showResults.
     *
     * @param results a {@link java.util.Collection} object.
     * @param <T>     a T object.
     */
    public <T> void showResults(Collection<T> results) {
        writeln("========");
        writeln("Results:");
        writeln("========");
        results.forEach(result -> writeln("  {0}", result));
        writeln();
    }

    public void forceShowOutput() {
        if (UnitTestSupport.getOverrideForceOutput() == false) {
            if (!smallStack.isEmpty()) {
                smallStack.clear();
            }
            pushBool(UnitTestSupport.getShowOutput());
            UnitTestSupport.setShowOutput(true);
        }
    }

    public void restoreShowOutput() {
        if (smallStack.isEmpty() == false) {
            UnitTestSupport.setShowOutput(popBool());
        }
    }

    private static void showElapsedTimes(StopWatch sw) {
        writeln("time elapsed in nanoseconds : {0}", Long.toString(sw.getTime(TimeUnit.NANOSECONDS)));
        writeln("time elapsed in microseconds: {0}", Long
            .toString(sw.getTime(TimeUnit.MICROSECONDS)));
        writeln("time elapsed in milliseconds: {0}", Long
            .toString(sw.getTime(TimeUnit.MILLISECONDS)));
        writeln("time elapsed: {0}", sw.toString());
    }
}
