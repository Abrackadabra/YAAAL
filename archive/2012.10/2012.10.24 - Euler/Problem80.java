package chelper;

import abrackadabra.Scanner;
import abrackadabra.math.BigDecimalUtils;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.MathContext;

public class Problem80 {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        MathContext mc = new MathContext(1000);
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            BigDecimal sqrt = BigDecimalUtils.sqrt(BigDecimal.valueOf(i), 300, mc);
            System.out.println(i + " " + sqrt);
            String s = sqrt.unscaledValue().toString();
            if (Math.round(Math.pow(Math.round(Math.sqrt(i)), 2)) != i) {
                for (int j = 0; j < 100; j++) {
                    sum += s.charAt(j) - '0';
                }
                System.out.println(sum);
            }
            System.out.println(sqrt.multiply(sqrt, mc));
        }
    }
}
