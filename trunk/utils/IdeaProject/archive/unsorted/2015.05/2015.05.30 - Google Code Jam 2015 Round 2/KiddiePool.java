package coding;

import ru.ifmo.niyaz.graphalgorithms.MinCostMaxFlowGraph;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class KiddiePool {

    static class WaterSource {
        int rate;
        int temp;

        public WaterSource(int rate, int temp) {
            this.rate = rate;
            this.temp = temp;
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        System.err.println(testNumber);
        int n = in.nextInt();
        long V = Math.round(in.nextDouble() * 1e4);
        long X = Math.round(in.nextDouble() * 1e4) * V;
        int[] rate = new int[n];
        int[] temp = new int[n];
        boolean less = false;
        boolean greater = false;
        for (int i = 0; i < n; i++) {
            rate[i] = (int) Math.round(in.nextDouble() * 1e4);
            temp[i] = (int) Math.round(in.nextDouble() * 1e4);
            if (temp[i] * V <= X) less = true;
            if (temp[i] * V >= X) greater = true;
        }
        out.print("Case #" + testNumber + ": ");
        if (!less || !greater) {
            out.println("IMPOSSIBLE");
            return;
        }
        WaterSource[] w = new WaterSource[n];
        double sumRate = 0;
        for (int i = 0; i < n; i++) {
            sumRate += rate[i];
            w[i] = new WaterSource(rate[i], temp[i]);
        }
        Arrays.sort(w, new Comparator<WaterSource>() {
            @Override
            public int compare(WaterSource o1, WaterSource o2) {
                return Integer.compare(o1.temp, o2.temp);
            }
        });
        double l = V / sumRate;
        double r = 1e20;
        for (int it = 0; it < 320; it++) {
            double mid = (l + r) * .5;
            if (l == mid || r == mid) break;
            double[] canGet = new double[n];
            for (int i = 0; i < n; i++) {
                canGet[i] = mid * w[i].rate;
            }
            double toGet = V;
            double tempMin = 0;
            for (int i = 0; i < n; i++) {
                double curGet = Math.min(toGet, canGet[i]);
                toGet -= curGet;
                tempMin += curGet * w[i].temp;
            }
            double tempMax = 0;
            toGet = V;
            for (int i = n - 1; i >= 0; i--) {
                double curGet = Math.min(toGet, canGet[i]);
                toGet -= curGet;
                tempMax += curGet * w[i].temp;
            }
//            System.out.println(tempMin + " " + tempMax);
            if (tempMin <= X && X <= tempMax){
                r = mid;
            } else {
                l = mid;
            }
        }
        out.println((l + r) * .5);
    }
}
