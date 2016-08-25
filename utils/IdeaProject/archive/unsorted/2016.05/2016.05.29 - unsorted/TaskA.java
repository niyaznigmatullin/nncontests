package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int q = in.nextInt();
        boolean[] a = new boolean[n];
        for (int i = 0; i < q; i++) {
            String s = in.next();
            int x = in.nextInt();
            --x;
            if (s.equals("?")) {
                int ans = -1;
                for (int j = 0; j < n; j++) {
                    if (a[j]) continue;
                    if (x == 0) {
                        ans = j;
                        break;
                    }
                    --x;
                }
                out.println(ans + 1);
            } else {
                a[x] = true;
                a[n - x - 1] = true;
            }
        }
    }
}
