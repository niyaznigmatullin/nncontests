package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int x = in.nextInt();
        int[] type = new int[n];
        int[] h = new int[n];
        int[] m = new int[n];
        for (int i = 0; i < n; i++) {
            type[i] = in.nextInt();
            h[i] = in.nextInt();
            m[i] = in.nextInt();
        }
        int ans = 0;
        for (int start = 0; start < 2; start++) {
            int cur = start;
            int ate = 0;
            int curX = x;
            boolean[] was = new boolean[n];
            while (true) {
                int best = -1;
                for (int i = 0; i < n; i++) {
                    if (type[i] != cur || was[i] || h[i] > curX) continue;
                    if (best < 0 || m[best] < m[i]) {
                        best = i;
                    }
                }
                if (best < 0) break;
                ++ate;
                cur ^= 1;
                was[best] = true;
                curX += m[best];
            }
            ans = Math.max(ans, ate);
        }
        out.println(ans);
    }
}
