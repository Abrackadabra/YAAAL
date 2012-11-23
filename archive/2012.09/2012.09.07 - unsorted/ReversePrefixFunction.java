package chelper;

import abrackadabra.io.Reader;

import java.io.PrintWriter;

public class ReversePrefixFunction {
    public void solve(int testNumber, Reader in, PrintWriter out) {
        try {
            while (true) {
                int n = in.nextInt();

                int m = in.nextInt();

                int[] a = new int[n];
                for (int i = 0; i < n; i++)
                    a[i] = in.nextInt();

                int[] string = new int[n];

                boolean ok = true;

                for (int i = 0; i < n; i++) {
                    ok = false;

                    for (string[i] = 0; string[i] < m; string[i]++) {
                        if (getPrefixFunction(a, string, i) == a[i]) {
                            ok = true;
                            break;
                        }
                    }

                    if (!ok) {
                        out.println("NO");
                        break;
                    }
                }
                if (!ok) continue;

                out.println("YES");
                for (int i = 0; i < n; i++) {
                    out.print((char) (string[i] + 'a'));
                }
                out.println();
            }
        } catch (Exception e) {
            return;
        }
    }

    int getPrefixFunction(int[] a, int[] string, int i) {
        if (i == 0) return 0;

        int j = a[i - 1];
        while (j > 0 && string[i] != string[j])
            j = a[j - 1];
        if (string[i] == string[j]) ++j;
        return j;
    }
}
