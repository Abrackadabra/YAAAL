import java.util.*;
import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: Abrackadabra
 * Date: 28/08/12
 * Time: 10:29
 */

public class A {
    IOMode mode = IOMode.CONSOLE;
    String fileName = "";
    boolean testBased = false;
    enum Next{
        NoRight,
        NoLeft,
        OK
    }

    int ans[][];
    Next next[][];
    String s;
    int ans(int l, int r){
        if(l>= r)
            return 0;
        if(next[l][r] != null)
            return ans[l][r];

        if(s.charAt(l) == s.charAt(r)){
            next[l][r] = Next.OK;
            ans[l][r] = 1 + ans(l + 1, r - 1);
            return ans[l][r];
        }

        int noL = ans(l + 1, r);
        int noR = ans(l , r - 1);

        if(noL >= noR){
            next[l][r] = Next.NoLeft;
            ans[l][r] = noL;
        }
        else {
            next[l][r] = Next.NoRight;
            ans[l][r] = noR;
        }
        return ans[l][r];
    }
    void solve() throws IOException {
        out.print(FBigInteger.valueOf(1000000009).nextProbablePrime().nextProbablePrime());
        if(true)
            return;
        int n = nextInt();
        ans = new int[n][n];
        next = new Next[n][n];
        s = nextString();
        out.println(ans(0, n - 1));
        int l = 0,  r = n- 1;
        while(l < r){
            if(next[l][r] == Next.OK){
                out.println((l + 1)+ " " +( r + 1));
                l++;
                r--;
            }
            else if(next[l][r] == Next.NoLeft){
                l++;
            }
            else if(next[l][r] == Next.NoRight){
                r--;
            }
        }

    }

    void preCalc() {

    }

    public static void main(String[] args) {
        debug = args.length > 0 && args[0].equals("Abra");
        new Thread(null, new Runnable() {
            public void run() {
                new A().run();
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
