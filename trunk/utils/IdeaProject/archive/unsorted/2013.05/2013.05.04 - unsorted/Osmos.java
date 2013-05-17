package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import sun.nio.cs.ext.MacHebrew;

import java.util.Arrays;

public class Osmos {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        out.print("Case #" + testNumber + ": ");
        long cur = in.nextLong();
        int n = in.nextInt();
        int[] a = in.readIntArray(n);
        Arrays.sort(a);
        int ans = Integer.MAX_VALUE;
        int already = 0;
        if (cur == 1) {
            out.println(n);
            return;
        }
        for (int i = 0; i < n; i++) {
            ans = Math.min(ans, n - i + already);
            while (cur <= a[i]) {
                cur += cur - 1;
                ++already;
            }
            cur += a[i];
        }
        ans = Math.min(ans, already);
        out.println(ans);
    }
}
