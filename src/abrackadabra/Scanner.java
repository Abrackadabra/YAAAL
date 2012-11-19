package abrackadabra;

import java.io.*;
import java.util.*;

public class Scanner {
    BufferedReader br;
    StringTokenizer in;

    public Scanner(InputStream inputStream) {
        br = new BufferedReader(new InputStreamReader(inputStream));
    }

    public String nextString() {
        return hasMoreTokens() ? in.nextToken() : null;
    }

    public String nextLine() {
        try {
            in = null; // riad legacy
            return br.readLine();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    boolean hasMoreTokens() {
        while (in == null || !in.hasMoreTokens()) {
            String s = nextLine();
            if (s == null) return false;
            in = new StringTokenizer(s);
        }
        return true;
    }

    public int nextInt() {
        return Integer.parseInt(nextString());
    }

    public String next(){
        return nextString();
    }

    public long nextLong() {
        return Long.parseLong(nextString());
    }

    public double nextDouble() {
        return Double.parseDouble(nextString());
    }

    public int[] nextIntArray(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = nextInt();
        }
        return a;
    }

    public long[] nextLongArray(int n) {
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = nextLong();
        }
        return a;
    }

    public double[] nextDoubleArray(int n) {
        double[] a = new double[n];
        for (int i = 0; i < n; i++) {
            a[i] = nextDouble();
        }
        return a;
    }

    public String[] nextStringArray(int n) {
        String[] a = new String[n];
        for (int i = 0; i < n; i++) {
            a[i] = nextString();
        }
        return a;
    }
}
