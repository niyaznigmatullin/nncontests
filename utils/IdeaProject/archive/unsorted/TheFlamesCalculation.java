package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

public class TheFlamesCalculation {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int[] a = new int[256];
        int[] b = new int[256];
        int ans = 0;
        for (char e : in.nextLine().toCharArray()) {
            if (!Character.isLetter(e)) {
                continue;
            }
            a[e - '0']++;
            ++ans;
        }
        for (char e : in.nextLine().toCharArray()) {
            if (!Character.isLetter(e)) {
                continue;
            }
            b[e - '0']++;
            ++ans;
        }
        for (int i = 0; i < a.length; i++) {
            ans -= Math.min(a[i], b[i]) << 1;
        }
        out.println(ANSWER[joseph(6, Math.max(1, ans)) - 1]);
    }

    int joseph(int n, int k) {
        return n > 1 ? (joseph(n - 1, k) + k - 1) % n + 1 : 1;
    }

    static final String[] ANSWER = {"FRIENDS", "LOVE", "ADORE", "MARRIAGE", "ENEMIES", "SISTER"};
}
