package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.*;

public class TaskA {

    static class Vest implements Comparable<Vest> {
        int x;
        int id;

        Vest(int x, int id) {
            this.x = x;
            this.id = id;
        }


        public int compareTo(Vest o) {
            if (x != o.x) {
                return x < o.x ? -1 : 1;
            }
            return id - o.id;
        }
    }

    static class Man {
        int left;
        int right;
        int id;

        Man(int left, int right, int id) {
            this.left = left;
            this.right = right;
            this.id = id;
        }
    }

	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int x = in.nextInt();
        int y = in.nextInt();
        Man[] a = new Man[n];
        for (int i = 0; i < n; i++) {
            int c = in.nextInt();
            a[i] = new Man(c - x, c + y, i);
        }
        NavigableSet<Vest> set = new TreeSet<Vest>();
        for (int i = 0; i < m; i++) {
            int c = in.nextInt();
            set.add(new Vest(c, i));
        }
        Arrays.sort(a, new Comparator<Man>() {
            public int compare(Man o1, Man o2) {
                return o1.left < o2.left ? -1 : o1.left > o2.left ? 1 : 0;
            }
        });
        List<String> answer = new ArrayList<String>();
        Vest tmp = new Vest(0, -1);
        for (Man e : a) {
            tmp.x = e.left;
            Vest cur = set.ceiling(tmp);
            if (cur == null || cur.x > e.right) {
                continue;
            }
            set.remove(cur);
            answer.add(e.id + 1 + " " + (cur.id + 1));
        }
        out.println(answer.size());
        for (String e : answer) {
            out.println(e);
        }
	}
}
