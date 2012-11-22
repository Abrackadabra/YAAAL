import java.util.*;
import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: Abrackadabra
 * Date: 26/08/12
 * Time: 11:07
 */

public class F {
    IOMode mode = IOMode.INOUT;
    String fileName = "subsorting";
    boolean testBased = false;

    class Node {
        int[] a;
        int l, r, m;
        Node left, right;

        Node(int[] a, int l, int r) {
            this.l = l;
            this.r = r;
            m = (this.l + this.r) / 2;
            if (l == r) {
                this.a = new int[]{a[l]};
                return;
            }
            left = new Node(a, l, m);
            right = new Node(a, m + 1, r);
            this.a = new int[r - l + 1];
            int i = 0, j = 0, c = 0;
            while (i < left.a.length || j < right.a.length) {
                if (j >= right.a.length || (i < left.a.length && left.a[i] <= right.a[j])) {
                    this.a[c++] = left.a[i++];
                } else {
                    this.a[c++] = right.a[j++];
                }
            }
        }

        int countLowerThanFrom(int x, int from) {
            if (from <= this.l) {
                int l = -1, r = a.length;
                while (r - l > 1) {
                    int m = (l + r) / 2;
                    if (a[m] >= x) {
                        r = m;
                    } else {
                        l = m;
                    }
                }
                return r;
            } else {
                if (l == r) return 0;
                if (from > m) return right.countLowerThanFrom(x, from);
                return left.countLowerThanFrom(x, from) + right.countLowerThanFrom(x, from);
            }
        }

        int getKthLowerThanFrom(int k, int x, int from) {
            if (l == r) {
                if (k == 1 && a[0] < x) return l;
                return -1;
            }
            int lowerLeft = left.countLowerThanFrom(x, from);
            if (lowerLeft >= k) {
                return left.getKthLowerThanFrom(k, x, from);
            }
            return right.getKthLowerThanFrom(k - lowerLeft, x, from);
        }

        int countHigherThanTo(int x, int to) {
            if (to >= this.r) {
                int l = -1, r = a.length;
                while (r - l > 1) {
                    int m = (l + r) / 2;
                    if (a[m] >= x) {
                        r = m;
                    } else {
                        l = m;
                    }
                }
                return a.length - r;
            } else {
                if (l == r) return 0;
                if (to <= m) return left.countHigherThanTo(x, to);
                return left.countHigherThanTo(x, to) + right.countHigherThanTo(x, to);
            }
        }

        int getKthHigherThanTo(int k, int x, int to) {
            if (l == r) {
                if (k == 1 && a[0] > x) return l;
                return -1;
            }
            int higherRight = right.countHigherThanTo(x, to);
            if (higherRight >= k) {
                return right.getKthHigherThanTo(k, x, to);
            }
            return left.getKthHigherThanTo(k - higherRight, x, to);
        }
    }

    void solve() throws IOException {
        int n = nextInt(), m = nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = nextInt();

        Node root = new Node(a, 0, n - 1);

        for (int i = 0; i < m; i++) {
            int ai = nextInt() - 1, bi = nextInt() - 1;
            if (ai < bi) {
                int k = bi - ai;
                int q = root.getKthLowerThanFrom(k, a[ai], ai + 1);
                if (q == -1) {
                    out.println(-1);
                } else {
                    out.println((ai + 1) + " " + (q + 1));
                }
            } else {
                int k = ai - bi;
                int q = root.getKthHigherThanTo(k, a[ai], ai - 1);
                if (q == -1) {
                    out.println(-1);
                } else {
                    out.println((q + 1) + " " + (ai + 1));
                }
            }
        }
    }

    void preCalc() {

    }

    public static void main(String[] args) {
        debug = args.length > 0 && args[0].equals("Abra");
        new Thread(null, new Runnable() {
            public void run() {
                new F().run();
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
