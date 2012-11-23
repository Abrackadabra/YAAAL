package chelper;

import abrackadabra.io.Reader;

import java.io.PrintWriter;

public class F2 {
    public void solve(int testNumber, Reader in, PrintWriter out) {
        int m = in.nextInt();
        int[] query = new int[m];
        for (int i = 0; i < m; i++)
            query[i] = in.nextInt();

        int n = in.nextInt();

        int[] string = new int[n];
        for (int i = 0; i < n; i++)
            string[i] = in.nextInt();

        if (n == 0) {
            out.println(0);
            return;
        }

        if (m == 1) {
            out.println(1);
            return;
        }

        if (n == 1) {
            out.println(0);
            return;
        }

        int[] a = new int[m - 1];
        for (int i = 0; i < m - 1; i++) {
            a[i] = query[i + 1] - query[i];
        }
        m--;
        query = a;

        int[] prefixFunction = new int[m];
        for (int i = 1; i < m; i++) {
            int j = prefixFunction[i - 1];
            while (j > 0 && query[i] != query[j])
                j = prefixFunction[j - 1];
            if (query[i] == query[j]) ++j;
            prefixFunction[i] = j;
        }

        if (n <= 1) {
            out.println(0);
            return;
        }

        a = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            a[i] = string[i + 1] - string[i];
        }
        n--;
        string = a;

        int prevValue = 0;

        for (int i = 0; i < n; i++) {
            int j = prevValue;
            while (j > 0 && string[i] != query[j])
                j = prefixFunction[j - 1];
            if (string[i] == query[j]) ++j;

            if (j == m) {
                out.println(1);
                return;
            }

            prevValue = j;
        }

        out.println(0);
    }
}
