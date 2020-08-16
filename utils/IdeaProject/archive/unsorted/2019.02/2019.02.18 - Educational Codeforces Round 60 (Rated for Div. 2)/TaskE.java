package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskE {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        String s = in.next();
        char[][] c = new char[3][s.length()];
        for (int i = 0; i < s.length(); i++) {
            int x = i;
            for (int j = 0; j < 3; j++) {
                c[j][i] = (char) (x % 26 + 'a');
                x /= 26;
            }
        }
        char[][] d = new char[3][];
        for (int i = 0; i < 3; i++) {
            out.println(new String(c[i]));
            out.flush();
            d[i] = in.next().toCharArray();
        }
        int[] p = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            int val = 0;
            for (int j = 2; j >= 0; j--) {
                val *= 26;
                val += d[j][i] - 'a';
            }
            p[i] = val;
        }
        char[] ans = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            ans[p[i]] = s.charAt(i);
        }
        out.println(ans);
    }
}
