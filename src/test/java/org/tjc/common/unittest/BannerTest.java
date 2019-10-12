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

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import static org.tjc.common.unittest.UnitTestSupport.methodName;
import static org.tjc.common.unittest.UnitTestSupport.writeBanner;
import static org.tjc.common.unittest.UnitTestSupport.writeln;

/**
 * I was using the Builder in Lombok 1.18.8 while I was trying to make this utility class modular. I
 * couldn't figure out why I wasn't able to get this working.
 *
 * @author tjclancy
 */
public class BannerTest {

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
    public void smokeTest() {
        writeBanner(methodName());
        var expected = ""
                + "***************************\n"
                + "* Title                   *\n"
                + "* ======================= *\n"
                + "* This is a long message. *\n"
                + "***************************";
        var banner = Banner.newInstance('*', '=', "Title", 0, "This is a long message.");
        writeln(banner.generateBanner());
        assertEquals(expected, banner.generateBanner());
    }

    @Test
    public void testBannerBuilder() {
        writeBanner(methodName());

        var messages = new ArrayList<String>();
        messages.add("This is another long message.");

        var expected = ""
                + "*********************************\n"
                + "* Title                         *\n"
                + "* ============================= *\n"
                + "* This is another long message. *\n"
                + "*********************************";
        var banner = Banner.newInstance('*', '=', "Title", 0, messages);
        writeln(banner.generateBanner());
        assertEquals(expected, banner.generateBanner());
    }

    @Test
    public void testSimpleBanner() {
        writeBanner(methodName());

        var expected = ""
                + "*********\n"
                + "* Title *\n"
                + "*********";
        var banner = Banner.newInstance("Title");
        writeln(banner.generateBanner());
        assertEquals(expected, banner.generateBanner());
    }

    @Test
    public void testWhenTitleLengthIsGreaterThanMaxMessagesLength() {
        writeBanner(methodName());

        var expected = ""
                + "*********\n"
                + "* Title *\n"
                + "* ===== *\n"
                + "* m1    *\n"
                + "* m2    *\n"
                + "* m3    *\n"
                + "*********";
        var messages = new ArrayList<String>();
        messages.add("m1");
        messages.add("m2");
        messages.add("m3");
        var banner = Banner.newInstance('*', '=', "Title", 0, messages);

        writeln(banner.generateBanner());
        assertEquals(expected, banner.generateBanner());

    }
}
