package coding;

public class VisitALot {
    static int[][] field;

    static boolean[][] used;
    static int[] path;

    static int[] DX = {1, 0, -1, 0};
    static int[] DY = {0, 1, 0, -1};
    static char[] DIRS = "SENW".toCharArray();

    static int go(int x, int y, int cur) {
        if ((cur + 1) * 2 >= field.length * field[x].length) {
            return cur;
        }
        used[x][y] = true;
        for (int dir = 0; dir < 4; dir++) {
            int nx = x + DX[dir];
            int ny = y + DY[dir];
            if (nx < 0 || ny < 0 || nx >= field.length || ny >= field[nx].length) continue;
            if (used[nx][ny] || field[nx][ny] == 1) continue;
            path[cur] = dir;
            int got = go(nx, ny, cur + 1);
            if (got >= 0) return got;
        }
        used[x][y] = false;
        return -1;
    }

    public String travel(int R, int C, int[] obsr, int[] obsc) {
        if (Math.max(R, C) <= 5) {
            field = new int[R][C];
            for (int i = 0; i < obsr.length; i++) {
                field[obsr[i]][obsc[i]] = 1;
            }
            path = new int[R * C];
            used = new boolean[R][C];
            int len = go(0, 0, 0);
            StringBuilder ans = new StringBuilder();
            for (int i = 0; i < len; i++) {
                ans.append(DIRS[path[i]]);
            }
            return ans.toString();
        }
        if (R < C) {
            boolean[] used = new boolean[C];
            for (int i = 0; i < obsr.length; i++) {
                used[obsc[i]] = true;
            }
            int x = 0;
            int y = 0;
            StringBuilder ans = new StringBuilder();
            while (true) {
                while (y < C && used[y]) {
                    ans.append("E");
                    y++;
                }
                if (y >= C) break;
                if (x == 0) {
                    while (x + 1 < R) {
                        ans.append("S");
                        x++;
                    }
                } else {
                    while (x > 0) {
                        ans.append("N");
                        x--;
                    }
                }
                ans.append("E");
                y++;
            }
            return ans.substring(0, ans.length() - 1);
        } else {
            boolean[] used = new boolean[R];
            for (int i = 0; i < obsr.length; i++) {
                used[obsr[i]] = true;
            }
            int x = 0;
            int y = 0;
            StringBuilder ans = new StringBuilder();
            while (true) {
                while (x < R && used[x]) {
                    ans.append("S");
                    x++;
                }
                if (x >= R) break;
                if (y == 0) {
                    while (y + 1 < C) {
                        ans.append("E");
                        y++;
                    }
                } else {
                    while (y > 0) {
                        ans.append("W");
                        y--;
                    }
                }
                ans.append("S");
                x++;
            }
            return ans.substring(0, ans.length() - 1);
        }
    }
}
