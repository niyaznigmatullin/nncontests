package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {

    static String[] K = {"qwertyuiop",
            "asdfghjkl;",
            "zxcvbnm,./}"};

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        String op = in.next();
        String s = in.next();
        int shift = op.equals("L") ? 1 : 9;
        for (char c : s.toCharArray()) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 10; j++) {
                    if (K[i].charAt(j) == c) {
                        out.print(K[i].charAt((j + shift) % 10));
                    }
                }
            }
        }
        out.println();
    }
}
