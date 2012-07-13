package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.Arrays;

public class TaskD {

    public class MultiSegmentTree {
        int[] min1;
        int[] min2;
        int[] count1;
        int[] count2;
        int[] add;
        final int n;

        public MultiSegmentTree(int n) {
            this.n = Integer.highestOneBit(n) << 1;
            int m = this.n * 2;
            min1 = new int[m];
            min2 = new int[m];
            Arrays.fill(min2, Integer.MAX_VALUE);
            count1 = new int[m];
            count2 = new int[m];
            Arrays.fill(count1, 1);
            add = new int[m];
        }

        void add(int node, int x) {
            add[node] += x;
        }

        private void relax(int node) {
            if (add[node] == 0) {
                return;
            }
            min1[node] = getMin1(node);
            min2[node] = getMin2(node);
            add((node << 1) | 1, add[node]);
            add((node << 1) + 2, add[node]);
            add[node] = 0;
        }

        private int getMin1(int node) {
            return min1[node] + add[node];
        }

        private int getMin2(int node) {
            return min2[node] + add[node];
        }

        private void add(int node, int l, int r, int left, int right, int x) {
            if (right <= l || r <= left) {
                return;
            }
            if (left <= l && r <= right) {
                add(node, x);
                return;
            }
            relax(node);
            int m = (l + r) >> 1;
            add((node << 1) | 1, l, m, left, right, x);
            add((node << 1) + 2, m, r, left, right, x);
            int a = min1[(node << 1) | 1];
            int ac = count1[(node << 1) | 1];
            int b = min2[(node << 1) | 1];
            int bc = count2[(node << 1) | 1];
            int c = min1[(node << 1) + 2];
            int cc = count1[(node << 1) + 2];
            int d = min2[(node << 1) + 2];
            int dc = count2[(node << 1) + 2];
            min1[node] = Math.min(a, c);
            count1[node] = 0;
            if (a == min1[node]) {
                count1[node] += ac;
            }
            if (b == min1[node]) {
                count1[node] += bc;
            }
            if (c == min1[node]) {
                count1[node] += cc;
            }
            if (d == min1[node]) {
                count1[node] += dc;
            }
            if (a == min1[node] && c == min1[node]) {
                min2[node] = Math.min(b, d);
            } else if (a != min1[node]) {
                min2[node] = a;
            } else {
                min2[node] = c;
            }
            count2[node] = 0;
            if (a == min2[node]) {
                count2[node] += ac;
            }
            if (b == min2[node]) {
                count2[node] += bc;
            }
            if (c == min2[node]) {
                count2[node] += cc;
            }
            if (d == min2[node]) {
                count2[node] += dc;
            }
        }

        private int getMin1(int node, int l, int r, int left, int right) {
            if (right <= l || r <= left) {
                return Integer.MAX_VALUE;
            }
            if (left <= l && r <= right) {
                return getMin1(node);
            }
            relax(node);
            int m = (l + r) >> 1;
            return Math.min(getMin1((node << 1) | 1, l, m, left, right),
                    getMin1((node << 1) + 2, m, r, left, right));
        }

        private int getMin2(int node, int l, int r, int left, int right) {
            if (right <= l || r <= left) {
                return Integer.MIN_VALUE;
            }
            if (left <= l && r <= right) {
                return getMin2(node);
            }
            relax(node);
            int m = (l + r) >> 1;
            return Math.max(getMin2((node << 1) | 1, l, m, left, right),
                    getMin2((node << 1) + 2, m, r, left, right));
        }

        public void add(int l, int r, int x) {
            if (l >= r) {
                return;
            }
            add(0, 0, n, l, r, x);
        }

        public int getMin1(int l, int r) {
            if (l >= r) {
                return Integer.MAX_VALUE;
            }
            return getMin1(0, 0, n, l, r);
        }

        public int getMin2(int l, int r) {
            if (l >= r) {
                return Integer.MAX_VALUE;
            }
            return getMin2(0, 0, n, l, r);
        }

        public int getCountMin1(int l, int r) {
            int min = getMin1(l, r);
            return getCountMin1(0, 0, n, l, r, min);
        }

        public int getCountMin2(int l, int r) {
            int min = getMin1(l, r);
            return getCountMin2(0, 0, n, l, r, min);
        }

        public int getCountMin1(int node, int l, int r, int left, int right, int minimum) {
            if (right <= l || r <= left) {
                return 0;
            }
            if (left <= l && r <= right) {
                return getMin1(node) == minimum ? count1[node] : 0;
            }
            relax(node);
            int m = (l + r) >> 1;
            return getCountMin1(node * 2 + 1, l, m, left, right, minimum) + getCountMin1(node * 2 + 2, m, r, left, right, minimum);
        }

        public int getCountMin2(int node, int l, int r, int left, int right, int minimum) {
            if (right <= l || r <= left) {
                return 0;
            }
            if (left <= l && r <= right) {
                return getMin1(node) == minimum ? count1[node] : getMin2(node) == minimum ? count2[node] : 0;
            }
            relax(node);
            int m = (l + r) >> 1;
            return getCountMin2(node * 2 + 1, l, m, left, right, minimum) + getCountMin2(node * 2 + 2, m, r, left, right, minimum);
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] p = new int[n];
        int[] prev = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = in.nextInt() - 1;
            prev[p[i]] = i;
        }
        int[] done = new int[n];
        done[p[0]] = 1;
        done[p[n - 1]] = 1;
        MultiSegmentTree d = new MultiSegmentTree(n);
        d.add(0, n, 1);
        for (int i = 0; i < n; i++) {
            if (i > 0) {
                if (p[i - 1] < p[i]) {
                    done[p[i]]++;
                }
            }
            if (i + 1 < n) {
                if (p[i + 1] < p[i]) {
                    done[p[i]]++;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (done[i] == 0) {
                d.add(i, n, 1);
            }
            if (done[i] == 2) {
                d.add(i, n, -1);
            }
        }
        long answer = 0;
        for (int i = n - 1; i > 0; i--) {
            System.err.println(d.getMin1(0, i) + " " + d.getMin2(0, i));
            if (d.getMin1(0, i) == 1) {
                answer += d.getCountMin1(0, i);
            }
            if (d.getMin1(0, i) == 2) {
                answer += d.getCountMin1(0, i);
            }
            if (d.getMin2(0, i) == 2) {
                answer += d.getCountMin2(0, i);
            }
            for (int k = 0; k < n; k++) {
                System.err.print(d.getMin1(k, k + 1) + " ");
            }
            System.err.println();
            int j = prev[i];
            if (j > 0) {
                if (p[j - 1] < i) {
                    done[p[j - 1]]++;
                    d.add(p[j - 1], n, -1);
                }
            }
            if (j + 1 < n) {
                if (p[j + 1] < i) {
                    done[p[j + 1]]++;
                    d.add(p[j + 1], n, -1);
                }
            }
        }
        out.println(answer);
    }
}
