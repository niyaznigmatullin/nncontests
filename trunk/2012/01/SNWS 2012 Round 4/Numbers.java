package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.Arrays;
import java.util.TreeSet;

public class Numbers {
     static class Element implements Comparable<Element> {
        int v;
        long d;

        public Element(int v, long d) {
            this.v = v;
            this.d = d;
        }

        public int compareTo(Element o) {
            if (d != o.d) {
                return d < o.d ? -1 : 1;
            }
            return v - o.v;
        }
    }
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int source = in.nextInt();
        int target = in.nextInt();
        int m = in.nextInt();
        final int n = 100001;
        int[] c = new int[m];
        for (int i = 0; i < m; i++) {
            c[i] = in.nextInt();
        }
        int[][] cost = new int[m][4];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < 4; j++) {
                cost[i][j] = in.nextInt();
            }
        }
        long[] d = new long[n];
        Arrays.fill(d, Long.MAX_VALUE);
        Element[] q = new Element[n];
        for (int i = 0; i < q.length; i++) {
            q[i] = new Element(i, Long.MAX_VALUE);
        }
        q[source].d = d[source] = 0;
        TreeSet<Element> queue = new TreeSet<Element>();
        queue.add(q[source]);
        while (!queue.isEmpty()) {
            Element el = queue.pollFirst();
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < 4; j++) {
                    int w = cost[i][j];
                    int newNumber = el.v;
                    if (j == 2) {
                        if ((long)newNumber * c[i] >= n) {
                            continue;
                        }
                        newNumber *= c[i];
                    } else if (j == 0) {
                        if (newNumber + c[i] >= n) {
                            continue;
                        }
                        newNumber += c[i];
                    } else if (j == 1) {
                        if (newNumber - c[i] < 0) {
                            continue;
                        }
                        newNumber -= c[i];
                    } else {
                        newNumber /= c[i];
                    }
                    if (d[newNumber] > d[el.v] + w) {
                        queue.remove(q[newNumber]);
                        d[newNumber] = q[newNumber].d = d[el.v] + w;
                        queue.add(q[newNumber]);
                    }
                }
            }
        }
        out.println(d[target]);
	}
}
