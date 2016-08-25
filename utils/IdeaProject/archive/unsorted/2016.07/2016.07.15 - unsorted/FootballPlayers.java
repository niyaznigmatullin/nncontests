package coding;

import ru.ifmo.niyaz.DataStructures.DisjointSetUnion;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class FootballPlayers {

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] from = new int[m];
        int[] to = new int[m];
        int[] w = new int[m];
        int maxW = 0;
        for (int i = 0; i < m; i++) {
            from[i] = in.nextInt() - 1;
            to[i] = in.nextInt() - 1;
            w[i] = in.nextInt();
            maxW = Math.max(maxW, w[i]);
        }
        int l = -1;
        int r = maxW + 1;
        while (l < r - 1) {
            int mid = l + r >>> 1;
            DisjointSetUnion dsu = new DisjointSetUnion(n);
            for (int i = 0; i < m; i++) {
                if (w[i] > mid) {
                    dsu.union(from[i], to[i]);
                }
            }
            int[] size = new int[n];
            for (int i = 0; i < n; i++) {
                size[dsu.get(i)]++;
            }
            boolean[] can = new boolean[n / 2 + 1];
            can[0] = true;
            for (int i = 0; i < n; i++) {
                if (size[i] == 0) continue;
                for (int cw = can.length - 1; cw >= size[i]; cw--) {
                    if (can[cw - size[i]]) {
                        can[cw] = true;
                    }
                }
            }
            if (can[n / 2]) {
                r = mid;
            } else {
                l = mid;
            }
        }
        out.println(r);
    }
}
