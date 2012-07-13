package mypackage;

import arrayutils.ArrayUtils;
import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProblemC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        int[] dp2 = new int[n + 1];
        int[] dp3 = new int[n + 1];
        int[] count = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp2[i] = dp2[i - 1];
            dp3[i] = dp3[i - 1];
            count[i] = 1;
            if (i > 1) {
                int new2 = dp2[i - 2] + 1;
                int new3 = dp3[i - 2];
                if (better(new2, new3, dp2[i], dp3[i])) {
                    dp2[i] = new2;
                    dp3[i] = new3;
                    count[i] = 2;
                }
            }
            if (i > 2 && (a[i - 3] <= a[i - 2] && a[i - 2] <= a[i - 1] || a[i - 3] >= a[i - 2] && a[i - 2] >= a[i - 1])) {
                int new2 = dp2[i - 3];
                int new3 = dp3[i - 3] + 1;
                if (better(new2, new3, dp2[i], dp3[i])) {
                    dp2[i] = new2;
                    dp3[i] = new3;
                    count[i] = 3;
                }
            }
        }
        List<Integer> answer = new ArrayList<Integer>();
        for (int i = n; i > 0; i -= count[i]) {
            answer.add(count[i]);
        }
        Collections.reverse(answer);
        out.println(answer.size());
        int[] ans = ArrayUtils.toPrimitiveArray(answer);
        for (int i = 1; i < ans.length; i++) {
            ans[i] += ans[i - 1];
        }
        out.printArray(ans);
    }

    static boolean better(int a2, int a3, int b2, int b3) {
        if (a2 <= b2 && a3 <= b3) {
            return false;
        }
        if (a2 >= b2 && a3 >= b3) {
            return true;
        }
        if (a2 >= b2) {
            a2 -= b2;
            b3 -= a3;
            return what(a2, b3);
        } else {
            b2 -= a2;
            a3 -= b3;
            return !what(b2, a3);
        }
    }

    static boolean what(int p2, int p3) {
        return Double.compare(p2 * Math.log(2), p3 * Math.log(3)) > 0;
    }
}
