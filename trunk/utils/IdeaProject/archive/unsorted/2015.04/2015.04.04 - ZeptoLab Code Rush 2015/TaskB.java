package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int all = (1 << n + 1) - 1;
        int[] a = new int[all + 1];
        for (int i = 2; i <= all; i++) {
            a[i] = in.nextInt();
        }
        long ans = 0;
        for (int i = all / 2; i > 0; i--) {
            int left = i * 2;
            int right = i * 2 + 1;
            ans += Math.abs(a[left] - a[right]);
            a[i] += Math.max(a[left], a[right]);
        }
        out.println(ans);
    }
}
