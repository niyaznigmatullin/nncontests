package coding;

import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.graphalgorithms.DijkstraGraph;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.*;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] all = new int[n + 2];
        int[] a = in.readIntArray(n);
        for (int i = 0; i < n; i++) all[i] = a[i];
        int u1 = in.nextInt();
        int u2 = in.nextInt();
        int t = in.nextInt();
        int b1 = in.nextInt();
        int l1 = in.nextInt();
        int b2 = in.nextInt();
        int l2 = in.nextInt();
        all[n] = l1;
        all[n + 1] = l2;
        all = ArrayUtils.sortAndUnique(all);
        l1 = Arrays.binarySearch(all, l1);
        l2 = Arrays.binarySearch(all, l2);
//        DijkstraGraph g = new DijkstraGraph(2 * all.length);
//        for (int i = 1; i < all.length; i++) {
//            g.addEdge(i - 1, i, (long) u1 * (all[i] - all[i - 1]));
//            g.addEdge(i, i - 1, (long) u1 * (all[i] - all[i - 1]));
//            g.addEdge(i - 1 + all.length, i + all.length, (long) u2 * (all[i] - all[i - 1]));
//            g.addEdge(i + all.length, i - 1 + all.length, (long) u2 * (all[i] - all[i - 1]));
//        }
//        for (int i = 0; i < n; i++) {
//            int v = Arrays.binarySearch(all, a[i]);
//            g.addEdge(v, v + all.length, t);
//            g.addEdge(v + all.length, v, t);
//        }
        boolean[] canGo = new boolean[all.length];
        for (int i = 0; i < n; i++) {
            canGo[Arrays.binarySearch(all, a[i])] = true;
        }
        long[] dist = new long[all.length * 2];
        Arrays.fill(dist, Long.MAX_VALUE);
        int[] q = new int[all.length * 2 + 2];
        boolean[] inQueue = new boolean[all.length * 2];
        int start = b1 == 1 ? l1 : l1 + all.length;
        int head = 0;
        int tail = 1;
        q[head] = start;
        inQueue[start] = true;
        dist[start] = 0;
        while (head != tail) {
            int v = q[head++];
            inQueue[v] = false;
            if (head == q.length) head = 0;
            int c = v >= all.length ? v - all.length : v;
            int u0 = v >= all.length ? u2 : u1;
            if (c - 1 >= 0 && dist[v - 1] > dist[v] + (long) u0 * (all[c] - all[c - 1])) {
                dist[v - 1] = dist[v] + (long) u0 * (all[c] - all[c - 1]);
                if (!inQueue[v - 1]) {
                    q[tail++] = v - 1;
                    if (tail == q.length) tail = 0;
                }
            }
            if (c + 1 < all.length && dist[v + 1] > dist[v] + (long) u0 * (all[c + 1] - all[c])) {
                dist[v + 1] = dist[v] + (long) u0 * (all[c + 1] - all[c]);
                if (!inQueue[v + 1]) {
                    q[tail++] = v + 1;
                    if (tail == q.length) tail = 0;
                }
            }
            if (canGo[c]) {
                int u = v >= all.length ? v - all.length : v + all.length;
                if (dist[u] > dist[v] + t) {
                    dist[u] = dist[v] + t;
                    if (!inQueue[u]) {
                        q[tail++] = u;
                        if (tail == q.length) tail = 0;
                    }
                }
            }
        }
        out.println(dist[b2 == 1 ? l2 : l2 + all.length]);
    }

    static class DijkstraGraph {


        public static class Edge {
            public int from;
            public int to;
            public long w;

            public Edge(int from, int to, long w) {
                this.from = from;
                this.to = to;
                this.w = w;
            }

        }

        static class Element implements Comparable<Element> {
            int v;
            long d;

            public Element(int v, long d) {
                this.v = v;
                this.d = d;
            }

            public int compareTo(Element o) {
                if (d != o.d) {
                    return d < o.d ? -1 : 1;
                }
                return v - o.v;
            }
        }

        ArrayList<Edge>[] edges;
        int n;


        public DijkstraGraph(int n) {
            this.n = n;
            @SuppressWarnings("unchecked")
            ArrayList<Edge>[] edges = new ArrayList[n];
            this.edges = edges;
            for (int i = 0; i < n; i++) {
                edges[i] = new ArrayList<Edge>();
            }
        }

        public Edge addEdge(int from, int to, long w) {
            Edge ret = new Edge(from, to, w);
            edges[from].add(ret);
            return ret;
        }

        long[] dijkstraSlow(int source) {
            long[] d = new long[n];
            Arrays.fill(d, Long.MAX_VALUE);
            d[source] = 0;
            boolean[] was = new boolean[n];
            while (true) {
                int min = -1;
                for (int i = 0; i < n; i++) {
                    if (was[i] || d[i] == Long.MAX_VALUE) {
                        continue;
                    }
                    if (min == -1 || d[min] > d[i]) {
                        min = i;
                    }
                }
                if (min == -1) {
                    break;
                }
                was[min] = true;
                for (int i = 0; i < edges[min].size(); i++) {
                    Edge e = edges[min].get(i);
                    if (d[e.to] > d[e.from] + e.w) {
                        d[e.to] = d[e.from] + e.w;
                    }
                }
            }
            return d;
        }

        public long[] dijkstra(int source) {
            final long[] d = new long[n];
            Arrays.fill(d, Long.MAX_VALUE);
            d[source] = 0;
//            TreeSet<Integer> queue = new TreeSet<>(new Comparator<Integer>() {
//                @Override
//                public int compare(Integer o1, Integer o2) {
//                    if (d[o1] != d[o2]) return Long.compare(d[o1], d[o2]);
//                    return Integer.compare(o1, o2);
//                }
//            });
            Queue<Integer> queue = new ArrayDeque<>();
            boolean[] inQueue = new boolean[n];
            queue.add(source);
            inQueue[source] = true;
            while (!queue.isEmpty()) {
                int v = queue.poll();
                inQueue[v] = false;
                for (int i = 0; i < edges[v].size(); i++) {
                    Edge e = edges[v].get(i);
                    if (d[e.to] > d[e.from] + e.w) {
                        d[e.to] = d[e.from] + e.w;
                        if (!inQueue[e.to])
                            queue.add(e.to);
                    }
                }
            }
            return d;
        }
    }
}


