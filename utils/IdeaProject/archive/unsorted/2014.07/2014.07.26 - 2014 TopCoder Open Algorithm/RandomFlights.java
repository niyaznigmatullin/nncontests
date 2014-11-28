package coding;

import java.util.Arrays;

public class RandomFlights {

    public double expectedDistance(int[] x, int[] y, String[] flight) {
        int n = x.length;
        double[] dp = new double[1 << n];
        double[][] d = new double[n][n];
        double[][] z = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                z[i][j] = Math.sqrt((x[i] - x[j]) * (x[i] - x[j]) + (y[i] - y[j]) * (y[i] - y[j]));
                if (flight[i].charAt(j) == '1' || i == j) {
                    d[i][j] = z[i][j];
                } else {
                    d[i][j] = Double.POSITIVE_INFINITY;
                }
            }
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
                }
            }
        }
        int initMask = 0;
        double sumDepth = 0;
        for (int i = 0; i < n; i++) {
            if (d[0][i] != Double.POSITIVE_INFINITY) {
                initMask |= 1 << i;
                sumDepth += d[0][i];
            }
        }
        if ((initMask & 2) != 0) {
            return d[0][1];
        }
        double[] pp = new double[1 << n];
        dp[initMask] = sumDepth;
        pp[initMask] = 1;
        double ans = 0;
        for (int mask = 0; mask < 1 << n; mask++) {
            double val = dp[mask];
            double valP = pp[mask];
            if (valP == 0) continue;
//            System.out.println(mask + " " + val + " " + valP);
            for (int i = 0; i < n; i++) {
                if (((mask >> i) & 1) == 1) continue;
                double curP = 1. / (n - Integer.bitCount(mask));
                int addMask = 0;
                double curSum = 0;
                for (int j = 0; j < n; j++) {
                    if (d[i][j] != Double.POSITIVE_INFINITY) {
                        addMask |= 1 << j;
                        curSum += d[i][j];
                    }
                }
                double edgesSum = 0;
                for (int j = 0; j < n; j++) {
                    if (((mask >> j) & 1) == 1) {
                        edgesSum += z[j][i];
                    }
                }
                if ((addMask & 2) != 0) {
                    ans += ((val + valP * edgesSum) / Integer.bitCount(mask) + valP * d[i][1]) / (n - Integer.bitCount(mask));
                }
                curSum *= valP;
                curSum += val + (Integer.bitCount(addMask) * (val + valP * edgesSum)) / Integer.bitCount(mask);
                dp[mask | addMask] += curSum * curP;
                pp[mask | addMask] += valP * curP;
            }
        }
        return ans;
    }
}
