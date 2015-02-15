package coding;

import java.util.Arrays;
import java.util.Comparator;

public class TrianglesContainOrigin {
    public long count(final int[] x, final int[] y) {
        int n = x.length;
        Integer[] id = new Integer[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
        Arrays.sort(id, new Comparator<Integer>() {

            int get(int x, int y) {
                if (x > 0) {
                    return y < 0 ? 8 : y > 0 ? 2 : 1;
                } else if (x < 0) {
                    return y < 0 ? 6 : y > 0 ? 4 : 5;
                } else {
                    return y < 0 ? 7 : y > 0 ? 3 : 0;

                }
            }

            @Override
            public int compare(Integer o1, Integer o2) {
                int c = get(x[o1], y[o1]) - get(x[o2], y[o2]);
                if (c != 0) return c;
                return x[o2] * y[o1] - x[o1] * y[o2];
            }
        });
        long ans = 0;
        for (int it = 0; it < n; it++) {
            int i = id[it];
            int cur = 0;
            for (int jt = it + 1; jt != it; jt++) {
                if (jt == n) jt = 0;
                int j = id[jt];
                if (x[i] * y[j] - y[i] * x[j] < 0) {
                    break;
                }
                ans += cur;
                cur++;
            }
        }
        return (long) n * (n - 1) * (n - 2) / 6 - ans;
    }
}
