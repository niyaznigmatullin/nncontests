package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.*;

public class TaskG {

    static class Segment {
        int left;
        int right;
        int x;

        Segment(int left, int right, int x) {
            this.left = left;
            this.right = right;
            this.x = x;
        }

        @Override
        public String toString() {
            return "Segment{" +
                    "left=" + left +
                    ", right=" + right +
                    ", x=" + x +
                    '}';
        }

        Segment(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = in.nextInt();
            y[i] = in.nextInt();
        }
        NavigableSet<Segment> set = new TreeSet<Segment>(new Comparator<Segment>() {
            public int compare(Segment o1, Segment o2) {
                return o1.left - o2.left;
            }
        });
        List<Segment> events = new ArrayList<Segment>();
        for (int i = 0; i < n; i++) {
            int j = (i + 1) % n;
            if (x[i] == x[j]) {
                events.add(new Segment(Math.min(y[i], y[j]), Math.max(y[i], y[j]), x[i]));
            }
        }
        Collections.sort(events, new Comparator<Segment>() {
            public int compare(Segment o1, Segment o2) {
                return o1.x - o2.x;
            }
        });
        int m = in.nextInt();
        Query[] q = new Query[m];
        for (int i = 0; i < m; i++) {
            q[i] = new Query(in.nextDouble(), in.nextDouble());
        }
        Query[] sorted = q.clone();
        Arrays.sort(sorted, new Comparator<Query>() {
            public int compare(Query o1, Query o2) {
                return Double.compare(o1.x, o2.x);
            }
        });
        int currentQuery = 0;
        int answer = 0;
        for (Segment e : events) {
            while (currentQuery < m && sorted[currentQuery].x < e.x) {
                Query f = sorted[currentQuery];
                int curY = (int) Math.round(Math.floor(f.y + 1e-1));
                Segment z = new Segment(curY, curY);
                Segment left = set.floor(z);
                if (left != null && left.left <= f.y && f.y <= left.right) {
                    ++answer;
                }
                ++currentQuery;
            }
            Segment s1 = set.floor(e);
            Segment s2 = set.ceiling(e);
            if (removeIt(set, e, s1) ||
                    removeIt(set, e, s2)) {
                continue;
            }
            if (s1 != null && s1.right == e.left) {
                set.remove(s1);
                e.left = s1.left;
            }
            if (s2 != null && s2.left == e.right) {
                set.remove(s2);
                e.right = s2.right;
            }
            set.add(e);
        }
        out.println(answer);
    }

    private boolean removeIt(NavigableSet<Segment> set, Segment e, Segment s1) {
        if (s1 != null && s1.left <= e.left && e.right <= s1.right) {
            set.remove(s1);
            Segment a = new Segment(s1.left, e.left);
            Segment b = new Segment(e.right, s1.right);
            if (a.left != a.right) {
                set.add(a);
            }
            if (b.left != b.right) {
                set.add(b);
            }
            return true;
        }
        return false;
    }

    static class Query {
        double x;
        double y;

        Query(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }
}
