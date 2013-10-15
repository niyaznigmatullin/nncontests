package lib.test.on2013_08.on2013_08_30_Codeforces_Round__198__Div__1_.B___Bubble_Sort_Graph;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = in.readIntArray(n);
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MIN_VALUE);
        int cur = 0;
        for (int i : a) {
            int l = 0;
            int r = cur + 1;
            while (l < r - 1) {
                int mid = l + r >> 1;
                if (i < dp[mid]) {
                    r = mid;
                } else {
                    l = mid;
                }
            }
            dp[l + 1] = i;
            if (l == cur) {
                ++cur;
            }
        }
        out.println(cur);
    }
}
