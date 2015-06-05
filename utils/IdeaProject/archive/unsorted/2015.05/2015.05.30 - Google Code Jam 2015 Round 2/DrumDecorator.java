package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.HashSet;
import java.util.Set;

public class DrumDecorator {

    static Set<String> q;
    static int[][] a;
    static int[][] need;
    static final int[] DX = {0, -1, 0};
    static final int[] DY = {-1, 0, 1};

    static boolean add(int x, int y, int r, int c, int toAdd) {
        boolean ok = true;
        for (int dir = 0; dir < 3; dir++) {
            int nx = x + DX[dir];
            int ny = (y + DY[dir] + c) % c;
            if (nx < 0) continue;
            if (a[nx][ny] == 0) continue;
            if (a[nx][ny] == a[x][y]) {
                need[nx][ny] += toAdd;
                need[x][y] += toAdd;
                if (need[x][y] < 0 || need[nx][ny] < 0) {
                    ok = false;
                }
            }
        }
        return ok;
    }

    static void go(int x, int y, int r, int c) {
        if (x == r) {
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    if (need[i][j] != 0) {
                        return;
                    }
                }
            }
            String best = null;
            for (int start = 0; start < c; start++) {
                String cur = "";
                for (int i = 0; i < c; i++) {
                    for (int j = 0; j < r; j++) {
                        cur = cur + a[j][(i + start) % c];
                    }
                }
                if (best == null || best.compareTo(cur) > 0) best = cur;
            }
            q.add(best);
            return;
        }
        if (y == c) {
            go(x + 1, 0, r, c);
            return;
        }
        for (int i = 1; i <= 4; i++) {
            if (i == 4 && (x == 1 || x + 1 == r)) continue;
            a[x][y] = i;
            need[x][y] = i;
            if (add(x, y, r, c, -1)) {
                if (x > 0 && need[x - 1][y] != 0) {

                } else
                    go(x, y + 1, r, c);
            }
            add(x, y, r, c, 1);
            a[x][y] = 0;
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        System.err.println(testNumber);
        int r = in.nextInt();
        int c = in.nextInt();
        a = new int[r][c];
        need = new int[r][c];
        q = new HashSet<>();
        go(0, 0, r, c);
        for (String e : q) {
            System.out.println("============");
            for (int i = 0; i < r * c; i += r) {
                System.out.println(e.substring(i, i + r));
            }
            System.out.println("============");
        }
        out.println("Case #" + testNumber + ": " + q.size());
    }
}
