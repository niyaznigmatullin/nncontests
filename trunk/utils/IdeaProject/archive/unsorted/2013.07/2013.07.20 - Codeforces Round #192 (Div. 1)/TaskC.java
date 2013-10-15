package coding;

import ru.ifmo.niyaz.DataStructures.DisjointSetUnion;
import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = i;
        }
        long[] edges = new long[m];
        for (int i = 0; i < m; i++) {
            int x = in.nextInt() - 1;
            int y = in.nextInt() -1 ;
            if (x > y) {
                int t = x;
                x = y;
                y = t;
            }
            edges[i] = (long) x * n + y;
        }
        ArrayUtils.sort(edges);
        int IT = 20000000 / n;
        for (int it = 0; it < IT; it++) {
            ArrayUtils.shuffle(p);
            int count = 0;
            for (int i = 0; i < n; i++) {
                int x = p[i];
                int y = p[(i + 1) % n];
                if (x > y) {
                    int t = x;
                    x = y;
                    y = t;
                }
                long ed = (long) x * n + y;
                if (Arrays.binarySearch(edges, ed) < 0) {
                    ++count;
                }
            }
            if (count >= m) {
                count = 0;
                for (int i = 0; count < m && i < n; i++) {
                    int x = p[i];
                    int y = p[(i + 1) % n];
                    if (x > y) {
                        int t = x;
                        x = y;
                        y = t;
                    }
                    long ed = (long) x * n + y;
                    if (Arrays.binarySearch(edges, ed) < 0) {
                        out.println((x + 1) + " " + (y + 1));
                        ++count;
                    }
                }
                return;
            }
        }
        out.println(-1);
    }
}
