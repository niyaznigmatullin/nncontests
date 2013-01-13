package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class EndOfTheWorld {
    static int[] index;
    static final int MAXN = 10000001;

    static {
        index = new int[MAXN];
        index[0] = index[1] = -1;
        for (int i = 2; i * i < MAXN; i++) {
            if (index[i] == 0) {
                for (int j = i * i; j < MAXN; j += i) {
                    index[j] = -1;
                }
            }
        }
        for (int i = 2, j = 0; i < MAXN; i++) {
            if (index[i] == 0) {
                index[i] = j++;
            }
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[][] a = new int[n][];
        for (int i = 0; i < n; i++) {
            a[i] = in.readIntArray(n);
        }
        long answer = 0;
        boolean[][] even = new boolean[n][n];
        boolean[][] odd = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (index[a[i][j]] >= 0) {
                    answer += index[a[i][j]];
                } else if ((a[i][j] & 1) == 0) {
                    even[i][j] = true;
                } else {
                    odd[i][j] = true;
                }
            }
        }
        q = new int[n * n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (even[i][j]) {
                    color(even, i, j);
                    answer += a[i][j] / 2;
                }
                if (odd[i][j]) {
                    color(odd, i, j);
                    answer += a[i][j] / 2 + 2;
                }
            }
        }
        out.println(answer);
    }

    static int[] DX = {1, 0, -1, 0};
    static int[] DY = {0, 1, 0, -1};
    static int[] q;

    static void color(boolean[][] a, int x, int y) {
        int head = 0;
        int tail = 1;
        int n = a.length;
        q[0] = x * n + y;
        a[x][y] = false;
        while (head < tail) {
            x = q[head] / n;
            y = q[head] % n;
            ++head;
            for (int dir = 0; dir < 4; dir++) {
                int i = x + DX[dir];
                int j = y + DY[dir];
                if (i >= 0 && i < n && j >= 0 && j < n && a[i][j]) {
                    q[tail++] = i * n + j;
                    a[i][j] = false;
                }
            }
        }
    }

}
