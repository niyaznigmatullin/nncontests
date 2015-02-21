package coding;

import ru.ifmo.niyaz.geometry.GeometryAlgorithms;
import ru.ifmo.niyaz.geometry.Point2DInteger;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskE {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int r = in.nextInt();
        ax = new int[12345];
        ay = new int[ax.length];
        cn = 0;
        for (int x = -r; x <= r; x++) {
            int y = 0;
            while (y * y + x * x <= r * r) ++y;
            --y;
            ax[cn] = x;
            ay[cn++] = y;
            if (y > 0) {
                ax[cn] = x;
                ay[cn++] = -y;
            }
        }
        Point2DInteger[] pp = new Point2DInteger[cn];
        for (int i = 0; i < cn; i++) {
            pp[i] = new Point2DInteger(ax[i], ay[i]);
        }
        pp = GeometryAlgorithms.convexHull(pp);
        cn = pp.length;
        for (int i = 0; i < cn; i++) {
            ax[i] = pp[i].x;
            ay[i] = pp[i].y;
        }
        d = new int[cn][cn];
        for (int i = 0; i < cn; i++) {
            for (int j = 0; j < cn; j++) {
                int dx = ax[i] - ax[j];
                int dy = ay[i] - ay[j];
                d[i][j] = dx * dx + dy * dy;
            }
        }
        best = new int[n + 1];
        answer = new int[n + 1][];
        cur = new int[n];
        go(0, n, 0, 0);
        out.println(best[n]);
        for (int i : answer[n]) {
            out.println(ax[i] + " " + ay[i]);
        }
    }

    static int[] cur;

    static int[][] answer;
    static int[] best;
    static int[] ax;
    static int[] ay;

    static void go(int x, int n, int last, int collect) {
        if (x == n) {
            if (collect > best[x]/* || collect == best[x] && count(answer[x]) > count(Arrays.copyOf(cur, x))*/) {
                best[x] = collect;
                answer[x] = Arrays.copyOf(cur, x);
            }
            return;
        }
        for (int i = last; i < cn; i++) {
            cur[x] = i;
            int add = 0;
            for (int j = 0; j < x; j++) add += dist(cur[j], i);
            go(x + 1, n, i, collect + add);
        }
    }

    static int cn;
    static int[][] d;

    static int count(int... a) {
        if (a == null) return 123;
        int[] x = a.clone();
        Arrays.sort(x);
        int ret = 1;
        for (int i = 1; i < x.length; i++) ret += x[i] != x[i - 1] ? 1 : 0;
        return ret;
    }

    static int dist(int i, int j) {
        return d[i][j];
    }
}
