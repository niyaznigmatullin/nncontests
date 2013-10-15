package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        String s = in.next();
        int[] count = new int[3];
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                count[s.charAt(i) - '1']++;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < count[i]; j++) {
                sb.append('+').append(i + 1);
            }
        }
        out.println(sb.toString().substring(1));
    }
}
