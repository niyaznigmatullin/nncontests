package mypackage;

import math.MathUtils;
import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.math.BigInteger;
import java.util.Arrays;

public class RSA {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int t = in.nextInt();
        int m = in.nextInt();
        int[] a = new int[m];
        for (int i = 0; i < m; i++) {
            a[i] = in.nextInt();
        }
        int[] primes = Arrays.copyOf(MathUtils.genPrimes(10000), t);
        boolean[][] matrix = new boolean[t][m];
        for (int i = 0; i < m; i++) {
            int p = a[i];
            for (int j = 0; j < t; j++) {
                int cnt = 0;
                while (p % primes[j] == 0) {
                    p /= primes[j];
                    ++cnt;
                }
                matrix[j][i] = (cnt & 1) == 1;
            }
        }
        int r = rank(matrix);
        out.println(BigInteger.ONE.shiftLeft(m - r).subtract(BigInteger.ONE));
	}

    static int rank(boolean[][] a) {
        int ret = 0;
        int n = a.length;
        int m = a[0].length;
        for (int i = 0; i < m && ret < n; i++) {
            if (!a[ret][i]) {
                for (int j = ret + 1; j < n; j++) {
                    if (a[j][i]) {
                        boolean[] t = a[j];
                        a[j] = a[ret];
                        a[ret] = t;
                        break;
                    }
                }
                if (!a[ret][i]) {
                    continue;
                }
            }
            for (int j = ret + 1; j < n; j++) {
                if (!a[j][i]) {
                    continue;
                }
                for (int k = 0; k < m; k++) {
                    a[j][k] ^= a[ret][k];
                }
            }
            ++ret;
        }
        return ret;
    }

}
