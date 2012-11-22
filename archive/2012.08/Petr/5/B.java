import java.util.*;
import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: Abrackadabra
 * Date: 29/08/12
 * Time: 12:28
 */

public class B {
    IOMode mode = IOMode.CONSOLE;
    String fileName = "";
    boolean testBased = false;
    int k;
    long ans;
    ArrayList<Integer>[] g;

    //HashMap<Integer, Integer>[] paths;

    HashMap<Integer, Integer> dfs(int v, int parent, int level) {
        HashMap<Integer, Integer> res = new HashMap<Integer, Integer>();
        for (int to : g[v]) {
            if (parent == to)
                continue;
            HashMap<Integer, Integer> childMap = dfs(to, v, level + 1);

            Integer delete = childMap.remove(k + level);
            ans += delete == null ? 0 : delete;

            if(childMap.size() > res.size()){
                HashMap<Integer, Integer> tmp = childMap;
                childMap = res;
                res = tmp;
            }



            for(Map.Entry<Integer, Integer> entry: childMap.entrySet()){
                if(res.containsKey(k + 2 * level - entry.getKey())) {
                    ans += 1L * entry.getValue() * res.get(k + 2 * level - entry.getKey());
                }


            }

            for(Map.Entry<Integer, Integer> entry: childMap.entrySet()){
                res.put(entry.getKey(), entry.getValue() + (res.containsKey(entry.getKey()) ? res.get(entry.getKey()): 0));
            }
        }

        res.put(level, 1);

        return res;

        /*con.println("vertex" + v);
        for(Map.Entry<Integer, Integer> entry: paths[v].entrySet()){
            con.println(entry);
        }
        con.println("ans " + ans[v]);*/
    }

    void solve() throws IOException {
        int n = nextInt();
        k = nextInt();
        g = new ArrayList[n];;
        for (int i = 0; i < n; ++i)
            g[i] = new ArrayList<Integer>();

        int root = new Random().nextInt(n);

        for (int i = 0; i < n - 1; ++i) {
            int a = nextInt() - 1;
            int b = nextInt() - 1;
            g[a].add(b);
            g[b].add(a);
        }

        dfs(root, -1, 42);

        out.print(ans);


    }

    void preCalc() {

    }

    public static void main(String[] args) {
        debug = args.length > 0 && args[0].equals("Abra");
        new Thread(null, new Runnable() {
            public void run() {
                new B().run();
            }
        }, "solution", 1L << 31).start();
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
