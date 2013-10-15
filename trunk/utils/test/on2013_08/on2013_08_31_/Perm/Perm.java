package lib.test.on2013_08.on2013_08_31_.Perm;



import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Perm {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = 0;
        while (m * m < n) ++m;
        int left = m * m - n;
        int cur = 1;
        List<Integer> ret = new ArrayList<>();
        while (cur <= n) {
            int t = Math.min(left, m - 1);
            left -= t;
            t = m - t;
            for (int i = 0; i < t; i++) {
                ret.add(cur + t - i - 1);
            }
            cur += t;
        }
        out.printArray(ArrayUtils.toPrimitiveArrayInteger(ret));
    }

    static void find9() {
        int n = 7;
        int[] p = new int[n];
        for (int i = 0; i < p.length; i++) {
            p[i] = i;
        }
        int[] best = null;
        int bestD = Integer.MAX_VALUE;
        do {
            int curD = Math.max(longest(p), longest(ArrayUtils.reverse(p)));
            if (curD < bestD) {
                best = p.clone();
                bestD = curD;
            }
        } while (ArrayUtils.nextPermutation(p));
        System.out.println(Arrays.toString(best));
    }

    static int longest(int[] p) {
        int n = p.length;
        int[] dp = new int[n];
        int ret = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (p[i] > p[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ret = Math.max(ret, dp[i]);
        }
        return ret;
    }
}
