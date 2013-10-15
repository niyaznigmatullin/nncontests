package lib.test.on2013_06.on2013_06_08_2013_TopCoder_Open_Algorithm.YetAnotherANDProblem;



import java.util.*;

public class YetAnotherANDProblem {
    public String test(int[] a, int k, int[] queries) {
        int n = a.length;
        char[] ans = new char[queries.length];
        Arrays.fill(ans, '-');
        for (int mask = 1; mask < 1 << n; mask++) {
            if (!ok(mask, k)) {
                continue;
            }
            int val = -1;
            for (int i = 0; i < n; i++) {
                if (((mask >> i) & 1) == 1) {
                    val &= a[i];
                }
            }
            for (int i = 0; i < queries.length; i++) {
                if (queries[i] == val) {
                    ans[i] = '+';
                }
            }
        }
        return new String(ans);
    }

    static boolean ok(int mask, int k) {
        int n = Integer.bitCount(mask);
        if (k == 0) {
            return n == 1;
        }
        if (k == 1) {
            return n == 2;
        }
        if (n < 3) return false;
        int cn = 2;
        --k;
        while (k > 0) {
            cn *= 2;
            k--;
            if (cn >= n) {
                return true;
            }
        }
        return false;
    }
}
