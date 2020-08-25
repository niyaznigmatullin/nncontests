package ru.ifmo.niyaz.graphalgorithms;

import java.util.Arrays;

public class TwoSATSolver {
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

    public void addClause(int v, int u) {
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

    public boolean[] solve() {
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
