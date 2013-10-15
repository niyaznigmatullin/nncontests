package lib.test.on2013_02.on2013_02_22_Volume_4._1357___Teakettle_1_0_for_Dummies;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Locale;

public class Task1357 {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] t = new int[n];
        for (int i = 0; i < n; i++) {
            t[i] = in.readTimeHMS(':');
        }
        int endTime = 0;
        final int C = 838;
        final int P = 400;
        for (int i = 0; i < n; ) {
            int startTime = Math.max(endTime, t[i]);
            int dt = (C * 80 + P - 1) / P;
            int j = i + 1;
            int count = 1;
            while (count < 5 && j < n && t[j] < startTime + dt) {
                ++count;
                dt = (C * 80 * count + P - 1) / P;
                ++j;
            }
            endTime = startTime + dt;
            int outT = endTime % 86400;
            String time = String.format(Locale.US, "%02d:%02d:%02d", outT / 3600, outT / 60 % 60, outT % 60);
            while (i < j) {
                out.println(time);
                ++i;
            }
        }
    }
}
