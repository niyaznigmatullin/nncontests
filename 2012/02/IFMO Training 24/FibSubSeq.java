package mypackage;

import arrayutils.ArrayUtils;
import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.*;

public class FibSubSeq {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        if (n == 1) {
            out.println(1);
            out.println(a[0]);
            return;
        }
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int[][] dp = new int[n][n];
        int[][] from = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int last = a[j] - a[i];
                Integer id = map.get(last);
                if (id == null) {
                    dp[i][j] = 2;
                    continue;
                }
                dp[i][j] = dp[id][i] + 1;
                from[i][j] = id;
            }
            map.put(a[i], i);
        }
        int ansLength = -1;
        List<Integer> ret = new ArrayList<Integer>();
        int ansI = -1;
        int ansJ = -1;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (ansLength < dp[i][j]) {
                    ansLength = dp[i][j];
                    ansI = i;
                    ansJ = j;
                }
            }
        }
        for (int i = ansI, j = ansJ; ; ) {
            if (dp[i][j] == 2) {
                ret.add(a[j]);
                ret.add(a[i]);
                break;
            }
            ret.add(a[j]);
            int t = from[i][j];
            j = i;
            i = t;
        }
        out.println(ret.size());
        Collections.reverse(ret);
        out.printArray(ArrayUtils.toPrimitiveArray(ret));
    }
}
