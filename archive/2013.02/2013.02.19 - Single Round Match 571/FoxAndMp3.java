package chelper;

import java.util.Arrays;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

public class FoxAndMp3 {
    public String[] playList(int n) {
        SortedSet<String> set = new TreeSet<String>();

        int d = 1;
        for (int i = 1; i <= 9; i++) {
            for (int j = d; j - d < 100 && j <= n; j++) {
                set.add(j + ".mp3");
            }
            d *= 10;
        }

        int l = Math.min(50, n);
        String[] ans = new String[l];

        int c = 0;
        for (String s : set) {
            if (c >= l) break;
            ans[c] = s;
            c++;
        }

        return ans;
    }

    public String[] playList2(int n) {
        SortedSet<String> set = new TreeSet<String>();

        for (int i = 1; i <= n; i++) {
            set.add(i + ".mp3");
            if (set.size() > 50) {
                set.remove(set.last());
            }
        }

        int l = Math.min(50, n);
        String[] ans = new String[l];

        int c = 0;
        for (String s : set) {
            if (c >= l) break;
            ans[c] = s;
            c++;
        }

        return ans;
    }

    public FoxAndMp3() {
        Random random = new Random();

        while (true) {
            int n = random.nextInt(1000000) + 1;
            String[] a = playList(n);
            String[] b = playList2(n);
            if (!Arrays.equals(a, b)) {
                System.out.println(n);
                System.out.println(Arrays.toString(a));
                System.out.println(Arrays.toString(b));
            }
            break;
        }
    }
}
