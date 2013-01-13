package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        String s = in.nextLine();
        String t = in.nextLine();
        List<String> a = getIt(s);
        List<String> b = getIt(t);
        if (!a.equals(b)) {
            out.println("NO");
        } else {
            out.println("YES");
        }
    }

    static List<String> getIt(String s) {
        StringTokenizer st1 = new StringTokenizer(s, ".,:;!? \t");
        List<String> ret = new ArrayList<String>();
        while (st1.hasMoreTokens()) {
            ret.add(st1.nextToken().toLowerCase());
        }
        return ret;
    }
}
