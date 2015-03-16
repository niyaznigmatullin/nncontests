package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] a = in.readIntArray(n);
        int[] b = in.readIntArray(m);
        boolean[] was = new boolean[m];
        int ans = 0;
        for (int i = 0; i < a.length; i++) {
            int best = -1;
            for (int j = 0; j < m; j++) {
                if (was[j]) continue;
                if (b[j] < a[i]) continue;
                if (best < 0 || b[j] < b[best]) best = j;
            }
            if (best < 0) ans++; else was[best] = true;
        }
        out.println(ans);
    }
}
