package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class BearAndRandomGrid {

    static int[] DX = {1, 0, -1, 0};
    static int[] DY = {0, 1, 0, -1};
    static String DIRS = "DRUL";

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int L = in.nextInt();
        int n = in.nextInt();
        char[] directions = in.next().toCharArray();
        int[] dirs = new int[L];
        for (int i = 0; i < L; i++) {
            dirs[i] = DIRS.indexOf(directions[i]);
        }
        char[][] c = in.readCharacterFieldTokens(n, n);
        int blockedCells = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                blockedCells += c[i][j] == '#' ? 1 : 0;
            }
        }
        if (blockedCells * 50 >= n * n) {
            int ans = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (c[i][j] == '#') {
                        continue;
                    }
                    int cx = i;
                    int cy = j;
                    int curMoves = L;
                    for (int move = 0; move < L; move++) {
                        int dir = dirs[move];
                        cx += DX[dir];
                        cy += DY[dir];
                        if (cx < 0 || cy < 0 || cx >= n || cy >= n || c[cx][cy] == '#') {
                            curMoves = move;
                            break;
                        }
                    }
                    ans ^= curMoves;
                }
            }
            out.println(ans);
        } else {
            int[] dx = new int[L];
            int[] dy = new int[L];
            int cx = 0;
            int cy = 0;
            for (int i = 0; i < L; i++) {
                cx += DX[dirs[i]];
                cy += DY[dirs[i]];
                dx[i] = cx;
                dy[i] = cy;
            }
            int[][] best = new int[n][n];
            for (int[] e : best) {
                Arrays.fill(e, L);
            }
            for (int i = -1; i <= n; i++) {
                for (int j = -1; j <= n; j++) {
                    if (i >= 0 && j >= 0 && i < n && j < n && c[i][j] != '#') continue;
                    for (int moves = 0; moves < L; moves++) {
                        int x = i - dx[moves];
                        int y = j - dy[moves];
                        if (x < 0 || y < 0 || x >= n || y >= n) {
                            continue;
                        }
                        best[x][y] = Math.min(best[x][y], moves);
                    }
                }
            }
            int ans = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (c[i][j] == '#') continue;
                    ans ^= best[i][j];
                }
            }
            out.println(ans);
        }
    }
}
