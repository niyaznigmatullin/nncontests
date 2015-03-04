package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        String s = in.next();
        String t = in.next();
        int[] count1 = get(t.toLowerCase());
        int[] count2 = get(t);
        int ok1 = 0;
        int ok2 = 0;
        boolean[] was = new boolean[s.length()];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            char d = Character.toLowerCase(c);
            if (count2[c] > 0) {
                --count2[c];
                --count1[d];
                ++ok1;
                was[i] = true;
            }
        }
        for (int i = 0; i < s.length(); i++) {
            if (was[i]) continue;
            char c = s.charAt(i);
            char d = Character.toLowerCase(s.charAt(i));
            if (count1[d] > 0) {
                --count1[d];
                ++ok2;
            }
        }
        out.println(ok1 + " " + ok2);
    }

    static int[] get(String s) {
        int[] ret = new int[256];
        for (char c : s.toCharArray()) ret[c]++;
        return ret;
    }
}
