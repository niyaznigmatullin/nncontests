package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Straight {

    static int[] DX = {1, 0, -1, 0};
    static int[] DY = {0, 1, 0, -1};

    static String DIR = "NESW";

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        String[] firstLine = in.nextLine().split(" ");
        int n = Integer.parseInt(firstLine[0]);
        int m = Integer.parseInt(firstLine[1]);
        int[][] can = new int[n][m];
        char[][] c = new char[2 * n - 1][];
        for (int i = 0; i < 2 * n - 1; i++) {
            int j = 2 * n - 1 - i - 1;
            c[j] = in.nextLine().toCharArray();
            c[j] = Arrays.copyOf(c[j], 2 * m - 1);
            for (int k = 0; k < 2 * m - 1; k++) {
                if (c[j][k] == 0) {
                    c[j][k] = ' ';
                }
            }
        }
        for (int i = 0; i < n; i++) {
            int rowNumber = 2 * i;
            for (int j = 0; j < m; j++) {
                int colNumber = 2 * j;
                for (int dir = 0; dir < 4; dir++) {
                    int x = rowNumber + DX[dir];
                    int y = colNumber + DY[dir];
                    if (x < 0 || y < 0 || x >= 2 * n - 1 || y >= 2 * m - 1) {
                        continue;
                    }
                    if (c[x][y] != ' ') {
                        can[i][j] |= 1 << dir;
                    }
                }
            }
        }
        Queue<Point> q = new ArrayDeque<Point>();
        q.add(new Point(n - 1, m - 1));
        int[][] d = new int[n][m];
        for (int[] e : d) {
            Arrays.fill(e, Integer.MAX_VALUE);
        }
        d[n - 1][m - 1] = 0;
        while (!q.isEmpty()) {
            Point v = q.poll();
            for (int dir = 0; dir < 4; dir++) {
                if (((can[v.x][v.y] >> dir) & 1) == 0) {
                    continue;
                }
                int x = v.x + DX[dir];
                int y = v.y + DY[dir];
                if (d[x][y] == Integer.MAX_VALUE) {
                    d[x][y] = d[v.x][v.y] + 1;
                    q.add(new Point(x, y));
                }
            }
        }
        int firstDir;
        if ((can[0][0] & 1) == 1 && d[1][0] == d[0][0] - 1) {
            firstDir = 0;
        } else {
            firstDir = 1;
        }
        int curDir = firstDir;
        int curX = DX[curDir];
        int curY = DY[curDir];
        StringBuilder ans = new StringBuilder();
        while (curX != n - 1 || curY != m - 1) {
            if (((can[curX][curY] >> curDir) & 1) == 1 && d[curX + DX[curDir]][curY + DY[curDir]] + 1 == d[curX][curY]) {
                ans.append('F');
                curX += DX[curDir];
                curY += DY[curDir];
                continue;
            }
            {
                int newDir = curDir + 1 & 3;
                if (((can[curX][curY] >> newDir) & 1) == 1 && d[curX + DX[newDir]][curY + DY[newDir]] + 1 == d[curX][curY]) {
                    ans.append('R');
                    curX += DX[newDir];
                    curY += DY[newDir];
                    curDir = newDir;
                    continue;
                }
            }
            {
                int newDir = curDir + 3 & 3;
                if (((can[curX][curY] >> newDir) & 1) == 1 && d[curX + DX[newDir]][curY + DY[newDir]] + 1 == d[curX][curY]) {
                    ans.append('L');
                    curX += DX[newDir];
                    curY += DY[newDir];
                    curDir = newDir;
                    continue;
                }
            }
        }
        out.println(DIR.charAt(firstDir));
        out.println(ans);
    }

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
