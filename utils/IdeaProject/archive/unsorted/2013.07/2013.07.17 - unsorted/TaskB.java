package coding;

import ru.ifmo.niyaz.DataStructures.Fenwick;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] p = in.readIntArray(n);
        for (int i = 0; i < n; i++) p[i]--;
//        int q = in.nextInt();
//        for (int it = 0; it < q; it++) {
//            int type = in.nextInt();
//            int x = in.nextInt() - 1;
//            int y = in.nextInt() - 1;
//            if (type == 1) {
//                out.println(getAns(p, x, y));
//            } else {
//                int t = p[x];
//                p[x] = p[y];
//                p[y] = t;
//            }
//        }
        Fenwick f = new Fenwick(n);
        int[] rev = new int[n];
        for (int i = 0; i < n; i++) rev[p[i]] = i;
        for (int i = 0; i < n; i++) {
            if (i == 0 || rev[i] < rev[i - 1]) {
                f.add(i, 1);
            }
        }
        int q = in.nextInt();
        for (int it = 0; it < q; it++) {
            int type = in.nextInt();
            int x = in.nextInt() - 1;
            int y = in.nextInt() - 1;
            if (type == 1) {
                out.println(1 + f.getSum(y) - f.getSum(x));
            } else {
                int t = p[x];
                p[x] = p[y];
                p[y] = t;
                rev[p[x]] = x;
                rev[p[y]] = y;
                for (int i = p[x] - 1; i <= p[x] + 1; i++) {
                    if (i < 0 || i >= n) continue;
                    int val;
                    if (i == 0 || rev[i] < rev[i - 1]) {
                        val = 1;
                    } else {
                        val = 0;
                    }
                    f.add(i, val - (f.getSum(i) - f.getSum(i - 1)));
                }
                for (int i = p[y] - 1; i <= p[y] + 1; i++) {
                    if (i < 0 || i >= n) continue;
                    int val;
                    if (i == 0 || rev[i] < rev[i - 1]) {
                        val = 1;
                    } else {
                        val = 0;
                    }
                    f.add(i, val - (f.getSum(i) - f.getSum(i - 1)));
                }
            }
        }
    }

//    static int getAns(int[] p, int left, int right) {
//        int n = p.length;
//        int[] dp = new int[n + 1];
//        Arrays.fill(dp, Integer.MAX_VALUE);
//        dp[0] = Integer.MIN_VALUE;
//        int cur = 1;
//        for (int i = n - 1; i >= 0; i--) {
//            if (p[i] < left || p[i] > right) continue;
//            int x = -p[i];
//            int l = 0;
//            int r = cur;
//            while (l < r - 1) {
//                int mid = l + r >> 1;
//                if (dp[mid] > x) {
//                    r = mid;
//                } else {
//                    l = mid;
//                }
//            }
//            dp[r] = x;
//            if (r == cur) {
//                ++cur;
//            }
//        }
//        return right - left + 1 - cur + 2;
//    }
}
