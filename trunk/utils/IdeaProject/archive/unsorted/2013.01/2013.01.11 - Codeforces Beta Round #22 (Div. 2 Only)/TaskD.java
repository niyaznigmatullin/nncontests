package coding;

import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class TaskD {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        Segment[] a = new Segment[n];
        for (int i = 0; i < n; i++) {
            a[i] = new Segment(in.nextInt(), in.nextInt());
        }
        Arrays.sort(a, new Comparator<Segment>() {
            public int compare(Segment o1, Segment o2) {
                return o1.b - o2.b;
            }
        });
        int last = Integer.MIN_VALUE;
        List<Integer> answer = new ArrayList<Integer>();
        for (Segment e : a) {
            if (last < e.a) {
                answer.add(e.b);
                last = e.b;
            }
        }
        out.println(answer.size());
        out.printArray(ArrayUtils.toPrimitiveArrayInteger(answer));
    }

    static class Segment {
        int a;
        int b;

        Segment(int a, int b) {
            if (a > b) {
                int t = a;
                a = b;
                b = t;
            }
            this.a = a;
            this.b = b;
        }
    }
}
