package coding;

import ru.ifmo.niyaz.math.MathUtils;

public class LittleElephantAndBoard {

    static final int MOD = 1000000007;

    static int modInverse(int x) {
        return MathUtils.modPow(x, MOD - 2, MOD);
    }

    static int mul(int a, int b) {
        long ret = (long) a * b % MOD;
        return (int) ret;
    }

    static int f(int n, int k) {
        return c(n + k - 1, k - 1);
    }

    static int c(int n, int k) {
        if (n < 0) return 0;
        if (k < 0 || k > n) return 0;
        return mul(mul(fact[n], invFact[n - k]), invFact[k]);
    }

    static int[] fact;
    static int[] invFact;
    static int[] powTwo;
    static final int MAXN = 1234567;
    static {
        fact = new int[MAXN];
        invFact = new int[MAXN];
        invFact[0] = fact[0] = 1;
        for (int i = 1; i < MAXN; i++) {
            fact[i] = mul(fact[i - 1], i);
            invFact[i] = modInverse(fact[i]);
        }
        powTwo = new int[MAXN];
        powTwo[0] = 1;
        for (int i = 1; i < MAXN; i++) {
            powTwo[i] = mul(powTwo[i - 1], 2);
        }
    }

    public int getNumber(int M, int R, int G, int B) {
        int a = M - R;
        int b = M - G;
        int c = M - B;
        long ans = 0;
        for (int i = a - 1; i <= a + 1; i++) {
            if (i < 0) continue;
            for (int odd = 0; odd <= i; odd++) {
                int even = i - odd;
                int left = M - a - 2 * even - odd;
                if ((left & 1) != 0 || left < 0) {
                    continue;
                }
                if (odd < Math.abs(b - c)) {
                    continue;
                }
                int c1 = c(even + odd, even);
                left /= 2;
                int c2 = f(left, even + odd);
                int c3 = c(odd, (odd - Math.abs(b - c)) / 2);
                int ways = mul(mul(c1, c2), c3);
                ways = mul(ways, powTwo[even]);
                if (i == a) {
                    ways = mul(2, ways);
                }
                ans += ways;
                if (ans >= MOD) ans -= MOD;
            }
        }
        ans += ans;
        return (int) (ans % MOD);
    }
}
