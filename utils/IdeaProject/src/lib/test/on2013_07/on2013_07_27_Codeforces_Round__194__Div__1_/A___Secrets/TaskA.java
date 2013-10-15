package lib.test.on2013_07.on2013_07_27_Codeforces_Round__194__Div__1_.A___Secrets;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        long n = in.nextLong();
        while (n % 3 == 0) {
            n /= 3;
        }
        out.println((n + 2) / 3);
    }
}
