package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskC {
    static int cn;
    static int n;
    static int[] cur;
    static int[][] answer;

    static boolean go(int x, int d, int k) {
        if (x == d) {
            answer[cn++] = cur.clone();
            return cn >= n;
        }
        for (int i = 1; i <= k; i++) {
            cur[x] = i;
            if (go(x + 1, d, k)) return true;
        }
        return false;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        n = in.nextInt();
        int k = in.nextInt();
        int d = in.nextInt();
        long cc = 1;
        for (int i = 0; i < d; i++) {
            cc *= k;
            if (cc >= n) break;
        }
        if (cc < n) {
            out.println("-1");
            return;
        }
        cn = 0;
        cur = new int[d];
        answer = new int[n][];
        go(0, d, k);
        for (int i = 0; i < d; i++) {
            for (int j = 0; j < n; j++) {
                if (j > 0) {
                    out.print(' ');
                }
                out.print(answer[j][i]);
            }
            out.println();
        }
    }
}
