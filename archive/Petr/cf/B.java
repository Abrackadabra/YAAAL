import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Created with IntelliJ IDEA.
 * User: Abrackadabra
 * Date: 31/08/12
 * Time: 19:32
 */

public class B {
    IOMode mode = IOMode.CONSOLE;
    String fileName = "";
    boolean testBased = false;

    class Node {
        int l, r, m;
        HashSet<Integer> set = new HashSet<Integer>();
        Node left, right;

        Node(int[] a, int l, int r) {
            this.l = l;
            this.r = r;
            m = (l + r) / 2;
            if (l == r) {
                set.add(a[l]);
                return;
            }
            left = new Node(a, l, m);
            right = new Node(a, m + 1, r);
        }
    }

    void solve() throws IOException {
        int n = nextInt(), m = nextInt();
        int[] a = new int[n];
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            a[i] = nextInt();
            if (!map.containsKey(a[i])) map.put(a[i], 0);
            map.put(a[i], map.get(a[i]) + 1);
        }
        HashSet<Integer> banned = new HashSet<Integer>();
        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            if (e.getValue() < e.getKey())
                banned.add(e.getKey());
        }
        HashSet<Integer> good = new HashSet<Integer>();
        ArrayList<int[]> goodList = new ArrayList<int[]>();
        ArrayList<Integer> goodVal = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            if (!banned.contains(a[i]))
                good.add(a[i]);
        }
        for (int i : good) {
            int[] q = new int[n];
            for (int j = 0; j < n; j++) {
                if (j != 0) q[j] = q[j - 1];
                if (a[j] == i) q[j]++;
            }
            goodList.add(q);
            goodVal.add(i);
        }
        for (int i = 0; i < m; i++) {
            int l = nextInt() - 1, r = nextInt() - 1;
            int res = 0;
            for (int j = 0; j < goodList.size(); j++) {
                int[] q = goodList.get(j);
                int val = goodVal.get(j);
                val -= q[r];
                if (l != 0) val += q[l - 1];
                if (val == 0) res++;
            }
            out.println(res);
        }
    }

    void preCalc() {

    }

    public static void main(String[] args) {
        debug = args.length > 0 && args[0].equals("Abra");
        new Thread(null, new Runnable() {
            public void run() {
                new B().run();
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
