package chelper;

import abrackadabra.io.Reader;
import java.io.PrintWriter;

public class RRECIPE {
	public void solve(int testNumber, Reader in, PrintWriter out) {
        String s = in.nextString();
        int n = s.length();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = s.charAt(i) == '?' ? -1 : (s.charAt(i) - 'a');
        }
        int ans = 1;
        int q = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] == -1) {
                q++;
                continue;
            }
            int opp = n - 1 - i;
            if (a[opp] == -1) a[opp] = a[i];
            if (a[opp] != a[i]) {
                out.println(0);
                return;
            }
        }
        q = (q + 1) / 2;
        for (int i = 0; i < q; i++) {
            ans = ((int) ((ans * 26L) % MOD));
        }
        out.println(ans);
	}

    int MOD = 100000009;
}
