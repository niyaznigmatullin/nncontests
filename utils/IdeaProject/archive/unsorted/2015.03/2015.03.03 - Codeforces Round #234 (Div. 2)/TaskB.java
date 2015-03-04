package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        boolean[] has = new boolean[m];
        for (int i = 0; i < n; i++) {
            char[] c = in.next().toCharArray();
            int id1 = -1;
            int id2 = -1;
            for (int j = 0; j < c.length; j++) {
                if (c[j] == 'G') id1 = j;
                if (c[j] == 'S') id2 = j;
            }
            if (id1 > id2) {
                out.println(-1);
                return;
            }
            has[id2 - id1] = true;
        }
        int ans = 0;
        for (boolean e : has) if (e) ++ans;
        out.println(ans);
    }
}
