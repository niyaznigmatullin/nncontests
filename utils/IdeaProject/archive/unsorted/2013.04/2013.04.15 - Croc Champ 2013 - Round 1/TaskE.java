package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskE {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        Arrays.fill(time, -1);
        Arrays.fill(sh, -1);
        Arrays.fill(wh, -1);
        change = 0;
        int n = in.nextInt();
        int m = in.nextInt();
        int[] a = in.readIntArray(n);
        int[] b = in.readIntArray(n);
        for (int i = 0; i < m; i++) {
            int t = in.nextInt();
            if (t == 1) {
                int x = in.nextInt() - 1;
                int y = in.nextInt() - 1;
                int k = in.nextInt();
                set(y, y + k - 1, x, y);
            } else {
                int x = in.nextInt() - 1;
                int[] d = get(x);
                if (d == null) {
                    out.println(b[x]);
                } else {
                    out.println(a[x - d[1] + d[0]]);
                }
            }
        }
    }

    static int MAXN = 1 << 17;
    static int[] wh = new int[MAXN * 2];
    static int[] sh = new int[MAXN * 2];
    static int[] time = new int[MAXN * 2];

    static int change;

    static void set(int left, int right, int y, int z) {
        left += MAXN;
        right += MAXN;
        ++change;
        while (left <= right) {
            if ((left & 1) == 1) {
                time[left] = change;
                wh[left] = y;
                sh[left] = z;
                ++left;
            }
            if ((right & 1) == 0) {
                time[right] = change;
                wh[right] = y;
                sh[right] = z;
                --right;
            }
            left >>= 1;
            right >>= 1;
        }
    }

    static int[] get(int x) {
        x += MAXN;
        int bestChange = -1;
        int w = -1;
        int s = -1;
        while (x > 0) {
            if (bestChange < time[x]) {
                w = wh[x];
                s = sh[x];
                bestChange = time[x];
            }
            x >>= 1;
        }
        if (bestChange < 0) {
            return null;
        }
        return new int[]{w, s};
    }
}
