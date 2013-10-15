package lib.test.on2013_01.on2013_01_13_Codeforces_Round__160__Div__1_.D___Maxim_and_Increasing_Subsequence;



import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskD {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int k = in.nextInt();
        int n = in.nextInt();
        int maxb = in.nextInt();
        int t = in.nextInt();
        for (int test = 0; test < k; test++) {
            int[] b = in.readIntArray(n);
            int[] c = ArrayUtils.sortAndUnique(b);
            for (int i = 0; i < n; i++) {
                b[i] = Arrays.binarySearch(c, b[i]);
            }
            if (t >= c.length) {
                out.println(c.length);
                continue;
            }
            int[] where = new int[c.length];
            for (int it = 0; it < t; it++) {
                for (int i : b) {
                    int z = i == 0 ? 0 : where[i - 1];
                    for (int j = i; j < c.length && where[j] == z; j++) {
                        where[j]++;
                    }
                }
            }
            int answer = 0;
            for (int i : where) {
                answer = Math.max(answer, i);
            }
            out.println(answer);
        }
    }
}
