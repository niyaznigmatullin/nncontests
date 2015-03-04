package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int s = in.nextInt();
        int[] d = new int[n];
        int[] k = new int[n];
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            d[i] = x * x + y * y;
            k[i] = in.nextInt();
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int w = s;
            for (int j = 0; j < n; j++) {
                if (d[j] <= d[i]) w += k[j];
            }
            if (w >= 1000000) {
                ans = Math.min(ans, d[i]);
            }
        }
        if (ans == Integer.MAX_VALUE) out.println(-1); else
        out.println(Math.sqrt(ans));
    }
}
