package lib.test.on2012_12.on2012_12_22_Codeforces_Round__157__Div__1_.A___Little_Elephant_and_Bits;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        String s = in.next();
        int pos = s.indexOf('0');
        if (pos >= 0) {
            out.println(s.substring(0, pos) + s.substring(pos + 1));
        } else {
            out.println(s.substring(1));
        }
	}
}
