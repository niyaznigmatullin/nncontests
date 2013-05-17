package lib.test.on2013_03.on2013_03_06_Single_Round_Match_572.NewArenaPassword;



import ru.ifmo.niyaz.DataStructures.DisjointSetUnion;

public class NewArenaPassword {
    public int minChange(String s, int k) {
        char[] c = s.toCharArray();
        int n = c.length;
        DisjointSetUnion dsu = new DisjointSetUnion(n);
        for (int i = 0; i < k; i++) {
            dsu.union(i, n - k + i);
        }
        char[][] d = new char[256][n];
        int[] cnt = new int[n];
        for (int i = 0; i < n; i++) {
            if (i < k || i + k >= n) {
                d[c[i]][dsu.get(i)]++;
                cnt[dsu.get(i)]++;
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (i < k || i + k >= n) {
                if (dsu.get(i) == i) {
                    int m = 0;
                    for (int j = 0; j < d.length; j++) {
                        m = Math.max(m, d[j][i]);
                    }
                    ans += cnt[i] - m;
                }
            }
        }
        return ans;
    }
}
