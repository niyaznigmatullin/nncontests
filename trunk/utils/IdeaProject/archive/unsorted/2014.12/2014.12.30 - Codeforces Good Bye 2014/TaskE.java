package coding;

import ru.ifmo.niyaz.DataStructures.Fenwick;
import ru.ifmo.niyaz.DataStructures.MaxSegmentTree;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskE {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] pos = new int[n];
        int[] len = new int[n];
        for (int i = 0; i < n; i++) {
            pos[i] = in.nextInt();
            len[i] = in.nextInt();
        }
        int[] which = new int[n];
        MaxSegmentTree wTree = new MaxSegmentTree(n);
        for (int i = n - 1; i >= 0; i--) {
            int l = -1;
            int r = n;
            while (l < r - 1) {
                int mid = (l + r) >> 1;
                if (pos[mid] > pos[i] + len[i]) {
                    r = mid;
                } else {
                    l = mid;
                }
            }
            which[i] = wTree.getMax(i + 1, r);
            if (which[i] < i) which[i] = i;
            wTree.set(i, which[i]);
        }
        MaxSegmentTree hTree = new MaxSegmentTree(n);
        for (int i = 0; i < n; i++) {
            hTree.set(i, pos[i] + len[i]);
        }
        int[] need = new int[n];
        for (int i = 0; i < n; i++) {
            int to = which[i];
            if (to + 1 >= n) {
                continue;
            }
            need[i] = Math.max(0, pos[to + 1] - hTree.getMax(i, to + 1));
        }
        final int K = 20;
        int[][] pp = new int[K][n];
        int[][] w = new int[K][n];
        for (int i = 0; i < n; i++) {
            pp[0][i] = which[i] + 1;
            w[0][i] = need[i];
        }
        for (int i = 1; i < K; i++) {
            for (int v = 0; v < n; v++) {
                pp[i][v] = pp[i - 1][v] < n ? pp[i - 1][pp[i - 1][v]] : pp[i - 1][v];
                w[i][v] = w[i - 1][v] + (pp[i - 1][v] < n ? w[i - 1][pp[i - 1][v]] : 0);
            }
        }
        int q = in.nextInt();
        for (int i = 0; i < q; i++) {
            int left = in.nextInt() - 1;
            int right = in.nextInt() - 1;
            if (which[left] >= right) {
                out.println(0);
                continue;
            }
            int ans = 0;
            for (int j = K - 1; j >= 0; j--) {
                if (pp[j][left] >= n || which[pp[j][left]] >= right) {
                    continue;
                }
                ans += w[j][left];
                left = pp[j][left];
            }
            ans += need[left];
            out.println(ans);
        }
    }
}
