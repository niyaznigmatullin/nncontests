package lib.test.on2013_08.on2013_08_30_Codeforces_Round__198__Div__1_.A___Tourist_Problem;



import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.Rational;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        long sum = 0;
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            sum += a[i];
        }
        ArrayUtils.sort(a);
//        Rational ans = new Rational(sum, n);
        Rational ans = Rational.ZERO;
        for (int i = 0; i < n; i++) {
            long n1 = (long) a[i] * (i - (n - i - 1));
            long d1 = n;
            ans = ans.add(new Rational(n1, d1));
        }
        ans = ans.multiply(new Rational(2));
        ans = ans.add(new Rational(sum, n));
        out.println(ans.getNum() + " " + ans.getDen());
    }
}
