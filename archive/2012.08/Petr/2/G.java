import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Created with IntelliJ IDEA.
 * User: Abrackadabra
 * Date: 25/08/12
 * Time: 13:51
 */

public class G {
    IOMode mode = IOMode.CONSOLE;
    String fileName = "";
    boolean testBased = false;

    HashSet<Integer> current = new HashSet<Integer>();

    HashMap<Integer, Character>[] graph;

    void send(int a, int b, int c) throws IOException {
        out.print("2 " + (a + 1) + " " + (b + 1) + " " + (c + 1) + "\n");
        out.flush();

        if (nextString().equals("E")) {
            System.exit(0);
        }
    }

    char ask(int a, int b) throws IOException {
        if (graph[a].containsKey(b))
            return graph[a].get(b);
        out.print("1 " + (a + 1) + " " + (b + 1) + "\n");
        out.flush();
        char c = nextString().charAt(0);
        graph[a].put(b, c);
        graph[b].put(a, c);
        return c;
    }

    void solve() throws IOException {
        int n = nextInt();
        graph = new HashMap[n];
        for (int i = 0; i < n; ++i) {
            graph[i] = new HashMap<Integer, Character>();
        }

        outer:
        for (int add = 0; add < n; ++add) {


            for (int cur1 : current)
                for (int cur2 : current) {
                    if (cur1 == cur2)
                        continue;
                    int t = ask(cur1, cur2);
                    if (t == ask(add, cur1) && t == ask(add, cur2)) {
                        send(add, cur1, cur2);

                        current.remove(cur1);
                        current.remove(cur2);

                        continue outer;
                    }

                }
            current.add(add);
        }


    }


    void preCalc() {

    }

    public static void main(String[] args) {
        debug = false;// args.length > 0 && args[0].equals("Abra");
        new Thread(null, new Runnable() {
            public void run() {
                new G().run();
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
