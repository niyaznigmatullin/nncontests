package lib.test.on2013_08.on2013_08_26_.Control;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.*;

public class Control {

    static class Point {
        int x;
        int y;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Point)) return false;

            Point point = (Point) o;

            if (x != point.x) return false;
            if (y != point.y) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }

        Point(int x, int y) {
            this.x = x;
            this.y = y;

        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        Set<Point> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            int c = in.nextInt() + 1;
            for (int dx = -c; dx <= c; dx++) {
                for (int dy = -c; dy <= c; dy++) {
                    if (Math.abs(dx) + Math.abs(dy) <= c + 1) {
                        set.add(new Point(x + dx, y + dy));
                    }
                }
            }
        }
        List<Point> cities = new ArrayList<>(set);
        Queue<Point> q = new ArrayDeque<>();
        for (Point e : cities) {
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    Point f = new Point(e.x + dx, e.y + dy);
                    if (set.contains(f)) {
                        continue;
                    }
                    if (hanging(set, f)) {
                        set.add(f);
                        q.add(f);
                    }
                }
            }
        }
        while (!q.isEmpty()) {
            Point e = q.poll();
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    Point f = new Point(e.x + dx, e.y + dy);
                    if (set.contains(f)) continue;
                    if (hanging(set, f)) {
                        set.add(f);
                        q.add(f);
                    }
                }
            }
        }
        out.println(set.size());
    }

    private boolean hanging(Set<Point> set, Point f) {
        boolean found = false;
        loop1:
        for (int x1 = -1; x1 <= 0; x1++) {
            for (int y1 = -1; y1 <= 0; y1++) {
                boolean ok = true;
                loop2:
                for (int x2 = 0; x2 <= 1; x2++) {
                    for (int y2 = 0; y2 <= 1; y2++) {
                        if (set.contains(new Point(f.x + x1 + x2, f.y + y1 + y2))) {
                            ok = false;
                            break;
                        }
                    }
                }
                if (ok) {
                    found = true;
                    break loop1;
                }
            }
        }
        return !found;
    }
}
