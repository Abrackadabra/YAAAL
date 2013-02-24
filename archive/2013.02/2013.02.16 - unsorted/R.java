package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

import java.io.FileWriter;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;

public class R {
    private static class Painting {
        BigDecimal value;
        private static final MathContext mc = new MathContext(1000);

        private Painting(double value) {
            this.value = BigDecimal.valueOf(value);
        }

        BigDecimal calc() {
            if (!fresh) {
                for (int i = 0; i < borrows.size(); i++) {
                    value = value.add(
                            borrows.get(i).calc().multiply(
                                    BigDecimal.valueOf(coeffs.get(i)),
                                    mc
                            )
                    );
                }

                fresh = true;
            }
            return value;
        }

        ArrayList<Painting> borrows = new ArrayList<Painting>();
        ArrayList<Double> coeffs = new ArrayList<Double>();

        boolean fresh = false;
    }

    public R() {
        String dir = "c:\\Downloads\\Chrome\\preec2013input\\input\\PROBLEMSET\\input\\R\\";
        for (int i = 1; i <= 10; i++) {
            try {
                System.out.println(i);
                solve(i, new InputReader(dir + i + ".in"), new OutputWriter(new FileWriter(dir + "R" + i + ".out")));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        Painting[] paintings = new Painting[n];

        for (int i = 0; i < n; i++) {
            paintings[i] = new Painting(in.nextDouble());
        }

        for (int i = 0; i < m; i++) {
            int who = in.nextInt() - 1;
            int from = in.nextInt() - 1;
            double c = in.nextDouble();

            paintings[who].borrows.add(paintings[from]);
            paintings[who].coeffs.add(c);
        }

        for (int i = 0; i < n; i++) {
            if (i > 0) out.print(" ");
            BigDecimal ans = paintings[i].calc();
            ans = ans.multiply(BigDecimal.valueOf(1000), new MathContext(0));
            ans = ans.divide(BigDecimal.valueOf(1000), new MathContext(1000));
            out.print(ans);
        }
        out.flush();
    }
}
