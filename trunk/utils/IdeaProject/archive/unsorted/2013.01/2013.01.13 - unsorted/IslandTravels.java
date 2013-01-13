package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class IslandTravels {

    static final int[] DX = {1, 0, -1, 0};
    static final int[] DY = {0, 1, 0, -1};
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        char[][] c = in.readCharacterFieldTokens(n, m);
        int[][] island = new int[n][m];
        for (int[] d : island) {
            Arrays.fill(d, -1);
        }
        int[] q = new int[n * m];
        int countIslands = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (c[i][j] != 'X' || island[i][j] >= 0) {
                    continue;
                }
                int head = 0;
                q[head++] = i * m + j;
                island[i][j] = countIslands++;
                while (head > 0) {
                    --head;
                    int x0 = q[head] / m;
                    int y0 = q[head] % m;
                    for (int dir = 0; dir < 4; dir++) {
                        int x = x0 + DX[dir];
                        int y = y0 + DY[dir];
                        if (x < 0 || y < 0 || x >= n || y >= m || c[x][y] != 'X' || island[x][y] >= 0) {
                            continue;
                        }
                        q[head++] = x * m + y;
                        island[x][y] = island[i][j];
                    }
                }
            }
        }
        int[][] dist = new int[n][m];
        int[][] a = new int[countIslands][countIslands];
        for (int[] d : a) {
            Arrays.fill(d, Integer.MAX_VALUE);
        }
        for (int id = 0; id < countIslands; id++) {
            a[id][id] = 0;
            int head = 0;
            int tail = 0;
            for (int[] d : dist) {
                Arrays.fill(d, -1);
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (island[i][j] != id) {
                        continue;
                    }
                    for (int dir = 0; dir < 4; dir++) {
                        int x = i + DX[dir];
                        int y = j + DY[dir];
                        if (x >= 0 && y >= 0 && x < n && y < m && c[x][y] == 'S') {
                            q[tail++] = x * m + y;
                            dist[x][y] = 1;
                        }
                    }
                }
            }
            while (head < tail) {
                int x0 = q[head] / m;
                int y0 = q[head] % m;
                ++head;
                for (int dir = 0; dir < 4; dir++) {
                    int x = x0 + DX[dir];
                    int y = y0 + DY[dir];
                    if (x < 0 || y < 0 || x >= n || y >= m || c[x][y] == '.' || dist[x][y] >= 0) {
                        continue;
                    }
                    if (c[x][y] == 'S') {
                        q[tail++] = x * m + y;
                        dist[x][y] = dist[x0][y0] + 1;
                    } else {
                        a[island[x][y]][id] = a[id][island[x][y]] = Math.min(a[id][island[x][y]], dist[x0][y0]);
                    }
                }
            }
        }
        for (int k = 0; k < countIslands; k++) {
            for (int i = 0; i < countIslands; i++) {
                for (int j = 0; j < countIslands; j++) {
                    if (a[i][k] == Integer.MAX_VALUE || a[k][j] == Integer.MAX_VALUE) {
                        continue;
                    }
                    a[i][j] = Math.min(a[i][j], a[i][k] + a[k][j]);
                }
            }
        }
        int[][] dp = new int[countIslands][1 << countIslands];
        for (int[] d : dp) {
            Arrays.fill(d, Integer.MAX_VALUE);
        }
        for (int mask = 1; mask < 1 << countIslands; mask++) {
            if ((mask & (mask - 1)) == 0) {
                dp[Integer.numberOfTrailingZeros(mask)][mask] = 0;
                continue;
            }
            for (int last = 0; last < countIslands; last++) {
                if (((mask >> last) & 1) == 0) {
                    continue;
                }
                for (int preLast = 0; preLast < countIslands; preLast++) {
                    if (last == preLast || ((mask >> preLast) & 1) == 0) {
                        continue;
                    }
                    int val = dp[preLast][mask ^ (1 << last)];
                    if (val == Integer.MAX_VALUE || a[preLast][last] == Integer.MAX_VALUE) {
                        continue;
                    }
                    dp[last][mask] = Math.min(dp[last][mask], val + a[preLast][last]);
                }
            }
        }
        int answer = Integer.MAX_VALUE;
        for (int last = 0; last < countIslands; last++) {
            answer = Math.min(answer, dp[last][(1 << countIslands) - 1]);
        }
        out.println(answer);
    }
}
