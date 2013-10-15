package lib.test.on2013_08.on2013_08_30_Codeforces_Round__198__Div__1_.D___Iahub_and_Xors;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskD {

    static long[][][][] f;

    static long query(int x, int y) {
        long[][] ff = f[x - 1 & 1][y - 1 & 1];
        long ret1 = 0;
        for (int i = x; i >= 0; i = (i & (i + 1)) - 1) {
            for (int j = y; j >= 0; j = (j & (j + 1)) - 1) {
                ret1 ^= ff[i][j];
            }
        }
        return ret1;
    }

    static long query(int x1, int y1, int x2, int y2) {
        return query(x2, y2) ^ query(x1 - 1, y2) ^ query(x2, y1 - 1) ^ query(x1 - 1, y1 - 1);
    }

    static void update(long[][] f, int x, int y, long v) {
        for (int i = x; i < f.length; i |= i + 1) {
            for (int j = y; j < f[i].length; j |= j + 1) {
                f[i][j] ^= v;
            }
        }
    }

    static void update(int x1, int y1, int x2, int y2, long v) {
        update(f[x2 & 1][y2 & 1], x2, y2, v);
        update(f[x1 - 1 & 1][y2 & 1], x1, y2, v);
        update(f[x2 & 1][y1 - 1 & 1], x2, y1, v);
        update(f[x1 - 1 & 1][y1 - 1 & 1], x1, y1, v);
    }


    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        f = new long[2][2][n][n];
        for (int i = 0; i < m; i++) {
            int op = in.nextInt();
            int x1 = in.nextInt() - 1;
            int y1 = in.nextInt() - 1;
            int x2 = in.nextInt() - 1;
            int y2 = in.nextInt() - 1;
            if (op == 1) {
                out.println(query(x1, y1, x2, y2));
            } else {
                long v = in.nextLong();
                update(x1, y1, x2, y2, v);
            }
        }
    }
}
