package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        char[] c = in.next().toCharArray();
        int[] ans = new int[12];
        int ac = 0;
        for (int a = 1; a <= 12; a++) {
            if (12 % a != 0) continue;
            char[][] d = new char[a][12 / a];
            for (int i = 0, cur = 0; i < a; i++) {
                for (int j = 0; j < d[i].length; j++) {
                    d[i][j] = c[cur++];
                }
            }
            boolean found = false;
            for (int j = 0; j < 12 / a; j++) {
                boolean ok = true;
                for (int i = 0; i < a; i++) {
                    if (d[i][j] != 'X') {
                        ok = false;
                        break;
                    }
                }
                if (ok) {
                    found = true;
                    break;
                }
            }
            if (found) ans[ac++] =a ;
        }
        out.print(ac);
        for (int i = 0; i < ac; i++) {
            out.print(" " + ans[i] + "x" + (12 / ans[i]));
        }
        out.println();
    }
}
