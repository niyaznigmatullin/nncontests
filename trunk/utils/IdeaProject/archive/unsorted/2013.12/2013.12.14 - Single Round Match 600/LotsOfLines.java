package coding;

import ru.ifmo.niyaz.math.MathUtils;

public class LotsOfLines {
    public long countDivisions(int a, int b) {
        int lines = a * b;
        long ans = lines + 1;
        for (int p = -b + 1; p <= b - 1; p++) {
            for (int q = 1; q <= a - 1; q++) {
                if (MathUtils.gcd(p, q) != 1) {
                    continue;
                }
                long cur = 0;
                int all = q;
                int just = a - all;
                if (p == 0) {
                    cur += (a - 1) * b;
                } else {
                    cur -= (long) just * Math.abs(p);
                    cur -= (long) all * b;
                    cur += a * b;
                }
                ans += cur;
            }
        }
        return ans;
    }
}
