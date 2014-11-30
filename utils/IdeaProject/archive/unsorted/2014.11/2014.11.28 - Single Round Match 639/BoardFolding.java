package coding;

public class BoardFolding {

    static int tonumber(char c) {
        if (Character.isDigit(c)) return c - '0';
        if (Character.isLowerCase(c)) return c - 'a' + 10;
        if (Character.isUpperCase(c)) return c - 'A' + 36;
        if (c == '#') return 62;
        if (c == '@') return 63;
        throw new AssertionError();
    }

    public int howMany(int n, int m, String[] compressedPaper) {
        boolean[][] paper = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                paper[i][j] = (tonumber(compressedPaper[i].charAt(j / 6)) >> (j % 6)) % 2 == 1;
            }
        }
        boolean[][] paperT = new boolean[m][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                paperT[j][i] = paper[i][j];
            }
        }
        return solve(n, m, paper) * solve(m, n, paperT);
    }

    private int solve(int n, int m, boolean[][] paper) {
        int[] vRadius = new int[m - 1];
        for (int i = 0; i + 1 < m; i++) {
            int radius = Integer.MAX_VALUE;
            for (int row = 0; row < n; row++) {
                int r = 0;
                while (i - r >= 0 && i + r + 1 < m && paper[row][i - r] == paper[row][i + r + 1]) {
                    ++r;
                }
                radius = Math.min(radius, r);
            }
            vRadius[i] = radius;
        }
        boolean[][] dp = new boolean[m][m];
        dp[0][m - 1] = true;
        for (int len = m; len >= 1; len--) {
            for (int start = 0; start + len <= m; start++) {
                int end = start + len - 1;
                if (!dp[start][end]) continue;
                for (int left = start; 2 * left + 1 - start <= end; left++) {
                    if (vRadius[left] >= left - start + 1) {
                        dp[left + 1][end] = true;
                    }
                }
                for (int right = end - 1; right - (end - right - 1) >= start; right--) {
                    if (vRadius[right] >= end - right) {
                        dp[start][right] = true;
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = i; j < m; j++) {
                if (dp[i][j]) ans++;
            }
        }
        return ans;
    }
}
