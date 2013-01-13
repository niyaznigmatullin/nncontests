package lib.test.on2012_12.on2012_12_29_Yandex_Algorithm_2011_lt_br_gt_Qualification_2.A___Double_Cola;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt() - 1;
        final String[] ANSWER = {"Sheldon", "Leonard", "Penny", "Rajesh", "Howard"};
        for (int k = 1; ; k <<= 1) {
            if (n < 5 * k) {
                out.println(ANSWER[n / k]);
                return;
            } else {
                n -= 5 * k;
            }
        }
    }
}
