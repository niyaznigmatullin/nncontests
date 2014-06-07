package coding;

import java.util.*;

public class LongLongTripDiv1 {

    static class Edge {
        int from;
        int to;
        int w;

        Edge(int from, int to, int w) {
            this.from = from;
            this.to = to;
            this.w = w;
        }
    }

    public String isAble(int n, int[] from, int[] to, int[] w, long T) {
        List<Edge>[] edges = new List[n];
        for (int i = 0; i < n; i++) edges[i] = new ArrayList<>();
        for (int i = 0; i < from.length; i++) {
            edges[from[i]].add(new Edge(from[i], to[i], w[i]));
            edges[to[i]].add(new Edge(to[i], from[i], w[i]));
        }
        if (edges[0].isEmpty()) return "Impossible";
        int mod = 2 * edges[0].get(0).w;
        final long[] d = new long[n * mod];
        Arrays.fill(d, Long.MAX_VALUE);
        d[0] = 0;
        NavigableSet<Integer> q = new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (d[o1] != d[o2]) return Long.compare(d[o1], d[o2]);
                return Integer.compare(o1, o2);
            }
        });
        q.add(0);
        while (!q.isEmpty()) {
            int v = q.pollFirst();
            int city = v / mod;
            int r = v % mod;
            for (int i = 0; i < edges[city].size(); i++) {
                Edge e = edges[city].get(i);
                int nr = (r + e.w) % mod;
                int nv = e.to * mod + nr;
                if (d[nv] > d[v] + e.w) {
                    q.remove(nv);
                    d[nv] = d[v] + e.w;
                    q.add(nv);
                }
            }
        }
        if (d[(n - 1) * mod + (int) (T % mod)] <= T) {
            return "Possible";
        }
        return "Impossible";
    }
}
