package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NavigableSet;
import java.util.TreeSet;

public class TaskE {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int w = in.nextInt();
        final int[] a = new int[n];
        final int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            b[i] = in.nextInt();
        }
        final int[] dif = new int[n];
        for (int i = 0; i < n; i++) dif[i] = b[i] - a[i];
        Integer[] id = new Integer[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
        Arrays.sort(id, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (dif[o1] != dif[o2]) {
                    return Integer.compare(dif[o1], dif[o2]);
                }
                return Integer.compare(o1, o2);
            }
        });
        Integer[] byA = new Integer[n];
        Integer[] byB = new Integer[n];
        for (int i = 0; i < n; i++) {
            byA[i] = byB[i] = i;
        }
        Arrays.sort(byA, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(a[o1], a[o2]);
            }
        });
        Arrays.sort(byB, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(b[o1], b[o2]);
            }
        });
        int[] ra = new int[n];
        int[] rb = new int[n];
        for (int i = 0; i < n; i++) {
            ra[byA[i]] = i;
            rb[byB[i]] = i;
        }
        long ans = Long.MAX_VALUE;
        int phase = -1;
        {
            Tree treeB = new Tree(n);
            Tree treeA = new Tree(n);
            for (int i = 0; i < n; i++) {
                treeA.set(ra[i], a[i], 1);
            }
            for (int i = 0; i <= n; i++) {
                int l = 0;
                int r = i;
                r = Math.min(r, w / 2);
                l = Math.max(l, (w - (n - i) + 1) / 2);
                l--;
//                System.out.println("i, l, r = " + i + " " + l + " " + r);
                while (l < r - 1) {
                    int mid = (l + r) >> 1;
                    long f1 = getValue(w, treeB, treeA, mid);
                    long f2 = getValue(w, treeB, treeA, mid + 1);
                    if (f1 > f2) {
                        l = mid;
                    } else {
                        r = mid;
                    }
                }
                if (r <= i && w - 2 * r <= n - i) {
                    long val = getValue(w, treeB, treeA, r);
                    if (ans > val) {
                        ans = val;
                        phase = i;
                    }
                }
                if (i < n) {
                    treeA.set(ra[id[i]], 0, 0);
                    treeB.set(rb[id[i]], b[id[i]], 1);
                }
            }
        }
        out.println(ans);
        {
            Tree treeB = new Tree(n);
            Tree treeA = new Tree(n);
            for (int i = 0; i < n; i++) {
                treeA.set(ra[i], a[i], 1);
            }
            boolean[] side = new boolean[n];
            for (int i = 0; i <= n; i++) {
                int l = 0;
                int r = i;
                r = Math.min(r, w / 2);
                l = Math.max(l, (w - (n - i) + 1) / 2);
                l--;
                while (l < r - 1) {
                    int mid = (l + r) >> 1;
                    long f1 = getValue(w, treeB, treeA, mid);
                    long f2 = getValue(w, treeB, treeA, mid + 1);
                    if (f1 > f2) {
                        l = mid;
                    } else {
                        r = mid;
                    }
                }
                if (r <= i && w - 2 * r <= n - i) {
                    if (phase == i) {
                        char[] ansS = new char[n];
                        Arrays.fill(ansS, '0');
                        int count = r;
                        for (int j : byB) {
                            if (count == 0) break;
                            if (side[j]) {
                                --count;
                                ansS[j] = '2';
                            }
                        }
                        count = w - 2 * r;
                        for (int j : byA) {
                            if (count == 0) break;
                            if (!side[j]) {
                                --count;
                                ansS[j] = '1';
                            }
                        }
                        out.println(ansS);
                        return;
                    }
                }
                if (i < n) {
                    treeA.set(ra[id[i]], 0, 0);
                    treeB.set(rb[id[i]], b[id[i]], 1);
                    side[id[i]] = true;
                }
            }
        }
    }

    private long getValue(int w, Tree treeB, Tree treeA, int mid) {
        return treeB.getKSum(mid) + treeA.getKSum(w - 2 * mid);
    }

    static class Tree {
        long[] sum;
        int[] count;
        int n;

        Tree(int n) {
            this.n = Integer.highestOneBit(n) << 1;
            sum = new long[this.n * 2];
            count = new int[this.n * 2];
        }

        void set(int x, int y, int z) {
            x += n;
            sum[x] = y;
            count[x] = z;
            while (x > 1) {
                x >>= 1;
                sum[x] = sum[x * 2] + sum[x * 2 + 1];
                count[x] = count[x * 2] + count[x * 2 + 1];
            }
        }

        long getKSum(int k) {
            if (k == 0) return 0;
            int l = 0;
            int r = n;
            int v = 1;
            long ret = 0;
            while (l < r - 1) {
                int mid = l + r >> 1;
                int leftCount = count[2 * v];
                if (leftCount >= k) {
                    r = mid;
                    v = v * 2;
                } else {
                    ret += sum[v * 2];
                    v = v * 2 + 1;
                    l = mid;
                }
            }
            ret += sum[v];
            return ret;
        }
    }
}
