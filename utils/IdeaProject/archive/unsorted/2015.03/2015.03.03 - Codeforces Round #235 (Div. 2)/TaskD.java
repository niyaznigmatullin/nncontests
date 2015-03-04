package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TaskD {

    static int[] count;

    static int code(int[] a) {
        int ret = 0;
        for (int i = 0; i < 10; i++) {
            ret = ret * (count[i] + 1) + a[i];
        }
        return ret;
    }

    static void decode(int x, int[] a) {
        for (int i = 9; i >= 0; i--) {
            a[i] = x % (count[i] + 1);
            x /= count[i] + 1;
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        String s = in.next();
        int m = in.nextInt();
        count = new int[10];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - '0']++;
        }
        int[] cur = new int[10];
        int all = 1;
        for (int i : count) all *= i + 1;
        long[][] dp = new long[m][all];
        dp[0][all - 1] = 1;
        for (int state = all - 1; state > 0; state--) {
            decode(state, cur);
            for (int mod = 0; mod < m; mod++) {
                long val = dp[mod][state];
                if (val == 0) continue;
                for (int d = 0; d < 10; d++) {
                    if (state + 1 == all && d == 0 || cur[d] == 0) continue;
                    --cur[d];
                    int nState = code(cur);
                    int nMod = (mod * 10 + d) % m;
                    dp[nMod][nState] += val;
                    ++cur[d];
                }
            }
        }
        out.println(dp[0][0]);
    }
}
