package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class Autocomplete {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        System.err.println("[" + testNumber + "]");
        int n = in.nextInt();
        String[] s = new String[n];
        int all = 1;
        for (int i = 0; i < n; i++) {
            s[i] = in.next();
            all += s[i].length();
        }
        int[][] link = new int[26][all];
        for (int[] e : link) {
            Arrays.fill(e, -1);
        }
        int ans = 0;
        int fr = 1;
        for (int i = 0; i < n; i++) {
            int v = 0;
            int cur = 0;
            for (char e : s[i].toCharArray()) {
                int c = e - 'a';
                if (link[c][v] >= 0) ++cur; else link[c][v] = fr++;
                v = link[c][v];
            }
            if (cur != s[i].length()) ++cur;
            ans += cur;
        }
        out.println("Case #" + testNumber + ": " + ans);
    }
}
