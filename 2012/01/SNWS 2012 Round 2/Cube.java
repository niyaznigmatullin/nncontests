package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.Arrays;

public class Cube {

    static char[][][] dontUseThisVariable;
    static int[][][] color;
    static final int[] DX = {1, -1, 0, 0, 0, 0};
    static final int[] DY = {0, 0, 1, -1, 0, 0};
    static final int[] DZ = {0, 0, 0, 0, 1, -1};

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        dontUseThisVariable = new char[n][n][];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dontUseThisVariable[i][j] = in.next().toCharArray();
            }
        }
        color = new int[n][n][n];
        for (int[][] d1 : color) {
            for (int[] d : d1) {
                Arrays.fill(d, -1);
            }
        }
        int colors = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (color[i][j][k] < 0) {
                        dfs(i, j, k, colors++);
                    }
                }
            }
        }
        if (colors == 1) {
            out.println("Yes");
            return;
        }
        for (int curColor = 0; curColor < colors; curColor++) {
            all:
            for (int dir = 0; dir < DX.length; dir++) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        for (int k = 0; k < n; k++) {
                            if (color[i][j][k] != curColor) {
                                continue;
                            }
                            int x = i;
                            int y = j;
                            int z = k;
                            while (x >= 0 && y >= 0 && z >= 0 && x < n && y < n && z < n) {
                                if (color[x][y][z] != curColor) {
                                    continue all;
                                }
                                x += DX[dir];
                                y += DY[dir];
                                z += DZ[dir];
                            }
                        }
                    }
                }
                out.println("No");
                return;
            }
        }
        for (int dir = 0; dir < DX.length; dir++) {
            boolean[][] g = new boolean[colors][colors];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < n; k++) {
                        int x = i - DX[dir];
                        int y = j - DY[dir];
                        int z = k - DZ[dir];
                        if (x >= 0 && y >= 0 && z >= 0 && x < n && y < n && z < n) {
                            continue;
                        }
                        x = i;
                        y = j;
                        z = k;
                        boolean[] have = new boolean[colors];
                        while (x >= 0 && y >= 0 && z >= 0 && x < n && y < n && z < n) {
                            int curColor = color[x][y][z];
                            for (int v = 0; v < colors; v++) {
                                if (have[v]) {
                                    g[curColor][v] = true;
                                }
                            }
                            have[curColor] = true;
                            x += DX[dir];
                            y += DY[dir];
                            z += DZ[dir];
                        }
                    }
                }
            }
            for (int k = 0; k < colors; k++) {
                for (int i = 0; i < colors; i++) {
                    for (int j = 0; j < colors; j++) {
                        g[i][j] |= g[i][k] && g[k][j];
                    }
                }
            }
            for (int i = 0; i < colors; i++) {
                for (int j = 0; j < colors; j++) {
                    if (i != j && !g[i][j]) {
                        out.println("No");
                        return;
                    }
                }
            }
        }
        out.println("Yes");
    }

    static void dfs(int i, int j, int k, int curColor) {
        color[i][j][k] = curColor;
        for (int dir = 0; dir < DX.length; dir++) {
            int x = i + DX[dir];
            int y = j + DY[dir];
            int z = k + DZ[dir];
            if (x < 0 || y < 0 || z < 0 || x >= dontUseThisVariable.length || y >= dontUseThisVariable[x].length || z >= dontUseThisVariable[x][y].length ||
                    color[x][y][z] >= 0 || dontUseThisVariable[i][j][k] != dontUseThisVariable[x][y][z]) {
                continue;
            }
            dfs(x, y, z, curColor);
        }
    }
}
