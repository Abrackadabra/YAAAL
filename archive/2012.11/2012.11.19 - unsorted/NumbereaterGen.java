package chelper;

import abrackadabra.Scanner;

import java.io.*;
import java.util.*;

public class NumbereaterGen {
    public void solve(int testNumber, Scanner in, PrintWriter cout) {
        try {
            PrintWriter out = new PrintWriter(new FileWriter("input.txt"));

            int n = 500;

            out.println(n);

            Random random = new Random();

            for (int i = 0; i < n; i++) {
                out.println(i);
            }

            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
