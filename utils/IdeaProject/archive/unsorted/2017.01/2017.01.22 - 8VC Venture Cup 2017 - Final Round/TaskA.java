package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] p = in.readIntArray(n);
        int[] b = in.readIntArray(n);
        int ans = 0;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum ^= b[i];
            p[i]--;
        }
        ans += sum == 0 ? 1 : 0;
        boolean[] was = new boolean[n];
        int cycles = 0;
        for (int i = 0; i < n; i++) {
            if (was[i]) continue;
            int v = i;
            while (!was[v]) {
                was[v] = true;
                v = p[v];
            }
            ++cycles;
        }
        if (cycles > 1) ans += cycles;
        out.println(ans);
    }
}
