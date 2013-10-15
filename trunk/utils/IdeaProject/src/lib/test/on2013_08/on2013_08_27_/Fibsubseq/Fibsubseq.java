package lib.test.on2013_08.on2013_08_27_.Fibsubseq;



import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.*;

public class Fibsubseq {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        final int[] a = in.readIntArray(n);
        if (n == 1) {
            out.println(1);
            out.println(a[0]);
            return;
        }
        int[] order1 = new int[n];
        int[] order2 = new int[n];
        for (int i = 0; i < n; i++) {
            order1[i] = i;
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int c = a[order1[i]] - a[order1[j]];
                if (c > 0 || c == 0 && order1[i] > order1[j]) {
                    int t = order1[i];
                    order1[i] = order1[j];
                    order1[j] = t;
                }
            }
        }
        int[][] dp = new int[n][];
        int[][] prev = new int[n][];
        for (int i = 0; i < n; i++) {
            dp[i] = new int[i];
            prev[i] = new int[i];
        }
        int cn1 = n;
        int cn2 = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < cn1; j++) {
                if (order1[j] == i) {
                    for (int k = j + 1; k < cn1; k++) {
                        order1[k - 1] = order1[k];
                    }
                    cn1--;
                    break;
                }
            }
            for (int j = 0; j < i; j++) {
                dp[i][j] = Math.max(dp[i][j], 2);
            }
            int number = a[i];
            int cur = 0;
            for (int it = 0; it < cn2; it++) {
                int j = order2[it];
                int sum = number + a[j];
                while (cur < cn1 && sum > a[order1[cur]]) ++cur;
                if (cur >= cn1) continue;
                int curBest = dp[i][j];
                if (cur < cn1 && sum == a[order1[cur]]) {
                    int k = order1[cur];
                    if (dp[k][i] < curBest + 1) {
                        dp[k][i] = curBest + 1;
                        prev[k][i] = j;
                    }
                }
            }
            order2[cn2] = i;
            for (int j = cn2; j > 0; j--) {
                int c = a[order2[j]] - a[order2[j - 1]];
                if (c < 0 || c == 0 && order2[j] < order2[j - 1]) {
                    int t = order2[j];
                    order2[j] = order2[j - 1];
                    order2[j - 1] = t;
                } else {
                    break;
                }
            }
            ++cn2;
        }
        int ans = 0;
        int bi = -1;
        int bj = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[i][j] > ans) {
                    ans = dp[i][j];
                    bi = i;
                    bj = j;
                }
            }
        }
        List<Integer> answer = new ArrayList<>();
        for (int i = bi, j = bj; ; ) {
            if (dp[i][j] == 2) {
                answer.add(a[i]);
                answer.add(a[j]);
                break;
            }
            answer.add(a[i]);
            int t = prev[i][j];
            i = j;
            j = t;
        }
        Collections.reverse(answer);
        if (ans != answer.size()) throw new AssertionError();
        out.println(ans);
        out.printArray(ArrayUtils.toPrimitiveArrayInteger(answer));
    }
}
