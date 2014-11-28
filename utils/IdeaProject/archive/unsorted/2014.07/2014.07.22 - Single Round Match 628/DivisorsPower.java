package coding;

import ru.ifmo.niyaz.math.MathUtils;

public class DivisorsPower {
    public long findArgument(long n) {
        long ans = -1;
        for (int pow = 2;; pow++) {
            long r = (long) Math.pow(n, 1. / pow);
            while (power(r, pow) < n) {
                ++r;
            }
            while (power(r, pow) > n) {
                --r;
            }
            if (r <= 1) break;
            if (power(r, pow) == n) {
                int divs = 0;
                for (int i = 1; i * i <= r; i++) {
                    if (r % i == 0) divs += 2;
                    if (i * i == r) divs--;
                }
                if (divs == pow) {
                    ans = r;
                }
            }
        }
        return ans;
    }

    static long power(long a, int b) {
        if (a == 0) return 0;
        long ret = 1;
        for (int i = 0; i < b; i++) {
            if (Long.MAX_VALUE / a < ret) return Long.MAX_VALUE;
            ret *= a;
        }
        return ret;
    }
}
