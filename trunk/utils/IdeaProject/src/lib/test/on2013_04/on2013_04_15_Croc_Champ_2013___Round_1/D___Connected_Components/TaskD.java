package lib.test.on2013_04.on2013_04_15_Croc_Champ_2013___Round_1.D___Connected_Components;



import ru.ifmo.niyaz.DataStructures.DisjointSetUnion;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.*;

public class TaskD {

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] from = new int[m];
        int[] to = new int[m];
        for (int i = 0; i < m; i++) {
            from[i] = in.nextInt() - 1;
            to[i] = in.nextInt() - 1;
        }
        List<Integer> a = new ArrayList<>();
        {
            DisjointSetUnion dsu = new DisjointSetUnion(n);
            for (int i = 0; i < m; i++) {
                if (dsu.union(from[i], to[i])) {
                    a.add(i);
                }
            }
        }
        List<Integer> b = new ArrayList<>();
        {
            DisjointSetUnion dsu = new DisjointSetUnion(n);
            for (int i = m - 1; i >= 0; i--) {
                if (dsu.union(from[i], to[i])) {
                    b.add(i);
                }
            }
        }
        int q = in.nextInt();
        DisjointSetUnion dsu = new DisjointSetUnion(n);
        for (int i = 0; i < q; i++) {
            dsu.clear();
            int left = in.nextInt() - 1;
            int right = in.nextInt() - 1;
            int ans = n;
            for (int e : a) {
                if (e >= left) {
                    break;
                }
                if (dsu.union(from[e], to[e])) {
                    --ans;
                }
            }
            for (int e : b) {
                if (e <= right) {
                    break;
                }
                if (dsu.union(from[e], to[e])) {
                    --ans;
                }
            }
            out.println(ans);
        }
    }
}
