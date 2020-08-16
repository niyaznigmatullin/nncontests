package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskE {

    static class TwoSATSolver {
        int[] fromEdges;
        int[] toEdges;
        int varsCount;
        int edgesCount;
        int[] head, next;
        int[] headR, nextR;

        public TwoSATSolver(int n, int maxEdges) {
            this.varsCount = n;
            fromEdges = new int[(1 + maxEdges) * 2];
            toEdges = new int[(1 + maxEdges) * 2];
            edgesCount = 0;
        }

        int not(int v) {
            return v < varsCount ? v + varsCount : v - varsCount;
        }

        private void addEdge(int v, int u) {
            if (edgesCount == fromEdges.length) {
                fromEdges = Arrays.copyOf(fromEdges, fromEdges.length * 2);
                toEdges = Arrays.copyOf(toEdges, toEdges.length * 2);
            }
            fromEdges[edgesCount] = v;
            toEdges[edgesCount] = u;
            ++edgesCount;
        }

        void addClause(int v, int u) {
            v = v < 0 ? (~v) + varsCount : v;
            u = u < 0 ? (~u) + varsCount : u;
            addEdge(not(v), u);
            addEdge(not(u), v);
        }

        boolean[] was;
        int[] order;
        int cn;
        int[] color;

        private void makeOrder(int v) {
            was[v] = true;
            for (int e = head[v]; e >= 0; e = next[e]) {
                int to = toEdges[e];
                if (!was[to]) {
                    makeOrder(to);
                }
            }
            order[cn++] = v;
        }

        private void makeColor(int v, int c) {
            color[v] = c;
            for (int e = headR[v]; e >= 0; e = nextR[e]) {
                int to = fromEdges[e];
                if (color[to] < 0) {
                    makeColor(to, c);
                }
            }
        }

        boolean[] solve() {
            int n = varsCount * 2;
            head = new int[n];
            headR = new int[n];
            for (int i = 0; i < n; i++) {
                head[i] = headR[i] = -1;
            }
            next = new int[edgesCount];
            nextR = new int[edgesCount];
            for (int i = 0; i < edgesCount; i++) {
                next[i] = head[fromEdges[i]];
                head[fromEdges[i]] = i;
                nextR[i] = headR[toEdges[i]];
                headR[toEdges[i]] = i;
            }
            cn = 0;
            order = new int[n];
            was = new boolean[n];
            for (int i = 0; i < n; i++) {
                if (was[i]) continue;
                makeOrder(i);
            }
            int colors = 0;
            color = new int[n];
            Arrays.fill(color, -1);
            for (int it = cn - 1; it >= 0; it--) {
                int v = order[it];
                if (color[v] < 0) {
                    makeColor(v, colors++);
                }
            }
            for (int i = 0; i < varsCount; i++) {
                if (color[i] == color[not(i)]) {
                    return null;
                }
            }
            int[] backColor = new int[colors];
            Arrays.fill(backColor, -1);
            for (int i = 0; i < n; i++) {
                int c = color[i];
                int cc = color[not(i)];
                if (backColor[c] >= 0 && backColor[c] != cc) {
                    throw new AssertionError();
                }
                backColor[c] = cc;
            }
            if ((colors & 1) == 1) throw new AssertionError();
            int[] w = new int[colors];
            Arrays.fill(w, -1);
            for (int i = colors - 1; i >= 0; i--) {
                if (w[i] < 0) {
                    w[i] = 1;
                    w[backColor[i]] = 0;
                }
            }
            boolean[] ret = new boolean[varsCount];
            for (int i = 0; i < varsCount; i++) {
                ret[i] = w[color[i]] == 1;
            }
            return ret;
        }
    }

    int T;
    int V;
    List<Integer>[] edges, paths;
    int empty;
    int free = 0;
    final int M = 1234567;
    TwoSATSolver twoSAT;

    int[] goLeft = new int[M];
    int[] goRight = new int[M];
    int[] maskSum = new int[M];
    int[] vert = new int[M];

    int newNode(int l, int r) {
        goLeft[free] = l;
        goRight[free] = r;
        vert[free] = -1;
        maskSum[free] = l >= 0 && r >= 0 ? (maskSum[l] + maskSum[r]) : 0;
        return free++;
    }

    int mergeImpl(int tA, int tB, int left, int right) {
        if (maskSum[tA] == 0) return tB;
        if (maskSum[tB] == 0) return tA;
        if (left == right - 1) {
            int v = newNode(-1, -1);
            maskSum[v] = maskSum[tA] ^ maskSum[tB];
            return v;
        }
        int mid = (left + right) >>> 1;
        return newNode(mergeImpl(goLeft[tA], goLeft[tB], left, mid), mergeImpl(goRight[tA], goRight[tB], mid, right));
    }

    int add(int v, int x, int mask, int left, int right) {
        if (left == right - 1) {
            if (x != left) throw new AssertionError();
            int u = newNode(-1,-1);
            maskSum[u] = maskSum[v] ^ mask;
            return u;
        }
        int mid = (left + right) >> 1;
        int tLeft = goLeft[v];
        int tRight = goRight[v];
        if (x < mid) {
            tLeft = add(tLeft, x, mask, left, mid);
        } else {
            tRight = add(tRight, x, mask, mid, right);
        }
        return newNode(tLeft, tRight);
    }

    void fillVertices(int v, int left, int right) {
        if (vert[v] >= 0) return;
        vert[v] = V++;
        if (left == right - 1) {
            int mask = maskSum[v];
            if ((mask & 1) == 1) {
                twoSAT.addClause(twoSAT.not(left), vert[v]);
            }
            if ((mask & 2) == 2) {
                twoSAT.addClause(left, vert[v]);
            }
            return;
        }
        int mid = (left + right) >> 1;
        fillVertices(goLeft[v], left, mid);
        fillVertices(goRight[v], mid, right);
        twoSAT.addClause(twoSAT.not(vert[goLeft[v]]), twoSAT.not(vert[goRight[v]]));
        twoSAT.addClause(twoSAT.not(vert[goLeft[v]]), vert[v]);
        twoSAT.addClause(twoSAT.not(vert[goRight[v]]), vert[v]);
    }

    int merge(int tA, int tB) {
        if (tA < 0) return tB;
        if (tB < 0) return tA;
        return mergeImpl(tA, tB, 0, T);
    }

    int dfs(int v, int pv) {
        int cur = -1;
        for (int i = 0; i < edges[v].size(); i++) {
            int to = edges[v].get(i);
            if (to == pv) continue;
            int child = dfs(to, v);
            if (pv >= 0) {
                cur = merge(cur, child);
            }
        }
        if (cur < 0) {
            cur = empty;
        }
        if (pv < 0) return -1;
        for (int i = 0; i < paths[v].size(); i++) {
            int e = paths[v].get(i);
            if (e >= 0) {
                cur = add(cur, e, 1, 0, T);
            } else {
                cur = add(cur, ~e, 2, 0, T);
            }
        }
        fillVertices(cur, 0, T);
        return cur;
    }

    int build(int left, int right) {
        if (left == right - 1) {
            return newNode(left, right);
        }
        int mid = (left + right) >>> 1;
        return newNode(build(left, mid), build(mid, right));
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        edges = new List[n];
        for (int i = 0; i < n; i++) edges[i] = new ArrayList<>();
        for (int i = 0; i + 1 < n; i++) {
            int v = in.nextInt() - 1;
            int u = in.nextInt() - 1;
            edges[v].add(u);
            edges[u].add(v);
        }
        int m = in.nextInt();
        paths = new List[n];
        for (int i = 0; i < n; i++) paths[i] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            int c = in.nextInt() - 1;
            int d = in.nextInt() - 1;
            paths[a].add(i);
            paths[b].add(i);
            paths[c].add(~i);
            paths[d].add(~i);
        }
        twoSAT = new TwoSATSolver(m + 1000000, 0);
        T = m;
        V = m;
        empty = build(0, T);
        fillVertices(empty, 0, T);
        dfs(0, -1);
        boolean[] ans = twoSAT.solve();
        if (ans == null) {
            out.println("NO");
        } else {
            out.println("YES");
            for (int i = 0; i < m; i++) {
                if (ans[i]) {
                    out.println(1);
                } else {
                    out.println(2);
                }
            }
        }
    }
}
