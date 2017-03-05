package coding;

import ru.ifmo.niyaz.DataStructures.FenwickRev;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Random;

public class TaskF {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
//        Random rng = new Random(12323L);
//        for (int tt = 0; tt < 7; tt++) {
//            int n = rng.nextInt(2) + 77;
//            HeroesSmart a = new HeroesSmart(n);
//            HeroesStupid b = new HeroesStupid(n);
//            for (int it = 0; it < 10000; it++) {
//                if (rng.nextBoolean()) {
//                    int add = rng.nextInt(10) + 1;
//                    int l = rng.nextInt(n);
//                    int r = rng.nextInt(n - l) + l + 1;
//                    a.add(l, r, add);
//                    b.add(l, r, add);
//                    System.out.println(l + " " + r + " " + add);
//                } else {
//                    int l = rng.nextInt(n - 1) / 2 * 2 + 1;
//                    int r = rng.nextInt(n - l) + l + 1;
//                    boolean ans1 = a.canBeat(l, r);
//                    boolean ans2 = b.canBeat(l, r);
//                    if (ans1 != ans2) {
//                        System.out.println(l + " " + r);
//                        throw new AssertionError();
//                    }
//                }
//            }
//        }
        int n = in.nextInt();
//        HeroesStupid real = new HeroesStupid(n);
//        HeroesStupid plusOne = new HeroesStupid(n + 1);
        HeroesSmart real = new HeroesSmart(n);
        HeroesSmart plusOne = new HeroesSmart(n + 1);
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            real.add(i, i + 1, x);
            plusOne.add(i + 1, i + 2, x);
        }
        int m = in.nextInt();
        for (int i = 0; i < m; i++) {
            int op = in.nextInt();
            if (op == 2) {
                int l = in.nextInt();
                int r = in.nextInt() + 1;
                boolean ans;
                if ((l & 1) == 1) {
                    ans = real.canBeat(l, r);
                } else {
                    ans = plusOne.canBeat(l + 1, r + 1);
                }
                out.println(ans ? 1 : 0);
            } else {
                int l = in.nextInt();
                int r = in.nextInt() + 1;
                int k = in.nextInt();
                real.add(l, r, k);
                plusOne.add(l + 1, r + 1, k);
            }
        }
    }

    static class HeroesSmart {
        FenwickRev all;
        MultiSegmentTree even;
        MultiSegmentTree odd;

        int pairs;

        HeroesSmart(int n) {
            all = new FenwickRev(n);
            pairs = (n + 1) / 2 + 1;
            even = new MultiSegmentTree(pairs);
            odd = new MultiSegmentTree(pairs);
        }

        void add(int l, int r, int k) {
            all.add(l, r, k);
            if ((l & 1) == 1) {
                odd.add(l / 2, pairs, k);
            } else {
                even.add(l / 2, pairs, k);
            }
            if ((r & 1) == 1) {
                odd.add(r / 2, pairs, -k);
            } else {
                even.add(r / 2, pairs, -k);
            }
        }

        boolean canBeat(int l, int r) { // [l, r)
            if ((l & 1) == 1) {
                long evenMin = even.getMin((l + 1) / 2, (r - 1) / 2 + 1);
                long sumEvenFirst = l == 0 ? 0 : even.getMin((l - 1) / 2, (l - 1) / 2 + 1);
                evenMin -= sumEvenFirst;
                long sumFirst = odd.getMin(l / 2, l / 2 + 1);
                int valFirst = all.getElement(l);
                long oddMin = odd.getMin(l / 2, (r - 2) / 2 + 1) - sumFirst + valFirst - 1;
                if (evenMin < 0 || oddMin < 0) {
                    return false;
                }
                if ((r & 1) == 0) {
                    return odd.getMin((r - 2) / 2, (r - 2) / 2 + 1) - sumFirst + valFirst - 1 == 0;
                } else {
                    return even.getMin((r - 1) / 2, (r - 1) / 2 + 1) - sumEvenFirst == 0;
                }
            }
            throw new AssertionError();
        }
    }

    static class HeroesStupid {
        int[] a;

        HeroesStupid(int n) {
            a = new int[n];
        }

        void add(int l, int r, int k) {
            for (int i = l; i < r; i++) {
                a[i] += k;
            }
        }

        boolean canBeat(int l, int r) { // [l, r)
            int cur = 0;
            for (int i = l; i < r; i++) {
                cur = a[i] - cur;
                int have = cur;
                if ((i & 1) == (l & 1)) {
                    --have;
                }
                if (have < 0) return false;
                if (i + 1 == r && have != 0) return false;
            }
            return true;
        }
    }

    static class MultiSegmentTree {
        private long[] min;
        private long[] add;
        final int n;

        public MultiSegmentTree(int n) {
            this.n = Integer.highestOneBit(n) << 1;
            min = new long[this.n << 1];
            add = new long[this.n << 1];
        }

        private void add(int node, long x) {
            add[node] += x;
        }

        private void relax(int node) {
            if (add[node] == 0) {
                return;
            }
            min[node] = getMin(node);
            add((node << 1) | 1, add[node]);
            add((node << 1) + 2, add[node]);
            add[node] = 0;
        }

        private long getMin(int node) {
            return min[node] + add[node];
        }

        private void add(int node, int l, int r, int left, int right, long x) {
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
            min[node] = Math.min(getMin((node << 1) | 1),
                    getMin((node << 1) + 2));
        }

        private long getMin(int node, int l, int r, int left, int right) {
            if (right <= l || r <= left) {
                return Long.MAX_VALUE;
            }
            if (left <= l && r <= right) {
                return getMin(node);
            }
            relax(node);
            int m = (l + r) >> 1;
            return Math.min(getMin((node << 1) | 1, l, m, left, right),
                    getMin((node << 1) + 2, m, r, left, right));
        }

        public void add(int l, int r, long x) {
            if (l >= r) {
                return;
            }
            add(0, 0, n, l, r, x);
        }

        public long getMin(int l, int r) {
            if (l >= r) {
                return Long.MAX_VALUE;
            }
            return getMin(0, 0, n, l, r);
        }
    }

}
