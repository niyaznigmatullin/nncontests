package lib.test.on2012_12.on2012_12_24_Codeforces_Beta_Round__67__Div__2_.A___Life_Without_Zeros;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        String a = in.next();
        String b = in.next();
        String c = Integer.parseInt(a) + Integer.parseInt(b) + "";
        a = a.replaceAll("0", "");
        b = b.replaceAll("0", "");
        c = c.replaceAll("0", "");
        if (Integer.parseInt(a) + Integer.parseInt(b) != Integer.parseInt(c)) {
            out.println("NO");
        } else {
            out.println("YES");
        }
	}
}
