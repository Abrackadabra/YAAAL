import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Created with IntelliJ IDEA.
 * User: Abrackadabra
 * Date: 30/08/12
 * Time: 13:07
 */

public class K {
    IOMode mode = IOMode.CONSOLE;
    String fileName = "";
    boolean testBased = false;

    int[] a;
    int n;
    boolean[] q;
    ArrayList<int[]> permutations = new ArrayList<int[]>();

    void solve() throws IOException {
        n = 6;
        int k = 3;
        a = new int[n];
        q = new boolean[n];
        generate(0);

        TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
        int tot = 0;

        for (int[] a : permutations) {
            if (getKInversions(a, 2, Integer.MAX_VALUE, 0) % 2 == 0) {
                tot++;
                int thr = getKInversions(a, k, Integer.MAX_VALUE, 0);
                if (!map.containsKey(thr)) map.put(thr, 0);
                map.put(thr, map.get(thr) + 1);
            }
        }
        out.println(map);
        int sum = 0;
        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            sum += e.getKey() * e.getValue();
        }
        out.println(sum);
        out.println("Tot = " + tot);
    }

    void generate(int x) {
        if (x == n) {
            permutations.add(a.clone());
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!q[i]) {
                q[i] = true;
                a[x] = (i + 1);
                generate(x + 1);
                q[i] = false;
            }
        }
    }

    int getKInversions(int[] a, int k, int max, int from) {
        if (k == 0) return 1;
        if (n - from < k) return 0;
        int res = 0;
        for (int i = from; i < n; i++) {
            if (a[i] < max)
                res += getKInversions(a, k - 1, a[i], i + 1);
        }
        return res;
    }

    void preCalc() {

    }

    public static void main(String[] args) {
        debug = args.length > 0 && args[0].equals("Abra");
        new Thread(null, new Runnable() {
            public void run() {
                new K().run();
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
