package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class Task1334 {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int[][] field = new int[8][8];
        for (int i = 0; i < 32; i++) {
            {
                String s = in.next();
                int x = s.charAt(0) - 'a';
                int y = s.charAt(1) - '1';
                field[x][y] = 1 + (i & 1);
            }
            for (int x = 0; x < 8; x++) {
                for (int y = 0; y < 8; y++) {
                    if (field[x][y] == 0) {
                        continue;
                    }
                    for (int dx = -1; dx <= 1; dx += 2) {
                        for (int dy = -1; dy <= 1; dy += 2) {
                            int x1 = x + dx;
                            int y1 = y + dy;
                            int x2 = x + 2 * dx;
                            int y2 = y + 2 * dy;
                            if (x2 < 0 || x2 >= 8 || y2 < 0 || y2 >= 8) {
                                continue;
                            }
                            if (field[x1][y1] > 0 && field[x2][y2] == 0 && field[x1][y1] != field[x][y]) {
                                out.println(i + 1);
                                return;
                            }
                        }
                    }
                }
            }
        }
        out.println("Draw");
    }
}
