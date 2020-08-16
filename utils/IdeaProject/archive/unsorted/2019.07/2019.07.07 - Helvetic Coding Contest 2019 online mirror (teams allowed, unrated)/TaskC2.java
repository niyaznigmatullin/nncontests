package coding;

import ru.ifmo.niyaz.DataStructures.MultiSegmentTree;
import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;
import java.util.Comparator;

public class TaskC2 {
    static class Event {
        int x;
        int y1;
        int y2;
        boolean start;

        public Event(int x, int y1, int y2, boolean start) {
            this.x = x;
            this.y1 = y1;
            this.y2 = y2;
            this.start = start;
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int r = in.nextInt();
        Event[] events = new Event[2 * n];
        int[] ys = new int[2 * n];
        for (int i = 0; i < n; i++) {
            int x0 = in.nextInt();
            int y0 = in.nextInt();
            int x = x0 + y0;
            int y = x0 - y0;
            ys[2 * i] = y;
            ys[2 * i + 1] = y + 2 * r;
            events[2 * i] = new Event(x, y, y + 2 * r, true);
            events[2 * i + 1] = new Event(x + 2 * r, y, y + 2 * r, false);
        }
        ys = ArrayUtils.sortAndUnique(ys);
        Arrays.sort(events, new Comparator<Event>() {
            @Override
            public int compare(Event o1, Event o2) {
                int c = Integer.compare(o1.x, o2.x);
                if (c != 0) return c;
                if (o1.start == o2.start) return 0;
                if (o1.start) return -1;
                return 1;
            }
        });
        MultiSegmentTree tree = new MultiSegmentTree(ys.length);
        int ans = 0;
        for (Event e : events) {
            int from = Arrays.binarySearch(ys, e.y1);
            int to = Arrays.binarySearch(ys, e.y2);
            if (e.start) {
                tree.add(from, to + 1, 1);
                ans = (int) Math.max(ans, tree.getMax(0, ys.length));
            } else {
                tree.add(from, to + 1, -1);
            }
        }
        out.println(ans);
    }
}
