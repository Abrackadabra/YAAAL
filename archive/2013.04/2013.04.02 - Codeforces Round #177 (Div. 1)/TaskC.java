package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[] a = new int[n + 1];
        Arrays.fill(a, -1);

        while (n > 0) {
            int d = 1;
            while (d << 1 <= n) {
                d <<= 1;
            }

            for (int i = d; i <= n; i++) {
                int opp = d - 1 - (i - d);

                a[i] = d - 1 - (i - d);
                a[opp] = i;
            }

            n = d - 1 - (n - d + 1);
        }

        if (a[0] == -1) {
            a[0] = 0;
        }

        long sum = 0;

        StringBuilder ans = new StringBuilder();

        Set<Integer> set = new HashSet<Integer>();

        for (int i = 0; i < a.length; i++) {
            if ((i ^ a[i]) != i + a[i]) {
                throw new RuntimeException();
            }
            set.add(a[i]);

            sum += i ^ a[i];
            ans.append(a[i] + " ");
        }

        if (set.size() != a.length) {
            throw new RuntimeException();
        }

        out.println(sum);
        out.println(ans.toString());
    }

    void test(int n) {
        boolean[] taken = new boolean[n + 1];
        for (int i = n; i >= 0; i--) {
            boolean ok = false;
            for (int j = n; j >= 0; j--) {
                if (!taken[j] && (j ^ i) == (j + i)) {
                    taken[j] = true;
                    ok = true;
                    break;
                }
            }
            if (!ok) {
                System.out.println(n);
                throw new RuntimeException();
            }
        }
    }
}
