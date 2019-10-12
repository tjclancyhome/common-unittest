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

import java.lang.reflect.Method;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.tjc.common.unittest.UnitTestSupport.methodName;
import static org.tjc.common.unittest.UnitTestSupport.writeBanner;
import static org.tjc.common.unittest.UnitTestSupport.writefln;
import static org.tjc.common.unittest.UnitTestSupport.writeln;

/**
 * @author tjclancy
 *
 */
public class ClassInfoTest extends BaseUnitTest {

    @Test
    public void testGetMethods() throws Exception {
        writeBanner(methodName());
        ClassInfo cnf = new ClassInfo("Hello. I'm a String!");
        cnf.getMethods()
                .forEach(m -> writefln("name: %s, params: %s", m.getName(), m.getParameterCount()));
        writeln();
        ClassInfo cnf2 = new ClassInfo(String.class);

        cnf2.getMethods()
                .forEach(m -> writefln("name: %s, params: %s", m.getName(), m.getParameterCount()));
        writeln();

        List<Method> cnf2Methods = cnf2.getMethods();
        cnf.getMethods().forEach(m -> {
            if (!cnf2Methods.contains(m)) {
                writefln("method: %s is missing from cnf2.methods()", m);
            } else {
                writefln("both lists contain this method: %s", m);
            }
        });
    }

}
