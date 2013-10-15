package lib.test.on2013_10.on2013_10_05_Single_Round_Match_593.HexagonalBoard;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HexagonalBoard {


    static int[] dx = {0, 0, -1, -1, 1, 1};
    static int[] dy = {1, -1, 0, 1, -1, 0};

    static boolean dfs(int x, int y, int cc) {
        was[x][y] = true;
        color[x][y] = cc;
        for (int dir = 0; dir < 6; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            if (nx < 0 || ny < 0 || nx >= c.length || ny >= c[nx].length || c[nx][ny] != 'X') {
                continue;
            }
            if (was[nx][ny]) {
                if (color[nx][ny] == cc) {
                    return false;
                }
                continue;
            }
            countEdges++;
            if (!dfs(nx, ny, cc ^ 1)) {
                return false;
            }
        }
        return true;
    }

    static char[][] c;
    static int[][] color;
    static boolean[][] was;
    static int countEdges;

    public int minColors(String[] graph) {
        int n = graph.length;
        c = new char[n][];
        for (int i = 0; i < n; i++) {
            c[i] = graph[i].toCharArray();
        }
        was = new boolean[n][n];
        color = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(color[i], -1);
        }
        countEdges = 0;
        int countVertices = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (was[i][j] || c[i][j] != 'X') continue;
                ++countVertices;
                if (!dfs(i, j, 0)) {
                    return 3;
                }
            }
        }
        if (countVertices == 0) return 0;
        return countEdges == 0 ? 1 : 2;
    }
}
