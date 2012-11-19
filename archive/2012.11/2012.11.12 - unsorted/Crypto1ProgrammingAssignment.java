package chelper;

import abrackadabra.Scanner;

import java.io.*;
import java.util.*;

public class Crypto1ProgrammingAssignment {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        ArrayList<int[]> cyphers = new ArrayList<int[]>();

        HashMap<Integer, Integer> sure = new HashMap<Integer, Integer>();

        while (true) {
            String s = in.nextString();
            if (s == null) {
                break;
            }
            if (s.equals("SURE")) {
                String q = in.nextLine();
                for (int i = 0; i < q.length(); i++) {
                    int p = (cyphers.size() - 1) * 1000 + i;
                    if (q.charAt(i) != '?') {
                        sure.put(p, (int) q.charAt(i));
                    }
                }
                continue;
            }
            int n = s.length() / 2;
            int[] a = new int[n];
            for (int i = 0; i < s.length(); i += 2) {
                a[i / 2] = getValue(s.charAt(i)) * 16 + getValue(s.charAt(i + 1));
            }
            cyphers.add(a);
        }

        HashSet<Integer> goodChars = new HashSet<Integer>();
        for (int i = 0; i < 26; i++) {
            goodChars.add('a' + i);
            //goodChars.add('A' + i);
        }
        String goodCharsString = "";
        for (int i = 0; i < goodCharsString.length(); i++) {
            goodChars.add((int) goodCharsString.charAt(i));
        }

        ArrayList<int[]> results = new ArrayList<int[]>();
        for (int[] cypher : cyphers) {
            int[] a = new int[cypher.length];
            Arrays.fill(a, '?');
            results.add(a);
        }




        for (int i = 0; true; i++) {
            boolean hasAnyone = false;
            int result = -1;
            for (int j = 0; j < cyphers.size(); j++) {
                if (cyphers.get(j).length > i) {
                    hasAnyone = true;
                }
                int p = j * 1000 + i;
                if (sure.containsKey(p)) {
                    result = cyphers.get(j)[i] ^ sure.get(p);
                }
            }
            if (!hasAnyone) {
                break;
            }

            for (int xor = 0; xor < 256 && result == -1; xor++) {
                boolean good = true;
                for (int[] cypher : cyphers) {
                    if (cypher.length <= i) {
                        continue;
                    }

                    int c = cypher[i] ^ xor;
                    if (!goodChars.contains(c)) {
                        good = false;
                    }
                }
                if (good) {
                    result = xor;
                }
            }
            if (result == -1) {
                continue;
            }

            for (int j = 0; j < cyphers.size(); j++) {
                if (results.get(j).length > i) {
                    results.get(j)[i] = cyphers.get(j)[i] ^ result;
                }
            }
        }

        for (int[] result : results) {
            out.print("[");

            for (int i : result) {
                out.print((char) i);
            }

            out.println("]");
        }

    }

    int getValue(char x) {
        if (x >= '0' && x <= '9') {
            return x - '0';
        } else {
            return x - 'a' + 10;
        }
    }
}
