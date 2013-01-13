package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        String a = in.next();
        String b = in.next();
        String c = Integer.parseInt(a) + Integer.parseInt(b) + "";
        a = a.replaceAll("0", "");
        b = b.replaceAll("0", "");
        c = c.replaceAll("0", "");
        if (Integer.parseInt(a) + Integer.parseInt(b) != Integer.parseInt(c)) {
            out.println("NO");
        } else {
            out.println("YES");
        }
	}
}
