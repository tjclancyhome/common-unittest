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
import java.util.Arrays;
import java.util.List;

/**
 * Class used to create a formatted banner within test case methods. Some examples:
 *
 * <pre>
 * <code>
 * ***********
 * * testFoo *
 * ***********
 *
 * or
 *
 * *************
 * * testFoo   *
 * *-----------*
 * * Message 1 *
 * * Message 2 *
 * *************
 * </code>
 * </pre>
 *
 * @author tjclancy
 *
 */
public class Banner {

    /**
     *
     */
    public static final char DEFAULT_BORDER_CHAR = '*';

    /**
     *
     */
    public static final char DEFAULT_TITLE_MESSAGE_SEPARATOR_CHAR = '-';

    private final Character borderChar;
    private final Character titleMessageSeparatorChar;
    private final String title;
    private final int numEndingBlankLines;
    private final List<String> messages;

    public Banner(Character borderChar, Character titleMessageSeparatorChar, String title,
            int numEndingBlankLines, List<String> messages) {
        this.borderChar = borderChar;
        this.titleMessageSeparatorChar = titleMessageSeparatorChar;
        this.title = title;
        this.numEndingBlankLines = numEndingBlankLines;
        this.messages = messages;
    }

    /*
     * I'm not sure that I like these static factory methods, but the can be useful. TODO: Figure
     * out whether to keep or kill.
     */
    /**
     *
     * @param borderChar
     * @param titleMessageSeparatorChar
     * @param title
     * @param numEndingBlankLines
     * @param messages
     *
     * @return
     */
    public static Banner newInstance(Character borderChar, Character titleMessageSeparatorChar,
            String title,
            int numEndingBlankLines, List<String> messages) {
        return new Banner(borderChar, titleMessageSeparatorChar, title, numEndingBlankLines,
                messages);
    }

    /**
     *
     * @param borderChar
     * @param titleMessageSeparatorChar
     * @param title
     * @param numEndingBlankLines
     * @param message
     *
     * @return
     */
    public static Banner newInstance(Character borderChar, Character titleMessageSeparatorChar,
            String title,
            int numEndingBlankLines, String message) {
        List<String> messages = new ArrayList<>(Arrays.asList(message));
        return new Banner(borderChar, titleMessageSeparatorChar, title, numEndingBlankLines,
                messages);
    }

    /**
     *
     * @param title
     * @param numEndingBlankLines
     * @param message
     *
     * @return
     */
    public static Banner newInstance(String title, int numEndingBlankLines, String message) {
        List<String> messages = new ArrayList<>(Arrays.asList(message));
        return new Banner(DEFAULT_BORDER_CHAR, DEFAULT_TITLE_MESSAGE_SEPARATOR_CHAR, title,
                numEndingBlankLines, messages);
    }

    /**
     *
     * @param title
     *
     * @return
     */
    public static Banner newInstance(String title) {
        List<String> messages = new ArrayList<>();
        return new Banner(DEFAULT_BORDER_CHAR, DEFAULT_TITLE_MESSAGE_SEPARATOR_CHAR, title, 0,
                messages);
    }

    /**
     *
     * @return
     */
    public String generateBanner() {
        StringBuilder sb = new StringBuilder();

        int maxStringLength = Math.max(title.length(), UnitTestSupport.maxLength(messages));

        String border = Strings.fill(borderChar, maxStringLength + 4);

        sb.append(border).append("\n");

        /*
         * Print title
         */
        if (title != null && !title.isEmpty()) {
            formatMessage(sb, maxStringLength, title, borderChar);
        }

        /*
         * Print messages if any exist.
         */
        if (!messages.isEmpty()) {
            formatTitleMessageSeparatorChar(sb, maxStringLength, titleMessageSeparatorChar,
                    borderChar);
            messages.forEach((s) -> {
                formatMessage(sb, maxStringLength, s, borderChar);
            });
        }
        sb.append(border);

        /*
         * Add blank lines, if a number > 0 is specified
         */
        for (int i = 0; i < numEndingBlankLines; i++) {
            sb.append("\n");
        }
        return sb.toString();
    }

    private static void formatMessage(StringBuilder sb, int len, String s, char borderChar) {
        sb.append("* ")
                .append(s)
                .append(Strings.fill(' ', len - s.length()))
                .append(" ")
                .append(borderChar)
                .append("\n");
    }

    private static void formatTitleMessageSeparatorChar(StringBuilder sb, int len, char sepChar,
            char borderChar) {
        sb.append("*").append(" ").append(Strings.fill(sepChar, len)).append(" ").append(borderChar)
                .append("\n");
    }

}
