package coding;

import ru.ifmo.niyaz.DataStructures.DisjointSetUnion;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskE {
    static boolean cactus(int[][] e) {
        int n = e.length;
        DisjointSetUnion dsu = new DisjointSetUnion(n);
        int[] deg = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                deg[i] += e[i][j];
                if (e[i][j] > 0) dsu.union(i, j);
            }
            if (deg[i] > 2) return false;
        }
        boolean[] has = new boolean[n];
        int[] size = new int[n];
        for (int i = 0; i < n; i++) {
            if (deg[i] < 2) has[dsu.get(i)] = true;
            size[dsu.get(i)]++;
        }
        for (int i = 0; i < n; i++) {
            if (size[i] > 0 && size[i] != n && !has[i]) return false;
        }
        return true;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] edges = new int[n][n];
        for (int i = 0; i < m; i++) {
            int v = in.nextInt() - 1;
            int u = in.nextInt() - 1;
            edges[v][u]++;
            edges[u][v]++;
        }
        if (!cactus(edges)) {
            out.println("NO");
            return;
        }
        int[] ax = new int[n * n];
        int[] ay = new int[n * n];
        int ac = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                while (true) {
                    edges[i][j]++;
                    edges[j][i]++;
                    if (!cactus(edges)) {
                        edges[i][j]--;
                        edges[j][i]--;
                        break;
                    } else {
                        ax[ac] = i;
                        ay[ac] = j;
                        ++ac;
                    }
                }
            }
        }
        out.println("YES");
        out.println(ac);
        for (int i = 0; i < ac; i++) {
            out.println((ax[i] + 1) + " " + (ay[i] + 1));
        }
    }
}
