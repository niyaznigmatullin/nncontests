package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.Arrays;

public class BearsAndBees {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] from = new int[m];
        int[] to = new int[m];
        for (int i = 0; i < m; i++) {
            from[i] = in.nextInt() - 1;
            to[i] = in.nextInt() - 1;
        }
        int[] from1 = new int[m * m];
        int[] to1 = new int[m * m];
        int[] deg1 = new int[m];
        int count1 = 0;
        for (int i = 0; i < m; i++) {
            int f1 = from[i];
            int t1 = to[i];
            for (int j = i + 1; j < m; j++) {
                int f2 = from[j];
                int t2 = to[j];
                if (f1 == f2 || f1 == t2 || t1 == f2 || t1 == t2) {
                    from1[count1] = i;
                    to1[count1] = j;
                    deg1[i]++;
                    deg1[j]++;
                    count1++;
                }
            }
        }
        int[] deg2 = new int[count1];
        for (int i = 0; i < count1; i++) {
            deg2[i] = deg1[from1[i]] + deg1[to1[i]] - 2;
        }
        long v = 0;
        long e = 0;
        for (int i : deg2) {
            v += i;
            e += (long) i * (i - 1) / 2;
        }
        v /= 2;
        out.println(v + " " + e);
    }
}
