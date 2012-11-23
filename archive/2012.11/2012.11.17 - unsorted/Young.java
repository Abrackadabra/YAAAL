package chelper;

import abrackadabra.io.InputReader;

import java.io.PrintWriter;
import java.util.*;

public class Young {
    ArrayList<Integer> a = new ArrayList<Integer>();
    int sum;

    ArrayList<Integer> best = new ArrayList<Integer>();
    long               max  = 0;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        String[] res = new String[101];

        res[1] = "1\n1 ";
        res[2] = "2\n1 1 ";
        res[3] = "4\n2 1 ";
        res[4] = "6\n2 1 1 ";
        res[5] = "9\n3 1 1 ";
        res[6] = "13\n3 2 1 ";
        res[7] = "18\n3 2 1 1 ";
        res[8] = "25\n4 2 1 1 ";
        res[9] = "32\n4 2 1 1 1 ";
        res[10] = "41\n5 2 1 1 1 ";
        res[11] = "55\n4 3 2 1 1 ";
        res[12] = "74\n5 3 2 1 1 ";
        res[13] = "93\n5 3 2 1 1 1 ";
        res[14] = "117\n6 3 2 1 1 1 ";
        res[15] = "144\n6 4 2 1 1 1 ";
        res[16] = "180\n6 4 2 2 1 1 ";
        res[17] = "229\n6 4 3 2 1 1 ";
        res[18] = "285\n6 4 3 2 1 1 1 ";
        res[19] = "355\n7 4 3 2 1 1 1 ";
        res[20] = "427\n7 5 3 2 1 1 1 ";
        res[21] = "521\n7 4 3 2 2 1 1 1 ";
        res[22] = "632\n7 5 3 2 2 1 1 1 ";
        res[23] = "773\n8 5 3 2 2 1 1 1 ";
        res[24] = "914\n8 5 3 2 2 1 1 1 1 ";
        res[25] = "1124\n8 5 4 3 2 1 1 1 ";
        res[26] = "1340\n8 5 4 3 2 1 1 1 1 ";
        res[27] = "1620\n8 5 4 3 2 2 1 1 1 ";
        res[28] = "1934\n9 5 4 3 2 2 1 1 1 ";
        res[29] = "2350\n9 6 4 3 2 2 1 1 1 ";
        res[30] = "2768\n9 6 4 3 2 2 1 1 1 1 ";
        res[31] = "3261\n10 6 4 3 2 2 1 1 1 1 ";
        res[32] = "3754\n10 6 4 3 2 2 1 1 1 1 1 ";
        res[33] = "4413\n10 7 5 3 2 2 1 1 1 1 ";
        res[34] = "5230\n10 7 5 4 3 2 1 1 1 ";
        res[35] = "6223\n10 7 5 4 3 2 1 1 1 1 ";
        res[36] = "7479\n10 7 5 4 3 2 2 1 1 1 ";
        res[37] = "8786\n10 7 5 4 3 2 2 1 1 1 1 ";
        res[38] = "10323\n11 7 5 4 3 2 2 1 1 1 1 ";
        res[39] = "11860\n11 7 5 4 3 2 2 1 1 1 1 1 ";
        res[40] = "13710\n11 7 5 4 3 2 2 2 1 1 1 1 ";
        res[41] = "15924\n11 7 5 4 3 3 2 2 1 1 1 1 ";
        res[42] = "18313\n12 7 5 4 3 3 2 2 1 1 1 1 ";
        res[43] = "21313\n11 8 6 4 3 3 2 2 1 1 1 1 ";
        res[44] = "24897\n12 8 6 4 3 3 2 2 1 1 1 1 ";
        res[45] = "28812\n11 8 6 5 4 3 2 2 1 1 1 1 ";
        res[46] = "33785\n12 8 6 5 4 3 2 2 1 1 1 1 ";
        res[47] = "38758\n12 8 6 5 4 3 2 2 1 1 1 1 1 ";
        res[48] = "44593\n12 8 6 5 4 3 2 2 2 1 1 1 1 ";
        res[49] = "51253\n12 8 6 5 4 3 3 2 2 1 1 1 1 ";
        res[50] = "58926\n13 9 6 5 4 3 2 2 2 1 1 1 1 ";
        res[51] = "67854\n13 9 7 5 4 3 2 2 2 1 1 1 1 ";
        res[52] = "78402\n13 9 7 5 4 3 3 2 2 1 1 1 1 ";
        res[53] = "89549\n13 9 7 5 4 3 3 2 2 1 1 1 1 1 ";
        res[54] = "102286\n14 9 7 5 4 3 3 2 2 1 1 1 1 1 ";
        res[55] = "115946\n14 10 7 5 4 3 3 2 2 1 1 1 1 1 ";
        res[56] = "131481\n14 10 7 5 4 3 3 2 2 2 1 1 1 1 ";
        res[57] = "149040\n14 10 7 5 4 3 3 2 2 2 1 1 1 1 1 ";
        res[58] = "170652\n14 9 7 6 5 4 3 2 2 2 1 1 1 1 ";
        res[59] = "195787\n14 10 7 6 5 4 3 2 2 2 1 1 1 1 ";
        res[60] = "223705\n14 10 8 6 5 4 3 2 2 2 1 1 1 1 ";
        res[61] = "256159\n14 10 8 6 5 4 3 3 2 2 1 1 1 1 ";
        res[62] = "292241\n14 10 8 6 5 4 3 3 2 2 1 1 1 1 1 ";
        res[63] = "333419\n15 10 8 6 5 4 3 3 2 2 1 1 1 1 1 ";
        res[64] = "376734\n15 11 8 6 5 4 3 3 2 2 1 1 1 1 1 ";
        res[65] = "426575\n15 10 8 6 5 4 3 3 2 2 2 1 1 1 1 1 ";
        res[66] = "482175\n15 11 8 6 5 4 3 3 2 2 2 1 1 1 1 1 ";
        res[67] = "546031\n16 11 8 6 5 4 3 3 2 2 2 1 1 1 1 1 ";
        res[68] = "609887\n16 11 8 6 5 4 3 3 2 2 2 1 1 1 1 1 1 ";
        res[69] = "681223\n17 11 8 6 5 4 3 3 2 2 2 1 1 1 1 1 1 ";
        res[70] = "763217\n16 11 8 6 5 4 4 3 3 2 2 1 1 1 1 1 1 ";
        res[71] = "861527\n16 11 8 6 5 4 4 3 3 2 2 2 1 1 1 1 1 ";
        res[72] = "979169\n15 11 9 7 6 5 4 3 3 2 2 1 1 1 1 1 ";
        res[73] = "1116124\n16 11 9 7 6 5 4 3 3 2 2 1 1 1 1 1 ";
        res[74] = "1258089\n16 12 9 7 6 5 4 3 3 2 2 1 1 1 1 1 ";
        res[75] = "1423437\n16 11 9 7 6 5 4 3 3 2 2 2 1 1 1 1 1 ";
        res[76] = "1604967\n16 12 9 7 6 5 4 3 3 2 2 2 1 1 1 1 1 ";
        res[77] = "1816080\n17 12 9 7 6 5 4 3 3 2 2 2 1 1 1 1 1 ";
        res[78] = "2027193\n17 12 9 7 6 5 4 3 3 2 2 2 1 1 1 1 1 1 ";
        res[79] = "2262881\n18 12 9 7 6 5 4 3 3 2 2 2 1 1 1 1 1 1 ";
        res[80] = "2506145\n18 13 9 7 6 5 4 3 3 2 2 2 1 1 1 1 1 1 ";
        res[81] = "2820234\n18 13 10 8 6 5 4 3 3 2 2 2 1 1 1 1 1 ";
        res[82] = "3149134\n18 13 10 8 6 5 4 3 3 2 2 2 1 1 1 1 1 1 ";
        res[83] = "3507733\n19 13 10 8 6 5 4 3 3 2 2 2 1 1 1 1 1 1 ";
        res[84] = "3918068\n18 13 10 8 6 5 4 4 3 3 2 2 1 1 1 1 1 1 ";
        res[85] = "4418444\n18 13 10 8 6 5 4 4 3 3 2 2 2 1 1 1 1 1 ";
        res[86] = "4923068\n18 13 10 8 6 5 4 4 3 3 2 2 2 1 1 1 1 1 1 ";
        res[87] = "5485391\n19 13 10 8 6 5 4 4 3 3 2 2 2 1 1 1 1 1 1 ";
        res[88] = "6171169\n18 13 10 8 7 6 5 4 3 3 2 2 2 1 1 1 1 1 ";
        res[89] = "6885083\n18 13 10 8 7 6 5 4 3 3 2 2 2 1 1 1 1 1 1 ";
        res[90] = "7681683\n19 13 10 8 7 6 5 4 3 3 2 2 2 1 1 1 1 1 1 ";
        res[91] = "8495787\n19 14 10 8 7 6 5 4 3 3 2 2 2 1 1 1 1 1 1 ";
        res[92] = "9474004\n19 14 11 9 7 6 5 4 3 3 2 2 2 1 1 1 1 1 ";
        res[93] = "10572785\n19 14 11 9 7 6 5 4 3 3 2 2 2 1 1 1 1 1 1 ";
        res[94] = "11771283\n20 14 11 9 7 6 5 4 3 3 2 2 2 1 1 1 1 1 1 ";
        res[95] = "13028133\n20 14 11 9 7 6 5 4 3 3 2 2 2 2 1 1 1 1 1 ";
        res[96] = "14634309\n19 14 11 9 7 6 5 4 4 3 3 2 2 2 1 1 1 1 1 ";
        res[97] = "16296994\n19 14 11 9 7 6 5 4 4 3 3 2 2 2 1 1 1 1 1 1 ";
        res[98] = "18148771\n20 14 11 9 7 6 5 4 4 3 3 2 2 2 1 1 1 1 1 1 ";
        res[99] = "20000548\n20 14 11 9 7 6 5 4 4 3 3 2 2 2 1 1 1 1 1 1 1 ";
        res[100] = "22121100\n20 14 11 9 7 6 5 4 4 3 3 2 2 2 2 1 1 1 1 1 1 ";

        out.println(res[in.nextInt()]);

        if (1 == 1) {
            return;
        }



        for (int n = 1; n <= 100; n++) {
            best.add(1);
            max = calc(best);


            while (true) {
                HashSet<ArrayList<Integer>> set = new HashSet<ArrayList<Integer>>();
                long newMax = max;
                ArrayList<Integer> newBest = null;
                for (int i = 0; i < best.size(); i++) {
                    for (int j = 0; j < best.size(); j++) {
                        for (int i1 = i; i1 < best.size(); i1++) {
                            for (int j1 = j; j1 < best.size(); j1++) {
                                for (int i2 = i1; i2 < best.size(); i2++) {
                                    for (int j2 = j1; j2 < best.size(); j2++) {
                                        ArrayList<Integer> t = new ArrayList<Integer>();

                                        for (int q = 0; q < best.size(); q++) {
                                            int v = best.get(q);
                                            if (q == i) {
                                                v--;
                                            }
                                            if (q == j) {
                                                v++;
                                            }
                                            if (q == i1) {
                                                v--;
                                            }
                                            if (q == j1) {
                                                v++;
                                            }
                                            if (q == i2) {
                                                v--;
                                            }
                                            if (q == j2) {
                                                v++;
                                            }
                                            if (v > 0) {
                                                t.add(v);
                                            }
                                            if (v < 0) {
                                                t = null;
                                                break;
                                            }
                                        }
                                        if (t == null) {
                                            continue;
                                        }

                                        Collections.sort(t);

                                        ArrayList<Integer> z = new ArrayList<Integer>();
                                        for (int q = t.size() - 1; q >= 0; q--) {
                                            z.add(t.get(q));
                                        }

                                        if (set.contains(z)) {
                                            continue;
                                        }

                                        long x = calc(z);
                                        if (x > newMax) {
                                            newMax = x;
                                            newBest = z;
                                        }
                                        set.add(z);
                                    }
                                }
                            }
                        }
                    }
                }
                if (newMax > max) {
                    best = newBest;
                    max = newMax;
                } else {
                    break;
                }
            }

            StringBuilder sb = new StringBuilder();
            sb.append(Long.toString(max));
            sb.append('\\');
            sb.append('n');
            for (int i : best) {
                sb.append(Integer.toString(i));
                sb.append(' ');
            }
            System.out.println("res[" + n + "] = \"" + sb.toString() + "\";");
        }
    }

    void go(int pos, int val) {
        a.add(val);
        sum -= val;

        if (sum == 0) {
            long t = calc(a);
            if (t > max || (t == max && a.size() > best.size())) {
                max = t;
                best = new ArrayList<Integer>();
                for (int i : a) {
                    best.add(i);
                }
            }
        } else {
            if (pos < 11) {
                for (int i = Math.min(val, sum); i >= val / 2; i--) {
                    go(pos + 1, i);
                }
            } else {
                /*if (sum >= 2) {
                    go(pos + 1, 2);
                    if (sum < 8)
                        go(pos + 1, 1);
                } else {
                    go(pos + 1, 1);
                }*/
                go(pos + 1, 1);
            }
        }

        a.remove(a.size() - 1);
        sum += val;
    }

    public long calc(ArrayList<Integer> a) {
        long[][] dp = new long[100][100];
        for (int i = 0; i < 100; i++) {
            for (int j = 0; i < a.size() && j < a.get(i); j++) {
                dp[i][j] = -1;
            }
        }

        for (int i = 99; i >= 0; i--) {
            for (int j = 0; j < 100; j++) {
                if (dp[i][j] != -1) {
                    dp[i][j] = 0;
                    if (j > 0) {
                        dp[i][j] += dp[i][j - 1];
                    }
                } else {
                    dp[i][j] = 1;
                    if (j > 0) {
                        dp[i][j] += dp[i][j - 1];
                    }
                    if (i < 99) {
                        dp[i][j] += dp[i + 1][j];
                    }
                }
            }
        }
        return dp[0][99];
    }
}
