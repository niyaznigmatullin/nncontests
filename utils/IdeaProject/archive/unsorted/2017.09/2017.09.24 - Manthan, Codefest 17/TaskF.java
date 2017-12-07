package coding;

import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;
import java.util.Random;

public class TaskF {
    static final int K = 333;

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int q = in.nextInt();
        int[] type = new int[q];
        int[] left = new int[q];
        int[] right = new int[q];
        int[] value = new int[q];
        for (int i = 0; i < q; i++) {
            type[i] = in.nextInt();
            left[i] = in.nextInt();
            right[i] = in.nextInt();
            if (type[i] == 1) {
                value[i] = in.nextInt();
            }
        }
        int[] xs = new int[K + K + 2];
        Random rng = new Random(System.currentTimeMillis());
        final int N = 123456;
        int[] mx = new int[N];
        int[] mn = new int[N];
        Arrays.fill(mx, Integer.MAX_VALUE);
        Arrays.fill(mn, Integer.MIN_VALUE);
        int[] blockMax = new int[N];
        int[] blockMin = new int[N];
        for (int from = 0; from < q; from += K) {
            int to = Math.min(q, from + K);
            int cn = 0;
            xs[cn++] = N;
            for (int i = 0; i < to; i++) {
                xs[cn++] = left[i];
                xs[cn++] = right[i];
            }
            for (int i = 0; i < cn; i++) {
                int j = rng.nextInt(i + 1);
                int t = xs[i];
                xs[i] = xs[j];
                xs[j] = t;
            }
            Arrays.sort(xs, 0, cn);
            long last = xs[0];
            int currentPos = 1;
            for (int i = 1; i < cn; i++) {
                if (last != xs[i]) {
                    xs[currentPos++] = xs[i];
                    last = xs[i];
                }
            }
            cn = currentPos;
            Arrays.fill(blockMax, 0, cn, Integer.MAX_VALUE);
            Arrays.fill(blockMin, 0, cn, Integer.MIN_VALUE);
            for (int i = 0, j = 0; i < N; i++) {
                if (xs[j] == i) {
                    ++j;
                }
                blockMax[j] = Math.min(blockMax[j], mx[i]);
                blockMin[j] = Math.max(blockMin[j], mn[i]);
            }
            for (int i = from; i < to; i++) {
                int lb = Arrays.binarySearch(xs, 0, cn, left[i]);
                int rb = Arrays.binarySearch(xs, 0, cn, right[i]);
                if (type[i] == 1) {
                    int k = value[i];
                    if (k > 0) {
                        for (int j = lb; j < rb; j++) {
                            blockMax[j] = Math.min(blockMax[j], k);
                        }
                    } else {
                        for (int j = lb; j < rb; j++) {
                            blockMin[j] = Math.max(blockMin[j], k);
                        }
                    }
                } else {
                    long ans = 0;
                    for (int j = lb; j < rb; j++) {
                        if (blockMax[j] != Integer.MAX_VALUE && blockMin[j] != Integer.MIN_VALUE) {
                            ans += (long) (blockMax[j] - blockMin[j]) * (xs[j + 1] - xs[j]);
                        }
                    }
                    out.println(ans);
                }
            }
            for (int i = 0, j = 0; i < N; i++) {
                if (xs[j] == i) ++j;
                mx[i] = Math.min(mx[i], blockMax[j]);
                mn[i] = Math.max(mn[i], blockMin[j]);
            }
        }
    }
}
