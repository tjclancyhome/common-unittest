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
import static org.tjc.common.unittest.UnitTestSupport.writeln;
import org.tjc.common.unittest.perf.StopWatch;

public abstract class BaseUnitTest {
    private final Deque<Boolean> smallStack;

    public BaseUnitTest() {
        smallStack = new ArrayDeque<>();
    }

    /**
     *
     * @param value
     */
    public void push(boolean value) {
        System.out.println(String.format("### pushing: %s", value));
        smallStack.push(value);
    }

    /**
     *
     * @return
     */
    public boolean pop() {
        boolean value = smallStack.pop();
        System.out.println(String.format("### popped: %s", value));
        return value;
    }

    /**
     *
     * @param <T>
     * @param sw
     * @param result
     */
    public <T> void showTimedResult(StopWatch sw, T result) {
        showElapsedTimes(sw);
        showResult(result);
    }

    /**
     *
     * @param <T>
     * @param result
     */
    public <T> void showResult(T result) {
        writeln("=======");
        writeln("Result:");
        writeln("=======");
        writeln("  {0}", result);
        writeln();
    }

    /**
     *
     * @param <T>
     * @param sw
     * @param results
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
     *
     * @param <T>
     * @param results
     */
    public <T> void showResults(Collection<T> results) {
        writeln("========");
        writeln("Results:");
        writeln("========");
        results.forEach(result -> writeln("  {0}", result));
        writeln();
    }

    private static void showElapsedTimes(StopWatch sw) {
        writeln("time elapsed in nanoseconds : {0}", Long.toString(sw.getTime(TimeUnit.NANOSECONDS)));
        writeln("time elapsed in microseconds: {0}", Long.toString(sw.getTime(TimeUnit.MICROSECONDS)));
        writeln("time elapsed in milliseconds: {0}", Long.toString(sw.getTime(TimeUnit.MILLISECONDS)));
        writeln("time elapsed: {0}", sw.toString());
    }
}
