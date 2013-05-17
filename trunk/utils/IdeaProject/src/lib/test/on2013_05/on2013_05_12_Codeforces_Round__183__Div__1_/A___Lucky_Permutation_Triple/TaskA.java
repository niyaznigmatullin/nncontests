package lib.test.on2013_05.on2013_05_12_Codeforces_Round__183__Div__1_.A___Lucky_Permutation_Triple;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        if ((n & 1) == 0) {
            out.println(-1);
            return;
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) ans[i] = i;
        int[] ans2 = new int[n];
        for (int i = 0; i < n; i++) ans2[i] = i * 2 % n;
        out.printArray(ans);
        out.printArray(ans);
        out.printArray(ans2);
    }
}
