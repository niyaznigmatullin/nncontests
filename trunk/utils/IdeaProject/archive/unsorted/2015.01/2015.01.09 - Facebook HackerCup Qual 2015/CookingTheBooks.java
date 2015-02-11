package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class CookingTheBooks {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        String s = in.next();
        String minimum = s;
        String maximum = s;
        char[] tmp = s.toCharArray();
        for (int i = 0; i < tmp.length; i++) {
            for (int j = i + 1; j < tmp.length; j++) {
                {
                    char t = tmp[i];
                    tmp[i] = tmp[j];
                    tmp[j] = t;
                }
                String cur = new String(tmp);
                if (tmp[0] != '0') {
                    if (cur.compareTo(minimum) < 0) minimum = cur;
                    if (cur.compareTo(maximum) > 0) maximum = cur;
                }
                {
                    char t = tmp[i];
                    tmp[i] = tmp[j];
                    tmp[j] = t;
                }
            }
        }
        out.println("Case #" + testNumber + ": " + minimum + " " + maximum);
    }
}
