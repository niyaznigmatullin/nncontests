package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.io.IOException;

public class TaskF {
    static char[] c;
    static int cur;

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        try {
            c = in.readLine().replace(" ", "").toCharArray();
        } catch (IOException e) {

        }
        cur = 0;
        Node root = parse();
        if (check(root, Long.MIN_VALUE / 2, Long.MAX_VALUE / 2) < 0) {
            out.println("No");
        } else {
            out.println("Yes");
        }
    }

    static class Node {
        Node left;
        Node right;
        long x;

        Node(Node left, Node right, long x) {
            this.left = left;
            this.right = right;
            this.x = x;
        }
    }

    int check(Node v, long min, long max) {
        if (v == null) {
            return 0;
        }
        if (v.x < min || v.x > max) {
            return -1;
        }
        int left = check(v.left, min, v.x - 1);
        if (left < 0) return -1;
        int right = check(v.right, v.x + 1, max);
        if (right < 0) return -1;
        if (Math.abs(left - right) > 1) {
            return -1;
        }
        return Math.max(left, right) + 1;
    }

    Node parse() {
        ++cur;
        if (c[cur] == '>') {
            ++cur;
            return null;
        }
        Node left = parse();
        ++cur;
        boolean signum = c[cur] == '-';
        if (signum) {
            ++cur;
        }
        long x = 0;
        while (c[cur] >= '0' && c[cur] <= '9') {
            x = x * 10 + c[cur++] - '0';
        }
        ++cur;
        Node right = parse();
        ++cur;
        return new Node(left, right, signum ? -x : x);
    }

}
