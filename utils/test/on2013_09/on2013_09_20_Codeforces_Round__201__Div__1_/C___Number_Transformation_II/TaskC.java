package lib.test.on2013_09.on2013_09_20_Codeforces_Round__201__Div__1_.C___Number_Transformation_II;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] x = in.readIntArray(n);
        int a = in.nextInt();
        int b = in.nextInt();
        int[] next = new int[n];
        int[] head = new int[a - b + 1];
        Arrays.fill(head, -1);
        int currentMinimal = a;
        for (int i = 0; i < n; i++) {
            int z = a / x[i] * x[i];
            if (z < b) {
                continue;
            }
            currentMinimal = Math.min(currentMinimal, z);
            z -= b;
            next[i] = head[z];
            head[z] = i;
        }
        int[] dp = new int[a - b + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[a - b] = 0;
        for (int i = a; i > b; i--) {
            int val = dp[i - b];
            if (val != Integer.MAX_VALUE) {
                int go = Math.min(currentMinimal, i - 1) - b;
                dp[go] = Math.min(dp[go], val + 1);
            }
            for (int e = head[i - b]; e >= 0; ) {
                int f = next[e];
                if (i - x[e] >= b) {
                    int z = i - x[e];
                    if (currentMinimal > z) {
                        currentMinimal = z;
                    }
                    z -= b;
                    next[e] = head[z];
                    head[z] = e;
                } else {
                    next[e] = -1;
                }
                e = f;
            }
        }
        out.println(dp[0]);
    }
}
