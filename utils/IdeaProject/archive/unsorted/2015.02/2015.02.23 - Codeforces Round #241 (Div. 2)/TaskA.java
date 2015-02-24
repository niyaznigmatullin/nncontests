package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int left = -2000000000;
        int right = -left;
        for (int i = 0; i < n; i++) {
            String s = in.next();
            int x = in.nextInt();
            String answer = in.next();
            if (answer.equals("N")) {
                if (s.equals("<")) s = ">="; else
                    if (s.equals(">")) s = "<="; else
                        if (s.equals(">=")) s = "<"; else
                            s = ">";
            }
            if (s.equals(">")) {
                left = Math.max(left, x + 1);
            } else if (s.equals("<")) {
                right = Math.min(right, x - 1);
            } else if (s.equals(">=")) {
                left = Math.max(left, x);
            } else {
                right = Math.min(right, x);
            }
        }
        if (left > right) out.println("Impossible"); else out.println(left);
    }
}
