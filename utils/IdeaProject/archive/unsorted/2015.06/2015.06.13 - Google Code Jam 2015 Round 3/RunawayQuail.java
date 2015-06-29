package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.*;

public class RunawayQuail {
    static class Man {
        int pos;
        int v;

        public Man(int pos, int v) {
            this.pos = pos;
            this.v = v;
        }

        double getPos(double t) {
            return pos + v * Integer.signum(pos) * t;
        }
    }
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        System.err.println(testNumber);
        int y = in.nextInt();
        int n = in.nextInt();
        int[] p = in.readIntArray(n);
        int[] s = in.readIntArray(n);
        List<Man> left = new ArrayList<>();
        List<Man> right = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            (p[i] < 0 ? left : right).add(new Man(p[i], s[i]));
        }
        Collections.sort(left, new Comparator<Man>() {
            @Override
            public int compare(Man o1, Man o2) {
                return Integer.compare(o2.pos, o1.pos);
            }
        });
        Collections.sort(right, new Comparator<Man>() {
            @Override
            public int compare(Man o1, Man o2) {
                return Integer.compare(o1.pos, o2.pos);
            }
        });
        double[][][] dp = new double[2][left.size() + 1][right.size() + 1];
        dp[0][0][0] = 0;
        for (int i = 0; i <= left.size(); i++) {
            for (int j = 0; j <= right.size(); j++) {
                for (int w = 0; w < 2; w++) {
                    double val = dp[w][i][j];
                    if (val == Double.POSITIVE_INFINITY) continue;
                    double pos = w == 0 ? (i == 0 ? 0 : left.get(i - 1).pos) : (j == 0 ? 0 : right.get(j - 1).pos);
                    if (i < left.size()) {
                        dp[0][i + 1][j] = Math.min(Math.abs(left.get(i).getPos(val) - pos) / (y - left.get(i).v), dp[0][i + 1][j]);
                    }
                    if (j < right.size()) {
                        dp[1][i][j + 1] = Math.min(dp[1][i][j + 1], Math.abs(right.get(j).getPos(val) - pos) / (y - right.get(j).v));
                    }
                }
            }
        }
    }
}
