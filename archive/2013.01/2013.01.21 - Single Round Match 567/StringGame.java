package chelper;

import java.util.*;

public class StringGame {
	public int[] getWinningStrings(String[] S) {
        int n = S.length;
        int[][] a = new int[n][26];
        for (int i = 0; i < n; i++) {
            for (char c : S[i].toCharArray()) {
                a[i][c - 'a']++;
            }
        }

        ArrayList<Integer> wins = new ArrayList<Integer>();

        for (int i = 0; i < n; i++) {
            boolean[] isAbleToWin = new boolean[n];

            for (int j = 0; j < 26; j++) {
                boolean ok = true;
                for (int k = 0; k < n && ok; k++) {
                    if (k == i) continue;
                    if (a[k][j] > a[i][j]) {
                        ok = false;
                    }
                }
                if (!ok) continue;

                for (int k = 0; k < n && ok; k++) {
                    if (k == i) continue;
                    if (a[k][j] < a[i][j]) {
                        isAbleToWin[k] = true;
                    }
                }
            }

            boolean ok = true;
            for (int k = 0; k < n && ok; k++) {
                if (k == i) continue;
                if (!isAbleToWin[k]) {
                    ok = false;
                }
            }
            if (ok) wins.add(i);
        }

        int[] ans = new int[wins.size()];
        for (int i = 0; i < wins.size(); i++) {
            ans[i] = wins.get(i);
        }

        return ans;
	}
}
