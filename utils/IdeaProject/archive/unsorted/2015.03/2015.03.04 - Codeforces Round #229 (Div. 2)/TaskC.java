package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int w = in.nextInt();
        char[] c = in.next().toCharArray();
        int[][] s = new int[k][c.length];
        for (int i = 0; i < c.length; i++) {
            int have = c[i] - '0';
            for (int j = 0; j < k; j++) {
                int dif = (i - j + k) % k;
                int need = 0;
                if (dif == k - 1) {
                    need = 1;
                }
                s[j][i] = have ^ need;
                if (i > 0) s[j][i] += s[j][i - 1];
            }
        }
        for (int i = 0; i < w; i++) {
            int l = in.nextInt() - 1;
            int r = in.nextInt() - 1;
            int ans = s[l % k][r];
            if (l > 0) ans -= s[l % k][l - 1];
            out.println(ans);
        }
    }
}
