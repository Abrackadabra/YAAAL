package chelper;

import abrackadabra.io.Reader;

import java.io.PrintWriter;

public class F {
    public void solve(int testNumber, Reader in, PrintWriter out) {
        int m = in.nextInt();
        int[] query = new int[m];
        for (int i = 0; i < m; i++)
            query[i] = in.nextInt();

        int[] prefixFunction = new int[m];
        for (int i = 1; i < m; i++) {
            int j = prefixFunction[i - 1];
            while (j > 0 && query[i] != query[j])
                j = prefixFunction[j - 1];
            if (query[i] == query[j]) ++j;
            prefixFunction[i] = j;
        }

        int n = in.nextInt();
        int[] string = new int[n];
        for (int i = 0; i < n; i++)
            string[i] = in.nextInt();

        for (int d = -100; d <= 100; d++) {
            int[] newQuery = new int[m];
            for (int i = 0; i < m; i++)
                newQuery[i] = query[i] + d;

            int prevValue = 0;

            for (int i = 0; i < n; i++) {
                int j = prevValue;
                while (j > 0 && string[i] != newQuery[j])
                    j = prefixFunction[j - 1];
                if (string[i] == newQuery[j]) ++j;

                if (j == m) {
                    out.println(1);
                    return;
                }

                prevValue = j;
            }
        }

        out.println(0);
    }
}
