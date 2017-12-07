package coding;

import ru.ifmo.niyaz.DataStructures.SuffixArray;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class NumberDecomposition {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        String s = in.next();
        int[] a = new int[n + 1];
        for (int i = 0; i < n; i++) {
            a[i] = s.charAt(i);
        }
        int[] sa = SuffixArray.buildSuffixArray(a);
        int len = n - k + 1;
        int best = -1;
        for (int i = n; i >= 0; i--) {
            if (sa[i] + len <= n) {
                best = sa[i];
                break;
            }
        }
        int[] ans = new int[n];
        for (int i = 0; i < len; i++) {
            ans[len - i - 1] = a[best + i] - '0';
        }
        for (int i = 0; i < best; i++) ans[0] += a[i] - '0';
        for (int i = best + len; i < n; i++) ans[0] += a[i] - '0';
        for (int i = 0; i + 1 < n; i++) {
            ans[i + 1] += ans[i] / 10;
            ans[i] %= 10;
        }
        int last = n - 1;
        while (ans[last] == 0) --last;
        while (last >= 0) {
            out.print(ans[last]);
            --last;
        }
        out.println();
    }
}
