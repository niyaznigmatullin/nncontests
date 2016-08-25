package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.*;

public class TaskD {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        Tube[] tubes = new Tube[n];
        for (int i = 0; i < n; i++) {
            tubes[i] = new Tube(in.nextInt(), in.nextInt(), in.nextInt() - 1, in.nextInt());
        }
        List<Tube>[] forReact = new List[m];
        for (int i = 0; i < m; i++) {
            forReact[i] = new ArrayList<>();
        }
        for (Tube e : tubes) {
            forReact[e.c].add(e);
        }
        Comparator<Tube> compByPercent = new Comparator<Tube>() {
            @Override
            public int compare(Tube o1, Tube o2) {
                return Integer.compare(o1.p, o2.p);
            }
        };
        for (int i = 0; i < m; i++) {
            Collections.sort(forReact[i], compByPercent);
        }
        double[] v = new double[m];
        for (int i = 0; i < m; i++) {
            v[i] = in.nextInt();
        }
        for (int r = 0; r < m; r++) {
            for (Tube z : forReact[r]) {
                double need = z.p * .01 * z.min;
                if (compare(need, v[r]) > 0) {
                    out.println("NO");
                    return;
                }
                v[r] -= need;
                z.put(r, need);
            }
            for (Tube z : forReact[r]) {
                double need = z.p * .01 * (z.max - z.min);
                if (need > v[r]) need = v[r];
                v[r] -= need;
                z.put(r, need);
            }
        }
        int cur = 0;
        for (Tube e : tubes) {
            double canHave = Math.max(0, e.min - e.good);
            while (cur < m && compare(canHave, 0) > 0) {
                if (compare(v[cur], 0) == 0) {
                    ++cur;
                    continue;
                }
                double u = Math.min(v[cur], canHave);
                canHave -= u;
                v[cur] -= u;
                e.put(cur, u);
            }
        }
        for (Tube e : tubes) {
            double canHave = Math.min(e.max, e.good / (0.01 * e.p)) - Math.max(e.min, e.good);
            while (cur < m && compare(canHave, 0) > 0) {
                if (compare(v[cur], 0) == 0) {
                    ++cur;
                    continue;
                }
                double u = Math.min(v[cur], canHave);
                canHave -= u;
                v[cur] -= u;
                e.put(cur, u);
            }
        }
        for (int i = 0; i < m; i++) {
            if (compare(v[i], 0) > 0) {
                out.println("NO");
                return;
            }
        }
        out.println("YES");
        for (Tube e : tubes) {
            e.check();
            out.print(e.inside.size());
            for (Map.Entry<Integer, Double> f : e.inside.entrySet()) {
                out.print(" " + (f.getKey() + 1) + " " + f.getValue());
            }
            out.println();
        }
    }

    static int compare(double a, double b) {
        return Math.abs(a - b) < 1e-7 ? 0 : a < b ? -1 : 1;
    }

    static class Tube {
        int min;
        int max;
        int c;
        int p;

        Map<Integer, Double> inside;
        double good;

        public Tube(int min, int max, int c, int p) {
            this.min = min;
            this.max = max;
            this.c = c;
            this.p = p;
            inside = new HashMap<>();
            good = 0;
        }

        void put(int type, double v) {
            if (type == c) {
                good += v;
            }
            Double q = inside.get(type);
            if (q == null) q = 0.0;
            q += v;
            inside.put(type, q);
        }

        void check() {
            double g = 0;
            double s = 0;
            for (Map.Entry<Integer, Double> e : inside.entrySet()) {
                if (e.getKey() == c) {
                    g += e.getValue();
                }
                s += e.getValue();
            }
            if (g / s < 0.01 * p) {
                throw new AssertionError();
            }
        }
    }
}
