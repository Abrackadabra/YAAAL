package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import java.util.Arrays;

public class K {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        boolean[][] a = new boolean[9][59];

        for (int i = 0; i < 9; i++) {
            String s = in.nextLine();
            for (int j = 0; j < 59; j++) {
                a[i][j] = s.charAt(j) == 'x';
            }
        }

        int[] l = new int[7];

        for (int i = 0; i < 10; i++) {
            int add = 6 * i;

            if (a[0][add + 1]) l[0]++;
            if (a[1][add + 0]) l[1]++;
            if (a[1][add + 4]) l[2]++;
            if (a[4][add + 1]) l[3]++;
            if (a[5][add + 0]) l[4]++;
            if (a[5][add + 4]) l[5]++;
            if (a[8][add + 1]) l[6]++;
        }

        for (int i : l) {
            if (i == 0) {
                out.print("-1 ");
                continue;
            }
            if (i == 10) {
                out.print("1 ");
                continue;
            }
            out.print("0 ");
        }
    }
}
