package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.Arrays;

public class TaskC {

    static class Figure implements Comparable<Figure> {

        int count;
        int cost;

        Figure(int count, int cost) {
            this.count = count;
            this.cost = cost;
        }

        public int compareTo(Figure o) {
            return cost - o.cost;
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        Figure[] f = new Figure[n];
        for (int i = 0; i < n; i++) {
            f[i] = new Figure(in.nextInt(), in.nextInt());
        }
        int t = in.nextInt();
        long[] p = new long[t];
        for (int i = 0; i < t; i++) {
            p[i] = in.nextLong();
        }
        Arrays.sort(f);
        long currentCount = 0;
        int currentMul = 0;
        long answer = 0;
        for (Figure e : f) {
            while (e.count > 0) {
                long left = currentMul == t ? Long.MAX_VALUE : p[currentMul] - currentCount;
                if (left <= 0) {
                    ++currentMul;
                    continue;
                }
                if (e.count >= left) {
                    answer += (long) e.cost * left * (currentMul + 1);
                    e.count -= left;
                    currentCount += left;
                    ++currentMul;
                } else {
                    answer += (long) e.cost * e.count * (currentMul + 1);
                    currentCount += e.count;
                    e.count = 0;
                }
            }
        }
        out.println(answer);
    }
}
