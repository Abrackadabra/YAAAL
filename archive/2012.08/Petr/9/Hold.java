import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Created with IntelliJ IDEA.
 * User: Abrackadabra
 * Date: 03/09/12
 * Time: 09:44
 */

public class Hold {
    IOMode mode = IOMode.INOUT;
    String fileName = "hats";
    boolean testBased = false;

    int n, m;
    int[] k;
    int[] kHave;

    int[] hats;

    int worstRes = 1000000;

    void solve() throws IOException {
        worstRes = 1000000;
        n = nextInt();
        m = nextInt();
        k = new int[m];
        kHave = new int[m];
        for (int i = 0; i < m; i++)
            k[i] = nextInt();

        Arrays.sort(k);

        hats = new int[n];
        ggo(0);

        long startTime = System.nanoTime();
        int bestRes = -1;
        HashMap<String, Integer> bestMap = new HashMap<String, Integer>();
        while (System.nanoTime() - startTime < 2e9) {
            HashMap<String, Integer> qMap = new HashMap<String, Integer>();

            int res = 100000;

            for (TreeMap<String, ArrayList<Integer>> t : stranges) {
                HashMap<Integer, Integer> scores = new HashMap<Integer, Integer>();
                for (Map.Entry<String, ArrayList<Integer>> e : t.entrySet()) {
                    int n = -1;
                    if (qMap.containsKey(e.getKey())) {
                        n = qMap.get(e.getKey());
                    } else {
                        n = e.getValue().get(random.nextInt(e.getValue().size()));
                        qMap.put(e.getKey(), n);
                    }
                    for (int x : e.getValue())
                        if (!scores.containsKey(x))
                            scores.put(x, 0);
                    scores.put(n, scores.get(n) + 1);
                }
                int min = 10000;
                for (Integer q : scores.values())
                    min = Math.min(min, q);
                if (min < res) res = min;
                if (res < bestRes) break;
            }

            if (res > bestRes) {
                bestRes = res;
                bestMap = qMap;
            }
        }
        con.println(bestRes);

        for (Map.Entry<String, Integer> e : bestMap.entrySet()) {
            map.put(e.getKey(), e.getValue());
        }

        worstRes = 1000000;

        ggo(0);

        out.println(worstRes);

        for (Map.Entry<String, Integer> e : map.entrySet()) {
            for (int i = 0; i < e.getKey().length(); i++)
                out.print((char)(e.getKey().charAt(i) + 1));
            out.println(" " + (e.getValue() + 1));
        }
    }

    int c = 0;

    Random random = new Random();

    HashSet<TreeMap<String, ArrayList<Integer>>> strangesSet = new HashSet<TreeMap<String, ArrayList<Integer>>>();
    ArrayList<TreeMap<String, ArrayList<Integer>>> stranges = new ArrayList<TreeMap<String, ArrayList<Integer>>>();

    void ggo(int x) {
        if (x == n) {
            int[] q = kHave.clone();
            Arrays.sort(q);
            if (!Arrays.equals(q, k)) return;

            TreeMap<String, ArrayList<Integer>> strange = new TreeMap<String, ArrayList<Integer>>();

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
                if (map.containsKey(s)) {
                    int ans = map.get(s);
                    if (ans == hats[i]) res++;
                }

                if (!strange.containsKey(s))
                    strange.put(s, possibilities);
            }
            if (!strangesSet.contains(strange)) {
                strangesSet.add(strange);
                stranges.add(strange);
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
                new Hold().run();
            }
        }, "solution", 1 << 26).start(); //64mb
    }

    void run() {
        testBased = debug;

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