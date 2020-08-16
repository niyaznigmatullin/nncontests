package coding;

import ru.ifmo.niyaz.DataStructures.DisjointSetUnion;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BitstringsAsAService {

    static class Elements {
        int size;
        List<Integer> all;

        Elements() {
            all = new ArrayList<>();
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        DisjointSetUnion dsu = new DisjointSetUnion(n);
        for (int i = 0; i < m; i++) {
            int left = in.nextInt() - 1;
            int right = in.nextInt() - 1;
            for (int j = left, k = right; j < k; j++, k--) {
                dsu.union(j, k);
            }
        }
        int[] id = new int[n];
        Arrays.fill(id, -1);
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (dsu.get(i) != i) continue;
            id[i] = cnt++;
        }
        Elements[] a = new Elements[cnt];
        for (int i = 0; i < cnt; i++) a[i] = new Elements();
        for (int i = 0; i < n; i++) {
            a[id[dsu.get(i)]].all.add(i);
        }
        for (int i = 0; i < cnt; i++) {
            a[i].size = a[i].all.size();
        }
        int[][] dp = new int[cnt + 1][n + 1];
        for (int[] e : dp) Arrays.fill(e, -1);
        dp[0][0] = 0;
        for (int i = 0; i < cnt; i++) {
            for (int w = 0; w <= n; w++) {
                if (dp[i][w] == -1) continue;
                dp[i + 1][w + a[i].size] = w;
                dp[i + 1][w] = w;
            }
        }
        int z = n / 2;
        while (dp[cnt][z] == -1) --z;
        char[] ans = new char[n];
        Arrays.fill(ans, '1');
        for (int i = cnt; i > 0; i--) {
            int nz = dp[i][z];
            if (nz != z) {
                for (int e : a[i - 1].all) {
                    ans[e] = '0';
                }
            }
            z = nz;
        }
        out.println("Case #" + testNumber + ": " + new String(ans));
    }
}
