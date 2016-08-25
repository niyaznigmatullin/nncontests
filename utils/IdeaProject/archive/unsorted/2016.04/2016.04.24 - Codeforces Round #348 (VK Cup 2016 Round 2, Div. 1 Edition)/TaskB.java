package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskB {
    static class Group {
        int[] a;
        int shift;

        public Group(int[] a) {
            this.a = a;
            shift = 0;
        }

        void shift(int x) {
            shift = (shift - x + a.length) % a.length;
        }

        int get(int x) {
            return a[(shift + x) % a.length];
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int q = in.nextInt();
        Group[] g = new Group[2];
        int[] a = new int[n >> 1];
        for (int i = 0; i < n; i += 2) {
            a[i / 2] = i;
        }
        g[0] = new Group(a.clone());
        for (int i = 1; i < n; i += 2) {
            a[i / 2] = i;
        }
        g[1] = new Group(a.clone());
        for (int i = 0; i < q; i++) {
            int t = in.nextInt();
            if (t == 2) {
                Group tt = g[0];
                g[0] = g[1];
                g[1] = tt;
            } else {
                int x = in.nextInt();
                x = (x % n + n) % n;
                g[0].shift(x / 2);
                g[1].shift((x + 1) / 2);
                if ((x & 1) == 1) {
                    Group tt = g[0];
                    g[0] = g[1];
                    g[1] = tt;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < (n >> 1); i++) {
            if (i > 0) sb.append(' ');
            sb.append(g[0].get(i) + 1);
            sb.append(' ');
            sb.append(g[1].get(i) + 1);
        }
        out.println(sb);
    }
}
