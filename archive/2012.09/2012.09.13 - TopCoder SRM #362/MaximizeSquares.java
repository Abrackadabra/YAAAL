package chelper;

public class MaximizeSquares {
	public int squareCount(int N) {
        int res = 0;
        for (int l = 2; true; l++) {
            int n = N;
            if (l * l > n)
                break;
            n -= l * l - 1;
            int q = 2;
            int i = l * l - 1;
            boolean ok = false;
            while (true) {
                i += q;
                for (int j = 0; j < l - 1; j++) {
                    if (i > N) {
                        ok = true;
                        break;
                    }
                    n--;
                    i++;
                }
                if (ok) break;
                i--;
                i += q;
                for (int j = 0; j < l - 1; j++) {
                    if (i > N) {
                        ok = true;
                        break;
                    }
                    n--;
                    i++;
                }
                if (ok) break;
                i--;
                q++;
            }
            res += n;
        }
        return res;
	}
}
