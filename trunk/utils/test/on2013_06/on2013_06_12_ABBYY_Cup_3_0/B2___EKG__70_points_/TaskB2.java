package lib.test.on2013_06.on2013_06_12_ABBYY_Cup_3_0.B2___EKG__70_points_;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskB2 {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int k = in.nextInt() - 1;
        int[] a = in.readIntArray(n);
        boolean[] notLast = new boolean[n];
        for (int i = 0; i < n; i++) {
            --a[i];
            if (a[i] >= 0) {
                notLast[a[i]] = true;
            }
        }
        int place = 0;
        {
            int v = k;
            while (v >= 0) {
                place++;
                v = a[v];
            }
        }
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 0; i < n; i++) {
            if (!notLast[i]) {
                int v = i;
                int count = 0;
                boolean wasK = false;
                while (v >= 0) {
                    if (v == k) wasK = true;
                    ++count;
                    v = a[v];
                }
                if (wasK) continue;
                for (int j = n; j >= 0; j--) {
                    if (dp[j] && j + count <= n) {
                        dp[j + count] = true;
                    }
                }
            }
        }
        for (int i = 0; i <= n; i++) {
            if (dp[i]) {
                out.println(i + place);
            }
        }
    }
}
