import java.util.*;
import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: Abrackadabra
 * Date: 02/09/12
 * Time: 23:33
 */

public class A {
    IOMode mode = IOMode.INOUT;
    String fileName = "art";
    boolean testBased = false;

    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

    HashSet<Integer> possible;
    HashSet<Integer> used;

    void solve() throws IOException {
        String[] ans = new String[71];
        ans[1] = "1\n1 ";
        ans[2] = "2\n1 2 ";
        ans[3] = "3\n1 2 3 ";
        ans[4] = "3\n1 2 3 ";
        ans[5] = "4\n1 3 4 5 ";
        ans[6] = "4\n1 2 3 6 ";
        ans[7] = "5\n1 2 5 6 7 ";
        ans[8] = "5\n1 2 3 6 8 ";
        ans[9] = "6\n1 2 3 6 8 9 ";
        ans[10] = "7\n1 2 3 6 8 9 10 ";
        ans[11] = "7\n1 2 3 6 8 9 10 ";
        ans[12] = "7\n1 2 3 6 8 9 10 ";
        ans[13] = "8\n1 2 3 6 8 9 10 13 ";
        ans[14] = "8\n1 2 3 6 8 9 10 13 ";
        ans[15] = "8\n1 2 3 6 8 9 10 13 ";
        ans[16] = "9\n1 2 3 8 9 10 13 15 16 ";
        ans[17] = "10\n1 2 3 8 9 10 13 15 16 17 ";
        ans[18] = "10\n1 2 3 8 9 10 13 15 16 17 ";
        ans[19] = "10\n1 2 3 8 9 10 13 15 16 17 ";
        ans[20] = "10\n1 2 3 6 9 10 11 17 19 20 ";
        ans[21] = "10\n1 2 3 6 9 10 11 17 19 20 ";
        ans[22] = "11\n1 2 3 10 11 12 15 17 18 19 22 ";
        ans[23] = "11\n1 2 3 6 10 13 15 17 20 22 23 ";
        ans[24] = "11\n1 2 3 6 9 11 13 18 19 22 24 ";
        ans[25] = "12\n1 2 5 8 10 12 17 18 21 23 24 25 ";
        ans[26] = "12\n1 2 3 6 9 11 13 18 19 22 24 26 ";
        ans[27] = "12\n1 2 3 6 9 11 13 18 19 22 24 26 ";
        ans[28] = "13\n1 2 3 12 13 14 19 20 21 24 26 27 28 ";
        ans[29] = "13\n1 2 3 6 8 9 13 17 19 24 26 28 29 ";
        ans[30] = "14\n1 3 4 5 14 15 16 21 22 23 26 28 29 30 ";
        ans[31] = "14\n1 3 4 5 14 15 16 21 22 23 26 28 29 30 ";
        ans[32] = "14\n1 2 3 6 8 15 17 19 24 25 28 30 31 32 ";
        ans[33] = "14\n1 2 3 6 8 11 19 23 24 25 28 30 32 33 ";
        ans[34] = "14\n1 2 3 6 8 10 11 19 23 25 30 32 33 34 ";
        ans[35] = "15\n1 2 3 6 9 18 19 20 26 27 28 31 33 34 35 ";
        ans[36] = "15\n1 2 3 6 8 17 19 24 25 26 29 31 32 33 36 ";
        ans[37] = "15\n1 2 3 6 8 9 17 19 24 26 27 31 32 36 37 ";
        ans[38] = "16\n1 2 5 6 7 10 21 22 23 29 30 31 34 36 37 38 ";
        ans[39] = "16\n1 2 3 6 8 10 13 21 25 29 30 31 34 36 38 39 ";
        ans[40] = "16\n1 2 3 6 8 9 17 19 24 27 29 31 32 36 37 40 ";
        ans[41] = "17\n1 2 3 6 9 10 17 19 22 27 29 30 34 35 38 40 41 ";
        ans[42] = "17\n1 2 3 6 8 10 21 23 25 30 31 32 35 37 38 39 42 ";
        ans[43] = "18\n1 3 4 5 8 12 19 21 23 24 29 32 35 36 37 40 42 43 ";
        ans[44] = "18\n1 2 5 7 8 9 12 21 24 27 29 31 36 37 38 41 43 44 ";
        ans[45] = "18\n1 2 5 6 7 18 20 21 22 25 28 32 36 37 38 41 43 45 ";
        ans[46] = "18\n1 2 3 6 9 11 19 22 24 26 32 33 36 37 38 41 43 46 ";
        ans[47] = "18\n1 2 3 6 8 9 10 13 29 30 31 38 39 40 43 45 46 47 ";
        ans[48] = "18\n1 2 3 6 8 9 10 13 17 29 32 34 36 41 42 43 46 48 ";
        ans[49] = "19\n1 2 3 6 8 9 13 24 26 28 29 34 38 40 41 42 45 48 49 ";
        ans[50] = "19\n1 2 3 6 8 9 10 13 29 30 31 38 39 40 43 45 46 47 50 ";
        ans[51] = "19\n1 2 3 6 8 9 10 13 17 29 32 34 41 42 43 46 48 50 51 ";
        ans[52] = "20\n1 2 6 7 8 11 23 25 26 27 36 37 38 43 44 45 48 50 51 52 ";
        ans[53] = "20\n1 2 3 6 8 9 24 26 27 28 37 38 39 44 45 46 49 51 52 53 ";
        ans[54] = "20\n1 2 3 6 8 9 10 13 17 30 32 34 40 41 44 46 48 51 53 54 ";
        ans[55] = "21\n1 2 3 6 8 9 13 26 28 29 30 39 40 41 46 47 48 51 53 54 55 ";
        ans[56] = "21\n1 2 3 6 8 9 10 13 29 30 31 40 41 42 47 48 49 52 54 55 56 ";
        ans[57] = "21\n1 2 3 6 8 9 10 13 29 30 31 40 41 42 47 48 49 52 54 55 56 ";
        ans[58] = "21\n1 2 3 6 8 9 10 13 29 30 31 34 39 41 46 47 48 51 53 54 58 ";
        ans[59] = "21\n1 2 3 6 8 9 10 13 17 29 30 34 38 40 43 50 51 55 57 58 59 ";
        ans[60] = "22\n1 2 3 6 8 9 10 13 29 30 31 34 36 43 45 47 52 53 56 58 59 60 ";
        ans[61] = "22\n1 2 3 6 8 9 10 13 29 30 31 34 36 43 45 47 52 53 56 58 59 60 ";
        ans[62] = "22\n1 2 3 6 8 9 10 13 17 29 30 34 38 41 43 50 51 52 57 58 59 62 ";
        ans[63] = "23\n1 2 7 8 9 12 14 15 16 33 35 36 37 47 48 49 54 55 56 59 61 62 63 ";
        ans[64] = "23\n1 2 3 6 8 9 10 27 29 30 31 34 41 43 45 46 51 54 57 58 59 62 64 ";
        ans[65] = "23\n1 2 3 6 8 9 10 13 29 32 34 36 39 46 47 48 51 54 55 56 62 64 65 ";
        ans[66] = "23\n1 2 3 6 8 9 10 13 17 29 30 34 36 38 43 51 52 55 58 59 60 65 66 ";
        ans[67] = "23\n1 2 3 6 8 9 10 13 17 29 30 34 36 38 43 51 52 55 58 59 60 65 66 ";
        ans[68] = "24\n1 2 3 6 8 9 10 13 29 30 31 34 38 43 47 49 50 51 57 58 62 63 66 68 ";
        ans[69] = "24\n1 2 3 6 8 9 10 13 17 32 36 38 40 41 46 53 54 55 60 61 64 65 68 69 ";
        ans[70] = "24\n1 2 3 6 8 9 10 13 17 32 36 38 40 41 46 53 54 55 60 61 64 65 68 69 ";

        out.println(ans[nextInt()]);



        if (true) return;
        map.put(0, 0);
        for (n = 1; n <= 70; n++) {
            possible = new HashSet<Integer>();
            used = new HashSet<Integer>();
            bestRes = -1;
            bestResSet = new HashSet<Integer>();

            for (int i = 0; i < n; i++)
                possible.add(i);

            go(0);

            TreeSet<Integer> res = new TreeSet<Integer>();

            for (int i : bestResSet)
                res.add(i + 1);

            String s = "ans[" + n + "] = \"";
            s += bestRes + "\\n";
            for (int i : res)
                s += i + " ";
            s += "\";";

            map.put(n, bestRes);


            out.println(s);
            out.flush();

            con.println(s);
        }
    }

    int n;

    int bestRes;
    HashSet<Integer> bestResSet;

    void go(int x) {
        if (used.size() + possible.size() <= bestRes) return;
        if (x != 0 && used.size() + map.get(n - x) <= bestRes) return;
        if (x == n) {
            if (used.size() > bestRes) {
                bestRes = used.size();
                bestResSet = (HashSet<Integer>) used.clone();
            }
            return;
        }

        HashSet<Integer> posClone = null;

        //1
        if (possible.contains(x)) {
            posClone = (HashSet<Integer>) possible.clone();
            possible.remove(x);
            for (int t : used) {
                int q = x + x - t + x - t;
                if (q < n)
                    possible.remove(q);
            }
            used.add(x);

            go(x + 1);

            used.remove(x);
        }

        if (posClone != null) possible = posClone;

        possible.remove(x);
        go(x + 1);
    }

    void preCalc() {

    }

    public static void main(String[] args) {
        debug = args.length > 0 && args[0].equals("Abra");
        new Thread(null, new Runnable() {
            public void run() {
                new A().run();
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
