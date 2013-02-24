package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import java.util.*;

public class F {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int q = in.nextInt();

        boolean[] phonesOn = new boolean[n];
        Arrays.fill(phonesOn, true);

        Map<Integer, Collection<Integer>> turnOffs = new HashMap<Integer, Collection<Integer>>();

        for (int i = 0; i < m; i++) {
            int who = in.nextInt() - 1;
            int when = in.nextInt() + 1;

            if (!turnOffs.containsKey(when)) {
                turnOffs.put(when, new ArrayList<Integer>());
            }

            turnOffs.get(when).add(who);
        }

        int[] answers = new int[q];

        Map<Integer, Collection<int[]>> totalRequests = new HashMap<Integer, Collection<int[]>>();
        Map<Integer, Collection<int[]>> personalRequests = new HashMap<Integer, Collection<int[]>>();

        for (int i = 0; i < q; i++) {
            if (in.nextInt() == 1) {
                int when = in.nextInt();
                if (!totalRequests.containsKey(when)) {
                    totalRequests.put(when, new ArrayList<int[]>());
                }
                totalRequests.get(when).add(new int[]{i});
            } else {
                int when = in.nextInt();
                int who = in.nextInt() - 1;
                if (!personalRequests.containsKey(when)) {
                    personalRequests.put(when, new ArrayList<int[]>());
                }
                personalRequests.get(when).add(new int[]{who, i});
            }
        }

        int totalOn = n;

        for (int i = 0; i < 111111; i++) {
            if (turnOffs.containsKey(i)) {
                for (int j : turnOffs.get(i)) {
                    if (!phonesOn[j]) continue;
                    phonesOn[j] = false;
                    totalOn--;
                    if (j > 1 && phonesOn[j - 1] && !phonesOn[j - 2]) {
                        if (!turnOffs.containsKey(i + 1)) {
                            turnOffs.put(i + 1, new ArrayList<Integer>());
                        }
                        turnOffs.get(i + 1).add(j - 1);
                    }
                    if (j < n - 2 && phonesOn[j + 1] && !phonesOn[j + 2]) {
                        if (!turnOffs.containsKey(i + 1)) {
                            turnOffs.put(i + 1, new ArrayList<Integer>());
                        }
                        turnOffs.get(i + 1).add(j + 1);
                    }
                }
            }

            if (totalRequests.containsKey(i)) {
                for (int[] j : totalRequests.get(i)) {
                    answers[j[0]] = totalOn;
                }
            }

            if (personalRequests.containsKey(i)) {
                for (int[] j : personalRequests.get(i)) {
                    answers[j[1]] = phonesOn[j[0]] ? -1 : -2;
                }
            }
        }


        for (int i : answers) {
            out.println(i);
        }
    }
}
