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
import org.junit.jupiter.api.Test;
import static org.tjc.common.unittest.UnitTestSupport.methodName;
import static org.tjc.common.unittest.UnitTestSupport.writeBanner;
import org.tjc.common.unittest.perf.StopWatch;

public class BaseUnitTestTest {

//    @BeforeEach
//    public void setUp() throws Exception {
//        setShowOutput(true);
//    }
//
//    @AfterEach
//    public void tearDown() throws Exception {
//        setShowOutput(false);
//    }
    @Test
    public void testShowTimedResult() {
        writeBanner(methodName());
        BaseUnitTest baseUnitTest = new BaseUnitTest();
        StopWatch sw = StopWatch.createStarted();
        sw.stop();
        BigDecimal result = bigDecimalOf(3.14159);
        baseUnitTest.showTimedResult(sw, result.toString());
    }

    @Test
    public void testShowResult() {
        writeBanner(methodName());
        BaseUnitTest baseUnitTest = new BaseUnitTest();
        BigDecimal result = bigDecimalOf(3.14159);
        baseUnitTest.showResult(result.toString());
    }

    @Test
    public void testShowTimedResults() {
        writeBanner(methodName());
        BaseUnitTest baseUnitTest = new BaseUnitTest();
        StopWatch sw = StopWatch.createStarted();
        List<String> results = Arrays.asList("0", "1", "2", "3");
        sw.stop();
        baseUnitTest.showTimedResults(sw, results);
    }

    @Test
    public void testShowResults() {
        writeBanner(methodName());
        List<String> results = Arrays.asList("0", "1", "2", "3");
        BaseUnitTest baseUnitTest = new BaseUnitTest();
        baseUnitTest.showResults(results);
    }

    private BigDecimal bigDecimalOf(double val) {
        BigDecimal bd = BigDecimal.valueOf(val);
        bd = bd.setScale(5);
        return bd;
    }

}
