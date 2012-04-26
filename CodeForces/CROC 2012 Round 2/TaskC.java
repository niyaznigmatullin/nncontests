package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

public class TaskC {

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        in.nextInt();
        in.nextInt();
        int x = in.nextInt();
        int y = in.nextInt();
        x = Math.abs(x - in.nextInt()) + 1;
        y = Math.abs(y - in.nextInt()) + 1;
        if (Math.min(x, y) <= 4 && Math.max(x, y) <= 5) {
            out.println("First");
        } else {
            out.println("Second");
        }
    }
}
