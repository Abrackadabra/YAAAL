package chelper;

import abrackadabra.io.InputReader;
import abrackadabra.io.OutputWriter;

public class J {
    long hp;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        hp = in.nextLong();

        long l = 0, r = 1000000000000000000L;

        while (r > l) {
            long m = (l + r) / 2;
            if (isOK(m)) {
                r = m;
            } else {
                l = m + 1;
            }
        }

        out.println(r);
    }

    boolean isOK(long add) {
        long linearDamage = 100500 + add;

        long hp = this.hp;

        int linHp = 11;

        while (true) {
            if (linHp > 0) {
                linHp--;
            } else {
                break;
            }

            hp /= 2;
            hp -= linearDamage;

            if (hp <= 0) break;
        }

        return linHp > 0;
    }
}
