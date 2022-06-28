package coding;

import com.sun.corba.se.impl.orbutil.graph.Graph;
import ru.ifmo.niyaz.graphalgorithms.GraphUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class TaskD {
    static int[] readArray(FastScanner in, int n, int k) {
        int[] ret = new int[n];
        for (int i = 0; i < k; i++) {
            ret[i] = in.nextInt();
        }
        long a = in.nextInt();
        long b = in.nextInt();
        int c = in.nextInt();
        int d = in.nextInt();
        for (int i = k; i < n; i++) {
            ret[i] = (int) ((ret[i - 2] * a + ret[i - 1] * b + c) % d) + 1;
        }
        return ret;
    }

    static int[] readTree(FastScanner in, int n, int k) {
        int[] ret = new int[n];
        for (int i = 0; i < k; i++) {
            ret[i] = in.nextInt();
        }
        long a = in.nextInt();
        long b = in.nextInt();
        int c = in.nextInt();
        for (int i = k; i < n; i++) {
            ret[i] = (int) ((ret[i - 2] * a + ret[i - 1] * b + c) % i) + 1;
        }
        return ret;
    }

    static int[] readArrayMod(FastScanner in, int n, int k, int mod) {
        int[] ret = new int[n];
        for (int i = 0; i < k; i++) {
            ret[i] = in.nextInt();
        }
        long a = in.nextInt();
        long b = in.nextInt();
        int c = in.nextInt();
        for (int i = k; i < n; i++) {
            ret[i] = (int) ((ret[i - 2] * a + ret[i - 1] * b + c) % mod) + 1;
        }
        return ret;
    }

    static class Edge {
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    static List<Edge>[] edges;
    static int[] enter;
    static int[] exit;
    static int T;
    static long[] depth;

    static void dfs(int v, long d) {
        enter[v] = T++;
        depth[v] = d;
        for (Edge e : edges[v]) {
            dfs(e.to, e.weight + d);
        }
        exit[v] = T;
    }

    static class Line {
        final long h;
        final long z;

        public Line(long h, long z) {
            this.h = h;
            this.z = z;
        }

        long value(long x) {
            return h * x + z;
        }
    }

    static class ConvexHull {
        List<Line> lines;

        ConvexHull() {
            lines = new ArrayList<>();
        }

        void addLine(Line line) {
            if (!lines.isEmpty()) {
                Line lastLine = lines.get(lines.size() - 1);
                if (lastLine.h == line.h && lastLine.z <= line.z) {
                    return;
                }
                if (lastLine.h == line.h) {
                    lines.remove(lines.size() - 1);
                }
                while (lines.size() > 1) {
                    if (onLeft(lines.get(lines.size() - 2), lines.get(lines.size() - 1), line)) {
                        lines.remove(lines.size() - 1);
                    } else {
                        break;
                    }
                }
            }
            lines.add(line);
        }

        boolean onLeft(Line first, Line second, Line third) {
//            return (third.z - second.z) / (second.h - third.h) <= (second.z - first.z) / (first.h - second.h);
            return BigInteger.valueOf(third.z - second.z).multiply(BigInteger.valueOf(first.h - second.h))
                    .compareTo(BigInteger.valueOf(second.z - first.z).multiply(BigInteger.valueOf(second.h - third.h))) <= 0;
        }

        Line getMin(long x) {
            if (lines.isEmpty()) {
                return null;
            }
            int left = -1;
            int right = lines.size() - 1;
            while (left < right - 1) {
                int mid = (left + right) >> 1;
                Line first = lines.get(mid);
                Line second = lines.get(mid + 1);
                if ((second.z - first.z) <= (first.h - second.h) * x) {
                    left = mid;
                } else {
                    right = mid;
                }
            }
            return lines.get(right);
        }
    }

    static class Tree {
        int n;
        ConvexHull[] hulls;

        Tree(int size) {
            n = 1;
            while (n < size) n *= 2;
            hulls = new ConvexHull[n * 2];
            for (int i = 0; i < n * 2; i++) {
                hulls[i] = new ConvexHull();
            }
        }

        void addLine(int pos, Line line) {
            pos += n;
            while (pos > 0) {
                hulls[pos].addLine(line);
                pos >>= 1;
            }
        }

        static Line min(Line a, Line b, long x) {
            if (a == null) return b;
            if (b == null) return a;
            if (a.value(x) <= b.value(x)) return a;
            return b;
        }

        Line bestLine(int left, int right, long x) {
            left += n;
            right += n;
            --right;
            Line best = null;
            while (left <= right) {
                if ((left & 1) == 1) {
                    best = min(best, hulls[left++].getMin(x), x);
                }
                if ((right & 1) == 0) {
                    best = min(best, hulls[right--].getMin(x), x);
                }
                left >>= 1;
                right >>= 1;
            }
            return best;
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        System.err.println("Test #" + testNumber);
        int n = in.nextInt();
        int numberOfQueries = in.nextInt();
        int k = in.nextInt();
        int[] parent = readTree(in, n, k);
        for (int i = 0; i < n; i++) {
            parent[i]--;
        }
        int[] length = readArray(in, n, k);
        int[] hire = readArray(in, n, k);
        int[] startQ = readArrayMod(in, numberOfQueries, k, n);
        for (int i = 0; i < numberOfQueries; i++) {
            startQ[i]--;
        }
        int[] careQ = readArray(in, numberOfQueries, k);
        edges = new List[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            edges[parent[i]].add(new Edge(parent[i], i, length[i]));
        }
        T = 0;
        enter = new int[n];
        exit = new int[n];
        depth = new long[n];
        dfs(0, 0L);
        Integer[] vertices = new Integer[n];
        for (int i = 0; i < n; i++) {
            vertices[i] = i;
        }
        Arrays.sort(vertices, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Long.compare(depth[o2], depth[o1]);
            }
        });
        Tree tree = new Tree(n);
        for (int v : vertices) {
            if (enter[v] + 1 == exit[v]) {
                Line line = new Line(depth[v], 0);
                tree.addLine(enter[v], line);
                continue;
            }
            int vCare = hire[v];
            Line best = tree.bestLine(enter[v], exit[v], vCare);
            long z = best.value(vCare) - depth[v] * vCare;
            Line line = new Line(depth[v], z);
            tree.addLine(enter[v], line);
//            out.println("v = " + v + ", z = " + z);
        }
        long ans = 1;
        final int MOD = 1000000007;
//        out.println(Arrays.toString(startQ));
//        out.println(Arrays.toString(careQ));
        for (int cq = 0; cq < numberOfQueries; cq++) {
            int start = startQ[cq];
            long care = careQ[cq];
            Line bestLine = tree.bestLine(enter[start], exit[start], care);
            long currentAnswer = bestLine.value(care) - depth[start] * care;
            currentAnswer %= MOD;
            ans = ans * (currentAnswer + 1) % MOD;
//            out.println(currentAnswer);
        }
        out.println("Case #" + testNumber + ": " + ans);
    }
}
