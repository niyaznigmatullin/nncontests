package lib.test.on2013_09.on2013_09_03_.Police;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Police {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int canBuild = in.nextInt();
        int[] from = new int[m];
        int[] to = new int[m];
        boolean[][] a = new boolean[n][n];
        for (int i = 0; i < n; i++) a[i][i] = true;
        for (int i = 0; i < m; i++) {
            from[i] = in.nextInt() - 1;
            to[i] = in.nextInt() - 1;
            a[from[i]][to[i]] = true;
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    a[i][j] |= a[i][k] && a[k][j];
                }
            }
        }
        int[] color = new int[n];
        Arrays.fill(color, -1);
        int colorsCount = 0;
        for (int i = 0; i < n; i++) {
            if (color[i] >= 0) continue;
            for (int j = i; j < n; j++) {
                if (a[i][j] && a[j][i]) {
                    color[j] = colorsCount;
                }
            }
            ++colorsCount;
        }
        int[] countVertices = new int[colorsCount];
        for (int i = 0; i < n; i++) {
            countVertices[color[i]]++;
        }
        int[] degIn = new int[colorsCount];
        int[] degOut = new int[colorsCount];
        for (int i = 0; i < m; i++) {
            int v = color[from[i]];
            int u = color[to[i]];
            if (v != u) {
                degIn[u]++;
                degOut[v]++;
            }
        }
        BigInteger ways = BigInteger.ONE;
        int haveMore = n;
        List<Integer> sizes = new ArrayList<>();
        for (int i = 0; i < colorsCount; i++) {
            if (degIn[i] == 0 || degOut[i] == 0) {
                sizes.add(countVertices[i]);
                haveMore -= countVertices[i];
            }
        }
        BigInteger[][] C = new BigInteger[n + 1][n + 1];
        for (BigInteger[] e : C) {
            Arrays.fill(e, BigInteger.ZERO);
        }
        for (int i = 0; i <= n; i++) {
            C[i][0] = BigInteger.ONE;
            for (int j = 1; j <= i; j++) {
                C[i][j] = C[i - 1][j - 1].add(C[i - 1][j]);
            }
        }
        BigInteger[][] dp = new BigInteger[sizes.size() + 1][canBuild + 1];
        for (BigInteger[] e : dp) {
            Arrays.fill(e, BigInteger.ZERO);
        }
        dp[0][0] = BigInteger.ONE;
        for (int i = 0; i < sizes.size(); i++) {
            int curSize = sizes.get(i);
            for (int j = 0; j <= canBuild; j++) {
                BigInteger val = dp[i][j];
                if (val.signum() == 0) continue;
                for (int get = 1; get <= curSize && get + j <= canBuild; get++) {
                    dp[i + 1][j + get] = dp[i + 1][j + get].add(val.multiply(C[curSize][get]));
                }
            }
        }
        BigInteger ans = BigInteger.ZERO;
        for (int j = 0; j <= canBuild; j++) {
            ans = ans.add(dp[sizes.size()][j].multiply(C[haveMore][canBuild - j]));
        }
        out.println(ans);
    }
}
