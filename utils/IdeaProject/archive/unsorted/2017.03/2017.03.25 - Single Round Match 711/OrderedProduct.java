package coding;

import java.util.Arrays;

public class OrderedProduct {

    static final int MOD = 1000000007;

    static int mul(int a, int b){
        return (int) ((long) a * b % MOD);
    }

    static int add(int a, int b) {
        a += b;
        if (a >= MOD) a -= MOD;
        return a;
    }

    static int[][] c;
    static int MAXN = 5100;
    static {
        c = new int[MAXN][MAXN];
        for (int i = 0; i < MAXN; i++) {
            c[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                c[i][j] = add(c[i - 1][j - 1], c[i - 1][j]);
            }
        }
    }

    static int buckets(int n, int k) {
        if (k == 0) {
            return n == 0 ? 1 : 0;
        }
        k--;
        n += k;
        if (k > n || k < 0) return 0;
        return c[n][k];
    }

    public int count(int[] a) {
        int[] dp = new int[1];
        dp[0] = 1;
        int cn = 0;
        for (int x : a) {
            int[] next = new int[cn + x + 1];
            for (int groups = 0; groups <= cn; groups++) {
                int val = dp[groups];
                if (val == 0) continue;
                for (int giveGroups = 0; giveGroups <= x; giveGroups++) {
                    for (int newGroups = 0; newGroups <= x - giveGroups; newGroups++) {
                        int give = buckets(giveGroups, groups);
                        int makeNew = buckets(newGroups, groups + 1);
                        int giveNew = buckets(x - giveGroups - newGroups, newGroups);
                        next[groups + newGroups] = add(next[groups + newGroups], mul(give, mul(makeNew, mul(giveNew, val))));
                    }
                }
            }
            cn += x;
            dp = next;
        }
        int ans = 0;
        for (int i : dp) ans = add(ans, i);
        return ans;
    }
}
