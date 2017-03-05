package coding;

import ru.ifmo.niyaz.DataStructures.Fenwick;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.MathUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class KmeanTree {

    static class Edge {
        int from;
        int to;
        long weight;

        public Edge(int from, int to, long weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    static List<Edge>[] edges;
    static int T;
    static int[] en, ex;
    static long[] depthW;
    static int[] depth;

    static void dfs(int v, int pv, long dW, int d) {
        en[v] = T++;
        depthW[v] = dW;
        depth[v] = d;
        for (int i = 0; i < edges[v].size(); i++) {
            Edge e = edges[v].get(i);
            if (e.to != pv) {
                dfs(e.to, v, dW + e.weight, d + 1);
            }
        }
        ex[v] = T;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        long k = in.nextLong();
        edges = new List[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
        }
        final long F = 1L << 6;
        for (int i = 0; i + 1 < n; i++) {
            int v = in.nextInt() - 1;
            int u = in.nextInt() - 1;
            long w = in.nextInt() * F;
            edges[v].add(new Edge(v, u, w));
            edges[u].add(new Edge(u, v, w));
        }
        en = new int[n];
        ex = new int[n];
        depthW = new long[n];
        depth = new int[n];
        T = 0;
        dfs(0, -1, 0, 0);
        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) {
            order[i] = i;
        }
        long l = -1;
        long r = 1L << 40;
        final long[] cd = new long[n];
        Fenwick f = new Fenwick(n);
        while (l < r - 1) {
            long mid = l + r >>> 1;
            for (int i = 0; i < n; i++) {
                cd[i] = depthW[i] - depth[i] * mid;
            }
            Arrays.sort(order, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    int c = Long.compare(cd[o1], cd[o2]);
                    if (c != 0) return c;
                    return -Integer.compare(en[o1], en[o2]);
                }
            });
//            System.out.println(mid + " " + Arrays.toString(order));
            long count = 0;
            for (int i : order) {
                count += f.getSum(en[i], ex[i]);
                f.add(en[i], 1);
            }
            for (int i = 0; i < n; i++) {
                f.add(i, -1);
            }
            if (count >= k) {
                r = mid;
            } else {
                l = mid;
            }
        }
        int[] count = new int[n];
        for (long mid = l; mid <= r; mid++) {
            for (int i = 0; i < n; i++) {
                cd[i] = depthW[i] - depth[i] * mid;
            }
            Arrays.sort(order, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    int c = Long.compare(cd[o1], cd[o2]);
                    if (c != 0) return c;
                    return -Integer.compare(en[o1], en[o2]);
                }
            });
            for (int i : order) {
                if (mid == l) {
                    count[i] = f.getSum(en[i], ex[i]);
                } else {
                    if (count[i] != f.getSum(en[i], ex[i])) {
                        for (int j = 0; j < n; j++) {
                            if (i != j && en[i] <= en[j] && ex[j] <= ex[i] && cd[j] <= cd[i] && depthW[i] - depth[i] * l < depthW[j] - depth[j] * l) {
                                long num = (depthW[j] - depthW[i]) / F;
                                long den = depth[j] - depth[i];
                                long g = MathUtils.gcd(num, den);
                                if (g > 1) {
                                    num /= g;
                                    den /= g;
                                }
                                out.println(num + "/" + den);
                                return;
                            }
                        }
                        throw new AssertionError();
                    }
                }
                f.add(en[i], 1);
            }
            for (int i = 0; i < n; i++) {
                f.add(i, -1);
            }
        }
        throw new AssertionError();
    }
}
