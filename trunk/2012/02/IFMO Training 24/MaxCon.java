package mypackage;

import arrayutils.ArrayUtils;
import com.sun.deploy.util.ArrayUtil;
import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MaxCon {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        char[] c1 = in.next().toCharArray();
        char[] c2 = in.next().toCharArray();
        int[][] dp = new int[c1.length + 1][c2.length + 1];
        for (int i = 1; i <= c1.length; i++) {
            for (int j = 1; j <= c2.length; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (c1[i - 1] == c2[j - 1]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }
        out.println(dp[c1.length][c2.length]);
        List<Integer> answer1 = new ArrayList<Integer>();
        List<Integer> answer2 = new ArrayList<Integer>();
        for (int i = c1.length, j = c2.length; i > 0 && j > 0; ) {
            if (c1[i - 1] == c2[j - 1] && dp[i][j] == dp[i - 1][j - 1] + 1) {
                answer1.add(i);
                answer2.add(j);
                --i;
                --j;
            } else if (dp[i][j] == dp[i - 1][j]) {
                --i;
            } else if (dp[i][j] == dp[i][j - 1]) {
                --j;
            } else {
                throw new AssertionError();
            }
        }
        Collections.reverse(answer1);
        Collections.reverse(answer2);
        out.printArray(ArrayUtils.toPrimitiveArray(answer1));
        out.printArray(ArrayUtils.toPrimitiveArray(answer2));
    }
}
