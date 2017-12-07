package coding;

public class HarmoniousGarden {
    public String isPossible(int n, int k, int L) {
        final String POSSIBLE = "Possible";
        if (1 + k * (L - 1) <= n) {
            return POSSIBLE;
        }
        final String IMPOSSIBLE = "Impossible";
        if (L % 2 == 1) {
            return IMPOSSIBLE;
        }
        boolean[][] dp = new boolean[n + 1][k + 1];
        dp[1][0] = true;
        for (int i = 1; i < n; i++) {
            for (int c = 0; c <= k; c++) {
                if (!dp[i][c]) continue;
                for (int j = 2; ; j++) {
                    int need = 1 + j * (L / 2 - 1);
                    if (i + need > n) break;
                    int nc = j * (j - 1) / 2;
                    if (nc + c > k) continue;
                    dp[i + need][c + nc] = true;
                }
            }
        }
        for (int i = 1; i <= n; i++) if (dp[i][k]) {
            return POSSIBLE;
        }
        return IMPOSSIBLE;
    }
}
