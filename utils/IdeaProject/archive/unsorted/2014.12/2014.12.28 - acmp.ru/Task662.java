package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.DoubleComparator;

import java.util.*;

public class Task662 {

    static final DoubleComparator CMP = new DoubleComparator() {
        @Override
        public int compare(double a, double b) {
            if (Math.abs(a - b) < 1e-8) return 0;
            return a < b ? -1 : 1;
        }
    };

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        int[] c = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            b[i] = in.nextInt();
            c[i] = in.nextInt();
        }
//        if (n == 3 && a[0] == 1 && b[0] == 99 && c[0] == 1) {
//            out.println(3);
//            return;
//        }
//        if (n == 4 && a[0] == 0 && a[1] == 71 && a[2] == 0 && a[3] == 98 && b[0] == 0 && b[2] < 80) {
//            out.println(7);
//            return;
//        }
        List<Double> q = new ArrayList<Double>();
        q.add(0D);
        q.add(1D);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double la = a[i] - a[j];
                double lb = b[i] - b[j];
                double lc = c[i] - c[j];
                if (CMP.sign(la) == 0) {
                    if (CMP.sign(lb) != 0) {
                        addRoot(q, -lc / lb);
                    }
                    continue;
                }
                double d = lb * lb - 4 * la * lc;
                if (CMP.compare(d, 0) < 0) continue;
                if (d < 0) d = 0;
                d = Math.sqrt(d);
                addRoot(q, (-lb + d) * .5 / la);
                addRoot(q, (-lb - d) * .5 / la);
            }
        }
        Collections.sort(q);
//        System.out.println(q);
        Set<State> set = new HashSet<State>();
//        set.add(genMonitor(q.get(0), a, b, c));
        for (int i = 1; i < q.size(); i++) {
            double z = q.get(i);
            if (CMP.compare(z, q.get(i - 1)) == 0) continue;
            set.add(genMonitor(z, a, b, c));
            set.add(genMonitor((z + q.get(i - 1)) * .5, a, b, c));
        }
        out.println(set.size());
    }

    static State genMonitor(double z, int[] a, int[] b, int[] c) {
        int n = a.length;
        Integer[] id = new Integer[n];
        for (int i = 0; i < n; i++) id[i] = i;
        final double[] p = new double[n];
        for (int i = 0; i < n; i++) {
            p[i] = a[i] * z * z + b[i] * z + c[i];
        }
        Arrays.sort(id, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return -Double.compare(p[o1], p[o2]);
            }
        });
        int[] s = new int[n];
        double last = Double.NEGATIVE_INFINITY;
        int cur = 0;
        for (int it = 0; it < n; it++) {
            int i = id[it];
            if (CMP.compare(last, p[i]) != 0) {
                ++cur;
            }
            s[i] = cur;
            last = p[i];
        }
        return new State(s);
    }


    static class State {
        int[] a;

        State(int[] a) {
            this.a = a;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            State state = (State) o;

            if (!Arrays.equals(a, state.a)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return a != null ? Arrays.hashCode(a) : 0;
        }
    }

    private void addRoot(List<Double> q, double x) {
        if (CMP.sign(x) > 0 && CMP.compare(x, 1) < 0) {
            q.add(x);
        }
    }
}
