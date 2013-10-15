package lib.test.on2013_03.on2013_03_10_Codeforces_Beta_Round__85__Div__1_Only_.A___Petya_and_Inequiations;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        long x = in.nextLong();
        int y = in.nextInt();
        y -= n - 1;
        x -= n - 1;
        if (y <= 0 || (long) y * y < x) {
            out.println(-1);
            return;
        }
        out.println(y);
        for (int i = 0; i < n - 1; i++) {
            out.println(1);
        }
    }
}
