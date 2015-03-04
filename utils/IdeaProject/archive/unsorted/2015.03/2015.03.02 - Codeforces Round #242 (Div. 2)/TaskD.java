package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

public class TaskD {
    static int tp;
    static int tu;
    static int td;

    static int f(int from, int to) {
        if (from == to) return tp;
        if (from > to) return td;
        else return tu;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int needT = in.nextInt();
        tp = in.nextInt();
        tu = in.nextInt();
        td = in.nextInt();
        int[][] a = in.readInt2DArray(n, m);
        int[][][] go = new int[4][n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i > 0) {
                    go[0][i][j] = go[0][i - 1][j] + f(a[i][j], a[i - 1][j]);
                }
                if (j > 0) {
                    go[1][i][j] = go[1][i][j - 1] + f(a[i][j], a[i][j - 1]);
                }
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (i + 1 < n) {
                    go[2][i][j] = go[2][i + 1][j] + f(a[i][j], a[i + 1][j]);
                }
                if (j + 1 < m) {
                    go[3][i][j] = go[3][i][j + 1] + f(a[i][j], a[i][j + 1]);
                }
            }
        }
        int ansDif = Integer.MAX_VALUE;
        int ansUp = -1;
        int ansDown = -1;
        int ansLeft = -1;
        int ansRight = -1;
        Element[] f = new Element[n - 1];
        Comparator<Element> comp = new Comparator<Element>() {
            @Override
            public int compare(Element o1, Element o2) {
                int c = Integer.compare(o1.sum, o2.sum);
                if (c != 0) return c;
                return Integer.compare(o1.id, o2.id);
            }
        };
        int[] p1 = new int[n + 1];
        int[] p2 = new int[n + 1];
        int[] where = new int[n - 1];
        for (int left = 0; left < m; left++) {
            for (int right = left + 2; right < m; right++) {
                Element cur = new Element(0, 0);
                for (int i = 0; i < n - 1; i++) {
                    f[i] = new Element(go[3][i][left] - go[3][i][right] - go[0][i][left] + go[2][i][right], i);
                }
                Arrays.sort(f, comp);
                for (int i = 0; i < n - 1; i++) where[f[i].id] = i;
                for (int i = 0; i <= n; i++) p1[i] = p2[i] = i;
                for (int i = n - 1; i > 0; i--) {
                    int zID = where[i - 1];
                    p1[get(p1, zID)] = get(p1, (zID == 0 ? n - 1 : zID - 1));
                    p2[get(p2, zID)] = get(p2, zID + 1);
                    int cost = go[1][i][right] - go[1][i][left] + go[0][i][left] - go[2][i][right];
                    cur.sum = needT - cost;
                    cur.id = -1;
                    int id = ~Arrays.binarySearch(f, cur, comp);
                    int prevID = id == 0 ? n - 1 : get(p1, id - 1);
                    int nextID = get(p2, id);
                    Element prev = prevID == n - 1 ? null : f[prevID];
                    Element next = nextID == n - 1 ? null : f[nextID];
                    if (prev != null) {
                        int all = prev.sum + cost;
                        if (Math.abs(all - needT) < ansDif) {
                            ansDif = Math.abs(all - needT);
                            ansUp = prev.id;
                            ansDown = i;
                            ansLeft = left;
                            ansRight = right;
                        }
                    }
                    if (next != null) {
                        int all = next.sum + cost;
                        if (Math.abs(all - needT) < ansDif) {
                            ansDif = Math.abs(all - needT);
                            ansUp = next.id;
                            ansDown = i;
                            ansLeft = left;
                            ansRight = right;
                        }
                    }
                }
            }
        }
        out.println((ansUp + 1) + " " + (ansLeft + 1) + " " + (ansDown + 1) + " " + (ansRight + 1));
    }

    static class Element {
        int sum;
        int id;

        public Element(int sum, int id) {
            this.sum = sum;
            this.id = id;
        }
    }

    static int get(int[] p, int x) {
        return x == p[x] ? x : (p[x] = get(p, p[x]));
    }
}
