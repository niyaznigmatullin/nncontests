package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TennisTournament {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        double p = in.nextInt() * .01;
        double[] prob = new double[m];
        int[] a = new int[m];
        for (int i = 0; i < m; i++) {
            a[i] = in.nextInt() - 1;
            prob[i] = 1;
        }
        Arrays.sort(a);
        for (; n > 1; n >>= 1) {
            List<Integer> next = new ArrayList<Integer>();
            List<Double> nextProb = new ArrayList<Double>();
            for (int i = 0; i < a.length; i++) {
                next.add(a[i] >> 1);
                if (i + 1 < a.length && a[i + 1] == (a[i] ^ 1)) {
                    double p1 = prob[i];
                    double p2 = prob[i + 1];
                    nextProb.add(p1 * p2 + p1 * (1 - p2) * p + (1 - p1) * p2 * p);
                    i++;
                } else {
                    nextProb.add(prob[i] * p);
                }
            }
            a = new int[next.size()];
            prob = new double[nextProb.size()];
            for (int i = 0; i < a.length; i++) {
                a[i] = next.get(i);
                prob[i] = nextProb.get(i);
            }
        }
        out.println(prob[0] * 100);
    }
}
