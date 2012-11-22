package misc.parallelsort;

import java.io.*;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * Author: Abrackadabra
 * Date: 20/11/12
 * Time: 01:16
 */
public class Generator {
    public static void main(String[] args) {
        try {
            int n = 10000;
            ArrayList<String> strings = new ArrayList<String>();
            String random = getRandomString();
            for (int i = 0; i < n / 2; i++) {
                strings.add(random);
                strings.add(getRandomString());
            }
            Collections.shuffle(strings);

            PrintWriter out = new PrintWriter(new FileWriter("input.txt"));
            for (String s : strings) {
                out.println(s);
            }
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Random random = new Random();

    private static String getRandomString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            stringBuilder.append((char) (random.nextInt(26) + 'a'));
        }
        return stringBuilder.toString();
    }
}
