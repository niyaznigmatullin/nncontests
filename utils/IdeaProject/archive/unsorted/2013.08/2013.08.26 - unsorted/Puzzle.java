package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class Puzzle {

    static int[] DX1 = {1, 1, 0, 0, 0, 0};
    static int[] DY1 = {1, 1, -1, -1, 1, 1};
    static int[] DX2 = {0, 0, 0, -1, -1, 0};
    static int[] DY2 = {1, -1, -1, -1, -1, 1};

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int pid = 0;
        while (true) {
            ++pid;
            int n = in.nextInt();
            if (n == 0) {
                break;
            }
            out.println("Puzzle " + pid);
            char[][] c = new char[n][];
            for (int i = 0; i < n; i++) {
                c[i] = in.next().toCharArray();
            }
            boolean found = false;
            for (int dir = 0; dir < 6; dir++) {
                boolean ok = true;
                all: for (int i = 0; i < n; i++) {
                    for (int j = 0; j < 2 * i + 1; j++) {
                        if (c[i][j] != '0') continue;
                        int[] dx = (j & 1) == 1 ? DX2 : DX1;
                        int[] dy = (j & 1) == 1 ? DY2 : DY1;
                        int x = i + dx[dir];
                        int y = j + dy[dir];
                        if (x >= 0 && x < n && y >= 0 && y < 2 * x + 1 && c[x][y] == '1') {
                            ok = false;
                            break all;
                        }
                    }
                }
                if (ok) {
                    found = true;
                    break;
                }
            }
            if (found) {
                out.println("Parts can be separated");
            } else {
                out.println("Parts cannot be separated");
            }
            out.println();
        }
    }
}
