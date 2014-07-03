package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class K {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = in.readIntArray(n);
        int[] ansL = new int[n];
        int[] ansR = new int[n];
        int ac = 0;
        for (int cur = 0; cur < n; ) {
            int next1 = cur;
            while (next1 < n && a[next1] >= next1 - cur + 1) {
                ++next1;
            }
            int next2 = cur;
            int curMin = Integer.MAX_VALUE;
            while (next2 < n && curMin > 0) {
                curMin = Math.min(curMin, a[next2]);
                ++next2;
                --curMin;
            }
            if (next1 > next2) {
                ansL[ac] = cur;
                ansR[ac] = next1 - 1;
                cur = next1;
            } else {
                ansL[ac] = next2 - 1;
                ansR[ac] = cur;
                cur = next2;
            }
            ++ac;
        }
        out.println(ac);
        for (int i = 0; i < ac; i++) {
            out.println((ansL[i] + 1) + " " + (ansR[i] + 1));
        }
    }
}
