package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int p = in.nextInt() - 1;
        if (p > n - p - 1) p = n - p - 1;
        char[] c = in.next().toCharArray();
        out.println(solve(p, c));
    }

    static int solve(int p, char[] c) {
        int[] a = new int[(c.length + 1) / 2];
        int minIndex = -1;
        int maxIndex = -1;
        int ret = 0;
        for (int i = 0; i < a.length; i++) {
            char c1 = c[i];
            char c2 = c[c.length - i - 1];
            int dif = Math.abs(c1 - c2);
            if (dif > 26 - dif) dif = 26 - dif;
            a[i] = dif;
            if (a[i] != 0) {
                if (minIndex < 0) minIndex = i;
                maxIndex = i;
                ret += a[i];
            }
        }
        if (minIndex < 0) return 0;
        return Math.abs(minIndex - maxIndex) + ret + Math.min(Math.abs(p - minIndex), Math.abs(p - maxIndex));
    }
}
