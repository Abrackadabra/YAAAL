package chelper;

import abrackadabra.Scanner;
import java.io.PrintWriter;

public class J {
    boolean good (long n, long m, long k, long d, long day, long month, long year){

        return (((day*m + k)*n + month + 1)*m + k)*n + year == d;
    }
	public void solve(int testNumber, Scanner in, PrintWriter out) {
        long n, m, k, d;
        long months[] = {31,28,31,30,31,30,31,31,30,31,30,31};
        m = in.nextLong();
        n = in.nextLong();
        k = in.nextLong();
        d = in.nextLong();
        int cnt = 0;
        int D = -1, M = -1, Y = -1;
        for (long year = 0; year < 100; ++year){
            if (year %4 == 0){
                long day = 29;
                int month = 1;
                if ( good(n, m, k, d, day, month,  year) ){
                    ++cnt;
                    D = (int)day;
                    M = (int)month;
                    Y = (int)year;
                }
            }
            for (int month = 0; month < 12; ++month){
                for (long day = 1; day <= months[month]; ++day)
                    if ( good(n, m, k, d, day, month,  year) ){
                        ++cnt;
                        D = (int)day;
                        M = (int)month;
                        Y = (int)year;
                    }
            }
        }
        if (cnt != 1){
            out.print("-1");
            return;
        }
        ++M;
        out.print(D + " " + M + " ");
        if (Y == 0)
            out.print("2000");
        else{
            out.print("19");
            if (Y < 10)
                out.print("0");
            out.print(Y);
        }
	}
}
