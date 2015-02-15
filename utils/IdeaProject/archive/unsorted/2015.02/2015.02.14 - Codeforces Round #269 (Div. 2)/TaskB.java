package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskB {

    static int[] a;
    static int[] id;

    static int[][] answer;
    static int cn;
    static boolean[] was;
    static int[] curid;

    static boolean go(int x, int n) {
        if (x == n) {
            answer[cn++] = curid.clone();
            return cn >= 3;
        }
        int first = -1;
        for (int it = 0; it < n; it++) {
            int i = id[it];
            if (was[i]) continue;
            if (first < 0) first = a[i];
            else if (first != a[i]) break;
            curid[x] = i;
            was[i] = true;
            if (go(x + 1, n)) {
                return true;
            }
            was[i] = false;
        }
        return false;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        a = in.readIntArray(n);
        id = new int[n];
        for (int i = 0; i < n; i++) id[i] = i;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (a[id[i]] > a[id[j]]) {
                    int t = id[i];
                    id[i] = id[j];
                    id[j] = t;
                }
            }
        }
        curid = new int[n];
        was = new boolean[n];
        answer = new int[3][];
        cn = 0;
        go(0, n);
        if (cn < 3) {
            out.println("NO");
        } else {
            out.println("YES");
            for (int[] e : answer) {
                for (int j = 0; j < n; j++) {
                    if (j > 0) out.print(' ');
                    out.print(e[j] + 1);
                }
                out.println();
            }
        }
    }
}
