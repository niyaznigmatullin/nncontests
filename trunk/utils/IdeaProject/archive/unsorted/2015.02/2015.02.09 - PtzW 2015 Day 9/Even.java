package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;
import java.util.BitSet;

public class Even {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        BitSet[] f = new BitSet[n];
        for (int i = 0; i < n; i++) f[i] = new BitSet(n);
        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            f[from].set(to);
            f[to].set(from);
        }
        for (int i = 0; i < n; i++) {
            if (f[i].cardinality() % 2 == 1) {
                f[i].set(i);
            }
        }
        BitSet[] a = new BitSet[n];
        for (int i = 0; i < n; i++) {
            a[i] = new BitSet();
            for (int j = 0; j < n; j++) {
                a[i].set(j, f[j].get(i));
            }
        }
        for (int i = 0; i < n; i++) {
            a[i].set(n, f[i].get(i));
        }
        for (int row = 0, col = 0; col < n; col++) {
            if (!a[row].get(col)) {
                for (int j = row + 1; j < n; j++) {
                    if (a[j].get(col)) {
                        BitSet t = a[j];
                        a[j] = a[row];
                        a[row] = t;
                        break;
                    }
                }
                if (!a[row].get(col)) continue;
            }
            for (int j = 0; j < n; j++) {
                if (j == row) continue;
                if (a[j].get(col))
                    a[j].xor(a[row]);
            }
            ++row;
        }
        for (int i = 0; i < n; i++) {
            if (a[i].cardinality() == 1 && a[i].get(n)) {
                out.println("IMPOSSIBLE");
                return;
            }
        }
        char[] ans = new char[n];
        Arrays.fill(ans, 'A');
        for (int i = 0; i < n; i++) {
            if (a[i].get(n)) {
                int q = a[i].nextSetBit(0);
                ans[q] = 'B';
            }
        }
        out.println(ans);
    }
}
