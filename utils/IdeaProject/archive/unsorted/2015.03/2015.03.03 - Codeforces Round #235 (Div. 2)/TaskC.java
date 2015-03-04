package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        if ((n + 1) * 2 < m || n > m + 1) {
            out.println(-1);
            return;
        }
        int[] ones = new int[n + 1];
        for (int i = 1; i < n; i++) {
            ones[i] = 1;
            --m;
        }
        for (int i = 0; i <= n; i++) {
            int add = Math.min(2 - ones[i], m);
            m -= add;
            ones[i] += add;
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i <= n; i++) {
            if (i > 0) ans.append('0');
            for (int j = 0; j < ones[i]; j++) ans.append('1');
        }
        out.println(ans);
    }
}
