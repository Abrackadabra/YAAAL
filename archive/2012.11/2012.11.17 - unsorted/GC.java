package chelper;

import abrackadabra.Scanner;

import java.io.PrintWriter;
import java.util.*;

public class GC {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        ArrayList<String> a = new ArrayList<String>();
        ArrayList<String> labels = new ArrayList<String>();
        while (true) {
            String s = in.nextString();
            if (s == null) {
                break;
            }
            if (s.charAt(0) == '>') {
                labels.add(s.substring(1));
                a.add("");
            } else {
                a.set(a.size() - 1, a.get(a.size() - 1) + s);
            }
        }
        double best = 0.0;
        String str = "";
        for (int i = 0; i < a.size(); i++) {
            double t = 0.0;
            for (char c : a.get(i).toCharArray()) {
                if (c == 'C' || c == 'G') {
                    t += 1.0 / a.get(i).length();
                }
            }
            if (t > best) {
                best = t;
                str = labels.get(i);
            }
        }
        out.println(str);
        out.println(best * 100 + "%");
    }
}
