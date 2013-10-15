package lib.test.on2013_09.on2013_09_14_Codeforces_Round__200__Div__1_.A___Rational_Resistance;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.Rational;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        Map<Rational, Integer> map = new HashMap<>();
        map.put(new Rational(1), 1);
        for (int i = 0; i < 5; i++) {
            Map<Rational, Integer> nMap = new HashMap<>(map);
            for (Rational a : map.keySet()) {
                for (Rational b : map.keySet()) {
                    {
                        Rational c = a.add(b);
                        Integer g = nMap.get(c);
                        int cur = map.get(a) + map.get(b);
                        if (g == null || g > cur) {
                            g = cur;
                        }
                        nMap.put(c, g);
                        if (c.equals(new Rational(11, 15))) {
                            System.out.println(a + " " + b + " " + cur);
                        }
                    }
                    {
                        Rational c = Rational.ONE.divide(a).add(Rational.ONE.divide(b));
                        c = Rational.ONE.divide(c);
                        Integer g = nMap.get(c);
                        int cur = map.get(a) + map.get(b);
                        if (g == null || g > cur) {
                            g = cur;
                        }
                        nMap.put(c, g);
                        if (c.equals(new Rational(11, 15))) {
                            System.out.println(a + " " + b + " " + cur);
                        }
                    }
                }
            }
            map = nMap;
            System.err.println(map);
        }
    }
//        long a = in.nextLong();
//        long b = in.nextLong();
//        if (a < b) {
//            long t = a;
//            a = b;
//            b = t;
//        }
//        long ans = 0;
//        while (b > 0) {
//            ans += a / b;
//            long t = a % b;
//            a = b;
//            b = t;
//        }
//        out.println(ans);
}
