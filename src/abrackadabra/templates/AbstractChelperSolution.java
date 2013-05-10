package abrackadabra.templates;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import java.io.IOException;
import java.io.OutputStream;

public abstract class AbstractChelperSolution {
    protected InputReader in;
    protected OutputWriter out;
    protected OutputWriter log;
    //protected boolean localMachine = System.getenv().containsKey("USER") && System.getenv("USER").equals("abra");
    protected boolean localMachine = Package.getPackage("chelper") != null;

    {
        if (localMachine) {
            log = new OutputWriter(new OutputStream() {
                @Override
                public void write(int b) throws IOException {
                    System.err.write(b);
                }
            });
        } else {
            log = new OutputWriter(new OutputStream() {
                @Override
                public void write(int b) throws IOException {
                }
            });
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        this.in = in;
        this.out = out;
        testStarts(testNumber);
        solve(testNumber);
        testEnds(testNumber);
        log.flush();
    }

    public abstract void solve(int testNumber);

    public void testStarts(int testNumber) {}

    public void testEnds(int testNumber) {}
}
