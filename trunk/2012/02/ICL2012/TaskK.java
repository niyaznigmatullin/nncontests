package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.Arrays;
import java.util.Comparator;

public class TaskK {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int m = in.nextInt();
        int n = in.nextInt();
        int k = in.nextInt();
        int[] last = new int[n];
        Arrays.fill(last, -1);
        int[] count = new int[n];
        for (int i = 0; i < k; i++) {
            int x = in.nextInt() - 1;
            int y = in.nextInt();
            if ((count[x] + 1) % m != y % m) {
                continue;
            }
            count[x]++;
            last[x] = i;
        }
        Integer[] order = getOrder(last, count);
        for (int i = 0; i < n; i++) {
            if (i > 0) {
                out.print(' ');
            }
            out.print(order[i] + 1);
        }
        out.println();
    }

    static Integer[] getOrder(final int[] last, final int[] count) {
        Integer[] ret = new Integer[last.length];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = i;
        }
        Arrays.sort(ret, new Comparator<Integer>() {

            public int compare(Integer o1, Integer o2) {
                if (count[o1] != count[o2]) {
                    return count[o2] - count[o1];
                }
                return last[o1] - last[o2];
            }
        });
        return ret;
    }
}
