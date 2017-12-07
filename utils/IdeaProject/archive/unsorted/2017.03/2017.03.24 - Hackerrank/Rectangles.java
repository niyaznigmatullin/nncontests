package coding;

import ru.ifmo.niyaz.DataStructures.FenwickMultiSum;
import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.graphalgorithms.DinicGraph;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Rectangles {
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
        Event[] events = new Event[n * 2];
        for (int i = 0; i < n; i++) {
            int x1 = in.nextInt();
            int y1 = in.nextInt();
            int x2 = in.nextInt();
            int y2 = in.nextInt();
            events[2 * i] = new Event(x1, y1, y2, true);
            events[2 * i + 1] = new Event(x2, y1, y2, false);
        }
        Arrays.sort(events, new Comparator<Event>() {
            @Override
            public int compare(Event o1, Event o2) {
                if (o1.x != o2.x) return Integer.compare(o1.x, o2.x);
                if (o1.start == o2.start) return 0;
                return o1.start ? -1 : 1;
            }
        });
        final int MAXN = 333333;
        FenwickMultiSum f = new FenwickMultiSum(MAXN);
        List<int[]> edges = new ArrayList<>();
        for (int i = 0, cur = 0; cur < events.length; i++) {
            while (cur < events.length && events[cur].x == i && events[cur].start) {
                Event e = events[cur++];
                f.add(e.y1, e.y2 + 1, 1);
            }
            long sum = f.getSum(0, MAXN);
            long curSum = 0;
            int pos = 0;
            while (curSum < sum) {
                int l = pos;
                int r = MAXN;
                while (l < r - 1) {
                    int mid = (l + r) >> 1;
                    if (f.getSum(0, mid) == curSum) {
                        l = mid;
                    } else {
                        r = mid;
                    }
                }
                pos = l;
                curSum = f.getSum(0, r);
                edges.add(new int[]{i, pos});
            }
            while (cur < events.length && events[cur].x == i && !events[cur].start) {
                Event e = events[cur++];
                f.add(e.y1, e.y2 + 1, -1);
            }
        }
        int[] rows = new int[edges.size()];
        int[] cols = new int[edges.size()];
        for (int i = 0; i < edges.size(); i++) {
            rows[i] = edges.get(i)[0];
            cols[i] = edges.get(i)[1];
        }
        rows = ArrayUtils.sortAndUnique(rows);
        cols = ArrayUtils.sortAndUnique(cols);
        DinicGraph g = new DinicGraph(rows.length + cols.length + 2);
        int src = rows.length + cols.length;
        int tar = src + 1;
        for (int[] e : edges) {
            g.addEdge(Arrays.binarySearch(rows, e[0]), Arrays.binarySearch(cols, e[1]) + rows.length, 1);
        }
        for (int i = 0; i < rows.length; i++) {
            g.addEdge(src, i, 1);
        }
        for (int i = 0; i < cols.length; i++) {
            g.addEdge(i + rows.length, tar, 1);
        }
        int ans = (int) g.getMaxFlow(src, tar);
        if (ans == rows.length) {
            out.println(ans);
            out.println(rows.length);
            out.printArray(rows);
            out.println(0);
            out.println();
            return;
        }
        List<Integer> ansRows = new ArrayList<>();
        List<Integer> ansCols = new ArrayList<>();
        for (int i = 0; i < cols.length; i++) {
            if (g.d[i + rows.length] != Integer.MAX_VALUE) {
                ansCols.add(cols[i]);
            }
        }
        for (int i = 0; i < rows.length; i++) {
            if (g.d[i] == Integer.MAX_VALUE) {
                ansRows.add(rows[i]);
            }
        }
        out.println(ans);
        out.println(ansRows.size());
        out.printArray(ArrayUtils.toPrimitiveArrayInteger(ansRows));
        out.println(ansCols.size());
        out.printArray(ArrayUtils.toPrimitiveArrayInteger(ansCols));
    }
}
