package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskC {
    static int[] x;
    static int[] y;
    static int[] a;
    static int[] b;
    static long[] d;
    static int ans;

    static void go(int cur, int n, int done) {
        if (done >= ans) return;
        if (cur == n) {
            int cn = 0;
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    long dx = x[i] - x[j];
                    long dy = y[i] - y[j];
                    d[cn++] = dx * dx + dy * dy;
                }
            }
            Arrays.sort(d);
            if (d[0] != d[3] || d[0] == 0) return;
            if (d[4] != d[5] || d[4] != 2 * d[0]) return;
            ans = done;
            return;
        }
        for (int i = 0; i < 4; i++) {
            go(cur + 1, n, done + i);
            int dx = x[cur] - a[cur];
            int dy = y[cur] - b[cur];
            x[cur] = a[cur] - dy;
            y[cur] = b[cur] + dx;
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int tests = in.nextInt();
        d = new long[6];
        for (int curTest = 0; curTest < tests; curTest++) {
            x = new int[4];
            y = new int[4];
            a = new int[4];
            b = new int[4];
            for (int i = 0; i < 4; i++) {
                x[i] = in.nextInt();
                y[i] = in.nextInt();
                a[i] = in.nextInt();
                b[i] = in.nextInt();
            }
            ans = 123;
            go(0, 4, 0);
            out.println(ans == 123 ? -1 : ans);
        }
    }
}
