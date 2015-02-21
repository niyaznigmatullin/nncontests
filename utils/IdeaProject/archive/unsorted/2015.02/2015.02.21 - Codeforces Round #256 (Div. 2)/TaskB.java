package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        char[] c = in.next().toCharArray();
        char[] t = in.next().toCharArray();
        boolean ok1 = isSub(t, c);
        Arrays.sort(c);
        Arrays.sort(t);
        boolean ok2 = Arrays.equals(c, t);
        boolean ok3 = isSub(t, c);
        if (!ok3) {
            out.println("need tree");
        } else if (!ok1 && !ok2) {
            out.println("both");
        } else if (ok1) {
            out.println("automaton");
        } else {
            out.println("array");
        }
    }

    static boolean isSub(char[] c, char[] s) {
        int cur = 0;
        for (char e : s) {
            if (cur < c.length && c[cur] == e) ++cur;
        }
        return cur == c.length;
    }
}
