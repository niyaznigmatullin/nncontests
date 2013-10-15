package lib.test.on2013_08.on2013_08_30_Codeforces_Round__198__Div__1_.C___Iahub_and_Permutations;



import com.sun.media.sound.MidiOutDeviceProvider;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskC {

    final int MOD = 1000000007;

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        boolean[] has = new boolean[n];
        boolean[] place = new boolean[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            if (a[i] > 0) {
                has[a[i] - 1] = true;
                place[i] = true;
            }
        }
        int[][] dp = new int[n + 1][n + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i][j] = (int) ((dp[i][j] + (long) dp[i - 1][j] * (i - j)) % MOD);
                if (j > 0) {
                    dp[i][j] = (int) ((dp[i][j] + (long) dp[i - 1][j - 1] * j) % MOD);
                }
            }
            if (i > 1) {
                dp[i][i] = (int) ((dp[i][i] + (long) (i - 1) * dp[i - 1][i - 2]) % MOD);
            }
        }
        int count = 0;
        int countCommon = 0;
        for (int i = 0; i < n; i++) {
            if (!has[i]) ++count;
            if (!has[i] && !place[i]) ++countCommon;
        }
        out.println(dp[count][countCommon]);
    }
}
