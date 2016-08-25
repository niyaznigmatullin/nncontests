package coding;

import ru.ifmo.niyaz.math.MathUtils;

import java.util.Arrays;

public class BearCircleGame {

    static final int MOD = 1000000007;

    static final int INV_TWO = MathUtils.modPow(2, MOD - 2, MOD);


    static int mul(int a, int b) {
        return (int) ((long) a * b % MOD);
    }

    static int add(int a, int b) {
        a += b;
        if (a >= MOD) a -= MOD;
        return a;
    }

    public int winProbability(int n, int k) {
        int[] two = new int[n + 1];
        two[0] = 1;
        for (int i = 1; i <= n; i++) {
            two[i] = mul(two[i - 1], INV_TWO);
        }
        int[] dp = new int[1];
        dp[0] = 1;
        for (int i = 2; i <= n; i++) {
            int g = MathUtils.gcd(i, k);
            int[] prev = new int[i / g];
            int[] suf = new int[i / g + 1];
            int[] pref = new int[i / g + 1];
            int factor = two[prev.length];
            int[] newDP = new int[i];
            int sum = MathUtils.modPow(MOD + 1 - factor, MOD - 2, MOD);
            for (int mod = 0; mod < g; mod++) {
                for (int j = 0, cur = mod; j < prev.length; j++, cur = (cur + k) % i) {
                    if (cur == 1) {
                        prev[j] = 0;
                    } else {
                        prev[j] = cur == 0 ? dp[0] : dp[cur - 1];
                    }
                }
                suf[prev.length] = 0;
                for (int j = prev.length - 1; j >= 0; j--) {
                    suf[j] = add(mul(suf[j + 1], INV_TWO), mul(prev[j], mul(sum, INV_TWO)));
                }
                pref[0] = 0;
                int f = sum;
                for (int j = 0; j <prev.length; j++) {
                    f = mul(f, INV_TWO);
                    pref[j + 1] = add(pref[j], mul(prev[j], f));
                }
                for (int j = 0, cur = mod; j < prev.length; j++, cur = (cur + k) % i) {
                    int curSum = add(suf[j + 1], mul(pref[j + 1], two[prev.length - j - 1]));
                    newDP[cur] = curSum;
                }
            }
            dp = newDP;
        }
        return dp[0];
    }
}
