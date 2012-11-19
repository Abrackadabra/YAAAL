package chelper;

import abrackadabra.Scanner;

import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CCard {
    AtomicInteger count = new AtomicInteger(0);

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        for (int n = 1; n <= 1000; n++) {
            while (count.intValue() >= 1) {
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            new MyThread(n, count).start();
        }
    }
}

class MyThread extends Thread {
    public MyThread(int n, AtomicInteger count) {
        this.n = n;
        this.count = count;
    }

    int n;
    int ans = 0;

    int k;

    int[] digits = new int[1000];

    void calc() {
        Arrays.fill(pairs, -1);
        for (k = 4; k <= n; k += 2) {
            if (k % 6 == 2) {
                continue;
            }
            for (int f = 0; f < n; f++) {
                go(0, f);
            }
        }
    }

    boolean debug = false;

    boolean[] alreadyContains = new boolean[1000];

    boolean[] hasToContain     = new boolean[1000];
    int       hasToContainSize = 0;

    int[] pairs = new int[1000];
    int   sum   = 0;

    int[][] V = new int[1000][1000];

    void go(int pos, int val) {
        digits[pos] = val;
        alreadyContains[val] = true;
        sum += val;

        if (pos == k - 1 && sum % (n - 1) != 0) {
            alreadyContains[val] = false;
            sum -= val;
            return;
        }

        ArrayList<Integer> newAdded = new ArrayList<Integer>();
        ArrayList<Integer> newSet = new ArrayList<Integer>();

        if (!hasToContain[val]) {
            hasToContain[val] = true;
            hasToContainSize++;
            newAdded.add(val);
        }

        for (int m = k; m >= 1; m--) {
            int carry = pos == 0 ? 0 : (V[pos - 1][m - 1] / n);

            int t = digits[pos] * m + carry;

            int res = t % n;
            carry = t / n;

            if (!hasToContain[res]) {
                hasToContain[res] = true;
                hasToContainSize++;
                newAdded.add(res);
            }

            if (hasToContainSize > k) {
                alreadyContains[val] = false;
                if (debug) {
                    System.out.println("1");
                }
                for (int i : newAdded) {
                    hasToContain[i] = false;
                    hasToContainSize--;
                }
                for (int i : newSet) {
                    pairs[i] = -1;
                }
                sum -= val;
                return;
            }

            if (pos == k - 1 && carry > 0) {
                alreadyContains[val] = false;
                if (debug) {
                    System.out.println("2");
                }
                for (int i : newAdded) {
                    hasToContain[i] = false;
                    hasToContainSize--;
                }
                for (int i : newSet) {
                    pairs[i] = -1;
                }
                sum -= val;
                return;
            }

            if (pos > 0) {
                int pd = V[pos - 1][m - 1] % n;

                if (pairs[pd] == -1) {
                    pairs[pd] = res;
                    newSet.add(pd);
                } else {
                    if (pairs[pd] != res) {
                        alreadyContains[val] = false;
                        if (debug) {
                            System.out.println("3");
                        }
                        for (int i : newAdded) {
                            hasToContain[i] = false;
                            hasToContainSize--;
                        }
                        for (int i : newSet) {
                            pairs[i] = -1;
                        }
                        sum -= val;
                        return;
                    }
                }
            }

            V[pos][m - 1] = t;
        }

        if (pos == k - 1 && pairs[digits[k - 1]] != -1 && pairs[digits[k - 1]] != digits[0]) {
            alreadyContains[val] = false;
            if (debug) {
                System.out.println("4");
            }
            for (int i : newAdded) {
                hasToContain[i] = false;
                hasToContainSize--;
            }
            for (int i : newSet) {
                pairs[i] = -1;
            }
            sum -= val;
            return;
        }

        if (pos == k - 1) {
            ans++;

            System.out.println("n = " + n);
            System.out.println(Arrays.toString(digits));
            //System.out.println(sum);
            //System.out.println();

            alreadyContains[val] = false;
            if (debug) {
                System.out.println("5");
            }
            for (int i : newAdded) {
                hasToContain[i] = false;
                hasToContainSize--;
            }
            for (int i : newSet) {
                pairs[i] = -1;
            }
            sum -= val;
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!alreadyContains[i]) {
                go(pos + 1, i);
            }
        }

        alreadyContains[val] = false;
        for (int i : newAdded) {
            hasToContain[i] = false;
            hasToContainSize--;
        }
        for (int i : newSet) {
            pairs[i] = -1;
        }
        sum -= val;
    }

    AtomicInteger count;

    @Override
    public void run() {
        count.incrementAndGet();
        calc();
        System.out.println("ans[" + n + "] = " + ans + ";");
        count.decrementAndGet();
    }
}