package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        String[] s = new String[n];
        for (int i = 0; i < n; i++) s[i] = in.next();
        int[][] a = new int[26][26];
        for (int i = 0; i + 1 < n; i++) {
            int x = 0;
            while (x < s[i].length() && x < s[i + 1].length() && s[i].charAt(x) == s[i + 1].charAt(x)) ++x;
            if (x >= s[i + 1].length()) {
                out.println("Impossible");
                return;
            }
            if (x >= s[i].length()) continue;
            a[s[i].charAt(x) - 'a'][s[i + 1].charAt(x) - 'a'] = 1;
        }
        int[] deg = new int[26];
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                deg[j] += a[i][j];
            }
        }
        int[] q = new int[26];
        int head = 0;
        int tail = 0;
        for (int i = 0; i < 26; i++) {
            if (deg[i] == 0) {
                q[tail++] = i;
            }
        }
        while (head < tail) {
            int v = q[head++];
            for (int i = 0; i < 26; i++) {
                if (a[v][i] == 1) {
                    deg[i]--;
                    if (deg[i] == 0) {
                        q[tail++] = i;
                    }
                }
            }
        }
        if (head != 26) {
            out.println("Impossible");
        } else {
            for (int i = 0; i < 26; i++) {
                out.print((char) ('a' + q[i]));
            }
            out.println();
        }
    }
}
