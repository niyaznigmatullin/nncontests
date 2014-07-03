package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.MathUtils;

import java.math.BigInteger;
import java.util.Arrays;

public class TaskD {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int[] primes = MathUtils.genPrimes(50);
        out.print("Case #" + testNumber + ": ");
        System.err.println("[" + testNumber + "]");
        int n = in.nextInt();
        int k = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        int answer = 0;
        for (int i = 0; i < n; i++) {
            int x = (a[i] + k - 1) / k * k;
            answer += x - a[i];
            a[i] = x;
        }
        for (int i = 0; i < n; i++) {
            a[i] /= k;
        }
        boolean canZero = true;
        for (int i = 0; i < n; i++) {
            if (a[i] > 1) {
                canZero = false;
            }
        }
        if (canZero) {
            int zero = -1;
            for (int i = 0; i < n; i++) {
                if (a[i] == 0) {
                    zero = i;
                    break;
                }
            }
            for (int i = 0; i < n; i++) {
                if (zero == i) {
                    continue;
                }
                if (a[i] == 0) {
                    answer += k;
                    a[i] = 1;
                }
            }
            out.println(answer);
            return;
        }
        Arrays.sort(a);
        int m = primes.length;
        final int M = 150;
        int[][] dp = new int[M][1 << m];
        for (int[] d : dp) {
            Arrays.fill(d, Integer.MAX_VALUE);
        }
        dp[0][0] = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] == 0) {
                answer += k;
                ++a[i];
            }
        }
        int[] allMasks = new int[M];
        for (int j = 1; j < M; j++) {
            int z = j;
            for (int i = 0; i < m; i++) {
                int p = primes[i];
                if (j % p == 0) {
                    allMasks[j] |= 1 << i;
                    while (z % p == 0) {
                        z /= p;
                    }
                }
            }
            if (z > 1 && allMasks[j] != 0) {
                allMasks[j] = -1;
            }
        }
        int[][] next = new int[M][1 << m];
        for (int x : a) {
            if (x == 1) {
                continue;
            }
            for (int[] d : next) {
                Arrays.fill(d, Integer.MAX_VALUE);
            }
            for (int mask = 0; mask < 1 << m; mask++) {
                int best = Integer.MAX_VALUE;
                for (int j = 0; j < M; j++) {
                    if (j >= x && best != Integer.MAX_VALUE) {
                        int nMask = allMasks[j];
                        if (nMask >= 0 && (mask & nMask) == 0) {
                            next[j][mask | nMask] = Math.min(next[j][mask | nMask], best + j - x);
                        }
                    }
                    if (best > dp[j][mask]) {
                        best = dp[j][mask];
                    }
                }
            }
            int[][] t = dp; dp = next; next = t;
        }
        int ans2 = Integer.MAX_VALUE;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < 1 << m; j++) {
                ans2 = Math.min(ans2, dp[i][j]);
            }
        }
        out.println(answer + ans2 * k);
//        BigInteger n = BigInteger.ONE;
//        while (true) {
//            n = n.nextProbablePrime();
//            if (n.compareTo(BigInteger.valueOf(50)) > 0) {
//                break;
//            }
//            out.println(n);
//        }
//        n = BigInteger.valueOf(50);
//        for (int i = 0; i < 20; i++) {
//            n = n.nextProbablePrime();
//            out.println(n);
//        }
    }
}
