package lib.test.on2013_03.on2013_03_23_Codeforces_Round__176__Div__1_.A___Lucky_Permutation;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] ans = new int[n];
        int m = n;
        if ((n & 1) == 1) {
            ans[n / 2] = n / 2;
            --m;
        }
        if (m % 4 != 0) {
            out.println(-1);
            return;
        }
        for (int i = 0; i < n / 2; i += 2) {
            ans[i] = i + 1;
            ans[i + 1] = n - i - 1;
            ans[n - i - 1] = n - i - 2;
            ans[n - i - 2] = i;
        }
        for (int i = 0; i < n; i++) {
            ++ans[i];
        }
        out.printArray(ans);
    }
}
