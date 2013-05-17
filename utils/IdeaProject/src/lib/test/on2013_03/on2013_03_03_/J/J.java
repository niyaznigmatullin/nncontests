package lib.test.on2013_03.on2013_03_03_.J;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class J {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int normalMen = 0;
        int madMen = 0;
        for (int man = 0; man < n; man++) {
            int type = in.nextInt();
            double answer;
            int seats = n - normalMen;
            if (type == 0) {
                answer = 1 - (double)madMen / seats;
                ++normalMen;
            } else {
                answer = 1. / seats;
                ++madMen;
            }
            if (man > 0) {
                out.print(' ');
            }
            out.print(answer);
        }
        out.println();
    }
}
