package coding;

import com.sun.org.apache.bcel.internal.generic.DUP_X1;

import java.util.Arrays;

public class Tunnels {

    static char[][] c;
    static int[][] id;
    static int[] DX = {1, 0, -1, 0};
    static int[] DY = {0, 1, 0, -1};
    static int n, m;

    static void dfs(int x, int y, int color) {
        id[x][y] = color;
        for (int dir = 0; dir < 4; dir++) {
            int nx = x + DX[dir];
            int ny = y + DY[dir];
            if (nx < 0 || ny < 0 || nx >= n || ny >= m || id[nx][ny] >= 0 || c[nx][ny] != 'X') {
                continue;
            }
            dfs(nx, ny, color);
        }
    }

    public int minimumTunnels(String[] s) {
        c = new char[s.length][];
        for (int i = 0; i < s.length; i++) {
            c[i] = s[i].toCharArray();
        }
        int countX = 0;
        for (char[] d : c) {
            for (char e : d)  {
                if (e == 'X') {
                    countX++;
                }
            }
        }
        if (countX == 0) return 0;
        n = c.length;
        m = c[0].length;
        if (m == 1) return 1;
        int cnt = 0;
        id = new int[n][m];
        for (int[] d : id) {
            Arrays.fill(d, -1);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (c[i][j] == 'X' && id[i][j] < 0) {
                    dfs(i, j, cnt++);
                }
            }
        }
        int[] minx = new int[cnt];
        int[] maxx = new int[cnt];
        Arrays.fill(minx, Integer.MAX_VALUE);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (id[i][j] >= 0) {
                    int z = id[i][j];
                    minx[z] = Math.min(minx[z], i);
                    maxx[z] = Math.max(maxx[z], i);
                }
            }
        }
        boolean[][] start = new boolean[n][m];
        boolean[][] end = new boolean[n][m];
        for (int i = 0; i < cnt; i++) {
            if (minx[i] == maxx[i]) {
                int cx = minx[i];
                int left = -1;
                int right = -1;
                for (int j = 0; j < m; j++) {
                    if (id[cx][j] == i) {
                        if (left < 0) left = j;
                        right = j;
                    }
                }
                boolean spec = cx == 0;
                if (left == 0 && right == m - 1) {
                    spec = true;
                }
                if (spec) {
                    if (left == 0) {
                        start[cx][left] = true;
                        end[cx][left] = true;
                    }
                    if (right == m - 1) {
                        start[cx][right] = true;
                        end[cx][right] = true;
                    }
                } else {
                    if (left == 0) {
                        start[cx][left] = true;
                    }
                    if (right == m - 1) {
                        start[cx][right] = true;
                    }
                }
            }
        }
        for (int i = 0; i < cnt; i++) {
            if (minx[i] != maxx[i]) {
                for (int t = 0; t < 2; t++) {
                    int cx = (t == 0 ? minx : maxx)[i];
                    for (int j = 0; j < m; j++) {
                        if (id[cx][j] != i) continue;
                        int cne = 0;
                        for (int dir = 0; dir < 4; dir++) {
                            int x = cx + DX[dir];
                            int y = j + DY[dir];
                            if (x < 0 || y < 0 || x >= n || y >= m || id[x][y] != i) {
                                continue;
                            }
                            ++cne;
                        }
                        if (cne == 1) {
                            if (j == 0 || j == m - 1) {
                                if (cx == minx[i]) {
                                    start[cx][j] = true;
                                }
                                if (cx == maxx[i]) {
                                    end[cx][j] = true;
                                }
                            }
                        }
                    }
                }
            }
        }
        start[0][0] = false;
        start[0][m - 1] = false;
        int[][][][] dp = new int[2][2][n + 1][n + 1];
        int[][][][] dp2 = new int[2][2][n + 1][n + 1];
        for (int[][][] d1 : dp) {
            for (int[][] d2 : d1) {
                for (int[] d : d2) {
                    Arrays.fill(d, Integer.MIN_VALUE);
                }
            }
        }
//        for (char[] d : c) {
//            System.out.println(d);
//        }
        dp[0][0][0][0] = 0;
        for (int i = 0; i < n; i++) {
            for (int[][][] d1 : dp2) {
                for (int[][] d2 : d1) {
                    for (int[] d : d2) {
                        Arrays.fill(d, Integer.MIN_VALUE);
                    }
                }
            }
//            System.out.println(i + " " + start[i][0] + " " + end[i][0] + " " + start[i][m - 1] + " " + end[i][m - 1]);
            for (int last1 = 0; last1 < 2; last1++) {
                for (int last2 = 0; last2 < 2; last2++) {
                    for (int left = 0; left <= i; left++) {
                        for (int right = 0; right <= i; right++) {
                            int val = dp[last1][last2][left][right];
                            if (val == Integer.MIN_VALUE) continue;
                            boolean any1 = start[i][0] && end[i][0];
                            boolean any2 = start[i][m - 1] && end[i][m - 1];
                            for (int dx = -1; dx <= 1; dx++) {
                                for (int d1 = 0; d1 < 3; d1++) {
                                    if (d1 == 0 && (dx == 1 || !start[i][0]) || d1 == 1 && (dx != 1 || !end[i][0]) || d1 == 2 && (start[i][0] || dx != 0)) continue;
                                    if (dx == -1 && (!start[i][0] || left == 0)) continue;
                                    if (dx == 1 && !end[i][0]) continue;
                                    int zval = dx < 0 ? 1 : 0;
                                    int nx = left + dx;
                                    if (dx == 0 && start[i][0]) {
                                        nx = 0;
                                    }
                                    int c1 = 1;
                                    if (d1 == 2 || dx == 0 && d1 == 1) c1 = 0;
                                    if (c1 + last1 > 1) continue;
                                    for (int dy = -1; dy <= 1; dy++) {
                                        for (int d2 = 0; d2 < 3; d2++) {
                                            if (d2 == 0 && (dy == 1 || !start[i][m - 1]) || d2 == 1 && (dy != 1 || !end[i][m - 1]) || d2 == 2 && (start[i][m - 1] || dy != 0))
                                                continue;
                                            if (dy == -1 && (!start[i][m - 1] || right == 0)) continue;
                                            if (dy == 1 && !end[i][m - 1]) continue;
                                            int zval2 = zval + (dy < 0 ? 1 : 0);
                                            int ny = right + dy;
                                            if (dy == 0 && start[i][m - 1]) {
                                                ny = 0;
                                            }
                                            int c2 = 1;
                                            if (d2 == 2 || dy == 0 && d2 == 1) c2 = 0;
                                            if (c2 + last2 > 1) continue;
                                            if (any1 && any2 && d1 == d2) {
                                                continue;
                                            }
                                            if (i == 0 && d1 == 1 && d2 == 1 && id[0][0] == id[0][m - 1]) continue;
//                                            if (i == 0 && (d1 < 2 || d2 < 2)) {
//                                                System.out.println("bad " + i + " " + d1 + " " + d2);
//                                            }
                                            dp2[c1][c2][nx][ny] = Math.max(dp2[c1][c2][nx][ny], val + zval2);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            int[][][][] t = dp;
            dp = dp2;
            dp2 = t;
//            for (int c = 0; c < 2; c++) {
//                for (int d = 0; d < 2; d++) {
//                    for (int left = 0; left <= n; left++) {
//                        for (int right = 0; right <= n; right++) {
//                            if (dp[c][d][left][right] != Integer.MIN_VALUE) {
//                                System.out.println(i + " " + c + " " + d + " " + left + " " + right + " " + dp[c][d][left][right]);
//                            }
//                        }
//                    }
//                }
//            }
        }
        int ret = 0;
        for (int c = 0; c < 2; c++) {
            for (int d = 0; d < 2; d++) {
                for (int i = 0; i <= n; i++) {
                    for (int j = 0; j <= n; j++) {
                        if (dp[c][d][i][j] > ret) ret = dp[c][d][i][j];
                    }
                }
            }
        }
//        System.out.println(cnt + " " + ret);
        return cnt - ret;
    }
}
