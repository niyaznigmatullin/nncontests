package lib.test.on2013_09.on2013_09_14_Codeforces_Round__200__Div__1_.C___Read_Time;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        long[] h = new long[n];
        for (int i = 0; i < n; i++) {
            h[i] = in.nextLong();
        }
        long[] p = new long[m];
        for (int i = 0; i < m; i++) {
            p[i] = in.nextLong();
        }
        long l = -1;
        long r = Long.MAX_VALUE / 3;
        while (l < r - 1) {
            long mid = l + r >> 1;
            int cur = 0;
            boolean ok = true;
            for (int i = 0; i < n; i++) {
                long first = h[i];
                if (cur < m && p[cur] < first) {
                    first = p[cur];
                }
                while (cur < m && p[cur] <= first) {
                    ++cur;
                }
                if (h[i] - first > mid) {
                    ok = false;
                    break;
                }
                while (cur < m && timeToDo(h[i], first, p[cur]) <= mid) {
                    ++cur;
                }
            }
            if (cur != m) ok = false;
            if (!ok) {
                l = mid;
            } else {
                r = mid;
            }
        }
        out.println(r);
    }

    static long timeToDo(long mid, long l, long r) {
        return r - l + Math.min(r - mid, mid - l);
    }
}
