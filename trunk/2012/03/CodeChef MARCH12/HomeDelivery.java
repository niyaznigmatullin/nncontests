package mypackage;

import DataStructures.DisjointSetUnion;
import niyazio.FastScanner;
import niyazio.FastPrinter;

public class HomeDelivery {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        DisjointSetUnion dsu = new DisjointSetUnion(n);
        for (int i = 0; i < m; i++) {
            dsu.union(in.nextInt(), in.nextInt());
        }
        int k = in.nextInt();
        for (int i = 0; i < k; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            out.println(dsu.get(a) == dsu.get(b) ? "YO" : "NO");
        }
    }
}
