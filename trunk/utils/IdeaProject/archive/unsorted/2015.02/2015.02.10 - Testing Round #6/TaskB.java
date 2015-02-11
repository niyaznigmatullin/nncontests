package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;
import java.util.Comparator;

public class TaskB {
    static class Segment {
        int start;
        int end;
        int id;

        Segment(int start, int end, int id) {
            this.start = start;
            this.end = end;
            this.id = id;
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        in.nextInt();
        int m = in.nextInt();
        Segment[] a = new Segment[m];
        for (int i = 0; i < m; i++) {
            int start = in.nextInt();
            int end = in.nextInt() + start - 1;
            a[i] = new Segment(start, end, i);
        }
        Arrays.sort(a, new Comparator<Segment>() {
            @Override
            public int compare(Segment o1, Segment o2) {
                if (o1.start != o2.start) return Integer.compare(o1.start, o2.start);
                return Integer.compare(o2.end, o1.end);
            }
        });
        int last = Integer.MIN_VALUE;
        boolean[] get = new boolean[m];
        Segment curMax = null;
        boolean first = true;
        for (Segment e : a) {
            if (first) {
                first = false;
                curMax = e;
                last = e.end;
                get[e.id] = true;
                continue;
            }
            if (e.start <= last + 1) {
                if (curMax.end < e.end) {
                    curMax = e;
                }
                continue;
            }
            last = curMax.end;
            get[curMax.id] = true;
            if (e.end > curMax.end) {
                curMax = e;
            }
        }
        if (curMax.end >= last) {
            get[curMax.id] = true;
        }
        int[] ans = new int[m];
        int ac = 0;
        for (int i = 0; i < m; i++) {
            if (!get[i]) {
                ans[ac++] = i + 1;
            }
        }
        out.println(ac);
        out.printArray(Arrays.copyOf(ans, ac));
    }
}
