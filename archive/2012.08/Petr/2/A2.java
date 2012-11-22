import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Created with IntelliJ IDEA.
 * User: Abrackadabra
 * Date: 25/08/12
 * Time: 12:20
 */

public class A2 {
    IOMode mode = IOMode.CONSOLE;
    String fileName = "";
    boolean testBased = false;

    void solve() throws IOException {
        int n = nextInt();
        int m = nextInt();
        int t = nextInt();
        int s = (t + 1) * n;

        double max[][] = new double[n][m + 1]; //i-th max, <=



        for(int v = 1; v <= m; ++v){
            double sum = 0;
            double p = v / (double)(m);
            double curVal = Math.pow(p, s);
            for(int i = 0; i < n; ++i){
                sum += curVal;
                max[i][v] = sum;
                curVal *= (1-p)/p * (s - i)/ (i + 1);
            }
        }



        double ansSum = 0;

        for(int i = 0;i < n; ++i){
            for(int j = 1; j <= m;++j){
                ansSum += (max[i][j] - max[i][j-1]) * j;
            }
        }

        ansSum /= n;

        out.print(ansSum);
    }

    void preCalc() {

    }

    public static void main(String[] args) {
        debug = args.length > 0 && args[0].equals("Abra");
        new Thread(null, new Runnable() {
            public void run() {
                new A2().run();
            }
        }, "solution", 1 << 31).start();
    }

    void run() {
        try {
            init();

            con.println("Start");
            long startTime = System.nanoTime();
            long startTotalMemory = Runtime.getRuntime().totalMemory();
            long startFreeMemory = Runtime.getRuntime().freeMemory();

            preCalc();
            int tests = testBased ? nextInt() : 1;
            for (testCounter = 1; testCounter <= tests; testCounter++)
                solve();
            out.flush();

            long finishTime = System.nanoTime();
            long finishTotalMemory = Runtime.getRuntime().totalMemory();
            long finishFreeMemory = Runtime.getRuntime().freeMemory();
            con.println("\nFinish");
            con.format("Time:   %f sec\n", (finishTime - startTime) / 1e9);
            con.format("Memory: %d bytes\n", (finishTotalMemory - finishFreeMemory - (startTotalMemory - startFreeMemory)));
            if (!debug) return;
            con.println("Input:");
            BufferedReader tbr = new BufferedReader(new FileReader("input.txt"));
            for (int i = 0; i < 5; i++) {
                String s = tbr.readLine();
                if (s == null) break;
                con.println(s);
            }
            if (tbr.readLine() != null) con.println("...");
            con.println("Output:");
            tbr = new BufferedReader(new FileReader("output.txt"));
            for (int i = 0; i < 5; i++) {
                String s = tbr.readLine();
                if (s == null) break;
                con.println(s);
            }
            if (tbr.readLine() != null) con.println("...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    BufferedReader br;
    StringTokenizer in;
    PrintWriter out;
    PrintStream con;
    static boolean debug;
    int testCounter;

    static enum IOMode {
        CONSOLE,
        TXT,
        INOUT,
        GENERATOR
    }

    void init() throws IOException {
        if (debug && mode == IOMode.GENERATOR) debug = false;
        if (fileName.length() > 0) mode = IOMode.INOUT;
        if (debug) {
            br = new BufferedReader(new FileReader("input.txt"));
            out = new PrintWriter(new FileWriter("output.txt"));
            con = System.out;
        } else {
            con = new PrintStream(new OutputStream() {
                public void write(int b) throws IOException {
                }
            });
            switch (mode) {
                case CONSOLE:
                    br = new BufferedReader(new InputStreamReader(System.in));
                    out = new PrintWriter(new OutputStreamWriter(System.out));
                    break;
                case TXT:
                    br = new BufferedReader(new FileReader("input.txt"));
                    out = new PrintWriter(new FileWriter("output.txt"));
                    break;
                case INOUT:
                    br = new BufferedReader(new FileReader(fileName + ".in"));
                    out = new PrintWriter(new FileWriter(fileName + ".out"));
                    break;
                case GENERATOR:
                    out = new PrintWriter(new FileWriter("input.txt"));
                    break;
            }
        }
    }

    boolean hasMoreTokens() throws IOException {
        while (in == null || !in.hasMoreTokens()) {
            String s = br.readLine();
            if (s == null) return false;
            in = new StringTokenizer(s);
        }
        return true;
    }

    String nextString() throws IOException {
        return hasMoreTokens() ? in.nextToken() : null;
    }

    int nextInt() throws IOException {
        return Integer.parseInt(nextString());
    }

    long nextLong() throws IOException {
        return Long.parseLong(nextString());
    }

    double nextDouble() throws IOException {
        return Double.parseDouble(nextString());
    }
}
