package chelper;

import java.util.*;

public class EllysRoomAssignmentsDiv1 {
    public double getAverage(String[] ratings) {
        StringBuilder stringBuilder = new StringBuilder();

        for (String s : ratings) {
            stringBuilder.append(s);
        }

        StringTokenizer stringTokenizer = new StringTokenizer(stringBuilder.toString());

        ArrayList<Integer> a = new ArrayList<Integer>();

        while (stringTokenizer.hasMoreTokens()) {
            a.add(Integer.parseInt(stringTokenizer.nextToken()));
        }

        int my = a.get(0);

        Collections.sort(a, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 > o2) {
                    return -1;
                }
                if (o1 < o2) {
                    return 1;
                }
                return 0;
            }
        });

        int r = (a.size() + 19) / 20;

        double result = 0.0;

        Set<Integer> set = new HashSet<Integer>();

        for (int i : a) {
            set.add(i);

            if (set.size() == r) {
                if (set.contains(my)) {
                    result += my;
                } else {
                    for (int j : set) {
                        result += 1.0 / r * j;
                    }
                }

                set.clear();
            }
        }

        if (set.contains(my)) {
            return (result + my) / ((a.size() + r - 1) / r);
        }

        double prob = 1.0 * set.size() / r;

        double avg = 0.0;

        for (int i : set) {
            avg += 1.0 * i / set.size();
        }

        result = prob * (result + avg) / ((a.size() + r - 1) / r) +
                 (1 - prob) * result / (a.size() / r);

        return result;
    }
}
