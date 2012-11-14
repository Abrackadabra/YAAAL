package chelper;

import abrackadabra.Scanner;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class wiki {
	public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = Integer.parseInt(in.readLine());

        ArrayList<String> names = new ArrayList<String>();

        HashMap<String, Integer> forms = new HashMap<String, Integer>();

        ArrayList<String> texts = new ArrayList<String>();

        for (int i = 0; i < n; i++) {
            String name = in.readLine();
            names.add(name);

            forms.put(name, i);
            forms.put(transform(name), i);

            int m = Integer.parseInt(in.readLine());
            for (int j = 0; j < m; j++) {
                String s = in.readLine();
                forms.put(s, i);
                forms.put(transform(s), i);
            }

            m = Integer.parseInt(in.readLine());
            String s = "";
            for (int j = 0; j < m; j++) {
                s += in.readLine() + "\n";
            }
            texts.add(s);
        }

        for (int i = 0; i < n; i++) {
            out.println(names.get(i));

            String s = texts.get(i);
            for (int j = 0; j < s.length(); j++) {
                if (isLatinLetter(s.charAt(j))) {
                    String word = "";
                    while (true) {
                        word += s.charAt(j++);
                        if (j >= s.length() || !isLatinLetter(s.charAt(j))) break;
                    }
                    j--;

                    if (!forms.containsKey(word) || forms.get(word) == i) {
                        out.print(word);
                        continue;
                    }

                    out.print("[[");

                    int z = forms.get(word);

                    if (word.equals(names.get(z)) || transform(word).equals(names.get(z))) {
                        out.print(word);
                    } else {
                        out.print(names.get(z));
                        out.print("|");
                        out.print(word);
                    }

                    out.print("]]");
                } else {
                    out.print(s.charAt(j));
                }
            }
        }
	}

    String transform(String s) {
        String q = s.substring(1);
        if (s.charAt(0) <= 'Z') {
            q = (char)(s.charAt(0) - 'A' + 'a') + q;
        } else {
            q = (char)(s.charAt(0) - 'a' + 'A') + q;
        }
        return q;
    }

    boolean isLatinLetter(int a) {
        return (a >= 'a' && a <= 'z') || (a >= 'A' && a <= 'Z');
    }
}
