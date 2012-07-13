package mypackage;

import DataStructures.DisjointSetUnion;
import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.HashMap;
import java.util.Map;

public class ProblemA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        DisjointSetUnion dsu = new DisjointSetUnion(n + 1);
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (int i = 0; i < n; i++) {
            int p1 = get(map, in.next());
            int p2 = get(map, in.next());
            dsu.union(p1, p2);
            out.println(dsu.count[dsu.get(p1)]);
        }
    }

    static int get(Map<String, Integer> map, String s) {
        if (!map.containsKey(s)) {
            map.put(s, map.size());
        }
        return map.get(s);
    }

    static class DisjointSetUnion {

        int[] p;
        int[] count;

        public DisjointSetUnion(int n) {
            p = new int[n];
            count = new int[n];
            clear();
        }

        public void clear() {
            for (int i = 0; i < p.length; i++) {
                p[i] = i;
                count[i] = 1;
            }
        }

        public int get(int x) {
            return x != p[x] ? p[x] = get(p[x]) : x;
        }

        public boolean union(int a, int b) {
            a = get(a);
            b = get(b);
            p[a] = b;
            if (a != b) {
                count[b] += count[a];
            }
            return a == b;
        }
    }

}
