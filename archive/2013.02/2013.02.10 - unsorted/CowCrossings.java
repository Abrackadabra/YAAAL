package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CowCrossings {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        ArrayList<Cow> cows = new ArrayList<Cow>();

        for (int i = 0; i < n; i++) {
            cows.add(new Cow(i, in.nextInt(), in.nextInt()));
        }

        int[] bottom = new int[n];
        int[] bottomPosition = new int[n];

        int[] top = new int[n];
        int[] topPosition = new int[n];

        Collections.sort(cows, Cow.AComparator);

        for (int i = 0; i < n; i++) {
            bottom[i] = cows.get(i).id;
            bottomPosition[cows.get(i).id] = i;
        }

        Collections.sort(cows, Cow.BComparator);

        for (int i = 0; i < n; i++) {
            top[i] = cows.get(i).id;
            topPosition[cows.get(i).id] = i;
        }

        int l = 0;

        boolean[] bad = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (bad[i]) {
                continue;
            }

            if (topPosition[bottom[i]] != l) {
                for (int j = l; j <= topPosition[bottom[i]]; j++) {
                    bad[topPosition[j]] = true;
                }
                l = topPosition[bottom[i]] + 1;
            } else {
                l++;
            }
        }

        int answer = 0;
        for (boolean i : bad) {
            if (!i) {
                answer++;
            }
        }

        out.println(answer);
    }

    static class Cow {
        int id;

        int a, b;

        Cow(int id, int a, int b) {
            this.id = id;
            this.a = a;
            this.b = b;
        }

        static final Comparator<Cow> AComparator = new Comparator<Cow>() {
            @Override
            public int compare(Cow o1, Cow o2) {
                return o1.a - o2.a;
            }
        };

        static final Comparator<Cow> BComparator = new Comparator<Cow>() {
            @Override
            public int compare(Cow o1, Cow o2) {
                return o1.b - o2.b;
            }
        };
    }
}
