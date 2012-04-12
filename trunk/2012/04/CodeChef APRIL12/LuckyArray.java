package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;
import sun.reflect.generics.tree.ArrayTypeSignature;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

public class LuckyArray {

    static final int MAXD = 10;
    static int[] EIGHT;

    static {
        EIGHT = new int[MAXD + 1];
        EIGHT[0] = 1;
        for (int i = 1; i <= MAXD; i++) {
            EIGHT[i] = EIGHT[i - 1] * 8;
        }
    }

    static int add(int a, int b) {
        a += b;
        if (a < 0) {
            return Integer.MAX_VALUE;
        }
        return a;
    }

    static int mul(int a, int b) {
        if ((long) a * b > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        return a * b;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
//        test();
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt() - 1;
        int[] c = new int[n - 1];
        for (int i = 0; i + 1 < n; i++) {
            c[i] = in.nextInt();
        }
        int last = -1;
        int[] answer = new int[n];
        int[] lucks = getLucks(m + 1);
        int[][] dp = new int[MAXD + 1][n];
        for (int i = 0; i <= MAXD; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= MAXD; j++) {
                if (c[n - i - 1] == 1) {
                    dp[j][i] = add(dp[j][i], mul(dp[j][i - 1], lucks[j]));
                } else {
                    for (int z = 0; z <= MAXD; z++) {
                        if (j == z) {
                            continue;
                        }
                        dp[j][i] = add(dp[j][i], mul(dp[z][i - 1], lucks[z]));
                    }
                }
            }
        }
        int prev = -1;
        for (int i = 0; i < n; i++) {
            int left = 0;
            int right = m + 1;
            while (left < right - 1) {
                int mid = left + right >> 1;
                int got = getCount(last, mid, n - i, m, dp, prev);
                if (got > k) {
                    right = mid;
                } else {
                    left = mid;
                }
            }
            if (right > m) {
                out.println(-1);
                return;
            }
            answer[i] = right;
            k -= getCount(last, left, n - i, m, dp, prev);
            last = getD(right);
            if (i + 1 < n) {
                prev = c[i];
            }
        }
        out.printArray(answer);
    }

    static int getCount(int last, int largest, int n, int m, int[][] dp, int prev) {
        if (largest == 0) {
            return 0;
        }
        int[] lucks = getLucks(largest + 1);
        int ret = 0;
        for (int i = 0; i < lucks.length; i++) {
            if ((last == i) != (prev == 1)) {
                continue;
            }
            ret = add(ret, mul(lucks[i], dp[i][n - 1]));
        }
        return ret;
    }

    static void test() {
        Random rand = new Random(123123L);
        while (true) {
            int x = rand.nextInt(10000) + 1;
            int[] a = getLucks(x);
            int[] b = new int[MAXD + 1];
            for (int i = 1; i < x; i++) {
                b[getD(i)]++;
            }
            if (!Arrays.equals(a, b)) {
                System.err.println(x);
                System.err.println(Arrays.toString(a));
                System.err.println(Arrays.toString(b));
                throw new AssertionError();
            }
        }
    }

    static int[] getLucks(int m) {
        int[] ret = new int[MAXD + 1];
        int[] d = new int[countDigits(m)];
        for (int i = d.length - 1, j = m; i >= 0; i--, j /= 10) {
            d[i] = j % 10;
        }
        for (int i = 0; i <= MAXD; i++) {
            int current = i;
            int ans = 0;
            for (int j = 0; j < d.length && current >= 0 && j + current <= d.length; j++) {
                for (int cur = 0; cur < d[j]; cur++) {
                    if (cur == 4 || cur == 7) {
                        if (current == 0) {
                            continue;
                        }
                        ans += EIGHT[d.length - j - 1 - (current - 1)] * (1 << current - 1) * c(d.length - j - 1, current - 1);
                    } else {
                        if (current + j < d.length) {
                            ans += EIGHT[d.length - j - 1 - current] * (1 << current) * c(d.length - j - 1, current);
                        }
                    }
                }
                if (d[j] == 4 || d[j] == 7) {
                    --current;
                }
            }
            ret[i] = ans;
        }
        --ret[0];
        return ret;
    }

    static int c(int n, int k) {
        if (k > n || k < 0) {
            return 0;
        }
        int ret = 1;
        for (int i = 0; i < k; i++) {
            ret = ret * (n - i) / (i + 1);
        }
        return ret;
    }

    static int countDigits(int x) {
        return x == 0 ? 0 : 1 + countDigits(x / 10);
    }

    static int getD(int x) {
        return x == 0 ? 0 : (x % 10 == 4 || x % 10 == 7 ? 1 : 0) + getD(x / 10);
    }
}
