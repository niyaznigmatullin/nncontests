package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskC {
    static char back(char c) {
        return c == 'N' ? 'S' : (c == 'S' ? 'N' : (c == 'E' ? 'W' : 'E'));
    }
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        in.nextInt();
        String s = in.next();
        String tt = in.next();
        StringBuilder tb = new StringBuilder();
        for (char c : tt.toCharArray()) {
            tb.append(back(c));
        }
        tb.reverse();
        String t = tb.toString();
        t = t + "$" + s;
        int[] p = new int[t.length()];
        p[0] = -1;
        int k = -1;
        for (int i = 1; i < t.length(); i++) {
            while (k > -1 && t.charAt(k + 1) != t.charAt(i)) k = p[k];
            if (t.charAt(k + 1) == t.charAt(i)) k++;
            p[i] = k;
        }
        if (k > -1) {
            out.println("NO");
        } else {
            out.println("YES");
        }
    }
}
