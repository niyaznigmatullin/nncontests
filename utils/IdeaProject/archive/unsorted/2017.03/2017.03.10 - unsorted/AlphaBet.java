package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class AlphaBet {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = in.readIntArray(n);
        boolean isOne = true;
        for (int i = 1; i < n; i++) {
            if (a[i] <= a[i - 1]) {
                isOne = false;
                break;
            }
        }
        if (isOne) {
            out.println(1);
            return;
        }
        int l = 1;
        int r = n;
        binarySearch: while (l < r - 1) {
            int mid = (l + r) >> 1;
            Node root = new Node(0);
            for (int i = 1; i < n; i++) {
                if (a[i] > a[i - 1]) continue;
                int x = a[i] - 1;
                while (x >= 0 && !inc(root, 0, 1000000001, x, mid)) {
                    --x;
                }
                if (x < 0) {
                    l = mid;
                    continue binarySearch;
                }
            }
            r = mid;
        }
        out.println(r);
    }

    static boolean inc(Node v, int left, int right, int x, int val) {
        if (left >= right - 1) {
            return ++v.value != val;
        }
        int mid = (left + right) >> 1;
        if (x < mid) {
            v.right = null;
            if (v.left == null) v.left = new Node(0);
            return inc(v.left, left, mid, x, val);
        } else {
            if (v.right == null) v.right = new Node(0);
            return inc(v.right, mid, right, x, val);
        }
    }

    static class Node {
        Node left;
        Node right;
        int value;

        public Node(int value) {
            this.value = value;
        }
    }
}
