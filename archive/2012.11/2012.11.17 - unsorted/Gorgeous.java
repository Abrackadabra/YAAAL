package chelper;

import abrackadabra.io.Reader;

import java.io.PrintWriter;

public class Gorgeous {
    int n;

    public void solve(int testNumber, Reader in, PrintWriter out) {
        String[] res = new String[10];

        res[1] = "3 ";
        res[2] = "12 15 ";
        res[3] = "102 105 108 ";
        res[4] = "1002 1503 2004 3006 ";
        res[5] = "10008 10014 10017 10020 10026 ";
        res[6] = "100008 100020 100026 100032 100035 100044 ";
        res[7] = "1000080 1000125 1000170 1000188 1000200 1000215 1000350 ";
        res[8] = "10001610 10001712 10001880 10001916 10002069 10002120 10002528 10003140 ";
        res[9] = "100060800 100060920 100061136 100061280 100061325 100061568 100061640 100062000 100064160 ";

        out.println(res[in.nextInt()]);

        for (n = 1; n <= 9; n++) {
            //calc();
        }
    }

    int[] a = new int[9];

    int l, r;

    void calc() {
        ok = false;
        l = 1;
        r = 10;
        for (int i = 1; i < n; i++) {
            l *= 10;
            r *= 10;
        }
        for (int i = l; i < r; i++) {
            go(0, i);
        }
    }

    boolean ok = false;

    void go(int i, int val) {
        if (ok) return;
        if (val % 3 != 0)
            return;
        a[i] = val;
        for (int j = 0; j < i; j++) {
            if ((a[i] + a[j]) % (a[i] - a[j]) != 0) return;
        }
        if (i == n - 1) {
            System.out.print("res[" + n + "] = \"");
            for (int j = 0; j < n; j++) {
                System.out.print(a[j] + " ");
            }
            System.out.println("\";");
            ok = true;
            return;
        }
        val++;
        for (int c = 0; val < r; ++val, ++c) {
            go(i + 1, val);
        }
    }
}
