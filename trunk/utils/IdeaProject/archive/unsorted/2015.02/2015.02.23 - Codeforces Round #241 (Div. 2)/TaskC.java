package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] c = new int[n];
        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            c[i] = in.nextInt();
            p[i] = in.nextInt();
        }
        boolean[] was = new boolean[n];
        int k = in.nextInt();
        int[] f = new int[k];
        for (int i = 0; i < k; i++) f[i] = in.nextInt();
        boolean[] used = new boolean[k];
        int[] ax = new int[n];
        int[] ay = new int[n];
        int ac = 0;
        int ans = 0;
        while (true) {
            int best = -1;
            for (int i = 0; i < n; i++) {
                if (was[i]) continue;
                if (best < 0 || p[i] > p[best]) best = i;
            }
            if (best < 0) break;
            was[best] = true;
            int best2 = -1;
            for (int i = 0; i < k; i++) {
                if (used[i] || f[i] < c[best]) continue;
                if (best2 < 0 || f[i] < f[best2]) best2 = i;
            }
            if (best2 < 0) continue;
            used[best2] = true;
            ans += p[best];
            ax[ac] = best;
            ay[ac++] = best2;
        }
        out.println(ac + " " + ans);
        for (int i = 0; i < ac; i++) {
            out.println((ax[i] + 1) + " " + (ay[i] + 1));
        }
    }
}
