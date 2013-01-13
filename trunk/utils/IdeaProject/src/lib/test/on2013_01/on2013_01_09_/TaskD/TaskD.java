package lib.test.on2013_01.on2013_01_09_.TaskD;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskD {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        char[] c = in.next().toCharArray();
        int n = c.length;
        int[] dp1 = new int[n + 1];
        int[] dp2 = new int[n + 1];
        int[] from1 = new int[n + 1];
        int[] from2 = new int[n + 1];
        dp1[0] = 0;
        dp2[0] = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            char e = c[i - 1];
            if (e == '(') {
                dp1[i] = Integer.MIN_VALUE;
                from1[i] = -1;
                if (dp1[i - 1] > dp2[i - 1]) {
                    dp2[i] = dp1[i - 1];
                    from2[i] = 0;
                } else {
                    dp2[i] = dp2[i - 1];
                    from2[i] = 1;
                }
                dp2[i] = Math.max(dp1[i - 1], dp2[i - 1]);
            } else if (e == ')') {
                from1[i] = from2[i] = 1;
                dp1[i] = dp2[i - 1];
                dp2[i] = dp2[i - 1];
            } else {
                dp1[i] = dp1[i - 1] + 1;
                dp2[i] = dp2[i - 1];
                from1[i] = 0;
                from2[i] = 1;
            }
        }
        for (int i = n, j = 0; i > 0; i--) {
            int[] dp = j == 0 ? dp1 : dp2;
            int[] from = j == 0 ? from1 : from2;
            if (from[i] == 1 || j == 1) {
                c[i - 1] = '*';
            }
            j = from[i];
        }
        out.println(c);
    }
}
