package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.lang.reflect.Array;
import java.util.*;

public class Defence {

    static class Edge {
        int to;
        int id;

        Edge(int to, int id) {
            this.to = to;
            this.id = id;
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        List<Edge>[] edges = new List[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<Edge>();
        }
        int start = in.nextInt() - 1;
        int finish = in.nextInt() - 1;
        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            edges[from].add(new Edge(to, i));
            edges[to].add(new Edge(from, i));
        }

        Queue<Integer> q = new ArrayDeque<Integer>();
        int[] d = new int[n];
        Arrays.fill(d, Integer.MAX_VALUE);
        d[start] = 0;
        q.add(start);
        while (!q.isEmpty()) {
            int v = q.poll();
            if (v == finish) {
                break;
            }
            for (Edge e : edges[v]) {
                if (d[e.to] == Integer.MAX_VALUE) {
                    d[e.to] = d[v] + 1;
                    q.add(e.to);
                }
            }
        }
        List<Integer>[] answer = new List[d[finish]];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < n; i++) {
            if (d[i] < d[finish]) {
                for (Edge e : edges[i]) {
                    if (d[e.to] == d[i] + 1) {
                        answer[d[i]].add(e.id);
                    }
                }
            }
        }
        out.println(answer.length);
        for (List<Integer> z : answer) {
            out.print(z.size());
            for (int i : z) {
                out.print(" " + (i + 1));
            }
            out.println();
        }
    }
}
