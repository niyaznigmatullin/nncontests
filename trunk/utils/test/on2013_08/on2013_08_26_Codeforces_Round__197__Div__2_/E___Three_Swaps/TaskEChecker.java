package lib.test.on2013_08.on2013_08_26_Codeforces_Round__197__Div__2_.E___Three_Swaps;



import net.egork.chelper.tester.StringInputStream;
import net.egork.chelper.tester.Verdict;
import net.egork.chelper.checkers.Checker;
import ru.ifmo.niyaz.io.FastScanner;

public class TaskEChecker implements Checker {
    public TaskEChecker(String parameters) {
    }

    public Verdict check(String input, String expectedOutput, String actualOutput) {
        FastScanner scanner = new FastScanner(new StringInputStream(input));
        int n = scanner.nextInt();
        int[] p = scanner.readIntArray(n);
        FastScanner out = new FastScanner(new StringInputStream(actualOutput));
        int k = out.nextInt();
        if (k > 3) return Verdict.WA;
        for (int i = 0; i < k; i++) {
            int l = out.nextInt();
            int r = out.nextInt();
            for (int x = l - 1, y = r - 1; x < y; x++, y--) {
                int t = p[x];
                p[x] = p[y];
                p[y] = t;
            }
        }
        for (int i = 0; i < n; i++) if (p[i] != i + 1) return Verdict.WA;
        return Verdict.OK;
    }
}
