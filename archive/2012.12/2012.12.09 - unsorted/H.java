package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;
import abrackadabra.util.StringUtils;

import java.util.*;

public class H {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        String[] a = new String[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextString();
        }
        String whole = in.nextString();
        int l = whole.length();

        boolean[][] canInsert = new boolean[l][n];
        for (int i = 0; i < n; i++) {
            int[] pref = StringUtils.getPrefixFunction(a[i] + " " + whole);
            for (int j = 0; j < l; j++) {
                canInsert[j][i] = j + 2 * a[i].length() < pref.length && pref[j + 2 * a[i].length()] == a[i].length();
            }
        }

        HashSet<Integer>[] possibleMasks = new HashSet[l + 1];
        for (int i = 0; i < l + 1; i++) {
            possibleMasks[i] = new HashSet<Integer>();
        }
        possibleMasks[0].add(0);
        for (int i = 0; i < l; i++) {
            for (int mask : possibleMasks[i]) {
                for (int ins = 0; ins < n; ins++) {
                    if (((1 << ins) & mask) > 0) {
                        continue;
                    }
                    if (!canInsert[i][ins]) {
                        continue;
                    }
                    possibleMasks[i + a[ins].length()].add(mask | (1 << ins));
                }
            }
        }

        Stack<Integer> res = new Stack<Integer>();

        int t = l;
        int mask = (1 << n) - 1;
        while (t != 0) {
            for (int i = 0; i < n; i++) {
                if (((1 << i) & mask) == 0) {
                    continue;
                }
                if (!possibleMasks[t - a[i].length()].contains(mask - (1 << i))) {
                    continue;
                }
                t -= a[i].length();
                mask -= 1 << i;
                res.add(i + 1);
            }
        }

        while (!res.isEmpty()) {
            out.print(res.pop() + " ");
        }
    }
}
