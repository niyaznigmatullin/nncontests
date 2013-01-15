package lib.test.on2013_01.on2013_01_14_Codeforces_Beta_Round__82__Div__2_.A___Card_Game;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        String lead = in.next();
        String a = in.next();
        String b = in.next();
        final String all = "6789TJQKA";
        int c = all.indexOf(a.charAt(0));
        int d = all.indexOf(b.charAt(0));
        if (a.charAt(1) == b.charAt(1) && c > d || lead.charAt(0) == a.charAt(1) && lead.charAt(0) != b.charAt(1)) {
            out.println("YES");
        } else {
            out.println("NO");
        }
    }
}
