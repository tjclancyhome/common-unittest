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

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tjc.common.unittest.perf.StopWatch;

import static org.tjc.common.unittest.UnitTestSupport.getShowOutput;
import static org.tjc.common.unittest.UnitTestSupport.methodName;
import static org.tjc.common.unittest.UnitTestSupport.setShowOutput;
import static org.tjc.common.unittest.UnitTestSupport.writeBanner;
import static org.tjc.common.unittest.UnitTestSupport.writeln;

public class BaseUnitTestTest extends BaseUnitTest {

    @BeforeEach
    public void setUp() throws Exception {
        pushBool(getShowOutput());
        setShowOutput(true);
    }

    @AfterEach
    public void tearDown() throws Exception {
        setShowOutput(popBool());
    }

    @Test
    public void testShowTimedResult() {
        writeBanner(methodName());
        StopWatch sw = StopWatch.createStarted();
        sw.stop();
        BigDecimal result = bigDecimalOf(3.14159);
        showTimedResult(sw, result.toString());
    }

    @Test
    public void testShowResult() {
        writeBanner(methodName());
        BigDecimal result = bigDecimalOf(3.14159);
        showResult(result.toString());
    }

    @Test
    public void testShowTimedResults() {
        writeBanner(methodName());
        StopWatch sw = StopWatch.createStarted();
        List<String> results = Arrays.asList("0", "1", "2", "3");
        sw.stop();
        showTimedResults(sw, results);
    }

    @Test
    public void testShowResults() {
        writeBanner(methodName());
        List<String> results = Arrays.asList("0", "1", "2", "3");
        showResults(results);
    }

    @Test
    public void testStdOutCaptureAndRelease() {
        writeBanner(methodName());
        writeln("--------------------------------");
        writeln("Capturing std out now.");
        writeln("--------------------------------");
        this.captureStdOut();
        writeln("First thing captured is me!");
        writeln();
        writeln("This is a test.");
        writeln();
        writeln("This is only a test.");
        writeln();
        System.out.println("Can SOPs be captured?");
        writeln();
        String capturedOutput = this.releaseStdOut();
        writeln("--------------------------------");
        writeln("Showing what was captured.");
        writeln("--------------------------------");
        writeln("{0}", capturedOutput);
        writeln("--------------------------------");
        writeln("Done showing what was captured.");
        writeln("--------------------------------");
    }

    private BigDecimal bigDecimalOf(double val) {
        BigDecimal bd = BigDecimal.valueOf(val);
        bd = bd.setScale(5);
        return bd;
    }

}
