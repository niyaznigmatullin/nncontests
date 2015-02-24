package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        String[] s = new String[n];
        for (int i = 0; i < n; i++) {
            s[i] = in.next();
        }
        int len = s[0].length();
        int[] letter = new int[len];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < s[i].length(); j++) {
                char c = s[i].charAt(j);
                if (c == '?' || letter[j] < 0) continue;
                if (letter[j] == 0) letter[j] = c; else
                    if (letter[j] != c) letter[j] = -1;
            }
        }
        for (int i = 0; i < len; i++) {
            if (letter[i] == 0) letter[i] = 'a';
            if (letter[i] < 0) letter[i] = '?';
            out.print((char) letter[i]);
        }
        out.println();
    }
}
