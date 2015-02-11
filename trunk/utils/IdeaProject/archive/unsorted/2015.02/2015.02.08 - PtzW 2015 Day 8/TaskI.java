package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskI {
    static int[][] edges1 = {
            {0, 2},
            {0, 4},
            {0, 6},
            {1, 2},
            {1, 3},
            {1, 5},
            {2, 3},
            {2, 6},
            {3, 4},
            {3, 5},
            {4, 5},
    };
    static int[][] edges2 = {
            {0, 3},
            {0, 4},
            {0, 6},
            {1, 2},
            {1, 3},
            {1, 5},
            {2, 3},
            {2, 4},
            {2, 6},
            {3, 5},
            {4, 5},
    };

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        if (n <= 6) {
            out.println("NO");
        } else {
            out.println("YES");
            int numOfEdges = edges1.length + n - 7;
            out.println(numOfEdges);
            for (int[] e : edges1) {
                out.println((e[0] + 1) + " " + (e[1] + 1));
            }
            for (int j = 8; j <= n; j++) {
                out.println((j - 1) + " " + (j));
            }
            out.println(numOfEdges);
            for (int[] e : edges2) {
                out.println((e[0] + 1) + " " + (e[1] + 1));
            }
            for (int j = 8; j <= n; j++) {
                out.println((j - 1) + " " + (j));
            }
        }
    }
}
