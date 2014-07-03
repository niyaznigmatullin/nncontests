package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.*;

public class Tree {

    static class EqClass {
        int[] a;

        EqClass(int[] a) {
            this.a = a;
            Arrays.sort(a);
        }

        @Override
        public boolean equals(Object o) {
            return Arrays.equals(a, ((EqClass) o).a);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(a);
        }
    }

    static Map<EqClass, Integer> map;
    static List<Integer>[] edges;
    static int[] answer;

    static void dfs(int v) {
        for (int i = 0; i < edges[v].size(); i++) {
            dfs(edges[v].get(i));
        }
        int[] children = new int[edges[v].size()];
        for (int i = 0; i < edges[v].size(); i++) {
            children[i] = answer[edges[v].get(i)];
        }
        EqClass ec = new EqClass(children);
        Integer f = map.get(ec);
        if (f == null) f = map.size();
        map.put(ec, f);
        answer[v] = f;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        edges = new List[n];
        for (int i = 0; i < n; i++) edges[i] = new ArrayList<>(1);
        for (int i = 1; i < n; i++) {
            edges[in.nextInt() - 1].add(i);
        }
        answer = new int[n];
        map = new HashMap<>(2 * n);
        dfs(0);
        for (int i = 0; i < n; i++) {
            if (i > 0) out.print(' ');
            out.print(answer[i] + 1);
        }
        out.println();
    }
}
