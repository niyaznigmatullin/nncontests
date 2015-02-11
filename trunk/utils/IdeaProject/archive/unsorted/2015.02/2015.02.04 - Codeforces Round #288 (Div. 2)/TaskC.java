package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int m = in.nextInt();
        int t = in.nextInt();
        int r = in.nextInt();
        if (t < r) {
            out.println(-1);
            return;
        }
        int shift = 333;
        boolean[] candle = new boolean[123456];
        int ans = 0;
        for (int i = 0; i < m; i++) {
            int x = in.nextInt() + shift;
            int count = 0;
            int cur = 0;
            for (int j = x - 1; j >= x - t; j--) {
                if (candle[j]) ++cur;
            }
            cur = r - cur;
            for (int j = x - 1; cur > 0; j--) {
                if (!candle[j]) {
                    --cur;
                    ++ans;
                    candle[j] = true;
                }
            }
        }
        out.println(ans);
    }
}
