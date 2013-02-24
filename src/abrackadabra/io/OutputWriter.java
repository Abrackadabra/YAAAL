package abrackadabra.io;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * Author: Abrackadabra
 * Date: 23/11/12
 * Time: 09:12
 */
public class OutputWriter extends PrintWriter {
    static final String DEFAULT_SEPARATOR = " ";

    String separator = DEFAULT_SEPARATOR;

    public void setSeparator(String separator) {
        this.separator = separator;
    }

    public OutputWriter(String fileName) throws FileNotFoundException {
        super(fileName);
    }

    public OutputWriter(OutputStream outputStream) {
        super(outputStream);
    }

    public OutputWriter(Writer writer) {
        super(writer);
    }

    public void printSeparated(Object... objects) {
        for (int i = 0; i < objects.length; i++) {
            if (i > 0) {
                print(separator);
            }
            print(objects[i]);
        }
    }

    public void printCase(int index) {
        printf("Case #%d: ", index);
    }

    public void printYesNo(boolean value) {
        printYesNo(value, false);
    }

    public void printYesNo(boolean value, boolean caps) {
        printYesNo(value, caps, false);
    }

    public void printYesNo(boolean value, boolean caps, boolean polish) {
        if (polish) {
            if (caps) {
                println(value ? "TAK" : "NIE");
            } else {
                println(value ? "Tak" : "Nie");
            }
        } else {
            if (caps) {
                println(value ? "YES" : "NO");
            } else {
                println(value ? "Yes" : "No");
            }
        }
    }

    public void printlnSpaced(Iterable<? extends Object> from) {
        boolean first = true;
        for (Object object : from) {
            if (!first) {
                print(' ');
            } else {
                first = false;
            }
            print(object);
        }
        println();
    }
}
