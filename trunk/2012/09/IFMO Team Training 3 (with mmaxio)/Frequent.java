package mypackage;

import DataStructures.MaxSegmentTree;
import DataStructures.MinSegmentTree;
import niyazio.FastScanner;
import niyazio.FastPrinter;

public class Frequent {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        if (n == 0) {
            throw new UnknownError();
        }
        int q = in.nextInt();
        MaxSegmentTree tree = new MaxSegmentTree(n);
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        int[] next = new int[n];
        int[] prev = new int[n];
        int[] id = new int[n];
        next[n - 1] = n;
        for (int i = n - 2; i >= 0; i--) {
            if (a[i] != a[i + 1]) {
                next[i] = i + 1;
            } else {
                next[i] = next[i + 1];
            }
        }
        prev[0] = -1;
        for (int i = 1; i < n; i++) {
            if (a[i] != a[i - 1]) {
                prev[i] = i - 1;
            } else {
                prev[i] = prev[i - 1];
            }
        }
        for (int i = 0, k = 0; i < n; k++) {
            int j = i;
            while (j < n && a[i] == a[j]) {
                id[j] = k;
                ++j;
            }
            tree.set(k, j - i);
            i = j;
        }
        for (int i = 0; i < q; i++) {
            int left = in.nextInt() - 1;
            int right = in.nextInt() - 1;
            if (a[left] == a[right]) {
                out.println(right - left + 1);
            } else {
                int answer = 0;
                answer = Math.max(next[left] - left, answer);
                answer = Math.max(answer, right - prev[right]);
                answer = Math.max(tree.getMax(id[left] + 1, id[right]), answer);
                out.println(answer);
            }
        }
    }
}
