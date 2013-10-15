package lib.test.on2013_04.on2013_04_19_Codeforces_Round__180__Div__1_.A___Parity_Game;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int c1 = countOnes(in.next());
        int c2 = countOnes(in.next());
        if ((c1 & 1) == 1) {
            ++c1;
        }
        if (c1 >= c2) {
            out.println("YES");
        } else {
            out.println("NO");
        }
    }

    static int countOnes(String s) {
        int ret = 0;
        for (char c : s.toCharArray()) {
            ret += c == '1' ? 1 : 0;
        }
        return ret;
    }
}
