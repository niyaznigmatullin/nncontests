package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.HashSet;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        HashSet<String> s = new HashSet<>();
        for (int i =0; i < n; i++) {
            String e = in.next();
            if (!s.add(e)) {
                out.println("YES");
            } else {
                out.println("NO");
            }
        }
    }
}
