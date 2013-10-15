package lib.test.on2013_08.on2013_08_26_Codeforces_Round__197__Div__2_.B___Xenia_and_Ringroad;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] a = in.readIntArray(m);
        int cur = 0;
        long ans = 0;
        for (int i = 0; i < m; i++) {
            --a[i];
            ans += (a[i] - cur + n) % n;
            cur = a[i];
        }
        out.println(ans);
    }
}
