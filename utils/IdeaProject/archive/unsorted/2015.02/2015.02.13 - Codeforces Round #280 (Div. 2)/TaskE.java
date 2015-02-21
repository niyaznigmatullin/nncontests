package coding;

import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskE {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int dx = in.nextInt();
        int dy = in.nextInt();
        if (dx == 0 && dy == 0) {
            long[] points = new long[m];
            for (int i = 0; i < m; i++) points[i] = in.nextInt() * n + in.nextInt();
            ArrayUtils.sort(points);
            int ans = Integer.MIN_VALUE;
            int ansx = -1;
            int ansy = -1;
            for (int i = 0; i < m; ) {
                int j = i;
                while (j < m && points[i] == points[j]) {
                    ++j;
                }
                if (j - i > ans) {
                    ans = j - i;
                    ansx = (int) (points[i] / n);
                    ansy = (int) (points[i] % n);
                }
                i = j;
            }
            out.println(ansx + " " + ansy);
            return;
        }
        if (dx == 0 || dy == 0) {
            int[] rows = new int[n];
            int[] cols = new int[n];
            for (int i = 0; i < m; i++) {
                int x = in.nextInt();
                int y = in.nextInt();
                rows[x]++;
                cols[y]++;
            }
            int ans = Integer.MIN_VALUE;
            int ansx = -1;
            int ansy = -1;
            if (dx == 0) {
                for (int i = 0; i < n; i++) {
                    if (rows[i] > ans) {
                        ans = rows[i];
                        ansx = i;
                        ansy = 0;
                    }
                }
            } else {
                for (int i = 0; i < n; i++) {
                    if (cols[i] > ans) {
                        ans = cols[i];
                        ansx = 0;
                        ansy = i;
                    }
                }
            }
            out.println(ansx + " " + ansy);
            return;
        }
        int[] times = new int[n];
        for (int i = 0; i < n; i++) {
            times[((int) ((long) dx * i % n))] = i;
        }
        int[] cnt = new int[n];
        for (int i = 0; i < m; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            int t = times[x];
            x = (int) ((x + n - (long) t * dx % n) % n);
            if (x != 0) throw new AssertionError();
            y = (int) ((y + n - (long) t * dy % n) % n);
            cnt[y]++;
        }
        int ans = Integer.MIN_VALUE;
        int ansx = -1;
        int ansy = -1;
        for (int i = 0; i < n; i++) {
            if (cnt[i] > ans) {
                ans = cnt[i];
                ansx = 0;
                ansy = i;
            }
        }
        out.println(ansx + " " + ansy);
    }
}
