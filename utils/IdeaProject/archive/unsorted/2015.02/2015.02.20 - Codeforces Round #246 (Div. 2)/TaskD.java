package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskD {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        char[] c = in.next().toCharArray();
        int[] p = new int[c.length];
        int k = -1;
        p[0] = -1;
        int[] f = new int[c.length];
        for (int i = 1; i < c.length; i++) {
            while (k > -1 && c[k + 1] != c[i]) k = p[k];
            if (c[k + 1] == c[i]) ++k;
            if (k >= 0) f[k]++;
            p[i] = k;
        }
        int[] len = new int[c.length];
        int[] count = new int[c.length];
        int ac = 0;
        f[c.length - 1]++;
        for (int i = c.length - 1; i >= 0; i--) {
            if (p[i] >= 0) {
                f[p[i]] += f[i];
            }
        }
        len[ac] = c.length;
        count[ac++] = f[c.length - 1];
        while (k > -1) {
            len[ac] = k + 1;
            count[ac++] = f[k];
            k = p[k];
        }
        out.println(ac);
        for (int i = ac - 1; i >= 0; i--) {
            out.println(len[i] + " " + count[i]);
        }
    }
}
