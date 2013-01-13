package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        char[][] c = in.readCharacterFieldTokens(n, m);
        int[][] dir = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dir[i][j] = "LDRU".indexOf(c[i][j]);
            }
        }
        int[][][] next = new int[n][m][4];
        for (int[][] d1 : next) {
            for (int[] d2 : d1) {
                Arrays.fill(d2, -1);
            }
        }
        {
            int[] up = new int[m];
            int[] left = new int[n];
            Arrays.fill(up, -1);
            Arrays.fill(left, -1);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (dir[i][j] < 0) {
                        continue;
                    }
                    next[i][j][3] = up[j];
                    if (up[j] >= 0) {
                        next[up[j]][j][1] = i;
                    }
                    up[j] = i;
                    next[i][j][0] = left[i];
                    if (left[i] >= 0) {
                        next[i][left[i]][2] = j;
                    }
                    left[i] = j;
                }
            }
        }
        int[][][] next2 = next.clone();
        for (int i = 0; i < next2.length; i++) {
            next2[i] = next2[i].clone();
            for (int j = 0; j < next2[i].length; j++) {
                next2[i][j] = next2[i][j].clone();
            }
        }
        int[] gotX = new int[n * m];
        int[] gotY = new int[n * m];
        int countGot = 0;
        int maximal = 0;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (dir[i][j] < 0) {
                    continue;
                }
                countGot = 0;
                int curX = i;
                int curY = j;
                while (curX >= 0 && curY >= 0) {
                    gotX[countGot] = curX;
                    gotY[countGot] = curY;
                    ++countGot;
                    int curDir = dir[curX][curY];
                    int[] links = next[curX][curY];
                    int goTo = links[curDir];
                    int nextX = curX;
                    int nextY = curY;
                    if ((curDir & 1) == 0) {
                        nextY = goTo;
                    } else {
                        nextX = goTo;
                    }
                    for (int d = 0; d < 4; d++) {
                        int z = links[d];
                        if (z < 0) {
                            continue;
                        }
                        int dx = curX;
                        int dy = curY;
                        if ((d & 1) == 0) {
                            dy = z;
                        } else {
                            dx = z;
                        }
                        next[dx][dy][d ^ 2] = links[d ^ 2];
                    }
                    curX = nextX;
                    curY = nextY;
                }
                if (countGot > maximal) {
                    maximal = countGot;
                    count = 1;
                } else if (countGot == maximal) {
                    ++count;
                }
                while (countGot > 0) {
                    --countGot;
                    curX = gotX[countGot];
                    curY = gotY[countGot];
                    int[] links1 = next[curX][curY];
                    int[] links2 = next2[curX][curY];
                    for (int d = 0; d < 4; d++) {
                        int val = links2[d];
                        links1[d] = val;
                        if (val < 0) {
                            continue;
                        }
                        int dx = curX;
                        int dy = curY;
                        if ((d & 1) == 0) {
                            dy = val;
                            next[dx][dy][d ^ 2] = curY;
                        } else {
                            dx = val;
                            next[dx][dy][d ^ 2] = curX;
                        }
                    }
                }
            }
        }
        out.println(maximal + " " + count);
    }
}
