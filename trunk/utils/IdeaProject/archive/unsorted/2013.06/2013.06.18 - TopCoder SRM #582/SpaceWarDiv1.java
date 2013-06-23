package coding;

import java.util.Arrays;

public class SpaceWarDiv1 {
    public long minimalFatigue(int[] a, int[] b, long[] c) {
        Arrays.sort(a);
        long l = -1;
        long r = Long.MAX_VALUE / 3;
        while (l < r - 1) {
            long mid = l + r >>> 1;
            long[] curCount = c.clone();
            for (int q = 0; q < a.length; q++) {
                long z = mid;
                while (z > 0) {
                    int best = -1;
                    for (int i = 0; i < curCount.length; i++) {
                        if (curCount[i] == 0) continue;
                        if (a[q] >= b[i]) {
                            if (best < 0 || b[best] < b[i]) {
                                best = i;
                            }
                        }
                    }
                    if (best < 0) break;
                    long get = Math.min(z, curCount[best]);
                    z -= get;
                    curCount[best] -= get;
                }
            }
            boolean ok = true;
            for (long i : curCount) {
                if (i > 0) {
                    ok = false;
                    break;
                }
            }
            if (ok) r = mid; else l = mid;
        }
        return r == Long.MAX_VALUE / 3 ? -1 : r;
    }
}
