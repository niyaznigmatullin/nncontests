package coding;

import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.stream.IntStream;

public class SortingPermutations {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = in.readIntArray(n);
        for (int i = 0; i < n; i++) a[i]--;
        if (isSorted(a)) {
            out.println("yes");
            return;
        }
        if (n < 5) {
            out.println("no");
            return;
        }
        if (n == 5) {
            ArrayUtils.reverse(a);
            out.println(isSorted(a) ? "yes" : "no");
            return;
        }
        boolean[] used = new boolean[n];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (used[i]) continue;
            int v = i;
            int cc = 0;
            while (!used[v]) {
                used[v] = true;
                cc++;
                v = a[v];
            }
            ans ^= cc % 2 == 0 ? 1 : 0;
        }
        for (int i = 0; i < n; i++) {
            if ((i % 2) != (a[i] % 2)) {
                ans = 1;
                break;
            }
        }
        if (ans == 1) {
            out.println("no");
            return;
        }
        out.println("yes");
    }

    private boolean isSorted(int[] a) {
        boolean isSorted = true;
        for (int i = 0; i < a.length; i++) if (a[i] != i) {
            isSorted = false;
            break;
        }
        return isSorted;
    }
}
