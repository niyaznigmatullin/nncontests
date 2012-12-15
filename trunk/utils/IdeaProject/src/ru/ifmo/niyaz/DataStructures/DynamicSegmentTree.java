package DataStructures;

/**
 * Created by IntelliJ IDEA.
 * User: niyaznigmatul
 * Date: 20.01.12
 * Time: 0:49
 * To change this template use File | Settings | File Templates.
 */
public class DynamicSegmentTree {
    static class Node {
        Node left;
        Node right;
        long sum;

        public void collect() {
            sum = 0;
            if (left != null) {
                sum += left.sum;
            }
            if (right != null) {
                sum += right.sum;
            }
        }
    }

    Node root;
    int n;

    public DynamicSegmentTree(int n) {
        root = new Node();
        this.n = n;
    }

    public void set(int x, long y) {
        root = set(root, 0, n, x, y);
    }

    static Node set(Node v, int left, int right, int x, long y) {
        if (v == null) {
            v = new Node();
        }
        if (left == right - 1) {
            v.sum = y;
            return v;
        }
        int mid = left + right >> 1;
        if (x < mid) {
            v.left = set(v.left, left, mid, x, y);
        } else {
            v.right = set(v.right, mid, right, x, y);
        }
        v.collect();
        return v;
    }

    public long getSum(int l, int r) {
        return getSum(root, 0, n, l, r);
    }

    static long getSum(Node v, int left, int right, int l, int r) {
        if (v == null || r <= left || right <= l) {
            return 0;
        }
        if (l <= left && right <= r) {
            return v.sum;
        }
        int mid = left + right >> 1;
        return getSum(v.left, left, mid, l, r) + getSum(v.right, mid, right, l, r);
    }
}
