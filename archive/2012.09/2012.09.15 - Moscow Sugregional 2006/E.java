package chelper;

import abrackadabra.io.InputReader;

import java.io.PrintWriter;

public class E {
	public void solve(int testNumber, InputReader in, PrintWriter out) {

        int n = in.nextInt();
        int[][] a = new int[n][n];
        for(int i = 0; i < n ;++i)
            for(int j = 0; j < n;++j){
                a[i][j] = in.nextInt();
                if(i == j && a[i][j] != 0){
                    out.println("NO");
                    return;
                }
            }


        for(int i = 0; i < n ;++i){
            for(int j = 0; j < n ;++j){
                if(a[i][j] != a[j][i]){
                    out.println("NO");
                    return;
                }
            }
        }


        for(int i = 0; i < n; ++i){
            for(int j = 0; j < n ;++j){
                for(int k = 0; k < n; ++k){
                    if(a[i][j] != -1){
                        if(a[i][k]!= -1 && a[k][j] != -1 && a[i][j] > a[i][k] + a[k][j]){
                            out.println("NO");
                            return;
                        }
                    }
                    else{
                        if(a[i][k] != -1 && a[k][j] != -1){
                            out.println("NO");
                            return;
                        }
                    }
                }
            }
        }
        out.println("YES");
	}
}
