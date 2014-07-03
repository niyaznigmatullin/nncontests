package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class L {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int d = in.nextInt();
        int a = in.nextInt();
        int b = in.nextInt();
        int[] p = in.readIntArray(n);
        int ans = 0;
        for (int i = 0; i + 1 < n; ) {
            if (p[i] == p[i + 1]) {
                ++i;
                continue;
            }
            int j = i + 1;
            while (j + 1 < n && Integer.signum(p[j] - p[i]) == Integer.signum(p[j + 1] - p[j])) {
                ++j;
            }
            if (p[j] > p[i]) {
                for (int k = i + 1; k <= j; k++) {
                    int x = Math.min((k - i) * a, d / p[k]);
                    d -= p[k] * x;
                    ans += x;
                }
            } else {
                for (int k = i + 1; k <= j; k++) {
                    int x = Math.min((k - i) * b, ans);
                    d += p[k] * x;
                    ans -= x;
                }
            }
            i = j;
        }
        out.println(d + " " + ans);
    }
}
