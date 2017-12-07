package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class LuckyDraws {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        for (int k = 1; k <= 17; k++) {
            stupid(k);
        }
    }

    static void stupid(int k) {
        int red = 26;
        int black = 26;
        double ans = go1(k, red, black);
        System.out.println("k = " + k + ", prob = " + ans);
    }

    static double go1(int k, int red, int black) {
        double best = 0.0;
        int bestMask = -1;
        for (int mask = 0; mask < 1 << k - 1 && mask < 10; mask++) {
            int getRed = Integer.bitCount(mask);
            int getBlack = k - getRed;
            if (getRed > red || getBlack > black) continue;
            double probability = 1. - go2(k, red - getRed, black - getBlack, mask);
            if (probability > best + 1e-9) {
                best = probability;
                bestMask = mask;
            }
        }
        System.out.println(bestMask);
        return best;
    }

    static double go2(int k, int red, int black, int mask1) {
        double best = 0.0;
        for (int mask = 0; mask < 1 << k; mask++) {
            if (mask == mask1) continue;
            int getRed = Integer.bitCount(mask);
            int getBlack = k - getRed;
            if (getRed > red || getBlack > black) continue;
            double probability = 1. - go0(k, red - getRed, black - getBlack, mask1, mask);
            if (probability > best) {
                best = probability;
            }
        }
        return best;
    }

    static double[][][] dp = new double[1 << 10][27][27];

    static double go0(int k, int allRed, int allBlack, int mask1, int mask2) {
        double[][][] dp = new double[1 << k][allRed + 1][allBlack + 1];
        dp[0][allRed][allBlack] = 1.;
        double ans = 0;
        for (int red = allRed; red >= 0; red--) {
            for (int black = allBlack; black >= 0; black--) {
                for (int mask = 0; mask < 1 << k; mask++) {
                    double val = dp[mask][red][black];
                    int read = allRed + allBlack - red - black;
                    if (read >= k && mask == mask1) {
                        ans += val;
                        continue;
                    }
                    if (read >= k && mask == mask2) continue;
                    if (black > 0) {
                        dp[(mask << 1) & ((1 << k) - 1)][red][black - 1] += val * (1. * black / (red + black));
                    }
                    if (red > 0) {
                        dp[(mask << 1) & ((1 << k) - 1) | 1][red - 1][black] += val * (1. * red / (red + black));
                    }
                }
            }
        }
        return ans;
    }
}
