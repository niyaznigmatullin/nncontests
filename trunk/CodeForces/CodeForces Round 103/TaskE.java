package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.Arrays;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;


public class TaskE {

    static class Segment implements Comparable<Segment>{
        int a;
        int b;
        int num;

        Segment(int a, int b, int num) {
            this.a = a;
            this.b = b;
            this.num = num;
        }

        public int compareTo(Segment o) {
            return b - o.b;
        }
    }

	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        NavigableSet<Integer> set = new TreeSet<Integer>();
        for (int i = 0; i < n; i++) {
            set.add(i);
        }
        Segment[] a = new Segment[m];
        for (int i = 0; i < m; i++) {
            int r = in.nextInt() - 1;
            int c = in.nextInt() - 1;
            a[i] = new Segment(n - r - 1, c, i);
        }
        Arrays.sort(a);
        NavigableSet<Integer> answer = new TreeSet<Integer>();
        for (Segment e : a) {
            Integer next = set.ceiling(e.a);
            if (next == null || next > e.b) {
                continue;
            }
            answer.add(e.num);
            set.remove(next);
        }
        out.println(answer.size());
        for (int i : answer) {
            out.print(i + 1 + " ");
        }
	}
}
