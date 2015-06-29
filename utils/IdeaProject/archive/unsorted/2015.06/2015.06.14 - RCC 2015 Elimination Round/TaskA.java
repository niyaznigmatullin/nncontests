package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int ones = 0;
        for (char c : in.next().toCharArray()) {
            if (c == '1') ++ones;
        }
        int ans = 0;
        int[][] c = new int[k + 1][k + 1];
        final int MOD = 1000000007;
        for (int i = 0; i <= k; i++) {
            c[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                c[i][j] = (c[i - 1][j - 1] + c[i - 1][j]) % MOD;
            }
        }
        for (int x = 0; x <= k; x++) {
            if (ones < x || (ones - x) % 3 != 0) continue;
            if (n - ones < (k - x) || (n - ones - (k - x)) % 2 != 0) continue;
            ans = (ans + c[k][x]) % MOD;
        }
        out.println(ans);
    }
}
