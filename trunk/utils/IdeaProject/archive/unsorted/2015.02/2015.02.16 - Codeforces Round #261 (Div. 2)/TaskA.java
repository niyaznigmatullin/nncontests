package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int x1 = in.nextInt();
        int y1 = in.nextInt();
        int x2 = in.nextInt();
        int y2 = in.nextInt();
        if (x1 == x2) {
            int nx = (x1 + Math.abs(y1 - y2));
            out.println(nx + " " + y1 + " " + nx + " " + y2);
        } else if (y1 == y2) {
            int ny = y1 + Math.abs(x1 - x2);
            out.println(x1 + " " + ny + " " + x2 + " " + ny);
        } else if (Math.abs(x1 - x2) == Math.abs(y1 - y2)) {
            out.println(x1 + " " + y2 + " " + x2 + " " + y1);
        } else {
            out.println(-1);
        }
    }
}
