package lib.test.on2013_05.on2013_05_02_.ReverseRoot;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReverseRoot {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        List<Double> z = new ArrayList<>();
        while (true) {
            String s = in.next();
            if (s == null) {
                break;
            }
            z.add(Double.parseDouble(s));
        }
        Collections.reverse(z);
        for (double e : z) {
            out.println(Math.sqrt(e));
        }
    }
}
