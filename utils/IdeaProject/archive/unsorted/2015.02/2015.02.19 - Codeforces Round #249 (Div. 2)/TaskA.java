package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int cur = m;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            if (x + cur > m) {
                ++ans;
                cur = x;
            } else {
                cur += x;
            }
        }
        out.println(ans);
    }
}
