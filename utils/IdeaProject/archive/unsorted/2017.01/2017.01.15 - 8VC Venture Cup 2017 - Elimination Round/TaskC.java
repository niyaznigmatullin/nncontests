package coding;

import ru.ifmo.niyaz.DataStructures.DisjointSetUnion;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        DisjointSetUnion dsu = new DisjointSetUnion(n);
        int[] a= in.readIntArray(n);
        for (int i = 0; i < n; i++) {
            int j = a[i] - 1;
            dsu.union(i, j);
        }
        int ans = 0;
        for (int i = 0; i < n; i++) if (dsu.get(i) == i) ++ans;
        out.println(ans);
    }
}
