package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

public class H {
    int[] need = {
            6,
            2,
            5,
            5,
            4,
            5,
            6,
            3,
            7,
            6
    };

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        long min = Long.MAX_VALUE;

        for (int f = 0; f < 10000; f++) {
            String s = Integer.toString(f);
            if (f == 0) s = "";

            int left = n;
            for (char c : s.toCharArray()) {
                left -= need[c - '0'];
            }

            if (left % 7 != 0 || left < 0) continue;

            StringBuilder sb = new StringBuilder();
            sb.append(s);
            for (int i = 0; i < left / 7; i++)
                sb.append('8');
            long t = Long.parseLong(sb.toString());
            if (t < min) {
                min = t;
            }
        }

        out.print(min + " ");

        if (n % 2 == 1) {
            out.print("7");
            n -= 3;
        }
        for (int i = 0; i < n / 2; i++) {
            out.print("1");
        }
        out.println();
    }
}
