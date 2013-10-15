package lib.test.on2013_04.on2013_04_15_Croc_Champ_2013___Round_1.A___SMSC;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] t = new int[n];
        int[] c = new int[n];
        for (int i = 0; i < n; i++) {
            t[i] = in.nextInt();
            c[i] = in.nextInt();
        }
        int last = 0;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += c[j];
            }
            last = Math.max(last, t[i] + sum);
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
//            int j = i;
//            int curLeft = c[i];
//            ans = Math.max(curLeft, ans);
//            while (j + 1 < n && t[j] + curLeft > t[j + 1]) {
//                curLeft -= t[j + 1] - t[j];
//                curLeft += c[j + 1];
//                ans = Math.max(ans, curLeft);
//                ++j;
//            }
//            i = j + 1;
            int sum = 0;
            ans = Math.max(ans, c[i] + sum);
            for (int j = i - 1; j >= 0; j--) {
                sum += c[j];
                int left = t[j] + sum - t[i];
                ans = Math.max(ans, c[i] + left);
            }
        }
        out.println(last + " " + ans);
    }
}
