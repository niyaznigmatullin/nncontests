package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        char[][] c = in.readCharacterFieldTokens(n, n);
        int[] a = in.readIntArray(n);
        int[] b = new int[n];
        int[] ans = new int[n];
        int ac = 0;
        all: while (true) {
            for (int i = 0; i < n; i++) {
                if (b[i] == a[i]) {
                    ans[ac++] = i + 1;
                    for (int j = 0; j < n; j++) {
                        b[j] += c[i][j] - '0';
                    }
                    continue all;
                }
            }
            break;
        }
        out.println(ac);
        Arrays.sort(ans, 0, ac);
        out.printArray(Arrays.copyOf(ans, ac));
    }
}
