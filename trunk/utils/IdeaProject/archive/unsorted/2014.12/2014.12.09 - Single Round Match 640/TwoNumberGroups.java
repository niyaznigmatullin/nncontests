package coding;

import ru.ifmo.niyaz.math.MathUtils;

import java.util.Arrays;
import java.util.Comparator;

public class TwoNumberGroups {
    public int solve(int[] A, int[] numA, int[] B, int[] numB) {
        int n = A.length;
        int m = B.length;
        final int[] all = new int[n * m];
        long[] count = new long[n * m];
        int cn = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                all[cn] = A[i] - B[j];
                if (all[cn] < 0) all[cn] = -all[cn];
                count[cn] = (long) numA[i] * numB[j];
                cn++;
            }
        }
        Integer[] id = new Integer[n * m];
        for (int i = 0; i < n * m; i++) id[i] = i;
        Arrays.sort(id, new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(all[o1], all[o2]);
            }
        });
        final int N = 30000000;
        int[] factor = MathUtils.getFactoringSieve(N);
        cn = 0;
        final int M = 32000;
        for (int i = 2; i < M && i < factor.length; i++) {
            if (factor[i] == i) cn++;
        }
        int[] primes = new int[cn];
        cn = 0;
        for (int i = 2; i < M && i < factor.length; i++) {
            if (factor[i] == i) primes[cn++] = i;
        }
        factor[1] = 0;
        factor[0] = 0;
        for (int i = 2; i < factor.length; i++) {
            int z = factor[i];
            if (i / z % z == 0) {
                factor[i] = factor[i / z];
            } else {
                factor[i] = z + factor[i / z];
            }
        }
        final int MOD = 1000000007;
        int ans = 0;
        for (int i = 0; i < n * m; ) {
            int j = i;
            long allCount = 0;
            while (j < n * m && all[id[i]] == all[id[j]]) {
                allCount += count[id[j]];
                ++j;
            }
            int q = all[id[i]];
            int res = 0;
            for (int f : primes) {
                if (q < factor.length) break;
                if (q % f == 0) {
                    while (q % f == 0) q /= f;
                    res += f;
                }
            }
            if (q >= factor.length) {
                res += q;
            } else
                res += factor[q];
            res %= MOD;
            ans = (int) ((ans + (long) res * allCount) % MOD);
            i = j;
        }
        return ans;
    }
}
