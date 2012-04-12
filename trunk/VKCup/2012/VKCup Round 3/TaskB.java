package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.Arrays;

public class TaskB {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] a = new int[2 * n];
        int maximal = 0;
        for (int i = 0; i < n; i++) {
            a[i] = a[i + n] = in.nextInt();
            maximal = Math.max(maximal, a[i]);
        }
        int[] b = new int[m];
        for (int i = 0; i < m; i++) {
            b[i] = in.nextInt();
            maximal = Math.max(maximal, b[i]);
        }
        int[] c = new int[maximal + 1];
        Arrays.fill(c, -1);
        for (int i = 0; i < m; i++) {
            c[b[i]] = i;
        }
        b = null;
        int[] deque = new int[2 * m];
        int head = 0;
        int tail = 0;
        int begin = 0;
        int ans = 0;
        for (int i = 0; i < 2 * n; i++) {
            while (i - begin > n) {
                head++;
                if (head == deque.length) {
                    head = 0;
                }
                begin++;
            }
            int pos = c[a[i]];
            if (pos < 0) {
                begin = i + 1;
                head = tail = 0;
                continue;
            }
            int from = get(deque, head);
            int to = get(deque, tail - 1);
            while (head != tail && !can(pos, from, to)) {
                head++;
                if (head == deque.length) {
                    head = 0;
                }
                ++begin;
                from = get(deque, head);
                to = get(deque, tail - 1);
            }
            deque[tail++] = pos;
            if (tail == deque.length) {
                tail = 0;
            }
            ans = Math.max(ans, size(head, tail, deque));
        }
        out.println(ans);
	}

    static int size(int head, int tail, int[] deque) {
        int ret = tail - head;
        if (ret < 0) {
            ret += deque.length;
        }
        return ret;
    }

    static boolean can(int pos, int from, int to) {
        return from <= to ? (pos < from || pos > to) : (to < pos && pos < from);
    }

    static  int get(int[] deque, int i) {
        if (i < 0) {
            i += deque.length;
        }
        return deque[i];
    }

}
