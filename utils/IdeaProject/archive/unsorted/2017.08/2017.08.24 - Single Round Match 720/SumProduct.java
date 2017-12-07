package coding;

public class SumProduct {

    static final int MOD = 1000000007;
    static final int MAXN = 1111;

    static int mul(int a, int b) {
        return (int) ((long) a * b % MOD);
    }

    static int add(int a, int b) {
        a += b;
        if (a >= MOD) a -= MOD;
        return a;
    }

    static final int[] FACT = new int[MAXN], INV = new int[MAXN], INVFACT = new int[MAXN];
    static final int[][] C = new int[MAXN][MAXN];
    static {
        INV[1] = 1;
        for (int i = 2; i < MAXN; i++) {
            INV[i] = mul(MOD - MOD / i, INV[MOD % i]);
        }
        FACT[0] = 1;
        INVFACT[0] = 1;
        for (int i = 1; i < MAXN; i++) {
            FACT[i] = mul(FACT[i - 1], i);
            INVFACT[i] = mul(INVFACT[i - 1], INV[i]);
        }
        for (int i = 0; i < MAXN; i++) {
            C[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                C[i][j] = add(C[i - 1][j - 1], C[i - 1][j]);
            }
        }
    }

    public int findSum(int[] amount, int blank1, int blank2) {
        int ans = 0;
        int ten1 = 1;
        int ansTen = 0;
        for (int len1 = 0; len1 < blank1; len1++) {
            int ten2 = ten1;
            for (int len2 = 0; len2 < blank2; len2++) {
                ansTen += ten2;
                ansTen %= MOD;
                ten2 = mul(ten2, 10);
            }
            ten1 = mul(ten1, 10);
        }
        for (int d1 = 0; d1 < 10; d1++) {
            if (amount[d1] == 0) continue;
            amount[d1]--;
            for (int d2 = 0; d2 < 10; d2++) {
                if (amount[d2] == 0) continue;
                amount[d2]--;
                int left = blank1 + blank2 - 2;
                int[] dp = new int[left + 1];
                dp[0] = 1;
                for (int d = 0; d < 10; d++) {
                    int[] ndp = new int[left + 1];
                    for (int got = 0; got <= left; got++) {
                        int val = dp[got];
                        if (val == 0) continue;
                        for (int get = 0; get + got <= left && get <= amount[d]; get++) {
                            ndp[get + got] = add(ndp[get + got], mul(val, C[left - got][get]));
                        }
                    }
                    dp = ndp;
                }
                ans += mul(dp[left], d1 * d2);
                ans %= MOD;
                amount[d2]++;
            }
            amount[d1]++;
        }
        return mul(ans, ansTen);
    }
}
