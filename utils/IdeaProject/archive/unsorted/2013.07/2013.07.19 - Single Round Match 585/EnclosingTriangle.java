package coding;

import java.util.Arrays;

public class EnclosingTriangle {

    static long vmulFromPoint(int xFrom, int yFrom, int x1, int y1, int x2, int y2) {
        x1 -= xFrom;
        y1 -= yFrom;
        x2 -= xFrom;
        y2 -= yFrom;
        return (long) x1 * y2 - (long) x2 * y1;
    }

    static long f(long n) {
        return n * (n - 1) / 2;
    }

    public long getNumber(int m, int[] x, int[] y) {
        int all = 4 * m;
        int[] xs = new int[all];
        int[] ys = new int[all];
        for (int i = 0; i < m; i++) {
            xs[i] = i;
            ys[i] = 0;
        }
        for (int i = 0; i < m; i++) {
            xs[i + m] = m;
            ys[i + m] = i;
        }
        for (int i = 0; i < m; i++) {
            xs[i + 2 * m] = m - i;
            ys[i + 2 * m] = m;
        }
        for (int i = 0; i < m; i++) {
            xs[i + m * 3] = 0;
            ys[i + m * 3] = m - i;
        }
        int n = x.length;
        int[] t1 = new int[all];
        int[] t2 = new int[all];
        for (int i = 0, j = 0; i < all; i++) {
            while (true) {
                boolean ok = true;
                for (int k = 0; k < n; k++) {
                    if (vmulFromPoint(x[k], y[k], xs[i], ys[i], xs[j], ys[j]) < 0) {
                        ok = false;
                        break;
                    }
                }
                if (ok) {
                    ++j;
                    if (j == all) j = 0;
                    continue;
                }
                t1[i] = j;
                break;
            }
        }
        for (int i = all - 1, j = all - 1; i >= 0; i--) {
            while (true) {
                boolean ok = true;
                for (int k = 0; k < n; k++) {
                    if (vmulFromPoint(x[k], y[k], xs[i], ys[i], xs[j], ys[j]) > 0) {
                        ok = false;
                        break;
                    }
                }
                if (ok) {
                    --j;
                    if (j < 0) j += all;
                    continue;
                }
                t2[i] = j;
                break;
            }
        }
        if (n == 0) {
            long ans = 0;
            for (int i = 0, j = 0; i < all; i++) {
                while (true) {
                    boolean ok = true;
                    for (int k = 0; k < n; k++) {
                        if (vmulFromPoint(x[k], y[k], xs[i], ys[i], xs[j], ys[j]) <= 0) {
                            ok = false;
                            break;
                        }
                    }
                    if (i == j || ok) {
                        ++j;
                        if (j == all) j = 0;
                        continue;
                    }
                    int count = j - i;
                    if (count < 0) {
                        count += all;
                    }
                    ans += f(count - 1);
                    break;
                }
            }
//            ans -= 4L * (m + 1) * m * (m - 1) / 6;
            ans = (long) all * (all - 1) * (all - 2) / 6 - ans;
            return ans;
        }

        long[] sum = new long[all];
        for (int i = 0; i < all; i++) {
            sum[i] = i == 0 ? 0 : sum[i - 1];
            sum[i] += t2[i];
        }
        long ans = 0;
        long ans2 = 0;
        for (int i = 0; i < all; i++) {
            int k = t1[i];
            if (k < i) k = all;
            int j = t2[i];
            if (j > i) j = -1;
            int l = j;
            int r = i;
            while (l < r - 1) {
                int mid = (l + r) >> 1;
                if (t2[mid] + 1 < i || t2[mid] + 1 >= k) r = mid;
                else l = mid;
            }
            if (l == j) continue;
            ans -= sum[l];
            if (j >= 0)
                ans += sum[j];
            ans2 -= sum[l];
            ans2 += sum[j + 1];
            if (t2[j + 1] + 1 == i) {
                ans2 -= i + 1;
            } else {
                ans2 -= t2[j + 1];
            }
            ans += (long) (k - 1) * (l - j);
            ans2 += (long) (k - 1) * (l - j);
        }
        long dif = ans - ans2;
        return ans - dif / 2;
    }
}
