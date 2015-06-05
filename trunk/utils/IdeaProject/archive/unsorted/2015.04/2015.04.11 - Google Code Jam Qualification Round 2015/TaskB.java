package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = in.readIntArray(n);
        int ans = Integer.MAX_VALUE;
        for (int eat = 1; eat < 1234; eat++) {
            int cur = eat;
            for (int i = 0; i < n; i++) {
                cur += (a[i] + eat - 1) / eat - 1;
            }
            ans = Math.min(ans, cur);
        }
        out.println("Case #" + testNumber + ": " + ans);
    }
}
