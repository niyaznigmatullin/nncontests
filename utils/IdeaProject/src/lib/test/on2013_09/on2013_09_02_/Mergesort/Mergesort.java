package lib.test.on2013_09.on2013_09_02_.Mergesort;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.HashMap;

public class Mergesort {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int t = in.nextInt();
        map = new HashMap<>();
        for (int i = 0; i < t; i++) {
            int n = in.nextInt();
            out.println(get(n));
        }
    }

    static HashMap<Integer, Double> map;

    static double get(int n) {
        if (n == 1) return 0;
        if (map.containsKey(n)) return map.get(n);
        int m = n >> 1;
        double ret = get(m) + get(n - m);
        ret = ret + n - getIt(n);
        map.put(n, ret);
        return ret;
    }

    static double getIt(int n) {
        if ((n & 1) == 1) ++n;
        return 1. * n / (n / 2 + 1);
    }
}
