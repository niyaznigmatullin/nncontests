package coding;

import ru.ifmo.niyaz.arrayutils.ArrayUtils;

import java.util.Arrays;

public class MinimumSquare {
    public long minArea(int[] x, int[] y, int K) {
        int n = x.length;
        int[] cy = new int[n];
        int[] xs = ArrayUtils.sortAndUnique(x);
        long ans = Long.MAX_VALUE;
        for (int x1 : xs) {
            for (int x2 : xs) {
                if (x2 < x1) continue;
                int cn = 0;
                for (int i = 0; i < n; i++) {
                    if (x[i] >= x1 && x[i] <= x2) {
                        cy[cn++] = y[i];
                    }
                }
                Arrays.sort(cy, 0, cn);
                for (int i = 0; i + K <= cn; i++) {
                    long max = Math.max(cy[i + K - 1] - cy[i], x2 - x1) + 2;
                    ans = Math.min(ans, max * max);
                }
             }
        }
        return ans;
    }
}
