package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class TaskB {
    long MOD = 1000000007;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long ans = 1;

        int n = in.nextInt();
        int k = in.nextInt();

        for (int i = 0; i < n - k; i++) {
            ans = (ans * (n - k)) % MOD;
        }

        ans = (ans * (k)) % MOD;

        int total = 1;
        for (int i = 0; i < k - 1; i++) {
            total *= k;
        }

        boolean[] ok = new boolean[k];

        Deque<Integer> deque = new LinkedList<Integer>();

        int[] a = new int[k];

        int mult = 0;

        for (int i = 0; i < total; i++) {
            int c = i;
            for (int j = 1; j < k; j++) {
                a[j] = c % k;
                c /= k;
            }

            Arrays.fill(ok, false);
            ok[0] = true;

            boolean nice = true;

            for (int j = 1; j < k && nice; j++) {
                if (ok[j]) {
                    continue;
                }

                if (ok[a[j]]) {
                    ok[j] = true;
                    continue;
                }

                c = j;

                while (deque.size() < 10) {
                    deque.add(c);

                    c = a[c];

                    if (ok[c]) {
                        break;
                    }
                }

                if (deque.size() == 10) {
                    nice = false;
                    deque.clear();
                    break;
                }

                while (!deque.isEmpty()) {
                    ok[deque.poll()] = true;
                }
            }

            if (nice) {
                mult++;
            }
        }

        ans = (ans * mult) % MOD;

        out.println(ans);
    }
}
