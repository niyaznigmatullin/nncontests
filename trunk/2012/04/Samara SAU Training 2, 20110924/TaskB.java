package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.ArrayList;
import java.util.List;

public class TaskB {
    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static void addAll(List<Point> all, int q) {
        if (q == 1) {
            all.add(new Point(0, 1));
            return;
        }
        if ((q & 1) == 0) {
            for (int i = 1; i <= q; i++) {
                all.add(new Point(q - 1, i));
            }
            for (int i = q - 2; i >= -q + 2; i--) {
                all.add(new Point(i, q));
            }
            for (int i = q; i >= 1; i--) {
                all.add(new Point(-q + 1, i));
            }
        } else {
            for (int i = 1; i <= q; i++) {
                all.add(new Point(-q + 1, i));
            }
            for (int i = -q + 2; i <= q - 2; i++) {
                all.add(new Point(i, q));
            }
            for (int i = q; i >= 1; i--) {
                all.add(new Point(q - 1, i));
            }
        }
    }

	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int q = 1;
        List<Point> all = new ArrayList<Point>();
        while (all.size() < 1000) {
            addAll(all, q);
            ++q;
        }
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            Point ans = all.get(in.nextInt() - 1);
            out.println(ans.x + "/" + ans.y);
        }
	}
}
