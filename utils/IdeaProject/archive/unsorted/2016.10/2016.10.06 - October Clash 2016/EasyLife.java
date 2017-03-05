package coding;

import ru.ifmo.niyaz.DataStructures.DisjointSetUnion;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class EasyLife {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] from = new int[m];
        int[] to = new int[m];
        for (int i = 0; i < m; i++) {
            from[i] = in.nextInt() - 1;
            to[i] = in.nextInt() - 1;
        }
        DisjointSetUnion dsu = new DisjointSetUnion(n);
        for (int i = 0; i < m; i++) {
            dsu.union(from[i], to[i]);
        }
        int[] vertices = new int[n];
        int[] edges = new int[n];
        for (int i = 0; i < n; i++) {
            vertices[dsu.get(i)]++;
        }
        for (int i = 0; i < m; i++) {
            edges[dsu.get(from[i])]++;
        }
        boolean canOne = false;
        int maxLess = 0;
        for (int i = 0; i < n; i++) {
            if (i != dsu.get(i)) continue;
            if (vertices[i] < edges[i]) {
                out.println(">1");
                return;
            }
            if (vertices[i] == edges[i]) {
                canOne = true;
            } else {
                maxLess = Math.max(maxLess, vertices[i]);
            }
        }
        if (canOne) {
            out.println("1/1");
        } else {
            out.println((maxLess - 1) + "/" + maxLess);
        }
    }
}
