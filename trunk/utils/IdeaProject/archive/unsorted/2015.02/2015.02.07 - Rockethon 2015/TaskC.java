package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] left = new int[n];
        int[] right = new int[n];
        for (int i = 0; i < n; i++) {
            left[i] = in.nextInt();
            right[i] = in.nextInt();
        }
        double ans = 0;
        for (int pay = 1; pay <= 10000; pay++) {
            for (int wins = 0; wins < n; wins++) {
                if (right[wins] <= pay) continue;
                boolean bad = false;
                double prob = 1.;
                double probLess = 1.;
                for (int second = 0; second < n; second++) {
                    if (second == wins) continue;
                    if (left[second] > pay) {
                        bad = true;
                        break;
                    }
                    prob *= (Math.min(pay, right[second]) - left[second] + 1);
                    probLess *= Math.min(pay - 1, right[second]) - left[second] + 1;
                }
                if (bad) continue;
                prob -= probLess;
                prob *= right[wins] - Math.max(pay + 1, left[wins]) + 1;
                ans += prob * pay;
            }
            for (int winMask = 1; winMask < 1 << n; winMask++) {
                if (Integer.bitCount(winMask) < 2) continue;
                double prob = 1.;
                boolean bad = false;
                for (int i = 0; i < n; i++) {
                    if (left[i] > pay) {
                        bad = true;
                        break;
                    }
                    if (((winMask >> i) & 1) == 0) {
                        prob *= Math.min(pay - 1, right[i]) - left[i] + 1;
                    } else {
                        if (pay < left[i] || pay > right[i]) {
                            bad = true;
                            break;
                        }
                    }
                }
                if (bad) continue;
                ans += prob * pay;
            }
        }
        for (int i = 0; i < n; i++) ans /= right[i] - left[i] + 1;
        out.println(ans);
    }
}
