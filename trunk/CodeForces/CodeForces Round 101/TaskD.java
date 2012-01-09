package mypackage;

import com.sun.org.apache.xalan.internal.xsltc.cmdline.Transform;
import niyazio.FastScanner;

import java.io.PrintWriter;
import java.util.*;

public class TaskD {

    static class Trampoline {
        int x;
        int d;
        int t;
        int p;

        Trampoline(int x, int d, int t, int p) {
            this.x = x;
            this.d = d;
            this.t = t;
            this.p = p;
        }
    }

    public void solve(int testNumber, FastScanner in, PrintWriter out) {
        int n = in.nextInt();
        Set<Integer> set = new HashSet<Integer>();
        int length = in.nextInt();
        Trampoline[] tramps = new Trampoline[n];
        set.add(0);
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            int d = in.nextInt();
            int t = in.nextInt();
            int p = in.nextInt();
            tramps[i] = new Trampoline(x, d, t, p);
            set.add(x);
            set.add(x + d);
            if (x - p >= 0) {
                set.add(x - p);
            }
        }
        set.add(length);
        int[] xs = new int[set.size()];
        int count = 0;
        for (int j : set) {
            xs[count++] = j;
        }
        Arrays.sort(xs);
        PriorityQueue<Element> pq = new PriorityQueue<Element>();
        List<Edge>[] edges = new ArrayList[count];
        for (int i = 0; i < count; i++) {
            edges[i] = new ArrayList<Edge>();
            if (i > 0) {
                edges[i].add(new Edge(i, i - 1, xs[i] - xs[i - 1], -1));
            }
            if (i + 1 < count) {
                edges[i].add(new Edge(i, i + 1, xs[i + 1] - xs[i], -1));
            }
        }
        for (int i = 0; i < n; i++) {
            int from = tramps[i].x - tramps[i].p;
            if (from < 0) {
                continue;
            }
            int z = Arrays.binarySearch(xs, from);
            if (z < 0) {
                z = (~z) - 1;
            }
            int g = Arrays.binarySearch(xs, tramps[i].d + tramps[i].x);
            edges[z].add(new Edge(z, g, tramps[i].x - xs[z] + tramps[i].t, i));
        }
        pq.add(new Element(0, 0));
        boolean[] was = new boolean[count];
        int[] dist = new int[count];
        Edge[] lastEdge = new Edge[count];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        while (!pq.isEmpty()) {
            Element current = pq.poll();
            if (was[current.v]) {
                continue;
            }
            was[current.v] = true;
            for (Edge e : edges[current.v]) {
                if (dist[e.to] > dist[e.from] + e.w) {
                    dist[e.to] = dist[e.from] + e.w;
                    pq.add(new Element(e.to, dist[e.to]));
                    lastEdge[e.to] = e;
                }
            }
        }
        out.println(dist[count - 1]);
        List<Integer> answer = new ArrayList<Integer>();
        for (int i = count - 1; i > 0; i = lastEdge[i].from) {
            Edge e = lastEdge[i];
            if (e.tramp >= 0) {
                answer.add(e.tramp);
            }
        }
        out.println(answer.size());
        Collections.reverse(answer);
        for (int i : answer) {
            out.print(i + 1 + " ");
        }
    }

    static class Element implements Comparable<Element> {
        int v;
        int d;

        Element(int v, int d) {
            this.v = v;
            this.d = d;
        }

        public int compareTo(Element o) {
            return d < o.d ? -1 : d > o.d ? 1 : 0;
        }
    }

    static class Edge {
        int from;
        int to;
        int w;
        int tramp;

        Edge(int from, int to, int w, int tramp) {
            this.from = from;
            this.to = to;
            this.w = w;
            this.tramp = tramp;
        }
    }
}
