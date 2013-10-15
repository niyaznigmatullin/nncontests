package lib.test.on2013_08.on2013_08_27_.Hex;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class Hex {

    static long[] answer = {2, 20, 980, 232848, 267227532, 1478619421136L, 39405996318420160L};

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        out.println(answer[n - 1]);
        if (true) return;
        long[] dp = new long[1 << n];
        dp[0] = 1;
        for (int i = 0; i < n; i++) {
            long[] next = new long[1 << n + i + 1];
            for (int mask = 0; mask < 1 << (n + i); mask++) {
                long val = dp[mask];
                if (val == 0) continue;
                for (int mask2 = 0; mask2 < 1 << (n + i + 1); mask2++) {
                    if (canDo(mask2, mask, n + i)) {
//                        System.out.println(mask + " " + mask2);
                        next[((1 << n + i + 1) - 1) ^ mask2] += val;
                    }
                }
            }
            dp = next;
        }
//        System.out.println(Arrays.toString(dp));
        long ans = 0;
        for (int i = 0; i < 1 << n + n; i++) {
            ans += dp[i] * dp[i];
        }
        out.println(ans);
    }

    static boolean canDo(int mask, int mask2, int k) {
//        System.out.println("cando " + mask + " " + mask2 + " " + k);
        for (int i = 0; i <= k; i++) {
            if (((mask >> i) & 1) == 0) continue;
            if (i > 0 && ((mask2 >> i - 1) & 1) == 0) {
                mask2 |= (1 << i - 1);
                continue;
            }
            if (i < k && ((mask2 >> i) & 1) == 0) {
                mask2 |= 1 << i;
                continue;
            }
            return false;
        }
//        System.out.println(mask + " " + mask2 + " " + k);
        if (mask2 != ((1 << k) - 1)) return false;
        return true;
    }
}
