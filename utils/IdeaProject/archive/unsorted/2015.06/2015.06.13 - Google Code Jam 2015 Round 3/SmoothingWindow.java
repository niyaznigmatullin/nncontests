package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class SmoothingWindow {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        System.err.println(testNumber);
        int n = in.nextInt();
        int k = in.nextInt();
        int maximal = 0;
        int[] a = in.readIntArray(n - k + 1);
        for (int i : a) {
            maximal = Math.max(maximal, Math.abs(i));
        }
        int[] dif = new int[n];
        for (int i = k; i < n; i++) {
            dif[i] = (a[i - k + 1] - a[i - k]) + dif[i - k];
        }
        int[] left = new int[k];
        int[] right = new int[k];
        long z = a[0];
        for (int i = 0; i < n; i++) {
            left[i % k] = Math.min(left[i % k], dif[i]);
            right[i % k] = Math.max(right[i % k], dif[i]);
        }
        for (int i = 0; i < k; i++) z += left[i];
        long ans = Long.MAX_VALUE;
        for (long x = z / k - 10000; ; x++) {
            long minSum = 0;
            for (int i = 0; i < k; i++) {
                minSum += x - left[i];
            }
            long neededSum = a[0];
            if (neededSum < minSum) {
                break;
            }
            neededSum -= minSum;
            long l = -1;
            long r = Integer.MAX_VALUE;
            while (l < r - 1) {
                long mid = (l + r) >> 1;
                long leftSum = neededSum;
                for (int i = 0; i < k; i++) {
                    if (mid < right[i] - left[i]) {
                        leftSum = 1;
                        break;
                    }
                    long can = mid - (right[i] - left[i]);
                    can = Math.min(can, leftSum);
                    leftSum -= can;
                }
                if (leftSum == 0) r = mid; else l = mid;
            }
            ans = Math.min(ans, r);
        }
        out.println("Case #" + testNumber + ": " + ans);
    }
}
