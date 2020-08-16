package coding;

import ru.ifmo.niyaz.graphalgorithms.KuhnMatchingGraph;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskB2 {

    static class Ship {
        int x;
        int a;
        int f;

        public Ship(int x, int a, int f) {
            this.x = x;
            this.a = a;
            this.f = f;
        }
    }

    static class Base {
        int x;
        int d;

        public Base(int x, int d) {
            this.x = x;
            this.d = d;
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] a = new int[n][n];
        for (int[] e : a) Arrays.fill(e, Integer.MAX_VALUE);
        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            a[from][to] = a[to][from] = 1;
        }
        for (int i = 0; i < n; i++) {
            a[i][i] = 0;
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (a[i][k] != Integer.MAX_VALUE && a[k][j] != Integer.MAX_VALUE) {
                        a[i][j] = Math.min(a[i][j], a[i][k] + a[k][j]);
                    }
                }
            }
        }
        int s = in.nextInt();
        int b = in.nextInt();
        int k = in.nextInt();
        int h = in.nextInt();
        Ship[] ships = new Ship[s];
        for (int i = 0; i < s; i++) {
            ships[i] = new Ship(in.nextInt() - 1, in.nextInt(), in.nextInt());
        }
        Base[] bases = new Base[b];
        for (int i = 0; i < b; i++) {
            bases[i] = new Base(in.nextInt() - 1, in.nextInt());
        }
        KuhnMatchingGraph g = new KuhnMatchingGraph(s, b);
        for (int i = 0; i < s; i++) {
            Ship e = ships[i];
            for (int j = 0; j < b; j++) {
                Base f = bases[j];
                if (f.d > e.a || e.f < a[e.x][f.x]) continue;
                g.addEdge(i, j);
            }
        }
        int have = g.getMaximalMatching();
        out.println(Math.min((long) h * s, (long) k * have));
    }
}
