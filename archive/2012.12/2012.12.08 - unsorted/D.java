package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import java.util.*;

public class D {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.nextLine();
        String[] words = s.split("\\d+");
        String[] numbers = s.split("\\(\\D*\\)");
        numbers = Arrays.copyOfRange(numbers, 1, numbers.length);

        long res = 0;
        for (int i = 0; i < words.length; i++) {
            res += Long.parseLong(numbers[i]) * (words[i].length() - 2);
        }

        out.println(res);
    }
}
