package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import java.util.*;

public class BeautifulStrings {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        out.printf("Case #%d: ", testNumber);

        String s = in.nextLine().toLowerCase();

        int[] a = new int[26];

        for (char c : s.toCharArray()) {
            if (Character.isLetter(c))
                a[c - 'a']++;
        }

        Arrays.sort(a);

        int res = 0;
        for (int i = 0; i < 26; i++) {
            res += (i + 1) * a[i];
        }

        out.println(res);
    }
}
