package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int t = in.nextInt();
        for (int test = 0; test < t; test++) {
            int n = in.nextInt();
            int[] a = in.readIntArray(n);
            for (int i = 0; i < n; i++) --a[i];
            int[] rev = new int[n];
            for (int i = 0; i < n; i++) rev[a[i]] = i;
            int ans = 0;
            for (int i = 0; i < n; ) {
                int j = i + 1;
                while (j < n && rev[j] > rev[j - 1]) {
                    ++j;
                }
                if (j < n) {
                    ++ans;
                    ++j;
                }
                i = j;
            }
            out.println(ans);
        }
    }
}
