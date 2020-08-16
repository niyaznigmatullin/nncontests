package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class ForegoneSolution {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        String s = in.next();
        char[] c = new char[s.length()];
        char[] d = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '4') {
                c[i] = '3';
                d[i] = '1';
            } else {
                c[i] = s.charAt(i);
                d[i] = '0';
            }
        }
        String ans1 = new String(c);
        int i = 0;
        while (d[i] == '0') {
            ++i;
        }
        String ans2 = new String(d).substring(i);
        out.println("Case #" + testNumber + ": " + ans1 + " " + ans2);
    }
}
