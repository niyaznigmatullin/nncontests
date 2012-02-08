package mypackage;

import DataStructures.DynamicSegmentTree;
import DataStructures.Fenwick;
import arrayutils.ArrayUtils;
import niyazio.FastScanner;
import niyazio.FastPrinter;
import sun.reflect.generics.tree.Tree;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class MakxSum {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.fastNextInt();
        int k1 = in.fastNextInt();
        int k2 = in.fastNextInt();
        int k3 = in.fastNextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        out.println(solve(n, a, k1) + " " + solve(n, a, k2) + " " + solve(n, a, k3));
    }

    static int solve(int n, int[] a, int k) {
        int sum = 0;
        for (int i : a) {
            sum += Math.abs(i);
        }
        int left = -sum - 1;
        int right = sum + 1;
        int[] b = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            b[i] = a[i - 1] + b[i - 1];
        }
        int[] c = ArrayUtils.sortAndUnique(b);
        Arrays.sort(c);
        while (left < right - 1) {
            int mid = (left + right) / 2;
            int count = 0;
            Fenwick f = new Fenwick(n + 1);
            for (int i = 0; i <= n; i++) {
                int need = Arrays.binarySearch(c, b[i] - mid);
                if (need < 0) {
                    need = (~need) - 1;
                }
                count += f.getSum(need);
                int cur = Arrays.binarySearch(c, b[i]);
                f.add(cur, 1);
            }
            if (count >= k) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
