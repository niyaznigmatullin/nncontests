package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        char[] c = in.next().toCharArray();
        char[] t = in.next().toCharArray();
        int pos = c.length - 1;
        while (pos >= 0 && c[pos] == 'z') {
            c[pos] = 'a';
            --pos;
        }
        if (pos < 0) {
            out.println("No such string");
        } else {
            c[pos]++;
            if (new String(c).compareTo(new String(t)) < 0) {
                out.println(c);
            } else {
                out.println("No such string");
            }
        }
    }
}
