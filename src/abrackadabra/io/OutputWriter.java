
package abrackadabra.io;

import java.io.*;
import java.util.Arrays;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * Author: Abrackadabra
 * Date: 23/11/12
 * Time: 09:12
 */
public class OutputWriter extends PrintWriter {
    static final String DEFAULT_SEPARATOR = " ";

    public OutputWriter(String fileName) {
        super(getWriter(fileName));
    }

    private static Writer getWriter(String s) {
        Writer writer;

        try {
            writer = new FileWriter(s);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return writer;
    }

    public OutputWriter(OutputStream outputStream) {
        super(outputStream);
    }

    public OutputWriter(Writer writer) {
        super(writer);
    }

    public void print(Object... objects) {
        printSeparated(DEFAULT_SEPARATOR, objects);
    }

    public void println(Object... objects) {
        printSeparated(DEFAULT_SEPARATOR, objects);
        println();
    }

    public void printSeparated(String separator, Object... objects) {
        for (int i = 0; i < objects.length; i++) {
            if (i > 0) {
                print(separator);
            }
            print(objects[i]);
        }
    }

    public void print(Iterable<?> from) {
        printSeparated(DEFAULT_SEPARATOR, from);
    }

    public void printSeparated(String separator, Iterable<?> from) {
        boolean first = true;
        for (Object object : from) {
            if (!first) {
                print(separator);
            } else {
                first = false;
            }
            print(object);
        }
    }
}
