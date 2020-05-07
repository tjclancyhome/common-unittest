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

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.tjc.common.unittest.UnitTestSupport.methodName;
import static org.tjc.common.unittest.UnitTestSupport.setShowOutput;
import static org.tjc.common.unittest.UnitTestSupport.writeBanner;
import static org.tjc.common.unittest.UnitTestSupport.writefln;
import static org.tjc.common.unittest.UnitTestSupport.writeln;

public class StdOutRedirectorTest {

    public StdOutRedirector capture = StdOutRedirector.getInstance();

    @BeforeEach
    public void setUp() throws Exception {
        writeln("setUp(): start capturing...");
        setShowOutput(true);
        capture.startCapture();

    }

    @AfterEach
    public void tearDown() throws Exception {
        capture.stopCapture();
        writeln("tearDown(): stopped capturing.");
        writefln("tearDown(): captured text:%n(%s)", capture.toString());
        setShowOutput(false);
    }

    @Test
    public void smokeTest() {
        writeBanner(methodName());

        StdOutRedirector or = StdOutRedirector.getInstance();
        writeln(or);
    }

    @Test
    public void testRedirector() throws Exception {
        writeBanner(methodName());

        writeln("***** testRedirector() *****");
        writeln("This should be captured output");
    }

    @Test
    public void testRuleBasedStdOutRedirector() throws Exception {
        writeBanner(methodName());

        /*
         * Print a string to capturedSysOut.
         */
        capture.getCapturedStdOut().println(
            "This should print directly to console bypassing the capture.");
        writeln("***** testRuleBasedStdOutRedirector() *****");
        writeln("##### entered testRuleBasedStdOutRedirector()");
        writeln("Abc-123");
    }

}
