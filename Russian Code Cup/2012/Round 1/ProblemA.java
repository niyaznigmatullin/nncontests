package mypackage;

import DataStructures.DisjointSetUnion;
import niyazio.FastScanner;
import niyazio.FastPrinter;

public class ProblemA {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        DisjointSetUnion dsu = new DisjointSetUnion(n);
        for (int i = 0; i < n; i++) {
            dsu.union(i, in.nextInt() - 1);
        }
        dsu.union(0, 1);
        for (int i = 0; i < m; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            if (dsu.get(a) == dsu.get(b)) {
                out.println("Yes");
            } else {
                out.println("No");
            }
        }
	}
}
