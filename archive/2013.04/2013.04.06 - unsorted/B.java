package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class B {
    int n;
    int m;
    int[] colors;
    int[] nextLeft;
    int[] nextRight;
    int[] colorLeftBorder;
    int[] colorRightBorder;
    List<SortedSet<Integer>> sets = new ArrayList<SortedSet<Integer>>();
    long ans = 0;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        n = in.nextInt();
        m = in.nextInt();

        colors = new int[n];
        nextLeft = new int[n];
        nextRight = new int[n];

        colorLeftBorder = new int[m + 1];
        colorRightBorder = new int[m + 1];

        for (int i = 0; i <= m; i++) {
            sets.add(new TreeSet<Integer>());
        }

        for (int i = 0; i < n; i++) {
            colors[i] = 0;
            sets.get(0).add(i);

            nextLeft[i] = i - 1;

            nextRight[i] = i + 1;
        }

        colorLeftBorder[0] = 0;
        colorRightBorder[0] = n - 1;

        for (int i = 0; i < m; i++) {
            int l = in.nextInt() - 1;
            int r = in.nextInt() - 1;

            color(l, r, i + 1);
        }

        out.println(ans);
        //System.out.println("Total = " + total);
    }

    void color(int l, int r, int color) {
        if (colors[l] != 0) {
            int prevColor = colors[l];
            SortedSet<Integer> prev = sets.get(prevColor);

            SortedSet<Integer> toRepaint = prev.tailSet(l).headSet(r + 1);

            ans += toRepaint.size();

            if (toRepaint.size() < prev.size() / 2) {
                // simple
                List<Integer> toPaint = new ArrayList<Integer>();
                toPaint.addAll(toRepaint);

                for (int i : toRepaint) {
                    sets.get(color).add(i);
                    colors[i] = color;
                }

                if (prev.tailSet(l).size() > prev.headSet(r + 1).size()) {
                    List<Integer> left = new ArrayList<Integer>();
                    left.addAll(prev.headSet(r + 1));
                    prev = prev.tailSet(l);
                    prev.addAll(left);
                } else {
                    List<Integer> left = new ArrayList<Integer>();
                    left.addAll(prev.tailSet(l));
                    prev = prev.headSet(r + 1);
                    prev.addAll(left);
                }

                // not painted
                toPaint = new ArrayList<Integer>();

                toPaint.addAll(sets.get(0).tailSet(l).headSet(r + 1));

                for (int i : toPaint) {
                    sets.get(0).remove(i);
                    sets.get(color).add(i);
                    colors[i] = color;
                }

                ans += toPaint.size();
            } else {
                // not simple
                List<Integer> toPaint = new ArrayList<Integer>();
                toPaint.addAll(prev.headSet(l));
                toPaint.addAll(prev.tailSet(r + 1));

                for (int i : prev.headSet(l)) {
                    sets.get(color).add(i);
                    colors[i] = color;
                }

                for (int i : prev.tailSet(r + 1)) {
                    sets.get(color).add(i);
                    colors[i] = color;
                }

                prev = prev.headSet(l).tailSet(r + 1);

                // not painted
                toPaint = new ArrayList<Integer>();

                toPaint.addAll(sets.get(0).tailSet(l).headSet(r + 1));

                for (int i : toPaint) {
                    sets.get(0).remove(i);
                    sets.get(prevColor).add(i);
                    colors[i] = prevColor;
                }

                ans += toPaint.size();
            }
        } else {
            // not painted
            List<Integer> toPaint = new ArrayList<Integer>();

            toPaint.addAll(sets.get(0).tailSet(l).headSet(r + 1));

            for (int i : toPaint) {
                sets.get(0).remove(i);
                sets.get(color).add(i);
                colors[i] = color;
            }

            ans += toPaint.size();
        }
    }
}
