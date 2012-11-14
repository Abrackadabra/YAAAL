package chelper;

import java.util.ArrayList;
import java.util.Comparator;

public class LeftRightDigitsGame2 {
    public String minNumber(String digitsString, String lowerBoundString) {
        int n = digitsString.length();
        ArrayListComparator comparator = new ArrayListComparator();

        ArrayList<Integer> digits = new ArrayList<Integer>();
        ArrayList<Integer> lowerBound = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            digits.add(digitsString.charAt(i) - '0');
            lowerBound.add(lowerBoundString.charAt(i) - '0');
        }

        int right = n - 1;
        ArrayList<Integer> best = null;
        ArrayList<Integer> x = new ArrayList<Integer>();
        int c = 0;
        while (true) {
            if (right < 0) break;

            // ONE
            int min = 100;
            int mini = -1;
            for (int i = right; i >= 0; i--) {
                if (digits.get(i) > lowerBound.get(c)) {
                    if (digits.get(i) < min) {
                        min = digits.get(i);
                        mini = i;
                    }
                }
            }
            if (mini != -1) {
                ArrayList<Integer> X = clone(x);
                ArrayList<Integer> Digits = clone(digits);
                int Right = right;
                X.add(min);
                Digits.remove(mini);
                Right = Right - 1;
                while (true) {
                    if (Right < 0) break;
                    int Min = 100;
                    int Mini = -1;
                    for (int i = Right; i >= 0; i--) {
                        if (Digits.get(i) < Min) {
                            Min = Digits.get(i);
                            Mini = i;
                        }
                    }
                    if (Mini == -1) break;
                    X.add(Min);
                    Digits.remove(Mini);
                    Right--;
                }
                for (Integer q : Digits)
                    X.add(q);

                if (comparator.compare(X, lowerBound) >= 0) {
                    if (best == null || comparator.compare(X, best) < 0) {
                        best = X;
                    }
                }
            }

            // TWO

            mini = -1;
            for (int i = right; i >= 0; i--) {
                if (digits.get(i) == lowerBound.get(c)) {
                    mini = i;
                    break;
                }
            }
            if (mini != -1) {
                x.add(lowerBound.get(c++));
                digits.remove(mini);
                right = mini - 1;
            } else {
                break;
            }
        }

        for (Integer q : digits) {
            x.add(q);
        }
        if (comparator.compare(x, lowerBound) >= 0) {
            if (best == null || comparator.compare(x, best) < 0) {
                best = x;
            }
        }

        if (best == null) {
            return "";
        }

        String s = "";
        for (Integer q : best)
            s += q;
        return s;
    }

    ArrayList<Integer> clone(ArrayList<Integer> x) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        for (Integer q : x) {
            res.add(q);
        }
        return res;
    }

    class ArrayListComparator implements Comparator<ArrayList<Integer>> {
        @Override
        public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
            for (int i = 0; i < o1.size(); i++) {
                if (o1.get(i) < o2.get(i)) return -1;
                if (o1.get(i) > o2.get(i)) return 1;
            }
            return 0;
        }
    }
}
