package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.Factor;
import ru.ifmo.niyaz.math.MathUtils;

import java.util.Arrays;
import java.util.HashSet;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        int[] c = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            c[i] = in.nextInt();
        }
        Factor[][] f = new Factor[n][];
        HashSet<Integer>[] factors = new HashSet[n];
        for (int i = 0; i < n; i++) {
            f[i] = MathUtils.factorize(a[i]);
            factors[i] = new HashSet<>();
            for (Factor e : f[i]) {
                factors[i].add((int) e.prime);
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int one = 0; one < n; one++) {
            int[] dp = new int[1 << f[one].length];
            Arrays.fill(dp, Integer.MAX_VALUE);
            dp[0] = c[one];
            for (int get = one + 1; get < n; get++) {
                int mask = 0;
                for (int i = 0; i < f[one].length; i++) {
                    if (!factors[get].contains((int) f[one][i].prime)) {
                        mask |= 1 << i;
                    }
                }
                for (int j = dp.length - 1; j >= 0; j--) {
                    if (dp[j] == Integer.MAX_VALUE) continue;
                    dp[j | mask] = Math.min(dp[j | mask], dp[j] + c[get]);
                }
            }
            ans = Math.min(ans, dp[dp.length - 1]);
        }
        out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }
}
