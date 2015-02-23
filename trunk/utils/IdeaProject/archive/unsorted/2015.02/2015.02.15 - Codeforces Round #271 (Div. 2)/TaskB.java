package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0, s = 0; i < n; i++) {
            s += in.nextInt();
            a[i] = s;
        }
        int m = in.nextInt();
        for (int i = 0; i < m; i++) {
            int x = in.nextInt();
            int l = -1;
            int r = n;
            while (l < r - 1) {
                int mid = l + r >> 1;
                if (x <= a[mid]) r = mid; else l = mid;
            }
            out.println((r + 1));
        }
    }
}
