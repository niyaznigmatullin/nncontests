package coding;

import ru.ifmo.niyaz.math.Factor;
import ru.ifmo.niyaz.math.MathUtils;

public class ElectronicPet {
    public long[] minimumSec(int[] period1, int[] time1, int[] period2, int[] time2) {
        int q = period1.length;
        long[] ans = new long[q];
        for (int i = 0; i < q; i++) {
            ans[i] = solve(period1[i], time1[i], period2[i], time2[i]);
        }
        return ans;
    }

    private long solve(int p1, int t1, int p2, int t2) {
        if (MathUtils.gcd(p1, p2) > 1) {
            return solveSimple(p1, t1, p2, t2);
        }
        return Math.min(solve2(p1, t1, p2, t2), solve2(p2, t2, p1, t1));
    }

    static long solveSimple(long p1, int t1, long p2, int t2) {
        long way1 = Math.max(1 + p1 * (t1 - 1), p2 * (t2 - 1));
        long way2 = Math.max(1 + p2 * (t2 - 1), p1 * (t1 - 1));
        return Math.min(way1, way2);
    }

    static long solve2(int p1, int t1, int p2, int t2) {
        Factor[] f1 = MathUtils.factorize(p1);
        Factor[] f2 = MathUtils.factorize(p2);
        int phi1 = p1;
        for (Factor e : f1) {
            phi1 -= phi1 / e.prime;
        }
        int phi2 = p2;
        for (Factor e : f2) {
            phi2 -= phi2 / e.prime;
        }
        long repeat1 = (long) MathUtils.modPow(p2, phi1 - 1, p1) * p2;
        long repeat2 = (long) MathUtils.modPow(p1, phi2 - 1, p2) * p1;
        long x1 = repeat1 / p1;
        long y1 = repeat1 / p2;
        long x2 = repeat2 / p1;
        long y2 = repeat2 / p2;
        if (x1 < x2) {
            {
                long t = x1;
                x1 = x2;
                x2 = t;
            }
            {
                long t = y1;
                y1 = y2;
                y2 = t;
            }
            {
                long t = repeat1;
                repeat1 = repeat2;
                repeat2 = t;
            }
        }
        if (x1 >= M || x2 >= M) {
            long ans = Long.MAX_VALUE;
            for (long get = 0; get * x1 <= t1; get++) {
                int get2 = (int) ((t1 - get * x1) / x2);
                long other = y1 * get + y2 * get2;
                if (other > t2) {
                    continue;
                }
                int left1 = (int) (t1 - (get * x1 + get2 * x2));
                int left2 = (int) (t2 - (get * y1 + get2 * y2));
                ans = Math.min(ans, repeat1 * get + repeat2 * get2 + solveSimple(p1, left1, p2, left2));
            }
            return ans;
        } else {
            long[] dp = new long[(int) (x1 * x2) + 1];
            return Long.MAX_VALUE;
        }
    }

    static int M = 1000;

}
