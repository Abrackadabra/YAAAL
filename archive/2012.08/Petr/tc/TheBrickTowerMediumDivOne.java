import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Created with IntelliJ IDEA.
 * User: Abrackadabra
 * Date: 01/09/12
 * Time: 20:30
 */

public class TheBrickTowerMediumDivOne {
    class Tower implements Comparable<Tower> {
        int number, height;

        Tower(int number, int height) {
            this.number = number;
            this.height = height;
        }

        public int compareTo(Tower o) {
            if (height != o.height) return height - o.height;
            return number - o.number;
        }

        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Tower tower = (Tower) o;

            if (height != tower.height) return false;
            if (number != tower.number) return false;

            return true;
        }

        
        public int hashCode() {
            int result = number;
            result = 31 * result + height;
            return result;
        }
    }

    int compare(int[] a, int[] b) {
        for (int i = 0; i < Math.min(a.length, b.length); i++) {
            if (a[i] < b[i]) return -1;
            if (a[i] > b[i]) return 1;
        }
        return 0;
    }

    public int[] find(int[] heights) {
        TreeSet<Tower> towers = new TreeSet<Tower>();
        int n = heights.length;
        for (int i = 0; i < n; i++) {
            towers.add(new Tower(i, heights[i]));
        }

        Tower min = new Tower(100, 100);
        for (Tower tower : towers) {
            if (tower.compareTo(min) < 0)
                min = tower;
        }

        ArrayList<Tower> towersByNumber = new ArrayList<Tower>();
        for (Tower tower : towers)
            if (!tower.equals(min))
                towersByNumber.add(tower);
        Collections.sort(towersByNumber, new Comparator<Tower>() {
            public int compare(Tower o1, Tower o2) {
                return o1.number - o2.number;
            }
        });

        int[] best = new int[n];
        Arrays.fill(best, 999999);

        for (int left = 0; left <= n - 1; left++) {
            int[] res = new int[n];
            int c = 0;
            int tbn = 0;
            ArrayList<Tower> t = new ArrayList<Tower>();
            for (int i = 0; i < left; i++) {
                t.add(towersByNumber.get(tbn++));
            }
            Collections.sort(t, new Comparator<Tower>() {
                
                public int compare(Tower o1, Tower o2) {
                    if (o2.height != o1.height) {
                        return o2.height - o1.height;
                    }
                    return o1.number - o2.number;
                }
            });
            for (Tower tower : t)
                res[c++] = tower.number;
            t.clear();

            res[c++] = min.number;

            for (int i = 0; i < n - 1 - left; i++) {
                t.add(towersByNumber.get(tbn++));
            }
            Collections.sort(t, new Comparator<Tower>() {
                
                public int compare(Tower o1, Tower o2) {
                    if (o1.height != o2.height) {
                        return o1.height - o2.height;
                    }
                    return o1.number - o2.number;
                }
            });
            for (Tower tower : t)
                res[c++] = tower.number;

            if (compare(res, best) < 0) best = res;
        }

        return best;
    }

    IOMode mode = IOMode.CONSOLE;
    String fileName = "";
    boolean testBased = false;

    void solve() throws IOException {
        out.println(Arrays.toString(find(new int[]{4, 7, 5})));
        out.println(Arrays.toString(find(new int[]{4, 4, 4, 4, 4, 4, 4})));
        out.println(Arrays.toString(find(new int[]{2, 3, 3, 2})));
        out.println(Arrays.toString(find(new int[]{13, 32, 38, 25, 43, 47, 6})));
        out.println(Arrays.toString(find(new int[]{3, 2, 3, 2})));
    }

    void preCalc() {

    }

    public static void main(String[] args) {
        debug = args.length > 0 && args[0].equals("Abra");
        new Thread(null, new Runnable() {
            public void run() {
                new TheBrickTowerMediumDivOne().run();
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
