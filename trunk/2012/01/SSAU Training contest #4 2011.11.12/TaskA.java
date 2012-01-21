package mypackage;

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.LineInputStream;
import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.ArrayList;
import java.util.List;

public class TaskA {
    static List<Integer>[] edges;
    static int[] x;
    static int[] y;
    static int[] freeY;

    static void dfs(int v, int parent, int depth) {
        x[v] = depth;
        y[v] = freeY[depth]++;
        for (int i : edges[v]) {
            if (i == parent) {
                continue;
            }
            dfs(i, v, depth + 1);
        }
    }

	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        edges = new List[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i + 1 < n; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            edges[from].add(to);
            edges[to].add(from);
        }
        x = new int[n];
        y = new int[n];
        freeY = new int[n + 1];
        dfs(0, -1, 0);
        for (int i = 0; i < n; i++) {
            out.println(x[i] + " " + y[i]);
        }
	}
}
