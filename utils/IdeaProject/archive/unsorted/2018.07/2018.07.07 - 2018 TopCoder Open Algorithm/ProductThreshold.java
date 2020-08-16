package coding;

import java.util.Arrays;
import java.util.List;

public class ProductThreshold {

    int[] generate(int n, int[] prefix, int spread, int offset) {
        int p = prefix.length;
        int[] a = new int[n];
        for (int i = 0; i < p; i++) {
            a[i] = prefix[i];
        }
        int seed = Math.abs(a[p - 1]);
        for (int i = p; i < n; i++) {
            seed = (int) (((long) seed * 1103515245 + 12345) & Integer.MAX_VALUE);
            a[i] = seed % spread + offset;
        }
        return a;
    }

    public long subarrayCount(int n, int limit, int[] Aprefix, int spread, int offset) {
        int[] a = generate(n, Aprefix, spread, offset);
        int lastZero = -1;
        long ans = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] == 0) {
                lastZero = i;
            }
            ans += lastZero + 1;
        }
        for (int i = 0; i < n; ) {
            if (a[i] == 0) {
                ++i;
                continue;
            }
            int j = i;
            while (j < n && a[j] != 0) ++j;
            ans += getNonZero(Arrays.copyOfRange(a, i, j), limit);
            i = j;
        }
        return ans;
    }

    long getNonZero(int[] a, int limit) {
        return getPositive(a, limit) + getNegative(a);
    }

    long getNegative(int[] a) {
        int countEven = 0;
        int countOdd = 0;
        int count = 0;
        long ans = 0;
        countEven++;
        for (int i : a) {
            if (i < 0) count++;
            if ((count & 1) == 0) {
                ans += countOdd;
                ++countEven;
            } else {
                ans += countEven;
                ++countOdd;
            }
        }
        return ans;
    }

    long getPositive(int[] a, int limit) {
        int n = a.length;
        int[] next = new int[n];
        int last = n;
        for (int i = n - 1; i >= 0; i--) {
            next[i] = last;
            if (Math.abs(a[i]) > 1) {
                last = i;
            }
        }
        int count = 0;
        int[] odd = new int[n];
        for (int i = 0; i < n; i++) {
            if (a[i] < 0) count ^= 1;
            odd[i] = count;
        }
        for (int i = 1; i < n; i++) odd[i] += odd[i - 1];
        count = 0;
        long ans = 0;
        for (int i = 0; i < n; i++) {
            long product = a[i];
            int j = i;
            while (j < n && Math.abs(product) <= limit) {
                j = next[j];
                if (j < n) {
                    product *= a[j];
                }
            }
            int gg = getSum(odd, i, j);
            if ((count & 1) == 0) {
                gg = (j - i) - gg;
            }
            ans += gg;
            if (a[i] < 0) count ^= 1;
        }
        return ans;
    }

    int getSum(int[] a, int left, int right) {
        if (left >= right) return 0;
        int ret = a[right - 1];
        if (left > 0) ret -= a[left - 1];
        return ret;
    }
}
