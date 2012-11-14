import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Created with IntelliJ IDEA.
 * User: Abrackadabra
 * Date: 02/09/12
 * Time: 14:03
 */

public class B {
    int MAXN = 1000001;
    int MAXM = MAXN / 10 + 1;
    int[] a = new int[MAXM];

    ArrayList<Integer>[] d = new ArrayList[MAXM];
    ArrayList<Integer>[] dists = new ArrayList[MAXM];

    ArrayList<int[]>[] t = new ArrayList[MAXN];

    void calll(int number, int del, int dist) {
        if (t[del].isEmpty()) {
            t[del].add(new int[]{dist, number});
            return;
        }

        if (dist < t[del].get(0)[0]) {
            t[del].add(0, new int[]{dist, number});
            if (t[del].size() == 3) {
                t[del].remove(2);
            }
            return;
        }

        if (t[del].size() == 1) {
            t[del].add(new int[]{dist, number});
            return;
        }

        if (dist < t[del].get(1)[0]) {
            t[del].remove(1);
            t[del].add(new int[]{dist, number});
        }
    }


    void filldist(int numberID, int index, ArrayList<Integer> prd, ArrayList<Integer> powers, int curdel, int curdist) {
        if (index == prd.size()) {
            d[numberID].add(curdel);
            dists[numberID].add(curdist);
            return;
        }
        for (int i = 0; i <= powers.get(index); ++i) {
            filldist(numberID, index + 1, prd, powers, curdel, curdist + powers.get(index) - i);
            curdel *= prd.get(index);
        }
    }

    int[] get(int number, int del, int dist) {

        if (t[del].size() < 2)
            return new int[]{10000000, 0};

        if (a[number] == a[t[del].get(0)[1]])
            return t[del].get(1);
        else
            return t[del].get(0);

    }

    void solve() throws IOException {
        int n = nextInt();
        int primes[] = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71,
                73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173,
                179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281,
                283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409,
                419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541,
                547, 557, 563, 569, 571, 577, 587, 593, 599, 601, 607, 613, 617, 619, 631, 641, 643, 647, 653, 659,
                661, 673, 677, 683, 691, 701, 709, 719, 727, 733, 739, 743, 751, 757, 761, 769, 773, 787, 797, 809,
                811, 821, 823, 827, 829, 839, 853, 857, 859, 863, 877, 881, 883, 887, 907, 911, 919, 929, 937, 941,
                947, 953, 967, 971, 977, 983, 991, 997};
        int primescnt = primes.length;
        for (int j = 0; j < n; ++j) {
            a[j] = nextInt();

            ArrayList<Integer> prd = new ArrayList<Integer>();
            ArrayList<Integer> powers = new ArrayList<Integer>();
            int c = a[j];
            for (int i = 0; i < primescnt; ++i) {
                if (c % primes[i] == 0) {
                    prd.add(primes[i]);
                    int cnt = 0;
                    while (c % primes[i] == 0) {
                        ++cnt;
                        c /= primes[i];
                    }
                    powers.add(cnt);
                }
            }
            if (c != 1) {
                prd.add(c);
                powers.add(1);
            }


            filldist(j, 0, prd, powers, 1, 0);
        }


        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < d[i].size(); ++j) {
                calll(i, d[i].get(j), dists[i].get(j));
            }
        }

        for (int i = 0; i < n; ++i) {
            int bestDist = 10000000;
            int prin = 1010101001;
            for (int j = 0; j < d[i].size(); ++j) {

                // out.println(a[i] + "  " + Arrays.toString(sqrt(i, d[i].sqrt(j), dists[i].sqrt(j))));
                int[] p = get(i, d[i].get(j), dists[i].get(j));
                if (dists[i].get(j) + p[0] < bestDist || dists[i].get(j) + p[0] == bestDist && p[1] < prin) {
                    bestDist = p[0] + dists[i].get(j);
                    prin = p[1];
                }
            }
            out.println(prin + 1);
        }

    }

    IOMode mode = IOMode.CONSOLE;
    String fileName = "";
    boolean testBased = false;

    void preCalc() {
        for (int i = 0; i < MAXN; i++) {
            if (i < MAXM) {
                d[i] = new ArrayList<Integer>();
                dists[i] = new ArrayList<Integer>();
            }
            t[i] = new ArrayList<int[]>();
        }
    }

    public static void main(String[] args) {
        debug = args.length > 0 && args[0].equals("Abra");
        new Thread(null, new Runnable() {
            public void run() {
                new B().run();
            }
        }, "solution", 1 << 26).start(); //64mb
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
