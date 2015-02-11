package coding;

import ru.ifmo.niyaz.graphalgorithms.KuhnMatchingGraph;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.MathUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        boolean[] isPrime = MathUtils.genPrimesBoolean(123456);
        int[][] has = new int[n][n];
        for (int two = 0; two < 2; two++) {
            KuhnMatchingGraph g = new KuhnMatchingGraph(n, n);
            for (int i = 0; i < n; i++) {
                if ((a[i] & 1) == 1) continue;
                for (int j = 0; j < n; j++) {
                    if ((a[j] & 1) == 0) continue;
                    if (has[i][j] > 0) continue;
                    if (isPrime[a[i] + a[j]]) {
                        g.addEdge(i, j);
                    }
                }
            }
            int f = g.getMaximalMatching();
            if (2 * f != n) {
                out.println("Impossible");
                return;
            }
            int[] p = g.getPaired2();
            for (int i = 0; i < n; i++) {
                if ((a[i] & 1) == 1) {
                    has[i][p[i]] = has[p[i]][i] = 1;
                }
            }
        }
        List<List<Integer>> answer = new ArrayList<>();
        boolean[] was = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (was[i]) continue;
            List<Integer> cur = new ArrayList<>();
            int prev = -1;
            int last = i;
            while (true) {
                was[last] = true;
                cur.add(last);
                int next = -1;
                for (int j = 0; j < n; j++) {
                    if (has[last][j] == 0) continue;
                    if (j == prev) continue;
                    next = j;
                    break;
                }
                prev = last;
                last = next;
                if (last == i) break;
            }
            answer.add(cur);
        }
        out.println(answer.size());
        for (List<Integer> e : answer) {
            out.print(e.size());
            for (int i : e) {
                out.print(" " + (i + 1));
            }
            out.println();
        }
    }
}
