package chelper;

import abrackadabra.io.InputReader;

import java.io.PrintWriter;

public class TaskE {
    int m;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        long n = in.nextLong();
        m = in.nextInt();
        int k = in.nextInt();

        int[][] matrix = new int[m][m];
        for (int i = 0; i < m * m; i++)
            matrix[i / m][i % m] = 1;

        for (int i = 0; i < k; i++) {
            String s = in.nextString();
            int a = s.charAt(0);
            if (a >= 'a')
                a = a - 'a';
            else
                a = a - 'A' + 26;
            int b = s.charAt(1);
            if (b >= 'a')
                b = b - 'a';
            else
                b = b - 'A' + 26;
            matrix[a][b] = 0;
        }

        int[][][] degs = new int[51][m][m];
        degs[0] = matrix;
        for (int i = 1; i < 51; i++) {
            degs[i] = multiply(degs[i - 1], degs[i - 1]);
        }

        int[][] res = new int[m][m];
        for (int i = 0; i < m; i++)
            res[i][i] = 1;

        n--;
        for (int i = 0; i < 51; i++) {
            if ((n & (1L << i)) > 0) {
                res = multiply(res, degs[i]);
            }
        }

        int sum = 0;
        for (int i = 0; i < m * m; i++) {
            sum = (sum + res[i / m][i % m]) % MOD;
        }

        out.println(sum);
    }

    int MOD = 1000000007;

    int[][] multiply(int[][] a, int[][] b) {
        int[][] res = new int[m][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < m; k++) {
                    res[i][j] = ((int) ((res[i][j] + 1L * a[i][k] * b[k][j]) % MOD));
                }
            }
        }
        return res;
    }
}
