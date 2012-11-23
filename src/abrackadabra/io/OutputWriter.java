package abrackadabra.io;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * Author: Abrackadabra
 * Date: 23/11/12
 * Time: 09:12
 */
public class OutputWriter extends PrintWriter {
    public OutputWriter(String fileName) throws FileNotFoundException {
        super(fileName);
    }

    public OutputWriter(OutputStream outputStream) {
        super(outputStream);
    }

    public OutputWriter(Writer writer) {
        super(writer);
    }
}
