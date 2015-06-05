package coding;

import ru.ifmo.niyaz.graphalgorithms.KuhnMatchingGraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FoxMeeting {
    static class Edge {
        int from;
        int to;
        int w;

        public Edge(int from, int to, int w) {
            this.from = from;
            this.to = to;
            this.w = w;
        }
    }

    static List<Edge>[] edges;
    static int[] foxes;
    static int[][] dist;

    static boolean can(int root, int d) {
        int n = edges.length;
        int head = 0;
        int tail = 0;
        int[] q = new int[n];
        q[tail++] = root;
        boolean[] was = new boolean[n];
        Edge[] lastEdge = new Edge[n];
        was[root] = true;
        while (head < tail) {
            int v = q[head++];
            for (int i = 0; i < edges[v].size(); i++) {
                Edge e = edges[v].get(i);
                if (!was[e.to]) {
                    was[e.to] = true;
                    q[tail++] = e.to;
                    lastEdge[e.to] = e;
                }
            }
        }
        boolean[] mark = new boolean[n];
        for (int i : foxes) {
            int canGo = d;
            int v = i;
            while (lastEdge[v] != null) {
                if (canGo >= lastEdge[v].w) {
                    canGo -= lastEdge[v].w;
                    v = lastEdge[v].from;
                } else break;
            }
            mark[v] = true;
        }
        int marked = 0;
        for (int i = n - 1; i >= 0; i--) {
            int v = q[i];
            if (mark[v]) {
                ++marked;
                if (lastEdge[v] != null) {
                    mark[lastEdge[v].from] = true;
                }
            }
        }
        if (marked > foxes.length) return false;
        KuhnMatchingGraph g = new KuhnMatchingGraph(n, n);
        for (int i = 0; i < n; i++) {
            if (!mark[i]) continue;
            for (int j : foxes) {
                if (dist[i][j] <= d) {
                    g.addEdge(i, j);
                }
            }
        }
        return g.getMaximalMatching() == marked;
    }

    static boolean can(int d) {
        int n = edges.length;
        for (int i = 0; i < n; i++) {
            if (can(i, d)) {
                return true;
            }
        }
        return false;
    }

    public int maxDistance(int[] A, int[] B, int[] L, int[] foxes_) {
        int n = L.length + 1;
        edges = new List[n];
        for (int i = 0; i < foxes_.length; i++) foxes_[i]--;
        foxes = foxes_;
        for (int i = 0; i < n; i++) edges[i] = new ArrayList<>();
        dist = new int[n][n];
        for (int[] e : dist) Arrays.fill(e, Integer.MAX_VALUE);
        for (int i = 0; i < n; i++) dist[i][i] = 0;
        for (int i = 0; i < n - 1; i++) {
            --A[i];
            --B[i];
            edges[A[i]].add(new Edge(A[i], B[i], L[i]));
            edges[B[i]].add(new Edge(B[i], A[i], L[i]));
            dist[A[i]][B[i]] = dist[B[i]][A[i]] = L[i];
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }
        int l = -1;
        int r = 1 << 27;
        while (l < r - 1) {
            int mid = (l + r) >>> 1;
            if (can(mid)) r = mid;
            else l = mid;
        }
        return r;
    }
}
