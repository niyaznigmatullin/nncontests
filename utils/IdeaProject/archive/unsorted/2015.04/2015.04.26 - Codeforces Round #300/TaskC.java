package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int ans = 0;
        int lastDay = -1;
        int lastHeight = -1;
        for (int i = 0; i < m; i++) {
            int day = in.nextInt();
            int height = in.nextInt();
            ans = Math.max(ans, height);
            if (lastDay < 0) {
                ans = Math.max(height + day - 1, ans);
            } else {
                if (Math.abs(day - lastDay) < Math.abs(height - lastHeight)) {
                    out.println("IMPOSSIBLE");
                    return;
                }
                int dif = Math.abs(day - lastDay) - Math.abs(height - lastHeight);
                ans = Math.max(ans, dif / 2 + Math.max(height, lastHeight));
            }
            lastDay = day;
            lastHeight = height;
        }
        ans = Math.max(ans, n - lastDay + lastHeight);
        out.println(ans);
    }
}
