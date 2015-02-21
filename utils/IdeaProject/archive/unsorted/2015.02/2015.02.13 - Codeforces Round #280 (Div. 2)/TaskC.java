package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;
import java.util.Comparator;

public class TaskC {
    static class Exam {
        int a;
        int b;

        Exam(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int r = in.nextInt();
        int avg = in.nextInt();
        Exam[] a = new Exam[n];
        for (int i = 0; i < n; i++) {
            a[i] = new Exam(in.nextInt(), in.nextInt());
        }
        Arrays.sort(a, new Comparator<Exam>() {
            @Override
            public int compare(Exam o1, Exam o2) {
                return Integer.compare(o1.b, o2.b);
            }
        });
        long sum = (long) avg * n;
        long have = 0;
        for (Exam e : a) {
            have += e.a;
        }
        if (have >= sum) {
            out.println(0);
            return;
        }
        long need = sum - have;
        long ans = 0;
        for (Exam e : a) {
            long minimal = Math.min(r - e.a, need);
            ans += minimal * e.b;
            need -= minimal;
        }
        out.println(ans);
    }
}
