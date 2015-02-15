package coding;

import java.util.Arrays;

public class BitToggler {
    public double[] expectation(int n, String[] bits, int[] pos) {
        double[] exp = gauss(n);
        double[] ans = new double[bits.length];
        for (int i = 0; i < bits.length; i++) {
            int ones = 0;
            for (int j = 0; j < bits[i].length(); j++) {
                if (bits[i].charAt(j) == '1') {
                    ones++;
                }
            }
            ans[i] = exp[ones * n + pos[i]];
        }
        return ans;
    }

    static double[] gauss(int n) {
        int nStates = n * (n + 1);
        double[][] a = new double[nStates][nStates + 1];
        double[] posExp = new double[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                posExp[i] += Math.abs(i - j);
            }
            posExp[i] /= n;
        }
        for (int ones = 0; ones <= n; ones++) {
            for (int where = 0; where < n; where++) {
                int state = ones * n + where;
                if (ones == 0 || ones == n) {
                    a[state][state] += 1;
                } else {
                    a[state][state] = 1;
                    for (int i = 0; i < n; i++) {
                        int state1 = (ones - 1) * n + i;
                        int state2 = (ones + 1) * n + i;
                        a[state][state1] += -1. * ones / n / n;
                        a[state][state2] += -1. * (n - ones) / n / n;
                    }
                    a[state][nStates] += posExp[where];
                }
            }
        }
        int[] id = new int[nStates];
        for (int i = 0; i < nStates; i++) id[i] = i;
        int cr = 0;
        for (int i = 0; i < nStates; i++) {
            int row = -1;
            for (int j = cr; j < nStates; j++) {
                if (row < 0 || Math.abs(a[j][i]) > Math.abs(a[row][i])) {
                    row = j;
                }
            }
            if (row != i) {
                double[] tt = a[row];
                a[row] = a[i];
                a[i] = tt;
                int t = id[row];
                id[row] = id[i];
                id[i] = t;
            }
            if (Math.abs(a[cr][i]) < 1e-8) continue;
            for (int j = cr + 1; j < nStates; j++) {
                double q = a[j][i] / a[cr][i];
                for (int k = 0; k <= nStates; k++) {
                    a[j][k] -= q * a[cr][k];
                }
            }
            ++cr;
        }
        for (int i = cr - 1; i >= 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                double q = a[j][i] / a[i][i];
                for (int k = 0; k <= nStates; k++) {
                    a[j][k] -= q * a[i][k];
                }
            }
        }
//        System.out.println(Arrays.deepToString(a));
        double[] expected = new double[nStates];
        for (int i = 0; i < nStates; i++) {
            expected[id[i]] = a[i][nStates] / a[i][i];
        }
        System.out.println(Arrays.toString(expected));
        return expected;
    }
}
