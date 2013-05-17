package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        int[] left = new int[m];
        int[] right = new int[m];
        int[] d = new int[m];
        for (int i = 0; i < m; i++) {
            left[i] = in.nextInt() - 1;
            right[i] = in.nextInt();
            d[i] = in.nextInt();
        }
        int[] c = new int[m];
        for (int i = 0; i < k; i++) {
            int x = in.nextInt() - 1;
            int y = in.nextInt();
            c[x]++;
            if (y < m) {
                --c[y];
            }
        }
        for (int i = 1; i < m; i++) {
            c[i] += c[i - 1];
        }
        long[] b = new long[n];
        for (int i = 0; i < m; i++) {
            long dd = (long) c[i] * d[i];
            b[left[i]] += dd;
            if (right[i] < n) {
                b[right[i]] -= dd;
            }
        }
        for (int i = 1; i < n; i++) {
            b[i] += b[i - 1];
        }
        for (int i = 0; i < n; i++) {
            if (i > 0)out.print(' ');
            out.print(a[i] + b[i]);
        }
        out.println();
    }
}
