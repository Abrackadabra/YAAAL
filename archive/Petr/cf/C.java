import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Created with IntelliJ IDEA.
 * User: Abrackadabra
 * Date: 31/08/12
 * Time: 20:16
 */

public class C {
    IOMode mode = IOMode.CONSOLE;
    String fileName = "";
    boolean testBased = false;

    void solve() throws IOException {
        int n = nextInt();
        int[] a = new int[n], aw = new int[n];
        int[] b = new int[n], bw = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = nextInt() - 1;
            aw[a[i]] = i;
        }
        for (int i = 0; i < n; i++) {
            b[i] = nextInt() - 1;
            bw[b[i]] = i;
        }

        int left = 1000000, lefts = 0;
        int right = 1000000;
        TreeMap<Integer, Integer> rightsMap = new TreeMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            int x = aw[i];
            int y = bw[i];
            int l = Math.abs(x - y);
            if (y <= x) {
                if (l < left) {
                    lefts = 0;
                    left = l;
                }
                if (left == l) lefts++;
            } else {
                if (l < right) right = l;
                if (!rightsMap.containsKey(l))
                    rightsMap.put(l, 0);
                rightsMap.put(l, rightsMap.get(l) + 1);
            }
        }
        for (int i = 0; i < n; i++) {
            out.println(Math.min(left, right));

            if (right == 0) {
                left = 0;
                lefts = rightsMap.get(rightsMap.firstKey());
                rightsMap.remove(rightsMap.firstKey());
            }

            int jumper = b[i];
            int l = aw[jumper];
            if (l == left) lefts--;
            if (lefts == 0) left = 1000000;

            left++;

            l = n - 1 - aw[jumper];
            int c = l + i + 1;
            if (!rightsMap.containsKey(c))
                rightsMap.put(c, 0);
            rightsMap.put(c, rightsMap.get(c) + 1);

            right = rightsMap.firstKey() - i - 1;
        }
    }

    void preCalc() {

    }

    public static void main(String[] args) {
        debug = args.length > 0 && args[0].equals("Abra");
        new Thread(null, new Runnable() {
            public void run() {
                new C().run();
            }
        }, "solution", 1 << 26).start();
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