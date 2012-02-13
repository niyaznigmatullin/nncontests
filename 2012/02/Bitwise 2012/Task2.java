package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Task2 {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        if (testNumber < 0) {
            throw new AssertionError();
        }
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        Graph g = new Graph(1 + n + m + 1);
        PriorityQueue<House> q = new PriorityQueue<House>();
        Graph.Edge[] edges = new Graph.Edge[m];
        int start = 0;
        int finish = n + m + 1;
        for (int i = 0; i < m; i++) {
            edges[i] = g.addEdge(start, i + 1, 0, 0);
            q.add(new House(i, 0));
        }
        for (int i = 0; i < k; i++) {
            int child = in.nextInt() - 1;
            int house = in.nextInt() - 1;
            g.addEdge(house + 1, child + m + 1, 0, 1);
        }
        for (int i = 0; i < n; i++) {
            g.addEdge(i + m + 1, finish, 0, 1);
        }
        long answer = 0;
        while (!q.isEmpty()) {
            House h = q.poll();
            edges[h.id].cap++;
            if (g.getMaxFlow(start, finish) == 0) {
                answer += (long) h.count * (h.count + 1) / 2;
                edges[h.id].cap--;
                continue;
            }
            h.count++;
            q.add(h);
        }
        out.println(answer);
    }

    static class House implements Comparable<House> {
        int id;
        int count;

        House(int id, int count) {
            this.id = id;
            this.count = count;
        }

        public int compareTo(House o) {
            return count - o.count;
        }
    }


    static class Graph {

        static class Edge {
            int from;
            int to;
            int flow;
            int cap;
            Edge rev;

            public Edge(int from, int to, int flow, int cap) {
                super();
                this.from = from;
                this.to = to;
                this.flow = flow;
                this.cap = cap;
            }

        }

        ArrayList<Edge>[] edges;
        boolean[] was;
        int n;

        public Graph(int n) {
            @SuppressWarnings("unchecked")
            ArrayList<Edge>[] edges = new ArrayList[n];
            this.edges = edges;
            this.n = n;
            for (int i = 0; i < edges.length; i++) {
                edges[i] = new ArrayList<Edge>();
            }
            was = new boolean[n];
        }

        Edge addEdge(int from, int to, int flow, int cap) {
            Edge e1 = new Edge(from, to, flow, cap);
            Edge e2 = new Edge(to, from, -flow, 0);
            e1.rev = e2;
            e2.rev = e1;
            edges[from].add(e1);
            edges[to].add(e2);
            return e1;
        }

        int dfs(int x, int target, int cMin) {
            was[x] = true;
            if (x == target) {
                return cMin;
            }
            for (int i = 0; i < edges[x].size(); ++i) {
                Edge e = edges[x].get(i);
                if (e.cap - e.flow == 0 || was[e.to]) {
                    continue;
                }
                int add = dfs(e.to, target, Math.min(cMin, e.cap - e.flow));
                if (add == 0) {
                    continue;
                }
                e.flow += add;
                e.rev.flow -= add;
                return add;
            }
            return 0;
        }

        long getMaxFlow(int source, int target) {
            Arrays.fill(was, false);
            return dfs(source, target, Integer.MAX_VALUE);
        }


    }
}
