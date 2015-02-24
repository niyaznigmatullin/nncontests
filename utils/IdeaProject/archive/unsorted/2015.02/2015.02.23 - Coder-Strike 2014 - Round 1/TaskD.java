package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.BitSet;

public class TaskD {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        BitSet[] a = new BitSet[n];
        for (int i = 0; i < n; i++) {
            a[i] = new BitSet(n);
        }
        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            a[from].set(to);
        }
        int[] ans = new int[n];
        int cn = 1;
        for (int i = 1; i < n; i++) {
            int j = cn;
            while (j > 0) {
                if (a[ans[j - 1]].get(i)) {
                    ans[j] = ans[j - 1];
                    j--;
                } else break;
            }
            ans[j] = i;
            ++cn;
        }
        for (int i = 0; i < n; i++) ++ans[i];
        out.printArray(ans);
    }
}
