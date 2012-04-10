package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

public class TaskB {

    static int[] DX = {1, 0, -1, 0};
    static int[] DY = {0, 1, 0, -1};

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        char[][] c = new char[n][];
        for (int i = 0; i < n; i++) {
            c[i] = in.next().toCharArray();
        }
        int[][][] d = new int[4][n][m];
        int[] q = new int[5 * n * m];
        boolean[][][] was = new boolean[4][n][m];
        int head = 0;
        int tail = 0;
        q[tail++] = code(3, n - 1, m - 1, n, m);
        was[3][n - 1][m - 1] = true;
        while (head != tail) {
            int state = q[head++];
            if (head == q.length) {
                head = 0;
            }
            int dir = state / (n * m);
            int x = state / m % n;
            int y = state % m;
            {
                int newX = x + DX[dir];
                int newY = y + DY[dir];
                if (newX < 0 || newY < 0 || newX >= n || newY >= m || was[dir][newX][newY]) {
                } else {
                    d[dir][newX][newY] = d[dir][x][y];
                    --head;
                    if (head < 0) {
                        head += q.length;
                    }
                    q[head] = code(dir, newX, newY, n, m);
                    was[dir][newX][newY] = true;
                }
            }
            if (c[x][y] != '#') {
                continue;
            }
            for (int i = 0; i < 4; i++) {
                int newDir = i + dir & 3;
                int newX = x;
                int newY = y;
                if (newX < 0 || newY < 0 || newX >= n || newY >= m || was[newDir][newX][newY]) {
                    continue;
                }
                d[newDir][newX][newY] = d[dir][x][y] + 1;
                q[tail++] = code(newDir, newX, newY, n, m);
                was[newDir][newX][newY] = true;
                if (tail == q.length) {
                    tail = 0;
                }
            }
        }
        if (!was[3][0][0]) {
            out.println(-1);
        } else {
            out.println(d[3][0][0]);
        }
    }

    static int code(int dir, int x, int y, int n, int m) {
        return y + x * m + dir * n * m;
    }
}
