package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        long ans = 0;
        for (int i = 0; i < n; ) {
            int j = i;
            while (j < n && a[i] == a[j]) {
                ++j;
            }
            long cnt = j - i;
            ans += cnt * (cnt + 1) / 2;
            i = j;
        }
        out.println(ans);
    }
}
