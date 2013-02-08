package ru.ifmo.niyaz.graphalgorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * Created by IntelliJ IDEA.
 * User: niyaznigmatul
 * Date: 18.01.12
 * Time: 19:15
 * To change this template use File | Settings | File Templates.
 */
public class DijkstraGraph {

    public long[] dijkstraMaxMin(int source) {
        long[] d = new long[n];
        Arrays.fill(d, Long.MIN_VALUE);
        Element[] q = new Element[n];
        for (int i = 0; i < q.length; i++) {
            q[i] = new Element(i, Long.MIN_VALUE);
        }
        q[source].d = d[source] = Long.MAX_VALUE;
        TreeSet<Element> queue = new TreeSet<Element>(new Comparator<Element>() {
            @Override
            public int compare(Element o1, Element o2) {
                if (o1.d != o2.d) {
                    return o1.d < o2.d ? 1 : -1;
                }
                return o1.v - o2.v;
            }
        });
        queue.add(q[source]);
        while (!queue.isEmpty()) {
            Element el = queue.pollFirst();
            for (int i = 0; i < edges[el.v].size(); i++) {
                Edge e = edges[el.v].get(i);
                long w = Math.min(d[e.from], e.w);
                if (d[e.to] < w) {
                    queue.remove(q[e.to]);
                    d[e.to] = q[e.to].d = w;
                    queue.add(q[e.to]);
                }
            }
        }
        return d;
    }
    public static class Edge {
        public int from;
        public int to;
        public int w;

        public Edge(int from, int to, int w) {
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

    public Edge addEdge(int from, int to, int w) {
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
        long[] d = new long[n];
        Arrays.fill(d, Long.MAX_VALUE);
        Element[] q = new Element[n];
        for (int i = 0; i < q.length; i++) {
            q[i] = new Element(i, Long.MAX_VALUE);
        }
        q[source].d = d[source] = 0;
        TreeSet<Element> queue = new TreeSet<Element>();
        queue.add(q[source]);
        while (!queue.isEmpty()) {
            Element el = queue.pollFirst();
            for (int i = 0; i < edges[el.v].size(); i++) {
                Edge e = edges[el.v].get(i);
                if (d[e.to] > d[e.from] + e.w) {
                    queue.remove(q[e.to]);
                    d[e.to] = q[e.to].d = d[e.from] + e.w;
                    queue.add(q[e.to]);
                }
            }
        }
        return d;
    }
}
