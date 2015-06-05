package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class SpaceTour {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        char[][] c = in.readCharacterFieldTokens(n, m);
        final int[] DX = {0, 1, 0, -1};
        final int[] DY = {1, 0, -1, 0};
        int[][][][] dp = new int[2][4][n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int startDir = 2; startDir < 4; startDir++) {
                    for (int turn = 0; turn < 2; turn++) {
                        int dir = (startDir + turn) & 3;
                        if (dir == 3 || dir == 2) {
                            int x = i + DX[dir];
                            int y = j + DY[dir];
                            if (x < 0 || y < 0 || x >= n || y >= m || c[x][y] == '0') continue;
                            dp[turn][startDir][i][j] = Math.max(dp[turn][startDir][i][j], dp[turn ^ 1][startDir][x][y] + 1);
                        }
                    }
                }
            }
            for (int j = m - 1; j >= 0; j--) {
                for (int startDir = 0; startDir < 4; startDir++) {
                    for (int turn = 0; turn < 2; turn++) {
                        int dir = (startDir + turn) & 3;
                        if (dir == 0) {
                            int x = i + DX[dir];
                            int y = j + DY[dir];
                            if (x < 0 || y < 0 || x >= n || y >= m || c[x][y] == '0') continue;
                            dp[turn][startDir][i][j] = Math.max(dp[turn][startDir][i][j], dp[turn ^ 1][startDir][x][y] + 1);
                        }
                    }
                }
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < m; j++) {
                for (int startDir = 0; startDir < 2; startDir++) {
                    for (int turn = 0; turn < 2; turn++) {
                        int dir = (startDir + turn) & 3;
                        if (dir == 1 || dir == 2) {
                            int x = i + DX[dir];
                            int y = j + DY[dir];
                            if (x < 0 || y < 0 || x >= n || y >= m || c[x][y] == '0') continue;
                            dp[turn][startDir][i][j] = Math.max(dp[turn][startDir][i][j], dp[turn ^ 1][startDir][x][y] + 1);
                        }
                    }
                }
            }
            for (int j = m - 1; j >= 0; j--) {
                for (int startDir = 0; startDir < 4; startDir++) {
                    for (int turn = 0; turn < 2; turn++) {
                        int dir = (startDir + turn) & 3;
                        if (dir == 0) {
                            int x = i + DX[dir];
                            int y = j + DY[dir];
                            if (x < 0 || y < 0 || x >= n || y >= m || c[x][y] == '0') continue;
                            dp[turn][startDir][i][j] = Math.max(dp[turn][startDir][i][j], dp[turn ^ 1][startDir][x][y] + 1);
                        }
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (c[i][j] == '0') continue;
                int sum = 1;
                for (int d = 0; d < 4; d++) {
                    sum += dp[0][d][i][j];
                }
                ans = Math.max(ans, sum);
            }
        }
        out.println(ans);
    }
}
