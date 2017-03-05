package coding;

import ru.ifmo.niyaz.DataStructures.DisjointSetUnion;

public class ConnectedSubgraph {
    public String[] construct(int k) {
        int n = 1;
        while ((1 << n) - 1 < k) {
            n++;
        }
        while (n <= 20) {
            f = new boolean[n][n];
            if (!go(n, k)) {
                n++;
                continue;
            }
//            int got = 0;
//            for (int mask = 1; mask < 1 << n; mask++) {
//                int comps = Integer.bitCount(mask);
//                DisjointSetUnion dsu = new DisjointSetUnion(n);
//                for (int i = 0; i < n; i++) {
//                    if (((mask >> i) & 1) == 0) continue;
//                    for (int j = 0; j < n; j++) {
//                        if (!f[i][j] || ((mask >> j) & 1) == 0) continue;
//                        if (dsu.union(i, j)) {
//                            comps--;
//                        }
//                    }
//                }
//                if (comps == 1) {
//                    ++got;
//                }
//            }
//            if (got != k) throw new AssertionError();
            String[] ans = new String[n];
            for (int i = 0; i < n; i++) {
                ans[i] = "";
                for (int j = 0; j < n; j++) {
                    if (f[i][j]) {
                        ans[i] += "Y";
                    } else {
                        ans[i] += "N";
                    }
                }
            }
            return ans;
        }
        throw new AssertionError();
    }

    static boolean go(int n, int k) {
        if (n == 0) {
            return k == 0;
        }
        if (k < n) return false;
        if (k == n) {
            return true;
        }
        if (k == n * (n + 1) / 2) {
            for (int i = 0; i < n - 1; i++) {
                f[i][i + 1] = f[i + 1][i] = true;
            }
            return true;
        }
        if (1 + n * (n - 1) == k) {
            for (int i = 0; i < n - 1; i++) {
                f[i][i + 1] = f[i + 1][i] = true;
            }
            f[0][n - 1] = f[n - 1][0] = true;
            return true;
        }
        if ((1 << (n - 1)) <= k) {
            if (go(n - 1, k - (1 << (n - 1)))) {
                for (int i = 0; i < n - 1; i++) {
                    f[i][n - 1] = f[n - 1][i] = true;
                }
                return true;
            }
        }
        for (int e = 2; e <= n; e++) {
            if (e * (e + 1) / 2 <= k) {
                if (go(n - e, k - e * (e + 1) / 2)) {
                    for (int i = n - e; i + 1 < n; i++) {
                        f[i][i + 1] = f[i + 1][i] = true;
                    }
                    return true;
                }
            }
        }
        if (go(n - 1, k - 1)) {
            return true;
        }
        return false;
    }

    static boolean[][] f;
}
