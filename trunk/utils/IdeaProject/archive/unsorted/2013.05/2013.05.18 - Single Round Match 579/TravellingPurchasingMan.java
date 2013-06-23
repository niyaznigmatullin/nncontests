package coding;

import java.util.Arrays;
import java.util.StringTokenizer;

public class TravellingPurchasingMan {
    public int maxStores(int n, String[] interestingStores, String[] roads) {
        int m = interestingStores.length;
        int[] start = new int[m];
        int[] end = new int[m];
        int[] dur = new int[m];
        for (int i = 0; i < interestingStores.length; i++) {
            String e = interestingStores[i];
            StringTokenizer st = new StringTokenizer(e);
            start[i] = Integer.parseInt(st.nextToken());
            end[i] = Integer.parseInt(st.nextToken());
            dur[i] = Integer.parseInt(st.nextToken());
        }
        int[][] a = new int[n][n];
        for (int[] d : a) {
            Arrays.fill(d, Integer.MAX_VALUE);
        }
        for (int i = 0; i < n; i++) a[i][i] = 0;
        for (String e : roads) {
            StringTokenizer st = new StringTokenizer(e);
            int v = Integer.parseInt(st.nextToken());
            int u = Integer.parseInt(st.nextToken());
            int len = Integer.parseInt(st.nextToken());
            a[v][u] = Math.min(a[v][u], len);
            a[u][v] = Math.min(a[u][v], len);
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (a[i][k] != Integer.MAX_VALUE && a[k][j] != Integer.MAX_VALUE) {
                        a[i][j] = Math.min(a[i][j], a[i][k] + a[k][j]);
                    }
                }
            }
        }
        int[][] dp = new int[m][1 << m];
        for (int[] e : dp) {
            Arrays.fill(e, Integer.MAX_VALUE);
        }
        for (int mask = 1; mask < 1 << m; mask++) {
            if ((mask & (mask - 1)) == 0) {
                int i = Integer.numberOfTrailingZeros(mask);
                int time = a[n - 1][i];
                if (time > end[i]) {
                    continue;
                }
                dp[i][mask] = Math.max(time, start[i]) + dur[i];
                continue;
            }
            for (int last = 0; last < m; last++) {
                if (((mask >> last) & 1) == 0) {
                    continue;
                }
                int nMask = mask ^ (1 << last);
                int ret = Integer.MAX_VALUE;
                for (int prev = 0; prev < m; prev++) {
                    if (((mask >> last) & 1) == 0 || last == prev) {
                        continue;
                    }
                    int val = dp[prev][nMask];
                    if (val == Integer.MAX_VALUE || a[prev][last] == Integer.MAX_VALUE) {
                        continue;
                    }
                    int nTime = val + a[prev][last];
                    if (nTime > end[last]) continue;
                    nTime = Math.max(nTime, start[last]) + dur[last];
                    if (ret > nTime) {
                        ret = nTime;
                    }
                }
                dp[last][mask] = ret;
            }
        }
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < 1 << m; j++) {
                if (dp[i][j] != Integer.MAX_VALUE) {
                    ans = Math.max(ans, Integer.bitCount(j));
                }
            }
        }
        return ans;
    }
}
