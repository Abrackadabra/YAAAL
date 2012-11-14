package chelper;

import abrackadabra.Scanner;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class A {
	public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        HashMap<String, Integer> institute = new HashMap<String, Integer>();
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i < n; i++) {
            String inst = in.next();
            String name = in.next();
            if (inst.equals("SCH")) continue;
            if (!institute.containsKey(inst))
                institute.put(inst, 0);
            int alr = institute.get(inst);
            if (inst.equals("MSU")) alr -= 2;
            if (alr < 2 && result.size() < 10) {
                result.add(inst + " " + name);
                institute.put(inst, institute.get(inst) + 1);
            }
        }
        out.println(result.size());
        for (String s : result) {
            out.println(s);
        }
	}
}
