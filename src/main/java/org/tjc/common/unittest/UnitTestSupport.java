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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import static java.util.Arrays.asList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

/**
 *
 * @author tjclancy
 */
public final class UnitTestSupport {

    public static final String SHOW_OUTPUT_SYS_PROPERTY = "unitTestSupport.showOutput";
    protected static final DirectoryStream.Filter<Path> FILES_ONLY_FILTER = filesOnlyFilter();
    private static final int BUFFER_SIZE = 2048;

    /**
     * Private structure makes this a simple utility class.
     */
    private UnitTestSupport() {
    }

    public static void writeln() {
        if (isShowOutput()) {
            System.out.println();
        }
    }

    public static void writeln(Object o) {
        if (isShowOutput()) {
            System.out.println(o);
        }
    }

    public static void writeln(String s) {
        if (isShowOutput()) {
            System.out.println(s);
        }
    }

    public static void writeln(boolean b) {
        if (isShowOutput()) {
            System.out.println(b);
        }
    }

    public static void writeln(char c) {
        if (isShowOutput()) {
            System.out.println(c);
        }
    }

    public static void writeln(char[] a) {
        if (isShowOutput()) {
            return;
        }
        System.out.println(a);
    }

    public static void writeln(double d) {
        if (isShowOutput()) {
            System.out.println(d);
        }
    }

    public static void writeln(float f) {
        if (isShowOutput()) {
            System.out.println(f);
        }
    }

    public static void writeln(int i) {
        if (isShowOutput()) {
            System.out.println(i);
        }
    }

    public static void writeln(long l) {
        if (isShowOutput()) {
            System.out.println(l);
        }
    }

    public static void write(Object o) {
        if (isShowOutput()) {
            System.out.print(o);
        }
    }

    public static void write(String s) {
        if (isShowOutput()) {
            System.out.print(s);
        }
    }

    public static void write(boolean b) {
        if (isShowOutput()) {
            System.out.print(b);
        }
    }

    public static void write(char c) {
        if (isShowOutput()) {
            System.out.print(c);
        }
    }

    public static void write(char[] a) {
        if (isShowOutput()) {
            System.out.print(a);
        }
    }

    public static void write(double d) {
        if (isShowOutput()) {
            System.out.print(d);
        }
    }

    public static void write(float f) {
        if (isShowOutput()) {
            System.out.print(f);
        }
    }

    public static void write(int i) {
        if (isShowOutput()) {
            System.out.print(i);
        }
    }

    public static void write(long l) {
        if (isShowOutput()) {
            System.out.print(l);
        }
    }

    public static void writef(String format, Object... args) {
        if (isShowOutput()) {
            System.out.printf(format, args);
        }
    }

    public static void writefln(String format, Object... args) {
        if (isShowOutput()) {
            if (format != null) {
                format = format + "\n";
                System.out.printf(format, args);
            }
        }
    }

    public static void writeln(String pattern, Object... args) {
        writeMessage(pattern, args);
    }

    public static void writeMessage(String pattern, Object... args) {
        if (isShowOutput()) {
            writeln(MessageFormat.format(pattern, args));
        }
    }

    public static void writeBanner(String message) {
        if (isShowOutput()) {
            System.out.println(banner(message));
        }
    }

    public static void writeUnderlined(String message) {
        if (isShowOutput()) {
            System.out.println(underline(message));
        }
    }

    public static void writeUnderlined(String message, char underlineChar) {
        if (isShowOutput()) {
            System.out.println(underline(message, underlineChar));
        }
    }

    public static String underline(String message) {
        return underline(message, '=');
    }

    public static String underline(String message, char underlineChar) {
        String underlineStr = fill(underlineChar, message.length());
        return String.format("%s\n%s", message, underlineStr);
    }

    public static void writeBanner(String pattern, Object... args) {
        writeBanner(MessageFormat.format(pattern, args));
    }

    public static String banner(String message) {
        String border = fill("*", message.length() + 4);
        return String.format("%s\n* %s *\n%s", border, message, border);
    }

    public static String multiLineBanner(String[] messages) {
        return multiLineBanner(Arrays.asList(messages));
    }

    public static String multiLineBanner(List<String> messages) {
        StringBuilder sb = new StringBuilder();
        int maxLength = maxLength(messages);
        String border = fill("*", maxLength + 4);
        sb.append(border).append("\n");
        messages.forEach((s) -> {
            sb.append("* ").append(s).append(fill(" ", maxLength - s.length())).append(" *").append("\n");
        });
        sb.append(border).append("\n");
        return sb.toString();
    }

    public static String fill(String fillValue, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(fillValue);
        }
        return sb.toString();
    }

    public static String fill(char c, int count) {
        char[] ca = new char[count];
        Arrays.fill(ca, c);
        return String.valueOf(ca);
    }

    public static int maxLength(String[] messages) {
        return maxLength(Arrays.asList(messages));
    }

    public static int maxLength(Collection<String> messages) {
        int max = 0;
        for (String s : messages) {
            max = Math.max(max, s.length());
        }
        return max;
    }

    /**
     * Simple string stuff
     */
    public static final class Strings {

        public static String fromFile(Path path) throws IOException {
            return new String(Files.readAllBytes(path));
        }

        public static String swap(String s, int indx1, int indx2) {
            char[] ca = s.toCharArray();
            char tmp = ca[indx1];
            ca[indx1] = ca[indx2];
            ca[indx2] = tmp;
            String swapped = String.valueOf(ca);
            return swapped;
        }

    }

    /**
     * Simple boolean logic
     */
    public static final class Logic {

        public static boolean and(boolean a, boolean b) {
            return Boolean.logicalAnd(a, b);
        }

        public static boolean or(boolean a, boolean b) {
            return Boolean.logicalOr(a, b);
        }

        public static boolean xor(boolean a, boolean b) {
            return Boolean.logicalXor(a, b);
        }

        public static boolean not(boolean b) {
            return !b;
        }

    }

    public static boolean isShowOutput() {
        return getShowOutput() == true;
    }

    public static boolean getShowOutput() {
        return Boolean.getBoolean(SHOW_OUTPUT_SYS_PROPERTY);
    }

    public static boolean systemPropertiesContainsKey(String name) {
        return System.getProperties().containsKey(name);
    }

    public static boolean isHideOutput() {
        return !isShowOutput();
    }

    public static void setShowOutput(boolean showOutput) {
        System.setProperty(SHOW_OUTPUT_SYS_PROPERTY, Boolean.toString(showOutput));
    }

    public static DirectoryStream.Filter<Path> filesOnlyFilter() {
        return (Path entry) -> pathIsFile(entry);
    }

    /**
     * Reads and returns all lines from a file.
     *
     * @param file The file to read.
     *
     * @return A list of Strings each representing a line from the file.
     *
     * @throws IOException If there is an error reading the file.
     */
    public static List<String> readLines(Path file) throws IOException {
        return Files.readAllLines(file);
    }

    /**
     * Counts and returns the number of lines in a file. This is inefficient but suitable for most test cases.
     *
     * @param file The file to read.
     *
     * @return The number of lines in the file.
     *
     * @throws IOException If an error occurs reading the file.
     */
    public static long countLines(Path file) throws IOException {
        return readLines(file).stream().count();
    }

    /**
     * Returns a stream of path object given a directory path.
     *
     * @param path
     *
     * @return
     *
     * @throws IOException
     */
    public static Stream<Path> streamPaths(Path path) throws IOException {
        return Files.list(path);
    }

    /**
     * Returns a stream of string where each string represents a line int the file.
     *
     * @param file
     *
     * @return
     *
     * @throws IOException
     */
    public static Stream<String> streamLines(Path file) throws IOException {
        return Files.lines(file, Charset.defaultCharset());
    }

    /**
     * Returns a BufferedReader from an InputStream.
     *
     * @param is
     *
     * @return
     */
    public static BufferedReader bufferedReaderFrom(InputStream is) {
        return new BufferedReader(new InputStreamReader(is));
    }

    /**
     * Reads the contents of a file, returning it as a String.
     *
     * @param path The file to read.
     *
     * @return The contents of the file as a String.
     *
     * @throws IOException If an error occurs when reading the file.
     */
    public static String fileToString(Path path) throws IOException {
        return new String(Files.readAllBytes(path));
    }

    /**
     *
     * @param fileName
     *
     * @return
     */
    public static boolean fileExists(String fileName) {
        return Files.exists(Paths.get(fileName));
    }

    /**
     *
     * @param path
     *
     * @return
     */
    public static boolean fileExists(final Path path) {
        return Files.exists(path);
    }

    /**
     *
     * @param file
     *
     * @return
     */
    public static boolean fileExists(final File file) {
        return file.exists();
    }

    /**
     * Checks that all files in the supplied collection exist.
     *
     * @param files
     *
     * @return
     */
    public static boolean filesExist(final Collection<File> files) {
        return files.stream().allMatch(file -> file.exists());
    }

    /**
     *
     * @param files
     *
     * @return
     */
    public static boolean filesExist(final File... files) {
        return asList(files).stream().allMatch(file -> file.exists());
    }

    /**
     *
     * @param files
     *
     * @return
     */
    public static boolean filesExist(final Stream<File> files) {
        return files.allMatch(file -> file.exists());
    }

    /**
     * Checks that all paths in the supplied collection exist.
     *
     * @param paths
     *
     * @return
     */
    public static boolean pathsExist(final Collection<Path> paths) {
        return paths.stream().allMatch(path -> Files.exists(path));
    }

    /**
     *
     * @param paths
     *
     * @return
     */
    public static boolean pathsExist(final Path... paths) {
        return asList(paths).stream().allMatch(path -> Files.exists(path));
    }

    /**
     *
     * @param paths
     *
     * @return
     */
    public static boolean pathsExist(final Stream<Path> paths) {
        return paths.allMatch(path -> Files.exists(path));
    }

    /**
     * Simple file copy method. Java 7+ introduces the Files utility class that works with Path objects.
     *
     * @param src A string containing a file path.
     * @param dst A string containing a file path.
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void copy(String src, String dst) throws FileNotFoundException, IOException {
        try (InputStream in = new FileInputStream(src);
                OutputStream out = new FileOutputStream(dst)) {
            byte[] buff = new byte[BUFFER_SIZE];
            int n;
            while ((n = in.read(buff)) >= 0) {
                out.write(buff, 0, n);
            }
        }
    }

    /**
     * Returns the files in a directory. If recursive == true then the director is scanned recursively.
     *
     * @param directory
     * @param recursive
     *
     * @return
     *
     * @throws IOException
     */
    public static List<String> fileNamesIn(String directory, boolean recursive) throws IOException {
        List<String> fileNames = new ArrayList<>();
        Path start = Paths.get(directory);

        if (Files.isDirectory(start)) {
            if (!recursive) {
                try (DirectoryStream<Path> ds = Files.newDirectoryStream(start, FILES_ONLY_FILTER)) {
                    ds.iterator().forEachRemaining(p -> {
                        fileNames.add(p.toString());
                    });
                }
            } else {
                try (Stream<Path> pathStream = Files.walk(start)) {
                    pathStream.forEach(p -> {
                        if (pathIsFile(p)) {
                            fileNames.add(p.toString());
                        }
                    });
                }
            }
        }
        return fileNames;
    }

    /**
     * Returns Path object for each file in basePath and if recursive returns Path object for basePath and its
     * subdirectories.
     *
     * @param start
     * @param recursive
     *
     * @return
     *
     * @throws IOException
     */
    public static List<Path> filesIn(Path start, boolean recursive) throws IOException {
        List<Path> files = new ArrayList<>();
        if (Files.isDirectory(start)) {
            if (!recursive) {
                try (DirectoryStream<Path> ds = Files.newDirectoryStream(start, FILES_ONLY_FILTER)) {
                    ds.iterator().forEachRemaining(p -> {
                        files.add(p);
                    });
                }
            } else {
                try (Stream<Path> pathStream = Files.walk(start)) {
                    pathStream.forEach(p -> {
                        if (pathIsFile(p)) {
                            files.add(p);
                        }
                    });
                }
            }
        }
        return files;
    }

    /**
     *
     * @param start
     *
     * @return
     *
     * @throws IOException
     */
    public static List<Path> filesInFileTree(Path start) throws IOException {
        List<Path> files = new ArrayList<>();
        Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (pathIsFile(file)) {
                    files.add(file);
                }
                return FileVisitResult.CONTINUE;
            }

        });
        return files;
    }

    /**
     *
     * @param path
     * @param visitor
     *
     * @return
     *
     * @throws IOException
     */
    public static Path visitFiles(Path path, FileVisitor<Path> visitor) throws IOException {
        return Files.walkFileTree(path, visitor);
    }

    /**
     *
     * @param p
     *
     * @return
     */
    public static boolean pathIsFile(Path p) {
        return Files.isRegularFile(p, LinkOption.NOFOLLOW_LINKS);
    }

    /**
     * Returns the name of the caller's method.
     * <p>
     * Note: This is original method name but I also like the naming convention the does not start with 'get'.
     * See the method methodName().
     *
     * @return The name of the calling method.
     */
    public static String getMethodName() {
        final StackTraceElement[] stes = Thread.currentThread().getStackTrace();
        StackTraceElement ste = stes[2];
        return ste.getMethodName();
    }

    /**
     * Returns the name of the caller's method.
     *
     * @return The name of the calling method.
     */
    public static String methodName() {
        final StackTraceElement[] stes = Thread.currentThread().getStackTrace();
        StackTraceElement ste = stes[2];
        return ste.getMethodName();
    }

    /**
     * Returns the name of the caller's method.
     *
     * @return The name of the calling method.
     */
    public static String thisMethodName() {
        final StackTraceElement[] stes = Thread.currentThread().getStackTrace();
        StackTraceElement ste = stes[2];
        return ste.getMethodName();
    }

    @SafeVarargs
    public static <T> List<T> listOf(T... members) {
        return new ArrayList<>(asList(members));
    }

    /**
     * Returns an empty ArrayList of type T. Note: Using this method name is consistent with the listOf(T...
     * members) but the the emptyList method is explicit.
     *
     * @param <T>
     *
     * @return
     */
    public static <T> List<T> listOf() {
        return new ArrayList<>();
    }

    /**
     * Returns an empty ArrayList of type T. Note: Not sure of I like this method naming over the listOf()
     * that returns an empty ArrayList of type T.
     *
     * @param <T>
     *
     * @return
     */
    public static <T> List<T> emptyList() {
        return new ArrayList<>();
    }

    /**
     *
     * @param ps
     */
    public static void listSystemProperties(PrintStream ps) {
        System.getProperties().list(ps);
    }

    public static String yesOrNo(boolean b) {
        return b == true ? "yes" : "no";
    }

    public static String trueOrFalse(boolean b) {
        return Boolean.toString(b);
    }

}
