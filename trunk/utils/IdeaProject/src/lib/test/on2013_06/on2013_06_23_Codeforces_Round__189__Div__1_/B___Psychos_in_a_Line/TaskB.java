package lib.test.on2013_06.on2013_06_23_Codeforces_Round__189__Div__1_.B___Psychos_in_a_Line;



import ru.ifmo.niyaz.DataStructures.MaxSegmentTree;
import ru.ifmo.niyaz.DataStructures.MinSegmentTree;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = in.readIntArray(n);
        MaxSegmentTree t1 = new MaxSegmentTree(n);
        MaxSegmentTree t2 = new MaxSegmentTree(n);
        int[] rev = new int[n];
        for (int i = 0; i < n; i++) {
            --a[i];
            rev[a[i]] = i;
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int got = t1.getMax(a[i] + 1, n);
            if (got == Integer.MIN_VALUE) {
                t2.set(i, -1);
            } else {
                int got2 = t2.getMax(got + 1, i);
                if (got2 == Integer.MIN_VALUE) {
                    got2 = 0;
                }
                t2.set(i, got2 + 1);
                if (got2 + 1 > ans) {
                    ans = got2 + 1;
                }
            }
            t1.set(a[i], i);
        }
        out.println(ans);
    }
}
