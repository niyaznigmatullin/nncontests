package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {

    static int get(char c) {
        return new int[]{0, 9, 5, 3, 3, 1}["qrbnp".indexOf(c) + 1];
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        char[][] c = in.readCharacterFieldTokens(8, 8);
        int a = 0;
        int b = 0;
        for (char[] d : c) {
            for (char e : d) {
                if (Character.isUpperCase(e)) {
                    a += get(Character.toLowerCase(e));
                } else {
                    b += get(e);
                }
            }
        }
        if (a == b) {
            out.println("Draw");
        } else if (a > b) {
            out.println("White");
        } else {
            out.println("Black");
        }
    }
}
