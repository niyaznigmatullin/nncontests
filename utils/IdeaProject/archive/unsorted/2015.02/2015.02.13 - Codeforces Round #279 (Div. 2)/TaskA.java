package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[][] a = new int[3][n];
        int[] cnt = new int[3];
        for (int i = 0; i < n; i++) {
            int x = in.nextInt() - 1;
            a[x][cnt[x]++] = i;
        }
        int ans = Math.min(cnt[0], Math.min(cnt[1], cnt[2]));
        out.println(ans);
        for (int i = 0; i < ans; i++) {
            out.println((a[0][--cnt[0]] + 1) + " " + (a[1][--cnt[1]] + 1) + " " + (a[2][--cnt[2]] + 1));
        }
    }
}
