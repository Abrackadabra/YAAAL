package chelper;

import abrackadabra.math.AMath;

import java.util.*;

public class TheSquareRootDilemma {
    public int countPairs(int A, int B) {
        int max = 77779;
        ArrayList<Integer> primes = AMath.getPrimes(max);

        if (A > B) {
            int C = A;
            A = B;
            B = C;
        }

        Map<Integer, List<Integer>> primeDivisors = new HashMap<Integer, List<Integer>>();

        Map<Set<Integer>, Integer> map = new HashMap<Set<Integer>, Integer>();

        for (int prime : primes) {
            for (int j = prime; j <= max; j += prime) {
                if (!primeDivisors.containsKey(j)) {
                    primeDivisors.put(j, new ArrayList<Integer>());
                }
                primeDivisors.get(j).add(prime);
            }
        }

        Map<Integer, Set<Integer>> sets = new HashMap<Integer, Set<Integer>>();

        for (int i = 1; i <= max; i++) {
            int x = i;
            HashSet<Integer> goodDivs = new HashSet<Integer>();
            if (primeDivisors.containsKey(x)) {
                for (int div : primeDivisors.get(x)) {
                    int c = 0;
                    while (x % div == 0) {
                        x /= div;
                        c++;
                    }
                    if (c % 2 == 1) {
                        goodDivs.add(div);
                    }
                }
            }

            if (!map.containsKey(goodDivs)) {
                map.put(goodDivs, 0);
            }
            if (i <= B) {
                map.put(goodDivs, map.get(goodDivs) + 1);
            }

            sets.put(i, goodDivs);
        }

        int res = 0;

        for (int i = 1; i <= A; i++) {
            res += map.get(sets.get(i));
        }

        return res;
    }
}
