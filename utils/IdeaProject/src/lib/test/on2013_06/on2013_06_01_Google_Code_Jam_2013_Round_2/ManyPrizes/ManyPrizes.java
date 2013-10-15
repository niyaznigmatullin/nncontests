package lib.test.on2013_06.on2013_06_01_Google_Code_Jam_2013_Round_2.ManyPrizes;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class ManyPrizes {
    static final int[][] dp;

    static {
        dp = new int[11][];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = new int[1 << i];
        }
        dp[0][0] = 0;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < 1 << i; j++) {
                if (j + 1 == (1 << i)) {
                    dp[i][j] = (1 << i) - 1;
                } else {
                    dp[i][j] = dp[i - 1][(j + 1) / 2];
                }
            }
        }
    }

    static long minimalPlace(int n, long x) {
        if (x == (1L << n) - 1) {
            return x;
        }
        return minimalPlace(n - 1, (x + 1) / 2);
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        out.print("Case #" + testNumber + ": ");
        System.err.println("[" + testNumber + "]");
        int n = in.nextInt();
        long k = in.nextLong();
//        int ans1 = 0;
//        int ans2 = 0;
//        for (int i = 0; i < 1 << n; i++) {
//            int minimal = dp[n][i];
//            int maximal = (1 << n) - dp[n][(1 << n) - i - 1] - 1;
//            if (minimal < k) {
//                ans2 = i;
//            }
//            if (maximal < k) {
//                ans1 = i;
//            }
////            System.out.println(i + " " + minimal + " " + maximal);
//        }
        long l = -1;
        long r = 1L << n;
        while (l < r - 1) {
            long mid = (l + r) >> 1;
            if (minimalPlace(n, mid) < k) {
                l = mid;
            } else {
                r = mid;
            }
        }
        long ans1 = l;
        l = -1;
        r = 1L << n;
        while (l < r - 1) {
            long mid = (l + r) >> 1;
            if ((1L << n) - 1 - minimalPlace(n, (1L << n) - mid - 1) < k) {
                l = mid;
            } else {
                r = mid;
            }
        }
        long ans2 = l;
        out.println(ans2 + " " + ans1);
    }
}
