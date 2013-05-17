package lib.test.on2013_04.on2013_04_19_Codeforces_Round__180__Div__1_.B___Fish_Weight;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.*;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        in.next();
        HashMap<Integer, Integer> z = new HashMap<>();
        for (int i = 0; i < n; i++) {
            add(z, in.nextInt(), 1);
        }
        for (int i = 0; i < m; i++) {
            add(z, in.nextInt(), -1);
        }
        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        for (int e : z.keySet()) {
            int d = z.get(e);
            if (d < 0) {
                d = -d;
                for (int i = 0; i < d; i++) {
                    b.add(e);
                }
            } else {
                for (int i = 0; i < d; i++) {
                    a.add(e);
                }
            }
        }
        Collections.sort(a);
        Collections.sort(b);
        if (a.size() > b.size()) {
            out.println("YES");
            return;
        }
        for (int i = a.size() - 1, j = b.size() - 1; i >= 0; i--, j--) {
            if (a.get(i) > b.get(j)) {
                out.println("YES");
                return;
            }
        }
        out.println("NO");
    }

    static void add(Map<Integer, Integer> z, int x, int d) {
        Integer e = z.get(x);
        if (e == null) {
            e = 0;
        }
        e += d;
        if (e == 0) {
            z.remove(x);
        } else {
            z.put(x, e);
        }
    }
}
