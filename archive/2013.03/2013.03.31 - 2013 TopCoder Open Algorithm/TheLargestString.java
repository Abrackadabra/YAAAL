package chelper;

import java.util.Arrays;

public class TheLargestString {
    public String find(String s, String t) {
        int n = s.length();

        boolean[] take = new boolean[n];

        char[] a = s.toCharArray();
        char[] b = t.toCharArray();

        int l = 0, r = n - 1;

        while (true) {
            int max = -1;
            int maxi = -1;
            for (int i = l; i <= r; i++) {
                if (a[i] > max) {
                    max = a[i];
                    maxi = i;
                }
            }

            take[maxi] = true;

            if (maxi == r) {
                break;
            } else {
                l = maxi + 1;
            }
        }

        for (int i = 0; i < n; i++) {
            if (!take[i]) {
                continue;
            }
            int next = -1;
            for (int j = i + 1; j < n; j++) {
                if (take[j]) {
                    next = a[j];
                    break;
                }
            }
            if (next == -1) continue;

            if (b[i] > next) {
                for (int j = i + 1; j < n; j++) {
                    take[j] = false;
                }
            }
        }

        int first = -1;
        for (int i = 0; i < n; i++) {
            if (take[i]) {
                first = i;
                break;
            }
        }

        for (int i = first + 1; i < n; i++) {
            if (take[i] && a[i] < b[first]) {
                take[i] = false;
            }
        }

        for (int i = 0; i < n; i++) {
            if (!take[i]) {
                continue;
            }

            StringBuilder with = new StringBuilder();
            StringBuilder without = new StringBuilder();

            for (int j = 0; j < n; j++) {
                if (take[j]) {
                    with.append(a[j]);
                    if (j != i) {
                        without.append(a[j]);
                    }
                }
            }

            for (int j = 0; j < n; j++) {
                if (take[j]) {
                    with.append(b[j]);
                    if (j != i) {
                        without.append(b[j]);
                    }
                }
            }

            if (with.toString().compareTo(without.toString()) < 0) {
                take[i] = false;
            }
        }

        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < n; i++) {
            if (take[i]) {
                ans.append(a[i]);
            }
        }

        for (int i = 0; i < n; i++) {
            if (take[i]) {
                ans.append(b[i]);
            }
        }

        return ans.toString();
    }
}
