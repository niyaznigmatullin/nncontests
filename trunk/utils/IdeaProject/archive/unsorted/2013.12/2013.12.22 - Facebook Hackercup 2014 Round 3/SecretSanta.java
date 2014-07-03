package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class SecretSanta {

    static int[][] C;
    static int[] FACT;
    static final int MOD = 1000000007;

    static {
        C = new int[555][555];
        for (int i = 0; i < C.length; i++) {
            C[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                C[i][j] = C[i - 1][j - 1] + C[i - 1][j];
                C[i][j] %= MOD;
            }
        }
        FACT = new int[555];
        FACT[0] = 1;
        for (int i = 1; i < FACT.length; i++) {
            FACT[i] = mul(FACT[i - 1], i);
        }
    }

    static int f(int n) {
        return FACT[n];
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        out.print("Case #" + testNumber + ": ");
        System.err.println(testNumber);
        int k = in.nextInt();
        int[][] dp = new int[1][1];
        dp[0][0] = 1;
        int count = 0;
        for (int it = 0; it < k; it++) {
            int x = in.nextInt();
            int[][] next = new int[count + x + 1][count + x + 1];
            for (int outEdges = 0; outEdges <= count; outEdges++) {
                for (int inEdges = 0; inEdges <= count; inEdges++) {
                    int val = dp[outEdges][inEdges];
                    if (val == 0) continue;
                    for (int putIn = 0; putIn <= x && putIn <= outEdges; putIn++) {
                        for (int putOut = 0; putOut <= x && putOut <= inEdges; putOut++) {
                            int val2 = mul(c(outEdges, putIn), mul(c(x, putIn), c(x, putOut)));
                            val2 = mul(val2, c(inEdges, putOut));
                            val2 = mul(val2, val);
                            val2 = mul(val2, f(putIn));
                            val2 = mul(val2, f(putOut));
                            next[outEdges - putIn + x - putOut][inEdges - putOut + x - putIn] = (next[outEdges - putIn + x - putOut][inEdges - putOut + x - putIn] + val2) % MOD;
                        }
                    }
                }
            }
            count += x;
            dp = next;
        }
        out.println(dp[0][0]);
    }

    static int mul(int a, int b) {
        return (int) ((long) a * b % MOD);
    }


    int c(int n, int k) {
        if (k < 0 || k > n) return 0;
        return C[n][k];
    }
}
