package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        char[] c = in.next().toCharArray();
        int k = in.nextInt();
        for (int i = 0; i < c.length; i++) {
            int best = i;
            for (int j = i + 1; j - i <= k && j < c.length; j++) {
                if (c[best] < c[j]) {
                    best = j;
                }
            }
            while (best > i) {
                char t = c[best];
                c[best] = c[best - 1];
                c[best - 1] = t;
                --k;
                --best;
            }
        }
        out.println(c);
    }
}
