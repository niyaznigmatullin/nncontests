package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        String s = in.next();
        int a = in.nextInt();
        int b = in.nextInt();
        int[] f = new int[s.length()];
        int[] g = new int[s.length()];
        for (int i = 0, cur = 0; i < s.length(); i++) {
            cur = cur * 10 + s.charAt(i) - '0';
            cur %= a;
            f[i] = cur;
        }
        for (int i = s.length() - 1, cur = 0, ten = 1; i >= 0; i--, ten = ten * 10 % b) {
            cur = cur + ten * (s.charAt(i) - '0');
            cur %= b;
            g[i] = cur;
        }
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(0) != '0' && s.charAt(i) != '0' && f[i - 1] == 0 && g[i] == 0) {
                out.println("YES");
                out.println(s.substring(0, i));
                out.println(s.substring(i));
                return;
            }
        }
        out.println("NO");
    }
}
