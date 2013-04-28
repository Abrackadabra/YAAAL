package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;
import abrackadabra.math.AMath;

import java.util.*;

public class F {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int[] have = in.nextIntArray(3);
        int n = in.nextInt();
        int[] a = new int[15];
        for (int i : in.nextIntArray(n)) {
            a[i]++;
        }
        for (int i = 0; i <= 14; i++) {
            a[i]--;
            for (int j = i; j <= 14; j++) {
                a[j]--;
                for (int k = j; k <= 14; k++) {
                    a[k]--;

                    if ((i != 0 && a[i] < 0) ||
                        (j != 0 && a[j] < 0) ||
                        (k != 0 && a[k] < 0)) {
                        a[k]++;
                        continue;
                    }

                    if (i == 0 && j == 0 && k == 0) {
                        estimate(3, have);
                        a[k]++;
                        continue;
                    }

                    if (i == 0 && j == 0) {
                        estimate(2, k, have[0], have[1]);
                        estimate(2, k, have[0], have[2]);
                        estimate(2, k, have[1], have[2]);
                        a[k]++;
                        continue;
                    }

                    if (i == 0) {
                        estimate(1, j, k, have[0]);
                        estimate(1, j, k, have[1]);
                        estimate(1, j, k, have[2]);
                        a[k]++;
                        continue;
                    }

                    estimate(0, i, j, k);

                    a[k]++;
                }
                a[j]++;
            }
            a[i]++;
        }

        out.println(3 - max3);
        for (int i : bestA) {
            out.print(i + " ");
        }
    }

    int max1 = -1;
    int max2 = -1;
    int max3 = -1;
    int[] bestA = null;

    void estimate(int original, int... a) {
        Arrays.sort(a);
        int t = a[2];
        if (a[0] == a[1] && a[1] == a[2]) {
            if (max1 < 3 || (max1 == 3 && t > max2) || (max1 == 3 && max2 == t && max3 < original)) {
                max1 = 3;
                max2 = t;
                max3 = original;
                bestA = a;
            }
        }
        if (a[0] == a[1] || a[0] == a[2] || a[1] == a[2]) {
            if (max1 < 2 || (max1 == 2 && t > max2) || (max1 == 2 && max2 == t && max3 < original)) {
                max1 = 2;
                max2 = t;
                max3 = original;
                bestA = a;
            }
        }
        if (max1 < 1 || (max1 == 1 && t > max2) || (max1 == 1 && max2 == t && max3 < original)) {
            max1 = 1;
            max2 = t;
            max3 = original;
            bestA = a;
        }
    }
}
