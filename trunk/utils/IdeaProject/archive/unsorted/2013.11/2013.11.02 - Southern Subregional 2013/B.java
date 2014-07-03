package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class B {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int m = in.nextInt();
        int n = in.nextInt();
        int maxDistance = in.nextInt() * 10;
        maxDistance *= maxDistance;
        int[] c = new int[m];
        for (int i = 0; i < m; i++) {
            c[i] = (int) Math.round(in.nextDouble() * 10);
        }
        int[] x = new int[n];
        int[] left = new int[n];
        int[] right = new int[n];
        long ans = 0;
        int curPos = 0;
        for (int i = 0; i < n; i++) {
            x[i] = (int) Math.round(in.nextDouble() * 10);
            int rightBound;
            {
                int l = -1;
                int r = m;
                while (l < r - 1) {
                    int mid = l + r >> 1;
                    if (c[mid] > x[i]) {
                        r = mid;
                    } else {
                        l = mid;
                    }
                }
                rightBound = r;
            }
            {
                int l = -1;
                int r = rightBound;
                while (l < r - 1) {
                    int mid = l + r >> 1;
                    long z = c[mid] - x[i];
                    z *= z;
                    z += 100;
                    if (z > maxDistance) {
                        l = mid;
                    } else {
                        r = mid;
                    }
                }
                left[i] = r;
            }
            int leftBound;
            {
                int l = -1;
                int r = m;
                while (l < r - 1) {
                    int mid = l + r >> 1;
                    if (c[mid] < x[i]) {
                        l = mid;
                    } else {
                        r = mid;
                    }
                }
                leftBound = l;
            }
            {
                int l = leftBound;
                int r = m;
                while (l < r - 1) {
                    int mid = l + r >> 1;
                    long z = c[mid] - x[i];
                    z *= z;
                    z += 100;
                    if (z > maxDistance) {
                        r = mid;
                    } else {
                        l = mid;
                    }
                }
                right[i] = l;
            }
            if (curPos < left[i]) {
                ans += c[left[i]] - c[curPos];
                curPos = left[i];
            }
            if (curPos > right[i]) {
                ans += c[curPos] - c[right[i]];
                curPos = right[i];
            }
        }
        out.println(ans / 10 + "." + ans % 10);
    }
}
