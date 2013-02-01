package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import java.util.*;

public class BalancedSmileys {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        out.printCase(testNumber);

        String s = in.nextLine();
        int n = s.length();

        List<Set<Integer>> dp = new ArrayList<Set<Integer>>();
        for (int i = 0; i < n + 1; i++) {
            dp.add(new HashSet<Integer>());
        }

        dp.get(0).add(0);

        for (int i = 0; i < n; i++) {
            for (int x : dp.get(i)) {
                if (s.charAt(i) == '(') {
                    dp.get(i + 1).add(x + 1);
                    continue;
                }
                if (s.charAt(i) == ')' && x > 0) {
                    dp.get(i + 1).add(x - 1);
                    continue;
                }
                if (i < n - 1 && s.charAt(i) == ':' && (s.charAt(i + 1) == '(' || s.charAt(i + 1) == ')')) {
                    dp.get(i + 2).add(x);
                }
                dp.get(i + 1).add(x);
            }
        }

        out.printYesNo(dp.get(n).contains(0), true);
    }
}
