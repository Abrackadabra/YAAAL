package abrackadabra.math;

public class Matrix {
    int n, m;
    int[][] a;

    public Matrix(int n, int m, int[][] a) {
        this.n = n;
        this.m = m;
        this.a = a;
    }

    public Matrix multiply(Matrix b, int MOD) {
        if (m != b.n)
            throw new IllegalArgumentException();

        int[][] res = new int[n][b.m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < b.m; j++) {
                for (int k = 0; k < m; k++) {
                    res[i][j] = ((int) ((res[i][j] + 1L * a[i][k] * b.a[k][j]) % MOD));
                }
            }
        }
        return new Matrix(n, b.m, res);
    }
}
