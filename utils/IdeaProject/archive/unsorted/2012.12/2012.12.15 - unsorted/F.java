package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.BitSet;

public class F {

    static class DSU {
        int[] p;
        BitSet bit;

        DSU(int n) {
            p = new int[n];
            for (int i = 0; i < n; i++) {
                p[i] = i;
            }
            bit = new BitSet(n);
        }

        int get(int x) {
            boolean cur = false;
            int last = x;
            for (int i = x; i != p[i]; i = p[i]) {
                cur ^= bit.get(i);
                last = p[i];
            }
            for (int i = x; i != p[i]; ) {
                int go = p[i];
                p[i] = last;
                boolean next = cur ^ bit.get(i);
                bit.set(i, cur);
                cur = next;
                i = go;
            }
            return last;
        }

        boolean union(int x, int y, boolean z) {
            int i = get(x);
            int j = get(y);
//            System.out.println(x + " " + y + " " + z + " " + i + " " + j);
            z = bit.get(x) ^ bit.get(y) ^ z;
            if (i == j) {
                return !z;
            }
            x = i;
            y = j;
            if (x > y) {
                p[x] = y;
                bit.set(x, z);
            } else {
                p[y] = x;
                bit.set(y, z);
            }
            return true;
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        DSU dsu = new DSU(n * 32);
        int[] shift = new int[n];
        for (int i = 0; i < m; i++) {
            int type = in.nextInt();
            if (type == 1) {
                int x = in.nextInt() - 1;
                int y = in.nextInt();
                shift[x] = (shift[x] - y) & 31;
            } else {
                int x = in.nextInt() - 1;
                int y = in.nextInt() - 1;
                long z = in.nextLong();
                for (int bit = 0; bit < 32; bit++) {
                    if (!dsu.union(x * 32 + (bit + shift[x] & 31), y * 32 + (bit + shift[y] & 31), ((z >>> 31 - bit) & 1) == 1)) {
                        out.println(-1);
                        return;
                    }
                }
            }
        }
        printIt(out, n, dsu);
    }

    private void printIt(FastPrinter out, int n, DSU dsu) {
        for (int i = 0; i < n; i++) {
            long x = 0;
            for (int j = 0; j < 32; j++) {
                int k = i * 32 + j;
                dsu.get(k);
                int q = dsu.bit.get(k) ? 1 : 0;
                x = (x << 1) + q;
            }
            if (i > 0) {
                out.print(' ');
            }
            out.print(x);
        }
        out.println();
    }
}
