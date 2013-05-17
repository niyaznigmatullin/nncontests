package lib.test.on2013_04.on2013_04_13_Russian_Code_Cup_2013_Qualification.TaskD;



import ru.ifmo.niyaz.geometry.Point3DDouble;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.DoubleComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskD {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        long r = in.nextInt();
        r *= r;
        int[] px = new int[n];
        int[] py = new int[n];
        int[] pz = new int[n];
        long[] vx = new long[n];
        long[] vy = new long[n];
        long[] vz = new long[n];
        for (int i = 0; i < n; i++) {
            px[i] = in.nextInt();
            py[i] = in.nextInt();
            pz[i] = in.nextInt();
            vx[i] = in.nextInt();
            vy[i] = in.nextInt();
            vz[i] = in.nextInt();
        }
        final int INF = (int) 5e7;
        List<Event> events = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int time = Integer.MIN_VALUE;
            {
                long left = 0;
                long right = 2 * INF;
                while (left < right - 4) {
                    long m1 = (left + left + right) / 3;
                    long m2 = (left + right + right) / 3;
                    long d1 = df(px[i], py[i], pz[i], vx[i], vy[i], vz[i], m1 - INF);
                    long d2 = df(px[i], py[i], pz[i], vx[i], vy[i], vz[i], m2 - INF);
                    if (d1 > d2) {
                        left = m1;
                    } else {
                        right = m2;
                    }
                }
                long best = Long.MAX_VALUE;
                for (long t = left; t <= right; t++) {
                    long cur = df(px[i], py[i], pz[i], vx[i], vy[i], vz[i], t - INF);
                    if (cur < best) {
                        time = (int) (t - INF);
                        best = cur;
                    }
                }
                if (best > r) {
                    continue;
                }
            }
            {
                long left = -INF;
                long right = time;
                while (left < right - 1) {
                    long mid = (left + right) >> 1;
                    if (df(px[i], py[i], pz[i], vx[i], vy[i], vz[i], mid) > r) {
                        left = mid;
                    } else {
                        right = mid;
                    }
                }
                events.add(new Event(right, -1, -1));
            }
            {
                long left = time;
                long right = INF;
                while (left < right - 1) {
                    long mid = (left + right) >> 1;
                    if (df(px[i], py[i], pz[i], vx[i], vy[i], vz[i], mid) > r) {
                        right = mid;
                    } else {
                        left = mid;
                    }
                }
                events.add(new Event(left, 1, -1));
            }
        }
        int m = in.nextInt();
        int[] answer = new int[m];
        for (int i = 0; i < m; i++) {
            events.add(new Event(in.nextInt(), 0, i));
        }
        Collections.sort(events);
        int cnt = 0;
        for (Event e : events) {
            if (e.type == -1) {
                ++cnt;
            }
            if (e.type == 1) {
                --cnt;
            }
            if (e.type == 0) {
                answer[e.id] = cnt;
            }
        }
        for (int i : answer) {
            out.println(i);
        }
    }

    static long df(int x1, int y1, int z1, long x2, long y2, long z2, long tt) {
        x2 = x2 * tt + x1;
        y2 = y2 * tt + y1;
        z2 = z2 * tt + z1;
        return x2 * x2 + y2 * y2 + z2 * z2;
    }

    static class Event implements Comparable<Event> {
        long x;
        int type;
        int id;

        Event(long x, int type, int id) {
            this.x = x;
            this.type = type;
            this.id = id;
        }

        @Override
        public int compareTo(Event o) {
            int c = Long.compare(x, o.x);
            if (c != 0) {
                return c;
            }
            return type - o.type;
        }
    }
}
