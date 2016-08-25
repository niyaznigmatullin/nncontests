package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.*;

public class TaskE {

    static int not(int x, int n) {
        if (x < n) {
            return x + n;
        }
        return x - n;
    }

    static boolean[] was;

    static void dfs(int v, CondensationGraph g) {
        was[v] = true;
        List<Integer> to = g.edges[v];
        for (int i = 0; i < to.size(); i++) {
            if (!was[to.get(i)]) {
                dfs(to.get(i), g);
            }
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m1 = in.nextInt();
        int m2 = in.nextInt();
        CondensationGraph g1 = getGraph(in, n, m1);
        CondensationGraph g2 = getGraph(in, n, m2);
        boolean[][] d1 = makeDFS(n, g1);
        boolean[][] d2 = makeDFS(n, g2);
        boolean isZero1 = computeIsZero(n, d1);
        boolean isZero2 = computeIsZero(n, d2);
        if (isZero1 && isZero2) {
            out.println("SIMILAR");
            return;
        }
        int[] val1 = getVal(n, d1);
        int[] val2 = getVal(n, d2);
        if (isZero2) {
            getSolution(n, g1, val1);
            for (int i = 0; i < n; i++) {
                if (i > 0) out.print(' ');
                out.println((val1[i] + 1) / 2);
            }
        } else if (isZero1) {
            getSolution(n, g2, val2);
            for (int i = 0; i < n; i++) {
                if (i > 0) out.print(' ');
                out.println((val2[i] + 1) / 2);
            }
        } else {
            if (!Arrays.equals(val1, val2)) {

            }
            for (int i = 0; i < n + n; i++) {
                for (int j = 0; j < n + n; j++) {
                    if (d1[i][j] != d2[i][j]) {

                    }
                }
            }
        }
    }

    private void getSolution(int n, CondensationGraph g1, int[] val1) {
        CondensationGraph r1 = g1.getCondensationNoCopies();
        int[] colors = g1.color;
        int mx = 0;
        for (int i : colors) mx = Math.max(mx, i);
        mx++;
        List<Integer>[] all = new List[mx];
        int[] any = new int[mx];
        for (int i = 0; i < mx; i++) {
            all[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            all[colors[i]].add(i);
            any[colors[i]] = i;
        }
        for (int i = mx - 1; i >= 0; i--) {
            if (getValue(val1, any[i]) != 0) continue;
            for (int j : all[i]) {
                setValue(val1, j);
            }
        }
    }

    private int getValue(int[] val, int i) {
        if (i < val.length) {
            return val[i];
        }
        return -val[i - val.length];
    }

    private void setValue(int[] val, int i) {
        if (i < val.length) {
            val[i] = 1;
        }
        val[i - val.length] = -1;
    }

    private boolean computeIsZero(int n, boolean[][] d1) {
        boolean isZero1 = false;
        for (int i = 0; i < n; i++) {
            if (d1[i][i + n] && d1[i + n][i]) {
                isZero1 = true;
                break;
            }
        }
        return isZero1;
    }

    private int[] getVal(int n, boolean[][] d1) {
        int[] val1 = new int[n];
        for (int i = 0; i < n; i++) {
            if (d1[i][i + n]) {
                val1[i] = 1;
            } else if (d1[i + n][i]) {
                val1[i] = -1;
            }
        }
        return val1;
    }

    private boolean[][] makeDFS(int n, CondensationGraph g1) {
        boolean[][] d1 = new boolean[n + n][n + n];
        for (int i = 0; i < n + n; i++) {
            was = d1[i];
            dfs(i, g1);
        }
        return d1;
    }

    private CondensationGraph getGraph(FastScanner in, int n, int m1) {
        CondensationGraph g1 = new CondensationGraph(n + n);
        for (int i = 0; i < m1; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            if (x < 0) {
                x = -x + n;
            }
            if (y < 0) {
                y = -y + n;
            }
            x--;
            y--;
            g1.addEdge(not(x, n), y);
            g1.addEdge(not(y, n), x);
        }
        return g1;
    }
    static class CondensationGraph {
        public int n;
        public List<Integer>[] edges;
        public List<Integer>[] revEdges;
        public boolean[] was;
        public int[] color;

        public CondensationGraph(int n) {
            this.n = n;
            edges = new List[n];
            revEdges = new List[n];
            for (int i = 0; i < n; i++) {
                edges[i] = new ArrayList<Integer>();
                revEdges[i] = new ArrayList<Integer>();
            }
        }

        public void addEdge(int from, int to) {
            edges[from].add(to);
            revEdges[to].add(from);
        }


        void fillTopSort(int v, List<Integer> topSort) {
            was[v] = true;
            for (int i : edges[v]) {
                if (was[i]) {
                    continue;
                }
                fillTopSort(i, topSort);
            }
            topSort.add(v);
        }

        void color(int v, int c) {
            color[v] = c;
            for (int i : revEdges[v]) {
                if (color[i] < 0) {
                    color(i, c);
                }
            }
        }

        public CondensationGraph getCondensationNoCopies() {
            was = new boolean[n];
            List<Integer> topSort = new ArrayList<Integer>();
            for (int i = 0; i < n; i++) {
                if (was[i]) {
                    continue;
                }
                fillTopSort(i, topSort);
            }
            Collections.reverse(topSort);
            int colors = 0;
            color = new int[n];
            Arrays.fill(color, -1);
            for (int i : topSort) {
                if (color[i] >= 0) {
                    continue;
                }
                color(i, colors++);
            }
            Set<Integer>[] sets = new Set[colors];
            for (int i = 0; i < colors; i++) {
                sets[i] = new HashSet<>();
            }
            for (int i = 0; i < n; i++) {
                for (int j : edges[i]) {
                    if (color[i] != color[j]) {
                        sets[color[i]].add(color[j]);
                    }
                }
            }
            CondensationGraph g = new CondensationGraph(colors);
            for (int i = 0; i < colors; i++) {
                for (int j : sets[i]) {
                    g.addEdge(i, j);
                }
            }
            return g;
        }

        public int[] getColors() {
            return color.clone();
        }

        List<Integer>[] getEdges;
    }

}
