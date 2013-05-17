package lib.test.on2013_02.on2013_02_10_Facebook_HackerCup_Round_2.Cake;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class Cake {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        out.print("Case #" + testNumber + ": ");
        int[] a = in.readIntArray(n);
        long answer = 1;
        int lines = 0;
        for (int i = 0; i < n; i++) {
            answer += lines;
            for (int j = 0; j < a[i]; j++) {
                answer += lines;
                lines++;
            }
            lines++;
        }
        out.println(answer + n);
    }
}
