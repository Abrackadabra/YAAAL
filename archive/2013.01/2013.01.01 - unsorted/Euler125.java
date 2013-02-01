package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

public class Euler125 {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long sum = 0;
        for (int i = 0; i < 100000000; i++) {
            if (i % 1000000 == 0) {
                System.out.println("//" + i);
            }
            if (isPalindromic(i) && isGoodSum(i)) {
                System.out.println(i);
                sum += i;
            }
        }
        System.out.println(sum);
        System.out.println(isPalindromic(5045));
    }

    boolean isPalindromic(int x) {
        int d = 1;
        while (d * 10 <= x) {
            d *= 10;
        }
        while (d > 1) {
            if (x / d != x % 10) {
                return false;
            }
            x = x % d / 10;
            d /= 100;
        }
        return true;
    }

    boolean isGoodSum(int x) {
        for (int i = 1; i * i < x; i++) {
            if (isGoodSum(x, i)) {
                return true;
            }
        }
        return false;
    }

    boolean isGoodSum(int x, int start) {
        int t = x;
        for (int i = start; x > 0; i++) {
            x -= i * i;
        }
        if (x == 0) {
            System.out.print("    ");
            for (int i = start; t > 0; i++) {
                t -= i * i;
                System.out.print(i + " ");
            }
            System.out.println();
        }
        return x == 0;
    }
}
