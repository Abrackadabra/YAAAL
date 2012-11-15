package chelper;

import abrackadabra.Scanner;
import java.io.PrintWriter;

public class REVC {
	public void solve(int testNumber, Scanner in, PrintWriter out) {
        String s = in.nextString();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == 'A')
                stringBuilder.append('T');
            if (s.charAt(i) == 'T')
                stringBuilder.append('A');
            if (s.charAt(i) == 'C')
                stringBuilder.append('G');
            if (s.charAt(i) == 'G')
                stringBuilder.append('C');
        }
        out.println(stringBuilder);
	}
}
