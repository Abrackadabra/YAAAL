package chelper;

import abrackadabra.io.Reader;

import java.io.PrintWriter;

public class Final {
    public void solve(int testNumber, Reader in, PrintWriter out) {
        int n = in.nextInt();
        int p = in.nextInt();
        int k = in.nextInt();
        int d = in.nextInt();
        boolean ok = false;
        int[] a = new int[n];
        int l = p;
        if (k == n) {
            if (d == 1) {
                for (int i = 0; i < n; i++) {
                    a[i] = p / n;
                    l -= a[i];
                }
                if (l == 0) ok = true;
            } else {
                for (int i = d - 2; i >= 0; i--) {
                    if (i == 0) {
                        if (l > a[i + 1]) {
                            ok = true;
                        }
                        a[i] = l;
                    } else {
                        a[i] = d - 1 - i;
                        l -= a[i];
                    }
                }
            }
        } else {
            if (d == 1) {
                int good = -1;
                for (int i = 0; i <= p; i++) {
                    if ((p - i) % k == 0 && i < (p - i) / k) {
                        good = i;
                    }
                }
                if (good != -1) ok = true;

                a[k] = good;
                l -= good;

                for (int i = 0; i < k; i++) {
                    a[i] = l / k;
                }
            } else {
                for (int i = d - 2; i >= 0; i--) {
                    if (i == 0) {
                        if (l > a[i + 1]) {
                            ok = true;
                        }
                        a[i] = l;
                    } else {
                        a[i] = d - 1 - i;
                        l -= a[i];
                    }
                }
            }
        }

        if (ok) {
            for (int i : a)
                out.println(i);
        } else {
            out.println("Wrong information");
        }
    }
}
