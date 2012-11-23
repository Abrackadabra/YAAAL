package chelper;

import abrackadabra.io.Reader;

import java.io.PrintWriter;
import java.util.ArrayList;

public class cat {
    public void solve(int testNumber, Reader in, PrintWriter out) {
        int a = in.nextInt() * 60, b = in.nextInt() * 60;
        int n = in.nextInt();
        int total = 60 * 24;
        int[] q = new int[total];
        for (int i = 0; i < n; i++) {
            String s = in.nextString();
            int l = (s.charAt(0) - '0') * 10 * 60;
            l += (s.charAt(1) - '0') * 60;
            l += (s.charAt(3) - '0') * 10;
            l += (s.charAt(4) - '0');

            int r = (s.charAt(6) - '0') * 10 * 60;
            r += (s.charAt(7) - '0') * 60;
            r += (s.charAt(9) - '0') * 10;
            r += (s.charAt(10) - '0');

            for (int t = l; true; t = (t + 1) % total) {
                q[t] = 1;
                if (t == r) break;
            }
        }

        ArrayList<int[]> sleeps = new ArrayList<int[]>();

        for (int i = 0; i < total; i++) {
            if (q[i] == 0) {
                int l = i;
                int r = -1;
                int c = 0;
                for (int t = i; true; t = (t + 1) % total) {
                    if (q[t] != 0)
                        break;
                    c++;
                    r = t;
                }
                for (int t = (i - 1 + total) % total; true; t = (t - 1 + total) % total) {
                    if (q[t] != 0)
                        break;
                    c++;
                    l = t;
                }

                if (c >= a) {
                    for (int t = i; true; t = (t + 1) % total) {
                        if (q[t] != 0)
                            break;
                        q[t] = -1;
                    }
                    for (int t = (i - 1 + total) % total; true; t = (t - 1 + total) % total) {
                        if (q[t] != 0)
                            break;
                        q[t] = -1;
                    }

                    sleeps.add(new int[]{l, r});
                }
            }
        }

        if (sleeps.size() == 0) {
            out.println("No");
            return;
        }

        for (int i = 0; i < total; i++) {
            if (q[i] != -1) {
                int c = 0;
                for (int t = i; true; t = (t + 1) % total) {
                    if (q[t] == -1 || c >= total) break;
                    c++;
                }
                for (int t = (i - 1 + total) % total; true; t = (t - 1 + total) % total) {
                    if (q[t] == -1 || c >= total) break;
                    c++;
                }

                if (c > b) {
                    out.println("No");
                    return;
                }
            }
        }

        out.println("Yes");
        out.println(sleeps.size());
        for (int[] sleep : sleeps) {
            String s = "";
            int l = sleep[0];
            int r = sleep[1];
            s += (char) (l / 60 / 10 + '0');
            s += (char) (l / 60 % 10 + '0');
            s += ':';
            s += (char) (l % 60 / 10 + '0');
            s += (char) (l % 10 + '0');
            s += '-';
            s += (char) (r / 60 / 10 + '0');
            s += (char) (r / 60 % 10 + '0');
            s += ':';
            s += (char) (r % 60 / 10 + '0');
            s += (char) (r % 10 + '0');

            out.println(s);
        }
    }
}
