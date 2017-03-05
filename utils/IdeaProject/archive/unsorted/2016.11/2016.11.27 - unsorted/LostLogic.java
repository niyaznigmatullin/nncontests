package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.*;

public class LostLogic {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[][] a = in.readInt2DArray(3, n);
        List<Edge> ret = new ArrayList<>();
        Map<Integer, List<Integer>> q = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (a[0][i] == a[1][i] && a[0][i] == a[2][i]) {
                if (a[0][i] == 0) {
                    ret.add(new Edge(i, ~i));
                } else {
                    ret.add(new Edge(~i, i));
                }
            } else {
                int mask = (a[0][i] * 4) + a[1][i] * 2 + a[2][i];
                if (a[0][i] == 1) {
                    mask = 7 - mask;
                }
                if (!q.containsKey(mask)) q.put(mask, new ArrayList<>());
                if (a[0][i] == 1) {
                    q.get(mask).add(~i);
                } else {
                    q.get(mask).add(i);
                }
            }
        }
        if (q.size() > 2) {
            out.println(-1);
            return;
        }
        if (q.size() < 2) throw new AssertionError();
        int mask1 = -1;
        int mask2 = -1;
        for (int i : q.keySet()) {
            if (mask1 < 0) mask1 = i; else mask2 = i;
        }
        int have = 1;
        for (int i = 0; i < 2; i++) {
            int r = ((mask1 >> i) & 1) * 2 + ((mask2 >> i) & 1);
            have |= 1 << r;
        }
        have = Integer.numberOfTrailingZeros(15 ^ have);
        List<Integer> list = q.get(mask1);
        int any1 = -1;
        for (int i = 0; i < list.size(); i++) {
            int cur = list.get(i);
            int next = list.get((i + 1) % list.size());
            ret.add(new Edge(cur, next));
            any1 = cur;
        }
        list = q.get(mask2);
        int any2 = -1;
        for (int i = 0; i < list.size(); i++) {
            int cur = list.get(i);
            int next = list.get((i + 1) % list.size());
            ret.add(new Edge(cur, next));
            any2 = cur;
        }
        if (have == 1) {
            ret.add(new Edge(~any1, ~any2));
        } else if (have == 2) {
            ret.add(new Edge(any1, any2));
        } else if (have == 3) {
            ret.add(new Edge(any1, ~any2));
        } else throw new AssertionError();
        out.println(ret.size());
        for (Edge e : ret) {
            out.print(e.from < 0 ? "!x" + ((~e.from) + 1) : "x" + (1 + e.from));
            out.print(" -> ");
            out.println(e.to < 0 ? "!x" + ((~e.to) + 1) : "x" + (1 + e.to));
        }
    }

    static class Edge {
        int from;
        int to;

        public Edge(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }
}
