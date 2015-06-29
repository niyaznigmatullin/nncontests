package coding;

import ru.ifmo.niyaz.DataStructures.Fenwick;
import ru.ifmo.niyaz.DataStructures.FenwickLong;
import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = in.nextInt();
            y[i] = in.nextInt();
        }
        int[] ys = ArrayUtils.sortAndUnique(y);
        for (int i = 0; i < n; i++) {
            y[i] = Arrays.binarySearch(ys, y[i]);
        }
        int[] left = new int[ys.length - 1];
        int[] right = new int[ys.length - 1];
        Arrays.fill(left, Integer.MAX_VALUE);
        Arrays.fill(right, Integer.MIN_VALUE);
        for (int i = 0; i < n; i++) {
            int j = (i + 1) % n;
            if (x[i] == x[j]) {
                for (int e = Math.min(y[i], y[j]); e < Math.max(y[i], y[j]); e++) {
                    right[e] = Math.max(right[e], x[i]);
                    left[e] = Math.min(left[e], x[i]);
                }
            }
        }
        List<Integer> xsList = new ArrayList<>();
        for (int i = 0; i < ys.length - 1; i++) {
            xsList.add(left[i]);
            xsList.add(right[i]);
            xsList.add((left[i] + right[i]) / 2);
            xsList.add((left[i] + right[i]) / 2 + 1);
        }
        int[] xs = ArrayUtils.sortAndUnique(ArrayUtils.toPrimitiveArrayInteger(xsList));
        List<Event>[] events = new List[xs.length];
        for (int i = 0; i < events.length; i++) events[i] = new ArrayList<>();
        for (int i = 0; i < ys.length - 1; i++) {
            events[Arrays.binarySearch(xs, left[i])].add(new Event(0, i));
            events[Arrays.binarySearch(xs, right[i])].add(new Event(2, i));
            events[Arrays.binarySearch(xs, (left[i] + right[i]) / 2)].add(new Event(1, i));
        }
        long cf = 0;
        long df = 0;
        long ans = 0;
        for (int cx = 0; cx < xs.length; cx++) {
            for (Event e : events[cx]) {
                if (e.type == 0) {
                    cf += ys[e.id + 1] - ys[e.id];
                    df -= (long) (ys[e.id + 1] - ys[e.id]) * left[e.id];
                }
            }
            ans = Math.max(ans, cf * xs[cx] + df);
            for (Event e : events[cx]) {
                if (e.type == 1) {
                    cf -= 2L * (ys[e.id + 1] - ys[e.id]);
                    df += (long) (ys[e.id + 1] - ys[e.id]) * (left[e.id] + right[e.id]);
                } else if (e.type == 2) {
                    cf += (ys[e.id + 1] - ys[e.id]);
                    df -= right[e.id] * (long) (ys[e.id + 1] - ys[e.id]);
                }
            }
        }
        long all = 0;
        for (int i = 0; i + 1 < ys.length; i++) {
            all += (long) (ys[i + 1] - ys[i]) * (right[i] - left[i]);
        }
        out.println(all - ans);
    }

    static class Event {
        int type;
        int id;

        public Event(int type, int id) {
            this.type = type;
            this.id = id;
        }
    }
}
