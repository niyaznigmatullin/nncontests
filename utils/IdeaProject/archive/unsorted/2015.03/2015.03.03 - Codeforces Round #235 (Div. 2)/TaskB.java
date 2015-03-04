package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int x = in.nextInt();
        int k = in.nextInt();
        boolean[] has = new boolean[--x];
        for (int i = 0; i < k; i++) {
            int type = in.nextInt();
            if (type == 1) {
                int a = in.nextInt() - 1;
                int b = in.nextInt() - 1;
                has[a] = true;
                has[b] = true;
            } else {
                int a = in.nextInt() - 1;
                has[a] = true;
            }
        }
        int maximal = 0;
        int minimal = 0;
        for (int i = 0; i < x;) {
            if (has[i]) {
                i++;
                continue;
            }
            int j = i;
            while (j < x && !has[j]) {
                ++j;
            }
            maximal += j - i;
            minimal += (j - i + 1) / 2;
            i = j;
        }
        out.println(minimal + " " + maximal);
    }
}
