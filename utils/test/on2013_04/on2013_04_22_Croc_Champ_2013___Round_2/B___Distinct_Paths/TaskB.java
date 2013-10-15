package lib.test.on2013_04.on2013_04_22_Croc_Champ_2013___Round_2.B___Distinct_Paths;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        n = in.nextInt();
        m = in.nextInt();
        k = in.nextInt();
        a = new int[n][];
        for (int i = 0; i < n; i++) {
            a[i] = in.readIntArray(m);
        }
        if (n + m - 1 > k) {
            out.println(0);
            return;
        }
        mask = new int[n][m];
        map = new int[k];
        curColor = new int[n][m];
        Arrays.fill(map, -1);
        cntMatched = 0;
        answer = 0;
        was = new boolean[k];
        go(0, 0, 0);
        out.println(answer);
    }

    static int[][] a;
    static int[][] mask;
    static int[][] curColor;
    static int k;
    static int n, m;
    static long answer;
    static int[] map;
    static int cntMatched;
    static boolean[] was;
    static int[][] C;
    static int[] fact;
    static {
        C = new int[21][21];
        fact = new int[12];
        for (int i = 0; i < C.length; i++) {
            C[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                C[i][j] = C[i - 1][j - 1] + C[i - 1][j];
            }
        }
        fact[0] = 1;
        for (int i = 1; i < fact.length; i++) {
            fact[i] = fact[i - 1] * i;
        }
    }

    static final int MOD = 1000000007;

    static void go(int x, int y, int colors) {
        if (y == m) {
            go(x + 1, 0, colors);
            return;
        }
        if (x == n) {
            answer = (answer + (long) fact[(colors - cntMatched)] * C[k - cntMatched][colors - cntMatched]) % MOD;
            return;
        }
        mask[x][y] = 0;
        if (x > 0) {
            mask[x][y] |= mask[x - 1][y] | (1 << curColor[x - 1][y]);
        }
        if (y > 0) {
            mask[x][y] |= mask[x][y - 1] | (1 << curColor[x][y - 1]);
        }
        int curMask = mask[x][y];
        int already = a[x][y] - 1;
        for (int i = 0; i <= colors && i < k; i++) {
            if (((curMask >> i) & 1) == 1) {
                continue;
            }
            if (already >= 0) {
                if (map[already] >= 0 && map[already] != i) {
                    continue;
                }
                if (map[already] < 0 && was[i]) {
                    continue;
                }
            }
            boolean set = false;
            if (already >= 0 && map[already] < 0) {
                set = true;
                map[already] = i;
                ++cntMatched;
                was[i] = true;
            }
            curColor[x][y] = i;
            go(x, y + 1, Math.max(colors, i + 1));
            if (set) {
                map[already] = -1;
                --cntMatched;
                was[i] = false;
            }
        }
    }
}
