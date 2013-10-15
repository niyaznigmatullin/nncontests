package coding;

import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TaskC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        String s = in.next();
        boolean[] has = new boolean[11];
        for (int i = 0; i < s.length(); i++) {
            has[i + 1] = s.charAt(i) == '1';
        }
        int m = in.nextInt();
        int[][][] dp = new int[m + 1][11][11];
        for (int[][] d1 : dp) {
            for (int[] d2 : d1) {
                Arrays.fill(d2, -1);
            }
        }
        dp[0][0][0] = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 0; j <= 10; j++) {
                for (int last = 0; last <= 10; last++) {
                    if (dp[i - 1][j][last] < 0) continue;
                    for (int k = 1; k <= 10; k++) {
                        if (!has[k]) continue;
                        if (k == last) continue;
                        int nj = k - j;
                        if (nj <= 0) continue;
                        dp[i][nj][k] = last;
                    }
                }
            }
        }
        int bi = -1;
        int bj = -1;
        for (int i = 1; i <= 10; i++)
            for (int j = 1; j <= 10; j++) {
                if (dp[m][i][j] >= 0) {
                    bi = i;
                    bj = j;
                    break;
                }
            }
        if (bi < 0) {
            out.println("NO");
            return;
        }
        out.println("YES");
        List<Integer> answer = new ArrayList<>();
        for (int i = m, j = bi, k = bj; i > 0; i--) {
            answer.add(k);
            int nk = dp[i][j][k];
            j = k - j;
            k = nk;
        }
        Collections.reverse(answer);
        out.printArray(ArrayUtils.toPrimitiveArrayInteger(answer));
    }
}
