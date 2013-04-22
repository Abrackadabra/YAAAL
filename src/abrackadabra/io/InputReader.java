package abrackadabra.io;

import java.io.*;
import java.nio.CharBuffer;
import java.util.*;

public class InputReader implements Iterable<String> {
    BufferedReader br;
    StringTokenizer in;

    public InputReader(String fileName) {
        try {
            br = new BufferedReader(new FileReader(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public InputReader(InputStream inputStream) {
        br = new BufferedReader(new InputStreamReader(inputStream));
    }

    String lastLine = null;

    boolean hasMoreTokens() {
        while (in == null || !in.hasMoreTokens()) {
            lastLine = nextLine();
            if (lastLine == null) {
                return false;
            }
            in = new StringTokenizer(lastLine);
        }
        return true;
    }

    public String nextString() {
        return hasMoreTokens() ? in.nextToken() : null;
    }

    public String nextLine() {
        boolean somethingLeft = in != null && in.hasMoreTokens();
        in = null;
        if (somethingLeft) {
            return lastLine;
        }
        try {
            return br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

    public String[] nextLineArray(int n) {
        String[] a = new String[n];
        for (int i = 0; i < n; i++) {
            a[i] = nextLine();
        }
        return a;
    }

    @Deprecated
    public String next() {
        return nextString();
    }

    public String readAll() {
        StringBuilder stringBuilder = new StringBuilder();
        int c;
        try {
            while ((c = br.read()) != -1) {
                stringBuilder.append((char) c);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return stringBuilder.toString();
    }

    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {
            @Override
            public boolean hasNext() {
                return hasMoreTokens();
            }

            @Override
            public String next() {
                return nextString();
            }

            @Override
            public void remove() {
                throw new RuntimeException();
            }
        };
    }
}
