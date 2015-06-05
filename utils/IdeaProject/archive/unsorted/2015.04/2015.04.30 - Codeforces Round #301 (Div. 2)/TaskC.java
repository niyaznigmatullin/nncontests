package coding;

import ru.ifmo.niyaz.DataStructures.DisjointSetUnion;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        char[][] c = in.readCharacterFieldTokens(n, m);
        int r1 = in.nextInt() - 1;
        int c1 = in.nextInt() - 1;
        int r2 = in.nextInt() - 1;
        int c2 = in.nextInt() - 1;
        boolean isX = c[r2][c2] == 'X';
        c[r1][c1] = '.';
        c[r2][c2] = '.';
        DisjointSetUnion dsu = new DisjointSetUnion(n * m);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (c[i][j] != '.') continue;
                if (i + 1 < n && c[i + 1][j] == '.') dsu.union(i * m + j, (i + 1) * m + j);
                if (j + 1 < m && c[i][j + 1] == '.') dsu.union(i * m + j, i * m + j + 1);
            }
        }
        if (r1 == r2 && c1 == c2) {
            int neighbours = 0;
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++)
                    if (dx * dx + dy * dy == 1) {
                        int x = r2 + dx;
                        int y = c2 + dy;
                        if (x < 0 || y < 0 || x >= n || y >= m || c[x][y] == 'X') continue;
                        ++neighbours;
                    }
            }
            if (neighbours >= 1) {
                out.println("YES");
            } else {
                out.println("NO");
            }
            return;
        }
        if (isX) {
            if (dsu.get(r1 * m + c1) == dsu.get(r2 * m + c2)) {
                out.println("YES");
            } else {
                out.println("NO");
            }
        } else {
            int neighbours = 0;
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++)
                    if (dx * dx + dy * dy == 1) {
                        int x = r2 + dx;
                        int y = c2 + dy;
                        if (x < 0 || y < 0 || x >= n || y >= m || c[x][y] == 'X') continue;
                        ++neighbours;
                    }
            }
            if (neighbours >= 2 && dsu.get(r1 * m + c1) == dsu.get(r2 * m + c2)) {
                out.println("YES");
            } else {
                out.println("NO");
            }
        }
    }
}
