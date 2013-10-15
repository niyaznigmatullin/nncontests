package lib.test.on2012_12.on2012_12_22_Codeforces_Round__157__Div__1_.B___Little_Elephant_and_Elections;



import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.io.FastScanner;

import java.util.Arrays;

public class TaskB {

    static final int MOD = 1000000007;

    static int cc(int n, int k) {
        long ret = 1;
        for (int i = 0; i < k; i++) {
            ret = ret * (n - i) / (i + 1);
        }
        return (int) ret;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int m = in.nextInt() + 1;
        String mS = m + "";
        int[] number = new int[mS.length()];
        for (int i = 0; i < mS.length(); i++) {
            number[i] = mS.charAt(i) - '0';
        }
        int[] count = new int[mS.length() + 1];
        long[][][] dp = new long[mS.length() + 1][10][mS.length() + 1];
        dp[0][0][0] = 1;
        for (int i = 1; i <= mS.length(); i++) {
            for (int last = 0; last < 10; last++) {
                for (int lucky = 0; lucky <= i; lucky++) {
                    for (int prev = 0; prev < 10; prev++) {
                        int newLucky = lucky - (last == 4 || last == 7 ? 1 : 0);
                        if (newLucky < 0) {
                            continue;
                        }
                        dp[i][last][lucky] += dp[i - 1][prev][newLucky];
                    }
                }
            }
        }
        int alreadyLucky = 0;
        for (int i = 0; i < mS.length(); i++) {
            for (int cur = 0; cur < number[i]; cur++) {
                for (int otherLucky = 0; alreadyLucky + otherLucky <= mS.length(); otherLucky++) {
                    count[alreadyLucky + otherLucky] += dp[mS.length() - i][cur][otherLucky];
                }
            }
            if (number[i] == 4 || number[i] == 7) {
                ++alreadyLucky;
            }
        }
        --count[0];
        long[] f = new long[mS.length()];
        for (int i = 0; i < mS.length(); i++) {
            part = new int[6];
            z = new int[mS.length()];
            f[i] = go(0, 6, i, count);
        }
        long ans = 0;
        for (int i = 0; i <= mS.length(); i++) {
            for (int j = 0; j < i; j++) {
                ans = (ans + count[i] * f[j]) % MOD;
            }
        }
        out.println(ans);
    }

    static int f(int n) {
        if (n == 0) {
            return 0;
        }
        return f(n / 10) + (n % 10 == 4 || n % 10 == 7 ? 1 : 0);
    }

    static int[] part;
    static int[] z;

    static long go2(int[] count) {
        Arrays.fill(z, 0);
        for (int i : part) {
            z[i]++;
        }
        long ret = 1;
        for (int i = 0; i < z.length; i++) {
            if (z[i] > 0) {
                ret *= c(count[i], z[i]);
                ret %= MOD;
            }
        }
        return ret;
    }

    static long c(int n, int k) {
        long ret = 1;
        for (int i = 0; i < k; i++) {
            ret = ret * (n - i) % MOD;
        }
        return ret;
    }

    static long go(int x, int n, int toGet, int[] count) {
        if (x == n) {
            return toGet == 0 ? go2(count) : 0;
        }
        long ret = 0;
        for (int cur = 0; cur <= toGet && cur < count.length; cur++) {
            part[x] = cur;
            ret = (ret + go(x + 1, n, toGet - cur, count)) % MOD;
        }
        return ret;
    }
}
