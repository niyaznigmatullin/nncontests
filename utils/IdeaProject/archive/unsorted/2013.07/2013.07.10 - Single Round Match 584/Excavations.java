package coding;

import ru.ifmo.niyaz.arrayutils.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Excavations {
    public long count(int[] kind, int[] depth, int[] found, int k) {
        int cntTypes = 0;
        for (int i : kind) {
            cntTypes = Math.max(cntTypes, i + 1);
        }
        int[] z = ArrayUtils.sortAndUnique(depth);
        int n = depth.length;
        long[][] C = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            C[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                C[i][j] = C[i - 1][j - 1] + C[i - 1][j];
            }
        }
        for (int i = 0; i < n; i++) {
            depth[i] = Arrays.binarySearch(z, depth[i]);
        }
        List<Integer>[] f = new List[cntTypes];
        for (int i = 0; i < cntTypes; i++) {
            f[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < n; i++) {
            f[kind[i]].add(depth[i]);
        }
        for (int i = 0; i < cntTypes; i++) {
            Collections.sort(f[i]);
        }
        long[][] dp = new long[k + 1][z.length];
        dp[0][0] = 1;
        for (int cur : found) {
            long[][] next = new long[k + 1][z.length];
            int canGet = f[cur].size();
            for (int have = 0; have <= k; have++) {
                for (int curMax = 0; curMax < z.length; curMax++) {
                    long val = dp[have][curMax];
                    if (val == 0) continue;
                    for (int first = 0; first < canGet; first++) {
                        for (int toGet = 1; first + toGet <= canGet && have + toGet <= k; toGet++) {
                            next[have + toGet][Math.max(curMax, f[cur].get(first))] += val * C[canGet - first - 1][toGet - 1];
                        }
                    }
                }
            }
            dp = next;
        }
        long ans = 0;
        for (int got = found.length; got <= k; got++) {
            for (int curMax = 0; curMax < z.length; curMax++) {
                int count = 0;
                for (int i = 0; i < n; i++) {
                    if (depth[i] <= curMax) continue;
                    boolean fnd = false;
                    for (int j : found) {
                        if (kind[i] == j) {
                            fnd = true;
                            break;
                        }
                    }
                    if (fnd) continue;
                    ++count;
                }
                if (count >= k - got) {
                    ans += dp[got][curMax] * C[count][k - got];
                }
            }
        }
        return ans;
    }
}
