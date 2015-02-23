package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int t = in.nextInt();
        int c = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            if (x <= t) continue;
            int left = Math.max(0, i - c + 1);
            a[left]++;
            if (i + 1 < n) a[i + 1]--;
        }
        for (int i = 1; i < n; i++) {
            a[i] += a[i - 1];
        }
        int ans = 0;
        for (int i = 0; i < n; i++) if (i + c <= n && a[i] == 0) ++ans;
        out.println(ans);
    }
}
