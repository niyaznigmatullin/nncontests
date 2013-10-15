package lib.test.on2013_08.on2013_08_26_.Task1970;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class Task1970 {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        a = new int[9][4][][];
        for (int i = 0; i < 9; i++) {
            int[][] b = in.readInt2DArray(4, 4);
            a[i][0] = b;
            for (int j = 1; j < 4; j++) {
                a[i][j] = rot(a[i][j - 1]);
            }
        }
        was = new boolean[9];
        field = new int[3][3][][];
//        if (!go(0, 0)) System.out.println("bad");
        if (!go(0, 0)) while (true);
        int[][] ans = new int[10][10];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int di = 0; di < 4; di++) {
                    for (int dj = 0; dj < 4; dj++) {
                        ans[i * 3 + di][j * 3 + dj] = field[i][j][di][dj];
                    }
                }
            }
        }
        for (int[] d : ans) {
            out.printArray(d);
        }
    }

    static int[][] rot(int[][] a) {
        int[][] ret = new int[a[0].length][a.length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                ret[j][a.length - i - 1] = a[i][j];
            }
        }
        return ret;
    }

    static int[][][][] a;
    static int[][][][] field;
    static boolean[] was;

    static boolean go(int x, int y) {
        if (y == 3) {
            return true;
        }
        if (x == 3) {
            return go(0, y + 1);
        }
        for (int i = 0; i < 9; i++) {
            if (was[i]) continue;
            was[i] = true;
            loop:
            for (int rot = 0; rot < 4; rot++) {
                int[][] cur = a[i][rot];
                if (x > 0) {
                    if (!matchesTop(field[x - 1][y], cur)) continue;
                }
                if (y > 0) {
                    if (!matchesLeft(field[x][y - 1], cur)) continue;
                }
                field[x][y] = cur;
                if (x < 2) {
                    boolean found = false;
                    all:
                    for (int j = 0; j < 9; j++) {
                        if (was[j]) continue;
                        for (int r = 0; r < 4; r++) {
                            if (matchesTop(cur, a[j][r])) {
                                found = true;
                                break all;
                            }
                        }
                    }
                    if (!found) continue;
                }
                if (y < 2) {
                    for (int j = 0; j <= x; j++) {
                        boolean found = false;
                        all:
                        for (int k = 0; k < 9; k++) {
                            if (was[k]) continue;
                            for (int r = 0; r < 4; r++) {
                                if (matchesLeft(field[j][y], a[k][r])) {
                                    found = true;
                                    break all;
                                }
                            }
                        }
                        if (!found) continue loop;
                    }
                }
                if (go(x + 1, y)) {
                    return true;
                }
            }
            was[i] = false;
        }
        return false;
    }

    static boolean matchesLeft(int[][] a, int[][] b) {
        for (int i = 0; i < 4; i++) {
            if (a[i][3] != b[i][0]) return false;
        }
        return true;
    }

    static boolean matchesTop(int[][] a, int[][] b) {
        for (int i = 0; i < 4; i++) {
            if (a[3][i] != b[0][i]) return false;
        }
        return true;
    }
}
