package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.Arrays;
import java.util.Comparator;

public class TaskD {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int sumA = in.nextInt();
        int sumB = in.nextInt();
        int n = in.nextInt();
        final int[] a = new int[n];
        final int[] b = new int[n];
        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) {
            order[i] = i;
            a[i] = in.nextInt();
            b[i] = in.nextInt();
        }
        Arrays.sort(order, new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                int z1 = b[o1] - a[o1];
                int z2 = b[o2] - a[o2];
                return 0;
            }
        });
    }
}
