package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

public class CR115A {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.nextString();

        int n = s.length();

        int max = -1;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                int val = 0;

                String x = s.substring(0, i + 1);
                if (calc(x) >= 0) {
                    val += calc(x);
                } else {
                    val = Integer.MIN_VALUE;
                }

                x = s.substring(i + 1, j + 1);
                if (calc(x) >= 0) {
                    val += calc(x);
                } else {
                    val = Integer.MIN_VALUE;
                }

                x = s.substring(j + 1, n);
                if (calc(x) >= 0) {
                    val += calc(x);
                } else {
                    val = Integer.MIN_VALUE;
                }

                if (val > max) {
                    max = val;
                }
            }
        }

        out.println(max);
    }

    int calc(String s) {
        if ((s.charAt(0) == '0' && s.length() > 1) || s.length() > 7) return -1;
        int value = Integer.parseInt(s);
        if (value > 1000000) return -1;
        return value;
    }
}
