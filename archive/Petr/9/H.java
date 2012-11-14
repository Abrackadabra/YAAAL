import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Created with IntelliJ IDEA.
 * User: Abrackadabra
 * Date: 03/09/12
 * Time: 09:44
 */

public class H {
    IOMode mode = IOMode.INOUT;
    String fileName = "hats";
    boolean testBased = false;

    int n, m;
    int[] k;
    int[] kHave;

    int[] hats;

    int worstRes = 1000000;

    void solve() throws IOException {
        n = nextInt();
        m = nextInt();
        k = new int[m];
        kHave = new int[m];
        for (int i = 0; i < m; i++)
            k[i] = nextInt();

        Arrays.sort(k);

        int best = -1;
        for (int i = 0; i < 20000; i++) {
            H h = new H();
            int t = h.ssolve(n, m, k);
            if (t > best) {
                best = t;
                map = h.map;
            }
        }

        out.println(best == 1000000 ? "!" : best);
        out.println("----");
        for (Map.Entry<String, Integer> e : map.entrySet()) {
            for (int i = 0; i < e.getKey().length(); i++)
                out.print((char) (e.getKey().charAt(i) + 1));
            out.println(" " + (e.getValue() + 1));
        }
        out.println("----");

        con.print(best == 1000000 ? "!" : best);

        if (true) return;

        hats = new int[n];
        ggo(0);

        out.print(worstRes);
        if (testBased) return;
        out.println();

        for (Map.Entry<String, Integer> e : map.entrySet()) {
            for (int i = 0; i < e.getKey().length(); i++)
                out.print((char) (e.getKey().charAt(i) + 1));
            out.println(" " + (e.getValue() + 1));
        }
    }

    int ssolve(int n, int m, int[] k) {
        this.n = n;
        this.m = m;
        this.k = k.clone();
        kHave = new int[m];
        worstRes = 1000000;
        c = new int[10];

        Arrays.sort(k);

        hats = new int[n];
        ggo(0);

        return worstRes;
    }

    int[] c = new int[10];

    Random random = new Random();

    void ggo(int x) {
        if (x == n) {
            int[] q = kHave.clone();
            Arrays.sort(q);
            if (!Arrays.equals(q, k)) return;


            int res = 0;

            for (int i = 0; i < n; i++) {
                int[] qHave = new int[m];

                String s = "";
                for (int j = 1; j <= n - 1; j++) {
                    s += hats[(i + j) % n];
                    qHave[hats[(i + j) % n]]++;
                }

                ArrayList<Integer> possibilities = new ArrayList<Integer>();
                for (int j = 0; j < m; j++) {
                    int[] w = qHave.clone();
                    w[j]++;
                    Arrays.sort(w);
                    if (Arrays.equals(k, w)) {
                        possibilities.add(j);
                    }
                }
                if (possibilities.size() == 0) throw new IllegalArgumentException();
                if (possibilities.size() == 1) {
                    map.put(s, possibilities.get(0));
                    res++;
                    continue;
                }
                int ans;
                if (map.containsKey(s)) {
                    ans = map.get(s);
                } else {
                    ans = possibilities.get(random.nextInt(possibilities.size()));
                    map.put(s, ans);
                }
                if (ans == hats[i]) res++;
            }
            if (res < worstRes) worstRes = res;

            return;
        }

        for (int i = 0; i < m; i++) {
            kHave[i]++;
            hats[x] = i;
            ggo(x + 1);
            kHave[i]--;
        }
    }

    HashMap<String, Integer> map = new HashMap<String, Integer>();

    void preCalc() {

    }

    public static void main(String[] args) {
        debug = args.length > 0 && args[0].equals("Abra");
        new Thread(null, new Runnable() {
            public void run() {
                new H().run();
            }
        }, "solution", 1 << 26).start(); //64mb
    }

    void run() {
        testBased = debug;
        try {
            init();

            con.println("Start");
            con.println("223442453523666666277475747218888864828681");
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