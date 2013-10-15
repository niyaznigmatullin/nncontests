package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskD {

    static void set(int[] t, int x, int y) {
        x += t.length / 2;
        t[x] = y;
        int cur = 0;
        while (x > 1) {
            x >>= 1;
            if (cur == 0) {
                t[x] = t[x * 2] | t[x * 2 + 1];
            } else {
                t[x] = t[x * 2] ^ t[x * 2 + 1];
            }
            cur ^= 1;
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] a = in.readIntArray(1 << n);
        int[] t = new int[2 * a.length];
        for (int i = 0; i < 1 << n; i++) {
            set(t, i, a[i]);
        }
        for (int i = 0; i < m; i++) {
            int x = in.nextInt() - 1;
            int y = in.nextInt();
            set(t, x, y);
            out.println(t[1]);
        }
    }
}
