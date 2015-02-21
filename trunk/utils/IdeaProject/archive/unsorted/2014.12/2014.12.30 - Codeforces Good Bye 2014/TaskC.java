package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] w = new int[n];
        for (int i = 0; i < n; i++) w[i] = in.nextInt();
        int[] books = new int[m];
        for (int i = 0; i < m; i++) {
            books[i] = in.nextInt() - 1;
        }
        int ans = 0;
        for (int i = 0; i < m; i++) {
            boolean[] q = new boolean[n];
            for (int j = i - 1; j >= 0; j--) {
                if (books[i] == books[j]) {
                    break;
                }
                q[books[j]] = true;
            }
            for (int j = 0; j < n; j++) {
                if (q[j]) {
                    ans += w[j];
                }
            }
        }
        out.println(ans);
    }
}
