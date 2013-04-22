package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import java.util.Deque;
import java.util.LinkedList;

public class C {
    int n, m;
    int[][] a;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        n = in.nextInt();
        m = in.nextInt();
        a = new int[n][m];

        for (int i = 0; i < n; i++) {
            String s = in.nextLine();
            for (int j = 0; j < m; j++) {
                if (s.charAt(j) != '.') {
                    a[i][j] = s.charAt(j) - 'a' + 1;
                }
            }
        }

        int l = -1, r = n + m;

        while (r - l > 1) {
            int m = (l + r) / 2;
            if (calc(m)) {
                r = m;
            } else {
                l = m;
            }
        }

        calc(r);
        out.println(r);
        out.println(path);
    }

    String path = "";

    boolean calc(int max) {
        int[][] dpCur = new int[n][m];
        int[][] prev = new int[n][m];

        if (a[0][0] != 0) {
            dpCur[0][0] = max == 0 ? Integer.MAX_VALUE / 2 : 1;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i != 0 || j != 0) {
                    dpCur[i][j] = Integer.MAX_VALUE / 2;
                }

                if (i > 0) {
                    int cur;
                    if (a[i][j] == 0 || a[i][j] != a[i - 1][j]) {
                        if (a[i][j] != 0) {
                            cur = 1;
                        } else {
                            cur = 0;
                        }
                    } else {
                        cur = dpCur[i - 1][j] + 1;
                    }

                    if (cur <= max && cur < dpCur[i][j] && dpCur[i - 1][j] != Integer.MAX_VALUE / 2) {
                        dpCur[i][j] = cur;
                        prev[i][j] = (i - 1) * m + j;
                    }
                }

                if (j > 0) {
                    int cur;
                    if (a[i][j] == 0 || a[i][j] != a[i][j - 1]) {
                        if (a[i][j] != 0) {
                            cur = 1;
                        } else {
                            cur = 0;
                        }
                    } else {
                        cur = dpCur[i][j - 1] + 1;
                    }

                    if (cur <= max && cur < dpCur[i][j] && dpCur[i][j - 1] != Integer.MAX_VALUE / 2) {
                        dpCur[i][j] = cur;
                        prev[i][j] = i * m + j - 1;
                    }
                }
            }
        }

        Deque<Integer> deque = new LinkedList<Integer>();

        int c = (n - 1) * m + m - 1;
        while (true) {
            deque.addFirst(c);
            if (c == 0) {
                break;
            }
            c = prev[c / m][c % m];
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (int i : deque) {
            stringBuilder.append((i / m + 1) + " " + (i % m + 1) + "\n");
        }

        path = stringBuilder.toString();

        return dpCur[n - 1][m - 1] != Integer.MAX_VALUE / 2;
    }
}
