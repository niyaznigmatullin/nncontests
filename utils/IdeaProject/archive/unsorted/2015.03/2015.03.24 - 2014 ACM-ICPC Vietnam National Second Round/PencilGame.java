package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.MathUtils;

import java.util.Arrays;

public class PencilGame {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        long L = in.nextLong();
        long ans = solve(n, m, L);
//        if (stupid(n, m, L) != solve(n, m, L)) {
//            System.out.println(n + " " + m + " " + L);
//            System.out.println(stupid(n, m, L) + " " + solve(n, m, L));
//            throw new AssertionError();
//        }
        out.println(ans == Long.MAX_VALUE ? -1 : ans);
    }

    static long stupid(int n, int m, long L) {
        long ans = Long.MAX_VALUE;
        for (int i1 = 0; i1 < n; i1++) {
            for (int j1 = 0; j1 < m; j1++) {
                for (int i2 = i1 + 1; i2 <= n; i2++) {
                    for (int j2 = j1 + 1; j2 <= m; j2++) {
                        int sum = 0;
                        for (int i = i1; i < i2; i++) {
                            for (int j = j1; j < j2; j++) {
                                sum += i * m + j;
                            }
                        }
                        if (sum == L) {
                            ans = Math.min(ans, (i2 - i1) * (j2 - j1));
//                            System.err.println(i1 + " " + i2 + " " + j1 + " " + j2);
                        }
                    }
                }
            }
        }
        return ans;
    }

    static long solve(int n, int m, long L) {
        //        long[] z = new long[12345];
//        int cn = 0;
//        long s = L * 4;
//        for (long i = 1; i * i <= s; i++) {
//            if (s % i != 0) continue;
//            z[cn++] = i;
//            if (i * i != s) z[cn++] = s / i;
//        }
//        z = Arrays.copyOf(z, cn);
//        Arrays.sort(z);
        long ans = Long.MAX_VALUE;
        for (long h = 1; h <= n; h++) {
            if (h > n) break;
            for (long w = 1; w <= m; w++) {
                long area = h * w;
                if (w > m || area >= ans) break;
                long sum1 = (w * (w - 1) >> 1) * h + (h * (h - 1) >> 1) * w * m;
                if (L < sum1) break;
                if (sum1 % area != L % area) continue;
                long dif = (L - sum1) / area;
                if (dif >= (n - h + 1) * m) continue;
                dif %= m;
                if (dif >= m - w + 1) {
                    continue;
                }
                ans = area;
            }
        }
        return ans;
    }
}
