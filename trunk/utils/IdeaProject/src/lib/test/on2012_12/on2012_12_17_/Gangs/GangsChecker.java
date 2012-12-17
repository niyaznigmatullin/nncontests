package lib.test.on2012_12.on2012_12_17_.Gangs;



import net.egork.chelper.checkers.Checker;
import net.egork.chelper.tester.StringInputStream;
import net.egork.chelper.tester.Verdict;
import ru.ifmo.niyaz.io.FastScanner;

/**
 * Created with IntelliJ IDEA.
 * User: niyaz.nigmatullin
 * Date: 17.12.12
 * Time: 15:50
 * To change this template use File | Settings | File Templates.
 */
public class GangsChecker implements Checker {

    public GangsChecker(String s) {

    }

    public Verdict check(String input, String expectedOutput, String actualOutput) {
        FastScanner inf = new FastScanner(new StringInputStream(input));
        FastScanner ouf = new FastScanner(new StringInputStream(actualOutput));
        FastScanner anf = new FastScanner(new StringInputStream(expectedOutput));
        int all = inf.nextInt();
        int n = inf.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = inf.nextInt();
        }
        String pa = ouf.next();
        String ja = anf.next();
        if (pa.equals("NO")) {
            return Verdict.OK;
        }
        if (ja.equals("YES") && pa.equals("NO")) {
            return Verdict.WA;
        }
        if (ja.equals("NO") && pa.equals("NO")) {
            return Verdict.OK;
        }
        int left = ouf.nextInt();
        int count = 0;
        int gang = -1;
        for (int i = 0; i < all; i++) {
            int x = ouf.nextInt();
            if (a[x - 1] == 0) {
                return Verdict.WA;
            }
            --a[x - 1];
            if (count == 0) {
                ++count;
                gang = x;
            } else if (gang == x) {
                ++count;
            } else {
                --count;
            }
        }
        if (gang != 1 || count == 0) {
            return Verdict.WA;
        }
        if (left != count) {
            return Verdict.WA;
        }
        return Verdict.OK;
    }
}
