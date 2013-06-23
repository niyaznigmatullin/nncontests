package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.MathUtils;

import java.math.BigInteger;
import java.util.Arrays;

public class TaskC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = in.readIntArray(n);
        int[] b = in.readIntArray(n);
        long[] dp = new long[n];
        dp[0] = 0;
        int cur = 1;
        long[] x = new long[n];
        long[] y = new long[n];
        long[] inum = new long[n];
        long[] iden = new long[n];
        x[0] = b[0];
        y[0] = 0;
        iden[0] = 1;
        for (int i = 1; i < n; i++) {
            int left = 0;
            int right = cur;
            while (left < right - 1) {
                int mid = left + right >>> 1;
                if (inum[mid] <= iden[mid] * a[i]) {
                    left = mid;
                } else {
                    right = mid;
                }
            }
            dp[i] = x[left] * a[i] + y[left];
//            System.out.println(i  + " " + dp[i] + " " + x[left] + " " + y[left]);
            long nx = b[i];
            long ny = dp[i];
            while (cur > 0) {
                long nz = (ny - y[cur - 1]);
                long dz = (x[cur - 1] - nx);
                long gd = MathUtils.gcd(Math.abs(nz), Math.abs(dz));
                if (gd > 1) {
                    nz /= gd;
                    dz /= gd;
                }
//                System.out.println(nz + " " + dz);
                if (less(nz, dz, inum[cur - 1], iden[cur - 1])) {
//                if (nz * iden[cur - 1] <= dz * inum[cur - 1]) {
                    --cur;
                } else {
                    x[cur] = nx;
                    y[cur] = ny;
                    inum[cur] = nz;
                    iden[cur] = dz;
                    ++cur;
                    break;
                }
            }
            if (cur == 0) {
                x[cur] = nx;
                y[cur++] = ny;
            }
//            System.out.println(cur + " " + Arrays.toString(x) + " " + Arrays.toString(y));
        }
        out.println(dp[n - 1]);
    }

    static boolean less(long a, long b, long c, long d) {
        return BigInteger.valueOf(a).multiply(BigInteger.valueOf(d)).compareTo(BigInteger.valueOf(b).multiply(BigInteger.valueOf(c))) <= 0;
    }
}
