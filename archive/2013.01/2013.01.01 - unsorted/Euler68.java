package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import java.util.*;

public class Euler68 {
    int n = 5;
    int[] a = new int[2 * n];
    boolean[] taken = new boolean[2 * n + 1];

	public void solve(int testNumber, InputReader in, OutputWriter out) {
        go(0);
	}

    int sum = 0;

    void go(int i) {
        if (i > n) {
            int thatSum = a[i - 1] + a[i - n - 1] + a[(i - n) % n];
            if (i == n + 1) {
                sum = thatSum;
            } else {
                if (sum != thatSum) {
                    return;
                }
            }
        }

        if (i == 2 * n) {
            System.out.println(toString() + " " + sum + " " + Arrays.toString(a));
            return;
        }

        for (a[i] = 1; a[i] <= 2 * n; a[i]++) {
            if (!taken[a[i]]) {
                taken[a[i]] = true;
                go(i + 1);
                taken[a[i]] = false;
            }
        }
    }

    @Override
    public String toString() {
        int min = Integer.MAX_VALUE;
        int mini = -1;
        for (int i = 0; i < n; i++) {
            if (min > a[i + n]) {
                min = a[i + n];
                mini = i;
            }
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < n; i++) {
            int t = (mini + i) % n;
            stringBuilder.append(a[t + n]);
            stringBuilder.append(a[t]);
            stringBuilder.append(a[(t + 1) % n]);
        }

        return stringBuilder.toString();
    }
}
