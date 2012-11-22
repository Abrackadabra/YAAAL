import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Created with IntelliJ IDEA.
 * User: Abrackadabra
 * Date: 30/08/12
 * Time: 14:16
 */

public class X {
    IOMode mode = IOMode.CONSOLE;
    String fileName = "";
    boolean testBased = false;

    void solve() throws IOException {
        int n = nextInt(), k = nextInt();
        if (k > n) {
            out.println(0);
            return;
        }
        if (n == k) {
            if (n == 1) {
                out.println(1);
                return;
            }

            double res = 0.0;

            if (n % 4 < 2)
                res = 1.0;

            res = res * 2.0;
            for (int i = 1; i <= n; i++)
                res /= i;
            out.println(res);
            return;
        }
        if (n == k + 1) {
            long ans = 2, add = 1;
            for (int i = 3; i <= n; i++) {
                if (i % 4 == 0) add += 2;
                if (i % 4 == 1) add += 1;
                if (i % 4 == 2) add += 0;
                if (i % 4 == 3) add += 1;
                ans += add;
            }
            con.println(ans);

            double res = ans * 2.0;
            for (int i = 1; i <= n; i++)
                res /= i;
            out.println(res);
            return;
        }
        if (n == k + 2) {
            double ans = (k + 1d) * (k + 2) / 2;
            ans *= ans;
            con.println(ans);

            double res = ans * 2.0;
            for (int i = 1; i <= n; i++)
                res /= i;
            out.println(res);
            return;
        }


        double ans = (k + 1d) * (k + 2) / 2;
        ans *= ans;
        con.println(ans);

        ans = Math.log(ans);



        for (int i = k + 2; i < n; i++) {
            ans += Math.log((i + 1d) * (i + 1));
            ans -= Math.log(i + 1 - k);
        }
        con.println(ans);

        ans += Math.log(2);
        for (int i = 1; i <= n; i++)
            ans -= Math.log(i);

        out.println(Math.exp(ans));
    }

    void preCalc() {

    }

    public static void main(String[] args) {
        debug = args.length > 0 && args[0].equals("Abra");
        new Thread(null, new Runnable() {
            public void run() {
                new X().run();
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
            for (int i = 0; i < 15; i++) {
                String s = tbr.readLine();
                if (s == null) break;
                con.println(s);
            }
            if (tbr.readLine() != null) con.println("...");
            con.println("Output:");
            tbr = new BufferedReader(new FileReader("output.txt"));
            for (int i = 0; i < 15; i++) {
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