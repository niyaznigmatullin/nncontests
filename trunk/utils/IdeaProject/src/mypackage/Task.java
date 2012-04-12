package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.*;

public class Task {

    static final Random rand = new Random(231212L);
    static Map<EquivalencyClass, Integer> map;

    static boolean isomorphism(List<Integer>[] edges1, List<Integer>[] edges2) {
        int n = edges1.length;
        if (n != edges2.length) {
            return false;
        }
        for (int j = 0; j < n; j++) {
            if (dfs(0, -1, edges1) == dfs(j, -1, edges2)) {
                return true;
            }
        }
        return false;
    }

    static boolean smartIsomorphism(List<Integer>[] edges1, List<Integer>[] edges2) {
        return false; // TODO
    }

    static int dfs(int v, int parent, List<Integer>[] edges) {
        int[] children = new int[edges[v].size() - (parent == -1 ? 0 : 1)];
        int count = 0;
        for (int i : edges[v]) {
            if (i == parent) {
                continue;
            }
            children[count++] = dfs(i, v, edges);
        }
        EquivalencyClass c = new EquivalencyClass(children);
        if (!map.containsKey(c)) {
            map.put(c, map.size());
        }
        return map.get(c);
    }

    static List<Integer>[] permutate(List<Integer>[] edges) {
        int[] p = getRandomPermutation(edges.length);
        List<Integer>[] ret = new List[edges.length];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = edges[p[i]];
            for (int j = 0; j < ret[i].size(); j++) {
                ret[i].set(j, p[ret[i].get(j)]);
            }
        }
        return ret;
    }

    static int[] getRandomPermutation(int n) {
        int[] p = new int[n];
        for (int i = 1; i < n; i++) {
            int j = rand.nextInt(i + 1);
            p[i] = p[j];
            p[j] = i;
        }
        return p;
    }

    static List<Integer>[] getEdges(int[] p, int n) {
        List<Integer>[] edges = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<Integer>();
        }
        for (int i = 1; i < n; i++) {
            edges[i].add(p[i]);
            edges[p[i]].add(i);
        }
        return edges;
    }


    public void solve(int testNumber, FastScanner in, FastPrinter out) {

    }

    static class EquivalencyClass {
        int[] a;

        EquivalencyClass(int[] a) {
            this.a = a.clone();
            Arrays.sort(a);
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof EquivalencyClass)) return false;

            EquivalencyClass that = (EquivalencyClass) o;

            if (!Arrays.equals(a, that.a)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return a != null ? Arrays.hashCode(a) : 0;
        }
    }
}
