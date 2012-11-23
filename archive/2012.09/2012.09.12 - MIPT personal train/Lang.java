package chelper;

import abrackadabra.io.InputReader;

import java.io.PrintWriter;
import java.util.ArrayList;

public class Lang {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
        String s = in.nextString();
        if (isJavanese(s) && isCglish(s)) {
            out.println(s);
            return;
        }
        if ((!isJavanese(s) && !isCglish(s)) || s.contains("__") || s.charAt(0) == '_' || s.charAt(s.length() - 1) == '_') {
            out.println("Error!");
            return;
        }
        String t = "";
        ArrayList<String> a = new ArrayList<String>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '_') {
                a.add(t);
                t = "";
                continue;
            }
            if (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') {
                a.add(t);
                t = "";
                t += (char) (s.charAt(i) - 'A' + 'a');
                continue;
            }
            t += s.charAt(i);
        }
        if (t.length() > 0) a.add(t);
        if (isJavanese(s)) {
            for (int i = 0; i < a.size(); i++) {
                out.print(a.get(i));
                if (i != a.size() - 1)
                    out.print("_");
            }
        }
        if (isCglish(s)) {
            out.print(a.get(0));
            for (int i = 1; i < a.size(); i++) {
                out.print((char) (a.get(i).charAt(0) - 'a' + 'A'));
                if (a.get(i).length() > 1)
                    out.print(a.get(i).substring(1));
            }
        }
	}

    boolean isJavanese(String s) {
        return !s.contains("_") && s.charAt(0) >= 'a';
    }

    boolean isCglish(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) <= 'Z' && s.charAt(i) >= 'A') return false;
        }
        return true;
    }
}
