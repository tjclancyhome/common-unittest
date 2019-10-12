/*
 * The MIT License
 *
 * Copyright 2018 tjclancy.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the
 * following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT
 * LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO
 * EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE
 * USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package org.tjc.common.unittest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * A singleton class that Captures std out (System.out), replacing it with a PrintStream that contains a
 * ByteArrayOutputStream.
 *
 * @author tjclancy
 */
public class StdOutRedirector implements AutoCloseable {

    private static final boolean AUTO_FLUSH = true;

    private PrintStream stdOut;
    private final PrintStream capturedStdOut;
    private ByteArrayOutputStream byteArrayOut;

    private StdOutRedirector() {
        this.capturedStdOut = System.out;
    }

    public static StdOutRedirector getInstance() {
        return SingletonInstance.INSTANCE;
    }

    /**
     * @return the capturing
     */
    public boolean isCapturing() {
        return !System.out.equals(this.capturedStdOut);
    }

    /**
     * @return the stdOut
     */
    public PrintStream getStdOut() {
        return stdOut;
    }

    /**
     * @return the capturedStdOut
     */
    public PrintStream getCapturedStdOut() {
        return capturedStdOut;
    }

    /**
     * @return the byteArrayOut
     */
    public ByteArrayOutputStream getByteArrayOut() {
        return byteArrayOut;
    }

    public void startCapture() {
        if (!isCapturing()) {
            if (this.byteArrayOut == null && this.stdOut == null) {
                this.byteArrayOut = new ByteArrayOutputStream();
                this.stdOut = new PrintStream(byteArrayOut, AUTO_FLUSH);
            }
            System.setOut(stdOut);
        }
    }

    public void stopCapture() {
        if (isCapturing()) {
            System.setOut(capturedStdOut);
        }
    }

    @Override
    public void close() throws Exception {
        System.setOut(capturedStdOut);
        stdOut.close();
        stdOut = null;
        byteArrayOut.reset();
        byteArrayOut = null;
    }

    @Override
    public String toString() {
        if (byteArrayOut != null) {
            try {
                byteArrayOut.flush();
            } catch (IOException e) {
                System.err.format("##### Caught exception: {}", e);
            }
            return byteArrayOut.toString();
        }
        return null;
    }

    private static final class SingletonInstance {

        static StdOutRedirector INSTANCE = new StdOutRedirector();
    }
}
