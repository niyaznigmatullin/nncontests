package coding;

import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.*;

public class TaskB {
    static class Neighbors {
        int[] a;

        public Neighbors(int[] a) {
            this.a = a;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Neighbors neighbors = (Neighbors) o;
            return Arrays.equals(a, neighbors.a);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(a);
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        List<Integer>[] edges = new List[n];
        for (int i = 0; i < n; i++) edges[i] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            edges[a].add((b - a + n) % n);
            edges[b].add((a - b + n) % n);
        }
        Map<Neighbors, Integer> classes = new HashMap<>();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            int[] z = new int[edges[i].size()];
            for (int j = 0; j < z.length; j++) {
                z[j] = edges[i].get(j);
            }
            ArrayUtils.sort(z);
            Neighbors cur = new Neighbors(z);
            Integer got = classes.get(cur);
            if (got == null) {
                got = classes.size();
                classes.put(cur, got);
            }
            a[i] = got;
        }
        int[] p = new int[n];
        int k = -1;
        p[0] = -1;
        for (int i = 1; i < n; i++) {
            while (k > -1 && a[i] != a[k + 1]) k = p[k];
            if (a[i] == a[k + 1]) ++k;
            p[i] = k;
        }
        k = -1;
        for (int i = 1; i + 1 < n + n; i++) {
            int c = a[i % n];
            while (k > -1 && c != a[k + 1]) k = p[k];
            if (c == a[k + 1]) ++k;
            if (k == n - 1) {
                out.println("Yes");
                return;
            }
        }
        out.println("No");
    }
}
