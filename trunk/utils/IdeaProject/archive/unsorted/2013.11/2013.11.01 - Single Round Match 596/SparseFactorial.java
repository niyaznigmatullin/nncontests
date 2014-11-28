package coding;

import ru.ifmo.niyaz.math.Factor;
import ru.ifmo.niyaz.math.MathUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SparseFactorial {
    static long f(long n) {
        long ret = 1;
        for (long i = 0; i * i < n; i++) {
            ret *= n - i * i;
        }
        return ret;
    }

    static long solve(long n, int d, long[][] minSqAll, Factor[] f) {
        if (n < 1) return 0;
        long ret = 0;
        int[] powPrimes = new int[f.length];
        for (int i = 0; i < f.length; i++) {
            powPrimes[i] = 1;
            for (int j = 0; j < f[i].pow
                    ; j++) {
                powPrimes[i] *= f[i].prime;
            }
        }
        for (int mod = 0; mod < d; mod++) {
            long minSq = 0;
            for (int pId = 0; pId < f.length; pId++) {
                minSq = Math.max(minSq, minSqAll[pId][mod % powPrimes[pId]]);
            }
//            System.out.println(mod + " " + minSq);
            long minimalByMod = minSq * minSq + 1;
            long minimal = minimalByMod - minimalByMod % d + mod;
            while (minimal > minimalByMod) {
                minimal -= d;
            }
            while (minimal < minimalByMod) {
                minimal += d;
            }
            long maximal = n - n % d + mod;
            while (maximal < n) {
                maximal += d;
            }
            while (maximal > n) {
                maximal -= d;
            }
            if (maximal >= minimal) {
                ret += (maximal - minimal) / d + 1;
            }
        }
        return ret;
    }

    public long getCount(long lo, long hi, long divisor_) {
        int d = (int) divisor_;
        Factor[] f = MathUtils.factorize(d);
        long[][] minSq = new long[f.length][];
        for (int i = 0; i < f.length; i++) {
            int p = 1;
            int[][][] indices = new int[f[i].pow][][];
            int[][] cn = new int[f[i].pow][];
            for (int j = 0; j < f[i].pow; j++) {
                p *= f[i].prime;
                cn[j] = new int[p];
                for (int k = 0; k < p; k++) {
                    int z = (int) ((long) k * k % p);
                    cn[j][z]++;
                }
            }
//            System.out.println(p + " " + f[i].prime + " " + f[i].pow);
            p = 1;
            for (int j = 0; j < f[i].pow; j++) {
                p *= f[i].prime;
                indices[j] = new int[p][];
                for (int k = 0; k < p; k++) {
                    indices[j][k] = new int[cn[j][k]];
                    cn[j][k] = 0;
                }
                for (int k = 0; k < p; k++) {
                    int z = (int) ((long) k * k % p);
                    indices[j][z][cn[j][z]++] = k;
                }
            }
            minSq[i] = new long[p];
            for (int mod = 0; mod < p; mod++) {
                int l = -1;
                int r = d + 1;
                while (l < r - 1) {
                    int mid = (l + r) >> 1;
                    int count = 0;
                    int curP = 1;
                    all: for (int k = 0; k < f[i].pow; k++) {
                        curP *= (int) f[i].prime;
                        int curMod = mod % curP;
                        if (indices[k][curMod].length > 0) {
                            int full = mid / curP;
                            int partial = mid % curP;
                            count += indices[k][curMod].length * full;
                            for (int j : indices[k][curMod]) {
                                if (j < partial) {
                                    count++;
                                }
                            }
                            if (count >= f[i].pow) break;
                        }
                    }
                    if (count >= f[i].pow) {
                        r = mid;
                    } else {
                        l = mid;
                    }
                }
                minSq[i][mod] = l;
            }
            indices = null;
        }
        return solve(hi, d, minSq, f) - solve(lo - 1, d, minSq, f);
    }
}
