package abrackadabra;

import java.io.*;
import java.util.*;

public class Scanner {
    BufferedReader br;
    StringTokenizer in;

    public Scanner(InputStream inputStream) {
        br = new BufferedReader(new InputStreamReader(inputStream));
    }

    public String next() {
        return hasMoreTokens() ? in.nextToken() : null;
    }

    public String readLine() {
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
            String s = readLine();
            if (s == null) return false;
            in = new StringTokenizer(s);
        }
        return true;
    }

    public String nextString() {
        return next();
    }

    public int nextInt() {
        return Integer.parseInt(nextString());
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
}
