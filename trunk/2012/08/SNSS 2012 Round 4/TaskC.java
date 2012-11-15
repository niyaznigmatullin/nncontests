package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

public class TaskC {

    static int[] DX = {0, -1, 0, 1};
    static int[] DY = {1, 0, -1, 0};
    static char[] DIRS = "ENWS".toCharArray();

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int m = in.nextInt();
        int n = in.nextInt();
        int startY = in.nextInt();
        int startX = in.nextInt();
        int finishY = in.nextInt();
        int finishX = in.nextInt();
        char[][] c = new char[n][];
        for (int i = 0; i < n; i++) {
            c[i] = in.next().toCharArray();
        }
        int[] qX = new int[n * m * 4];
        int[] qY = new int[n * m * 4];
        int[] qDir = new int[n * m * 4];
        boolean[][][] was = new boolean[4][n][m];
        qX[0] = startX;
        qY[0] = startY;
        qDir[0] = 0;
        int head = 0;
        int tail = 1;
        int[][][] lastDir = new int[4][n][m];
        while (head < tail) {
            int curX = qX[head];
            int curY = qY[head];
            int curDir = qDir[head++];
            if (curX == finishX && curY == finishY) {
                StringBuilder answer = new StringBuilder();
                while (curX != startX || curY != startY) {
                    int last = lastDir[curDir][curX][curY];
                    curY = last % m;
                    last /= m;
                    curX = last % n;
                    last /= n;
                    curDir = last & 3;
                    last >>= 2;
                    answer.append(DIRS[last]);
                }
                out.println(answer.reverse());
                return;
            }
            int val = convert(c[curX][curY]);
            for (int jdir = 0; jdir < 4; jdir++) {
                int dir = jdir - curDir & 3;
                if (((val >> dir) & 1) == 0) {
                    continue;
                }
                int newX = curX + DX[dir];
                int newY = curY + DY[dir];
                if (newX < 0 || newY < 0 || newX >= n || newY >= m) {
                    continue;
                }
                int newVal = convert(c[newX][newY]);
                int newDir = curDir + ((newVal >> 4) & 1) & 3;
                if (was[newDir][newX][newY]) {
                    continue;
                }
                was[newDir][newX][newY] = true;
                lastDir[newDir][newX][newY] = jdir * 4 * n * m + curDir * n * m + curX * m + curY;
                qX[tail] = newX;
                qY[tail] = newY;
                qDir[tail] = newDir;
                tail++;
            }
        }
        throw new AssertionError();
    }

    private int convert(char ch) {
        return ch >= '0' && ch <= '9' ? ch - '0' : ch - 'A' + 10;
    }
}
