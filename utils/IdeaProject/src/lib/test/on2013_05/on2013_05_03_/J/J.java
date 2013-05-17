package lib.test.on2013_05.on2013_05_03_.J;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.*;

public class J {

    static class Edge {
        int from;
        int to;
        int length;
        int speedLimit;
        int id;

        Edge(int from, int to, int length, int speedLimit, int id) {
            this.from = from;
            this.to = to;
            this.length = length;
            this.speedLimit = speedLimit;
            this.id = id;
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        List<Edge>[] edges = new List[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            int speed = in.nextInt();
            int len = in.nextInt();
            edges[from].add(new Edge(from, to, len, speed, i));
            edges[to].add(new Edge(to, from, len, speed, i));
        }
        double l = 0;
        double r = 1e8;
        int t = in.nextInt();
        last = new Edge[n];
        for (int it = 0; it < 100; it++) {
            double mid = (l + r) * .5;
            if (check(mid, n, edges) <= t) {
                r = mid;
            } else {
                l = mid;
            }
        }
        check(r, n, edges);
        out.print(r);
        List<Integer> answer = new ArrayList<>();
        for (int v = n - 1; v > 0; v = last[v].from) {
            answer.add(last[v].id);
        }
        Collections.reverse(answer);
        out.println(" " + answer.size());
        for (int i = 0; i < answer.size(); i++) {
            if (i > 0) out.print(' ');
            out.print(answer.get(i) + 1);
        }
        out.println();
    }

    static Edge[] last;

    static double check(double over, int n, List<Edge>[] edges) {
        final double[] d = new double[n];
        Arrays.fill(d, Long.MAX_VALUE);
        d[0] = 0;
        TreeSet<Integer> queue = new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int c = Double.compare(d[o1], d[o2]);
                if (c != 0) return c;
                return o1 - o2;
            }
        });
        queue.add(0);
        boolean[] was = new boolean[n];
        while (!queue.isEmpty()) {
            int v = queue.pollFirst();
            was[v] = true;
            for (int i = 0; i < edges[v].size(); i++) {
                Edge e = edges[v].get(i);
                if (was[e.to]) continue;
                double weight = e.length / (e.speedLimit + over);
                if (d[e.to] > d[e.from] + weight) {
                    queue.remove(e.to);
                    d[e.to] = d[e.from] + weight;
                    last[e.to] = e;
                    queue.add(e.to);
                }
            }
        }
        return d[n - 1];
    }
}
