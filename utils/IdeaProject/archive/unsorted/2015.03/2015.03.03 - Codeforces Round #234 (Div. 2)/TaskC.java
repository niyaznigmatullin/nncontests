package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int a = in.nextInt() & 3;
        int b = in.nextInt() & 1;
        int c = in.nextInt() & 3;
        int p = in.nextInt();
        for (int test = 0; test < p; test++) {
            int x = in.nextInt();
            int y = in.nextInt();
            int s = n;
            int t = m;
            for (int i = 0; i < a; i++) {
                int nx = y;
                int ny = s - x + 1;
                int tmp = s;
                s = t;
                t = tmp;
                x = nx;
                y = ny;
            }
            for (int i = 0; i < b; i++) {
                int nx = x;
                int ny = t - y + 1;
                x = nx;
                y = ny;
            }
            for (int i = 0; i < c; i++) {
                int nx = t - y + 1;
                int ny = x;
                int tmp = s;
                s = t;
                t = tmp;
                x = nx;
                y = ny;
            }
            out.println(x + " " + y);
        }
    }
}
