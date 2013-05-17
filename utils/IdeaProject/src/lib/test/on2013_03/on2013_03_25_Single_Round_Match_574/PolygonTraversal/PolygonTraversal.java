package lib.test.on2013_03.on2013_03_25_Single_Round_Match_574.PolygonTraversal;



public class PolygonTraversal {
    public long count(int n, int[] points) {
        for (int i = 0; i < points.length; i++) {
            --points[i];
        }
        int startMask = 0;
        for (int i : points) {
            startMask |= 1 << i;
        }
        int start = points[0];
        int end = points[points.length - 1];
        long[][] dp = new long[n][1 << n];
        dp[end][startMask] = 1;
        for (int mask = startMask; mask < 1 << n; mask++) {
            for (int last = 0; last < n; last++) {
                if (((mask >> last) & 1) == 0) {
                    continue;
                }
                long val = dp[last][mask];
                if (val == 0) {
                    continue;
                }
                for (int next = 0; next < n; next++) {
                    if (((mask >> next) & 1) == 1) {
                        continue;
                    }
                    boolean ok1 = false;
                    boolean ok2 = false;
                    for (int k = 0; k < n; k++) {
                        if (k == last || k == next || ((mask >> k) & 1) == 0) {
                            continue;
                        }
                        if (bet(k, last, next)) {
                            ok1 = true;
                        }
                        if (bet(k, next, last)) {
                            ok2 = true;
                        }
                    }
                    if (ok1 && ok2) {
                        dp[next][mask | (1 << next)] += val;
                    }
                }
            }
        }
        long ret = 0;
        for (int i = 0; i < n; i++) {
            if ((i + 1) % n != start && (start + 1) % n != i) {
                ret += dp[i][(1 << n) - 1];
            }
        }
        return ret;
    }

    static boolean bet(int i, int left, int right) {
        if (left < right) {
            return left < i && i < right;
        } else {
            return i > left || i < right;
        }
    }
}
