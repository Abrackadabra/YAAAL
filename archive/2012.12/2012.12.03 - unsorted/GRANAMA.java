package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;
import abrackadabra.structure.Counter;

import java.util.*;

public class GRANAMA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
        String a = in.nextString(), b = in.nextString();
        out.println((compareOne(a, b) == compareTwo(a, b)) ? "YES" : "NO");
	}

    boolean compareOne(String a, String b) {
        Counter x = new Counter();
        Counter y = new Counter();
        for (char c : a.toCharArray()) {
            x.add(c);
        }
        for (char c : b.toCharArray()) {
            y.add(c);
        }
        return x.equals(y);
    }

    boolean compareTwo(String a, String b) {
        HashSet x = new HashSet();
        HashSet y = new HashSet();
        for (char c : a.toCharArray()) {
            x.add(c);
        }
        for (char c : b.toCharArray()) {
            y.add(c);
        }
        return x.equals(y);
    }
}
