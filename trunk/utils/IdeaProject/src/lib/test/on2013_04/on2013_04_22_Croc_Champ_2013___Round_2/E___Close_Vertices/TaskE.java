package lib.test.on2013_04.on2013_04_22_Croc_Champ_2013___Round_2.E___Close_Vertices;



import ru.ifmo.niyaz.DataStructures.Fenwick;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class TaskE {

    static class Edge {
        int from;
        int to;
        int w;
        Edge rev;
        boolean killed;

        Edge(int from, int to, int w) {
            this.from = from;
            this.to = to;
            this.w = w;
        }
    }

    static void kill(Edge e) {
        e.killed = true;
        e.rev.killed = true;
    }

    static List<Edge>[] edges;
    static int L;
    static int W;
    static int[] sz;
    static int[] color;
    static int[] vertices;
    static int[] depth;
    static int[] weight;
    static int cnv;

    static void dfs(int v, int p) {
        sz[v] = 1;
        for (int i = 0; i < edges[v].size(); i++) {
            Edge e = edges[v].get(i);
            if (e.killed || e.to == p) {
                continue;
            }
            dfs(e.to, v);
            sz[v] += sz[e.to];
        }
    }

    static void dfs2(int v, int p, int w, int d) {
        vertices[cnv++] = v;
        depth[v] = d;
        weight[v] = w;
        for (int i = 0; i < edges[v].size(); i++) {
            Edge e = edges[v].get(i);
            if (e.killed || e.to == p) {
                continue;
            }
            dfs2(e.to, v, w + e.w, d + 1);
        }
    }

    static void color(int v, int p, int c) {
        color[v] = c;
        for (int i = 0; i < edges[v].size(); i++) {
            Edge e = edges[v].get(i);
            if (e.killed || e.to == p) {
                continue;
            }
            color(e.to, v, c);
        }
    }

    static void solve(int v) {
        cnv = 0;
        dfs(v, -1);
        {
            int all = sz[v];
            int p = -1;
            boolean changed = true;
            while (changed) {
                changed = false;
                for (int i = 0; i < edges[v].size(); i++) {
                    Edge e = edges[v].get(i);
                    if (e.killed || e.to == p) {
                        continue;
                    }
                    if (sz[e.to] * 2 > all) {
                        p = v;
                        v = e.to;
                        changed = true;
                        break;
                    }
                }
            }
        }
        dfs(v, -1);
        dfs2(v, -1, 0, 0);
        int countChildren = 0;
        for (int i = 0; i < edges[v].size(); i++) {
            Edge e = edges[v].get(i);
            if (e.killed) {
                continue;
            }
            countChildren++;
        }
        int[] children = new int[countChildren];
        countChildren = 0;
        for (int i = 0; i < edges[v].size(); i++) {
            Edge e = edges[v].get(i);
            if (e.killed) {
                continue;
            }
            children[countChildren++] = e.to;
        }
        int countColors = 0;
        color[v] = countColors++;
        for (int i = 0; i < edges[v].size(); i++) {
            Edge e = edges[v].get(i);
            if (e.killed) {
                continue;
            }
            color(e.to, v, countColors++);
        }
        Fenwick[] fs = new Fenwick[countChildren + 1];
        fs[0] = new Fenwick(2);
        for (int i = 0; i < countChildren; i++) {
            fs[i + 1] = new Fenwick(sz[children[i]] + 1);
        }
        Fenwick all = new Fenwick(sz[v] + 1);
        Integer[] id = new Integer[cnv];
        for (int i = 0; i < id.length; i++) {
            id[i] = i;
        }
//        System.out.println(v + " " + Arrays.toString(depth) + " " + Arrays.toString(weight) + " " + Arrays.toString(Arrays.copyOf(vertices, cnv)) + " " + Arrays.toString(color) + " " + Arrays.toString(sz));
        Arrays.sort(id, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int v = vertices[o1];
                int u = vertices[o2];
                return Integer.compare(weight[v], weight[u]);
            }
        });
        int curQ = cnv - 1;
        for (int i : id) {
            int cv = vertices[i];
            long cw = weight[cv];
            while (curQ >= 0) {
                int cu = vertices[id[curQ]];
                int cuw = weight[cu];
                if (cw + cuw > W) {
                    answer += all.getSum(L - depth[cu]);
                    answer -= fs[color[cu]].getSum(L - depth[cu]);
//                    System.out.println(cu + " " + answer + " " + all.getSum(L - depth[cu]) + " " + fs[color[cu]].getSum(L - depth[cu]) + " " + (L - depth[cu]));
                    --curQ;
                } else {
                    break;
                }
            }
            all.add(depth[cv], 1);
            fs[color[cv]].add(depth[cv], 1);
//            System.out.println("add " + cv);
        }
        while (curQ >= 0) {
            int cu = vertices[id[curQ]];
            answer += all.getSum(L - depth[cu]);
            answer -= fs[color[cu]].getSum(L - depth[cu]);
//            System.out.println(cu + " " + answer + " " + all.getSum(L - depth[cu]) + " " + fs[color[cu]].getSum(L - depth[cu]) + " " + (L - depth[cu]));
            --curQ;
        }
        for (int i = 0; i < edges[v].size(); i++) {
            kill(edges[v].get(i));
        }
        for (int i : children) {
            solve(i);
        }
    }

    static long answer;

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        edges = new List[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
        }
        L = in.nextInt();
        W = in.nextInt();
        for (int i = 1; i < n; i++) {
            int p = in.nextInt() - 1;
            int w = in.nextInt();
//            System.out.println(i + " " + p);
            Edge e1 = new Edge(i, p, w);
            Edge e2 = new Edge(p, i, w);
            e1.rev = e2;
            e2.rev = e1;
            edges[i].add(e1);
            edges[p].add(e2);
        }
        answer = 0;
        sz = new int[n];
        vertices = new int[n];
        color = new int[n];
        weight = new int[n];
        depth = new int[n];
        solve(0);
        out.println(answer >> 1);
    }
}
