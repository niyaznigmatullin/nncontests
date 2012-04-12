package mypackage;

import graphalgorithms.DinicGraph;
import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Attack {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int a = in.nextInt() - 1;
        int b = in.nextInt() - 1;
        DinicGraph g = new DinicGraph(n);
        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            int c = in.nextInt();
            g.addEdge(from, to, 0, c);
            g.addEdge(to, from, 0, c);
        }
        g.getMaxFlow(a, b);
        boolean[] was1;
        boolean[] was2;
        {
            Queue<Integer> q = new ArrayDeque<Integer>();
            q.add(a);
            was1 = new boolean[n];
            was1[a] = true;
            while (!q.isEmpty()) {
                int v = q.poll();
                for (DinicGraph.Edge e : g.edges[v]) {
                    if (was1[e.to]) {
                        continue;
                    }
                    if (e.cap > e.flow && e.cap > 0) {
                        q.add(e.to);
                        was1[e.to] = true;
                    }
                }
            }
        }
        {
            Queue<Integer> q = new ArrayDeque<Integer>();
            q.add(b);
            was2 = new boolean[n];
            Arrays.fill(was2, true);
            was2[b] = false;
            while (!q.isEmpty()) {
                int v = q.poll();
                for (DinicGraph.Edge er : g.edges[v]) {
                    DinicGraph.Edge e = er.rev;
                    if (!was2[e.from]) {
                        continue;
                    }
                    if (e.cap > e.flow && e.cap > 0) {
                        q.add(e.from);
                        was2[e.from] = false;
                    }
                }
            }
        }
        out.println(Arrays.equals(was1, was2) ? "UNIQUE" : "AMBIGUOUS");
    }

}
