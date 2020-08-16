package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class Permutation {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        String s = in.next();
        if (s.length() < 1 || s.length() > 100) throw new AssertionError();
        int[] f = new int[1 << 10];
        int[][] withLast = new int[1 << 10][10];
        f[0] = 1;
        int allMask = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < '0' || s.charAt(i) > '9') throw new AssertionError();
            allMask |= 1 << (s.charAt(i) - '0');
        }
        for (int i = 0; i < s.length(); i++) {
            int d = s.charAt(i) - '0';
            for (int mask = (1 << 10) - 1; mask >= 0; mask--) {
                if ((mask & allMask) != mask) continue;
                if (((mask >> d) & 1) == 0) continue;
                int old = withLast[mask][d];
                withLast[mask][d] = f[mask & ~(1 << d)];
                f[mask] += withLast[mask][d] - old;
            }
        }
        out.println(f[allMask]);
    }
}
