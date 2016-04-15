package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class HighSecurity {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        in.nextInt();
        char[][] c = new char[2][];
        c[0] = ("X" + in.next() + "X").toCharArray();
        c[1] = ("X" + in.next() + "X").toCharArray();
        int ans = 0;
        for (int i = 1; i + 1 < c[0].length; i++) {
            for (int j = 0; j < 2; j++) {
                if (c[j][i] == '.' && c[j][i - 1] == 'X' && c[j][i + 1] == 'X' && c[j ^ 1][i] == '.') {
                    c[j ^ 1][i] = 'X';
                    c[j][i] = 'X';
                    int f = i - 1;
                    while (c[j ^ 1][f] == '.') {
                        c[j ^ 1][f] = 'X';
                        --f;
                    }
                    f = i + 1;
                    while (c[j ^ 1][f] == '.') {
                        c[j ^ 1][f] = 'X';
                        ++f;
                    }
                    ans++;
                }
            }
        }
        for (int i = 1; i + 1 < c[0].length; i++) {
            for (int j = 0; j < 2; j++) {
                if (c[j][i] == '.') {
                    ++ans;
                    int f = i;
                    while (c[j][f] == '.') {
                        c[j][f] = 'X';
                        ++f;
                    }
                }
            }
        }
        out.println("Case #" + testNumber + ": " + ans);
    }
}
