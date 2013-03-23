package coding;

import ru.ifmo.niyaz.DataStructures.FenwickRev;
import ru.ifmo.niyaz.DataStructures.MultiSegmentTree;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.*;

public class TaskD {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        Event[] events = new Event[2 * m];
        for (int i = 0; i < m; i++) {
            int l = in.nextInt();
            int r = in.nextInt();
            int t = in.nextInt();
            events[2 * i] = new Event(l, true, t);
            events[2 * i + 1] = new Event(r, false, t);
        }
        NavigableMap<Integer, Integer> map = new TreeMap<>();
        Arrays.sort(events, new Comparator<Event>() {
            @Override
            public int compare(Event o1, Event o2) {
                return o1.x - o2.x;
            }
        });
        int[] q = in.readIntArray(n);
        int last = 0;
        FenwickRev f1 = new FenwickRev(n);
        FenwickRev f2 = new FenwickRev(n);
        for (Event e : events) {
            if (!map.isEmpty() && last < e.x) {
                int time = map.firstKey();
                int left = last;
                int right = e.x;
                int firstQ;
                int lastQ;
                {
                    int l = -1;
                    int r = n;
                    while (l < r - 1) {
                        int mid = l + r >> 1;
                        if (q[mid] >= time - right) {
                            r = mid;
                        } else {
                            l = mid;
                        }
                    }
                    firstQ = r;
                }
                {
                    int l = -1;
                    int r = n;
                    while (l < r - 1) {
                        int mid = l + r >> 1;
                        if (q[mid] > time - left) {
                            r = mid;
                        } else {
                            l = mid;
                        }
                    }
                    lastQ = r;
                }
                f1.add(firstQ, lastQ, 1);
                f2.add(firstQ, lastQ, -(time - right));
                f2.add(lastQ, n, right - left);
            }
            if (e.start) {
                add(map, e.time);
            } else {
                remove(map, e.time);
            }
            last = e.x;
        }
        for (int i = 0; i < n; i++) {
            int ans = f1.getElement(i) * q[i] + f2.getElement(i);
            out.println(ans);
        }
    }

    static void add(Map<Integer, Integer> map, int x) {
        Integer z = map.get(x);
        if (z == null) {
            z = 0;
        }
        map.put(x, z + 1);
    }

    static void remove(Map<Integer, Integer> map, int x) {
        Integer z = map.get(x);
        if (z == 1) {
            map.remove(x);
        } else {
            map.put(x, z - 1);
        }
    }

    static class Event {
        int x;
        boolean start;
        int time;

        Event(int x, boolean start, int time) {
            this.x = x;
            this.start = start;
            this.time = time;
        }
    }
}
