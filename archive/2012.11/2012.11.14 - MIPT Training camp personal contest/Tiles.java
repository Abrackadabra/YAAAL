package chelper;

import abrackadabra.io.InputReader;

import java.io.PrintWriter;

public class Tiles {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int w = in.nextInt(), h = in.nextInt(), a = in.nextInt();

        int q = 0;
        q += (w / a) * (h / a);

        int x = w % a;
        int y = h % a;

        if (x != 0 && y != 0) {
            q += w / a + h / a;
            if (x + y > a)
                q++;
        }

        if (x != 0 && y == 0) {
            int t = h;
            h = w;
            w = t;
            x = w % a;
            y = h % a;
        }
        if (x == 0 && y != 0) {
            q += w / a;
        }

        out.println(q);
    }
}


/*
package chelper;

import abrackadabra.io.InputReader;

import java.io.PrintWriter;

public class Tiles {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int w = in.nextInt(), h = in.nextInt(), a = in.nextInt();

        int q = 0;
        q += (w / a) * (h / a);

        int x = w % a;
        int y = h % a;

        if (x != 0 && y != 0) {
            //q += 1;
            if (y > x) {
                int t = h;
                h = w;
                w = t;
                x = w % a;
                y = h % a;
            }
            int little = w / a, big = h / a;

            if (x <= a / 2 && y <= a / 2) {
                q += (w / a + h / a + 2) / 2;
            } else if (x > a / 2 && y > a / 2) {
                q += w / a + h / a + 1;
            } else if (x + y <= a) {
                int m = Math.min(little, big);
                q += m;
                little -= m;
                big -= m;
                if (little > 0) {
                    q += (little + 2) / 2;
                }
                if (big > 0) {
                    q += big;
                }
                if (little == 0 && big == 0)
                    q++;
            } else {
                q += big;
                q += (little + 2) / 2;
            }
        }

        if (x != 0 && y == 0) {
            int t = h;
            h = w;
            w = t;
            x = w % a;
            y = h % a;
        }
        if (x == 0 && y != 0) {
            if (y <= a / 2) {
                int t = w / a;
                q += t / 2;
                if (t % 2 == 1) {
                    q++;
                }
            } else {
                q += w / a;
            }
        }

        out.println(q);
    }
}

*/