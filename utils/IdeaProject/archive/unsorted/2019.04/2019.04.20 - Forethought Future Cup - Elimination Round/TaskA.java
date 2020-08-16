package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        String s = in.next();
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == 'a') count++;
        }
        if (count * 2 > s.length()) {
            out.println(s.length());
        } else {
            out.println(count * 2 - 1);
        }
    }
}
