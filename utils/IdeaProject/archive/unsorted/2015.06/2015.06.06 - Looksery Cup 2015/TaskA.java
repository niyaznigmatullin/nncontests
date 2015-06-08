package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        char[][] c = in.readCharacterFieldTokens(n, m);
        char[] z = new char[4];
        int ans = 0;
        for (int i = 0; i + 1 < n; i++) {
            for (int j = 0; j + 1 < m; j++) {
                z[0] = c[i][j];
                z[1] = c[i + 1][j];
                z[2] = c[i][j + 1];
                z[3] = c[i + 1][j + 1];
                Arrays.sort(z);
                if (new String(z).equals("acef")) {
                    ++ans;
                }
            }
        }
        out.println(ans);
    }
}
