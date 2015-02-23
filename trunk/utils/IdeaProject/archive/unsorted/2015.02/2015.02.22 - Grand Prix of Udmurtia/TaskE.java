package coding;

import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.graphalgorithms.MinCostMaxFlowGraph;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.*;

public class TaskE {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = in.nextInt();
            y[i] = in.nextInt();
        }
        int[] xs = ArrayUtils.sortAndUnique(x);
        int[] ys = ArrayUtils.sortAndUnique(y);
        HashMap<Element, Integer> interesting = new HashMap<>();
        int[][] f = new int[n][k];
        int[][] fCost = new int[n][k];
        int[] count = new int[n];
        for (int i = 0; i < k; i++) {
            count[in.nextInt() - 1]++;
        }
        for (int c = 1; c <= n; c++) {
            TreeSet<Element> q = new TreeSet<>(new Comparator<Element>() {
                @Override
                public int compare(Element o1, Element o2) {
                    int c = Integer.compare(o1.val, o2.val);
                    if (c != 0) return c;
                    c = Integer.compare(o1.x, o2.x);
                    if (c != 0) return c;
                    return Integer.compare(o1.y, o2.y);
                }
            });
            for (int cx : xs) {
                for (int cy : ys) {
                    q.add(getElement(cx, cy, x, y, c));
                }
            }
            HashSet<Element> was = new HashSet<>();
            HashMap<Element, Integer> got = new HashMap<>();
            while (got.size() < k) {
                Element v = q.pollFirst();
                boolean canAdd = true;
                for (int i = 0; i < n; i++) {
                    if (x[i] == v.x && y[i] == v.y) {
                        canAdd = false;
                        break;
                    }
                }
                if (canAdd) {
                    got.put(v, v.val);
                }
                for (int dx = -1; dx <= 1; dx++) {
                    for (int dy = -1; dy <= 1; dy++) {
                        if (dx == 0 && dy == 0) continue;
                        int nx = v.x + dx;
                        int ny = v.y + dy;
                        if (nx < 0 || ny < 0 || nx > 5000 || ny > 5000) continue;
                        Element e = getElement(nx, ny, x, y, c);
                        if (was.add(e)) {
                            q.add(e);
                        }
                    }
                }
            }
            int cn = 0;
            for (Element e : got.keySet()) {
                if (!interesting.containsKey(e)) {
                    interesting.put(e, interesting.size());
                }
                fCost[c - 1][cn] = got.get(e);
                f[c - 1][cn++] = interesting.get(e);
            }
        }
        MinCostMaxFlowGraph g = new MinCostMaxFlowGraph(2 + n + interesting.size());
        int src = n + interesting.size();
        int tar = src + 1;
        for (int i = 0; i < n; i++) {
            g.addEdge(src, i, count[i], 0);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k; j++) {
                g.addEdge(i, n + f[i][j], 1, fCost[i][j]);
            }
        }
        for (int i = 0; i < interesting.size(); i++) g.addEdge(i + n, tar, 1, 0);
        out.println(g.getMinCostMaxFlow(src, tar)[1]);
    }

    static Element getElement(int cx, int cy, int[] x, int[] y, int k) {
        int mask = 0;
        int ans = 0;
        for (int i = 0; i < k; i++) {
            int best = -1;
            int dist = 0;
            for (int j = 0; j < x.length; j++) {
                if (((mask >> j) & 1) == 1) continue;
                int cd = Math.abs(cx - x[j]) + Math.abs(cy - y[j]);
                if (best < 0 || dist > cd) {
                    best = j;
                    dist = cd;
                }
            }
            ans += dist;
            mask |= 1 << best;
        }
        return new Element(cx, cy, ans);
    }

    static class Element {
        final int x;
        final int y;
        int val;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Element element = (Element) o;

            if (x != element.x) return false;
            if (y != element.y) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }

        @Override
        public String toString() {
            return "Element{" +
                    "x=" + x +
                    ", y=" + y +
                    ", val=" + val +
                    '}';
        }

        Element(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }
}
