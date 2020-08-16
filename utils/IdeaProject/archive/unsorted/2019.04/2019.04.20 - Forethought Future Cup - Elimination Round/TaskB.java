package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        String s = in.next();
        int count = 0;
        StringBuilder t = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == 'a') count++; else t.append(c);
        }
        int n = s.length() - count;
        if (n % 2 != 0) {
            out.println(":(");
            return;
        }
        String z = t.toString();
        if (!z.substring(0, n / 2).equals(z.substring(n / 2)) || !z.substring(n / 2).equals(s.substring(s.length() - n / 2))) {
            out.println(":(");
            return;
        }
        out.println(s.substring(0, s.length() - n / 2));
    }
}
