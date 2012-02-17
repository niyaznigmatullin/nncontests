package mypackage;

import DataStructures.DisjointSetUnion;
import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.Arrays;

public class TaskF {

    static final int[] DX = {1, 0, -1, 0};
    static final int[] DY = {0, 1, 0, -1};

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        char[][] c = new char[n][m];
        for (int i = 0; i < n; i++) {
            c[i] = in.next().toCharArray();
        }
        int startX = -1;
        int startY = -1;
        int finishX = -1;
        int finishY = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (c[i][j] == 'L') {
                    if (startX < 0) {
                        startX = i;
                        startY = j;
                    } else {
                        finishX = i;
                        finishY = j;
                    }
                }
            }
        }
        int start = code(startX, startY, n, m);
        int finish = code(finishX, finishY, n, m);
        DisjointSetUnion dsu = new DisjointSetUnion(n * m);
        int[] q = new int[n * m];
        int head = 0;
        int tail = 0;
        int[][] d = new int[n][m];
        for (int[] e : d) {
            Arrays.fill(e, Integer.MAX_VALUE);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (c[i][j] != 'X') {
                    q[tail++] = code(i, j, n, m);
                    d[i][j] = 0;
//                    for (int dir = 0; dir < 4; dir++) {
//                        int x = i + DX[dir];
//                        int y = j + DY[dir];
//                        if (x < 0 || y < 0 || x >= n || y >= m || c[x][y] == 'X') {
//                            continue;
//                        }
//                        dsu.union(code(i, j, n, m), code(x, y, n, m));
//                    }
                }
            }
        }
        int answer;
        if (dsu.get(start) == dsu.get(finish)) {
            answer = 0;
        } else {
            answer = -1;
            int lastDist = Integer.MIN_VALUE;
            all:
            while (head < tail) {
                int v = q[head++];
                int curX = v / m;
                int curY = v % m;
//                System.err.println(lastDist + " " + d[curX][curY]);
                if (d[curX][curY] != lastDist) {
                    for (int i = head - 1; i < tail; i++) {
                        int x = q[i] / m;
                        int y = q[i] % m;
                        for (int dir2 = 0; dir2 < 4; dir2++) {
                            int xx = x + DX[dir2];
                            int yy = y + DY[dir2];
                            if (xx < 0 || yy < 0 || xx >= n || yy >= m || d[xx][yy] != d[x][y]) {
                                continue;
                            }
                            dsu.union(q[i], code(xx, yy, n, m));
                        }
                    }
                    lastDist = d[curX][curY];
                    if (dsu.get(start) == dsu.get(finish)) {
                        answer = lastDist;
                        break all;
                    }
                }
                for (int dir = 0; dir < 4; dir++) {
                    int x = curX + DX[dir];
                    int y = curY + DY[dir];
                    if (x < 0 || y < 0 || x >= n || y >= m) {
                        continue;
                    }
                    int u = code(x, y, n, m);
                    dsu.union(v, u);
                    if (d[x][y] == Integer.MAX_VALUE) {
                        d[x][y] = d[curX][curY] + 1;
                        q[tail++] = u;
                    }
                }
            }
        }
        out.println(answer);
//        int stupidAnswer = solveStupid(n, m, c, startX, startY, finishX, finishY);
//        if (answer != stupidAnswer) {
//            throw new AssertionError("expected " + stupidAnswer + ", found " + answer);
//        }
    }

    private int solveStupid(int n, int m, char[][] c, int startX, int startY, int finishX, int finishY) {
        int stupidAnswer = 0;
        int[][] z = getMeltTime(n, m, c);
        boolean[][] was = new boolean[n][m];
        while (!canDo(stupidAnswer, n, m, z, startX, startY, finishX, finishY, was)) {
            ++stupidAnswer;
            for (boolean[] e : was) {
                Arrays.fill(e, false);
            }
        }
        return stupidAnswer;
    }

    static boolean canDo(int moves, int n, int m, int[][] melt, int startX, int startY, int finishX, int finishY, boolean[][] was) {
        if (finishX == startX && finishY == startY) {
            return true;
        }
        was[startX][startY] = true;
        for (int dir = 0; dir < 4; dir++) {
            int x = startX + DX[dir];
            int y = startY + DY[dir];
            if (x < 0 || y < 0 || x >= n || y >= m || melt[x][y] > moves || was[x][y]) {
                continue;
            }
            if (canDo(moves, n, m, melt, x, y, finishX, finishY, was)) {
                return true;
            }
        }
        return false;
    }

    static int[][] getMeltTime(int n, int m, char[][] c) {
        int[] q = new int[n * m];
        int head = 0;
        int tail = 0;
        int[][] ret = new int[n][m];
        for (int[] d : ret) {
            Arrays.fill(d, Integer.MAX_VALUE);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (c[i][j] != 'X') {
                    q[tail++] = code(i, j, n, m);
                    ret[i][j] = 0;
                }
            }
        }
        while (head < tail) {
            int v = q[head++];
            int curX = v / m;
            int curY = v % m;
            for (int dir = 0; dir < 4; dir++) {
                int x = curX + DX[dir];
                int y = curY + DY[dir];
                if (x < 0 || y < 0 || x >= n || y >= m || ret[x][y] != Integer.MAX_VALUE) {
                    continue;
                }
                ret[x][y] = ret[curX][curY] + 1;
                q[tail++] = code(x, y, n, m);
            }
        }
        return ret;
    }

    static int code(int i, int j, int n, int m) {
        return i * m + j;
    }

}
