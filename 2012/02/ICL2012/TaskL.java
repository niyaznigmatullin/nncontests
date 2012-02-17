package mypackage;

import DataStructures.DisjointSetUnion;
import niyazio.FastScanner;
import niyazio.FastPrinter;

public class TaskL {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] z = new int[n];
        DisjointSetUnion dsu = new DisjointSetUnion(n);
        for (int i = 1; i < n; i++) {
            int x = in.nextInt();
            z[i] = x;
            if (i + x > n) {
                out.println(-1);
                return;
            }
            for (int j = 0; j < x; j++) {
                dsu.union(j, i + j);
            }
        }
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            answer[i] = dsu.get(i) + 1;
        }
        for (int i = 1; i < n; i++) {
            int y = 0;
            while (i + y < n && answer[i + y] == answer[y]) {
                ++y;
            }
            if (y != z[i]) {
                out.println(-1);
                return;
            }
        }
        for (int i = 0; i < n; i++) {
            if (i > 0) {
                out.print(' ');
            }
            out.print(answer[i]);
        }
        out.println();
	}
}
