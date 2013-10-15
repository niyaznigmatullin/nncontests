package lib.test.on2013_01.on2013_01_13_.Seating;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;
import java.util.Random;

public class Seating {

    static class Tree {
        int[] maxSegment;
        int[] maxLeft;
        int[] maxRight;
        int[] count;
        boolean[] setZero;
        boolean[] setOne;
        int n;

        Tree(int n) {
            n = Integer.highestOneBit(n) << 1;
            this.n = n;
            count = new int[n * 2];
            maxLeft = new int[n * 2];
            maxRight = new int[n * 2];
            setZero = new boolean[n * 2];
            setOne = new boolean[n * 2];
            maxSegment = new int[n * 2];
            for (int i = n; i < 2 * n; i++) {
                count[i] = 1;
            }
            for (int i = n - 1; i > 0; i--) {
                count[i] = count[i * 2] + count[i * 2 + 1];
            }
            for (int i = 1; i < 2 * n; i++) {
                maxLeft[i] = maxRight[i] = maxSegment[i] = count[i];
            }
        }

        void fillOne(int left, int right) {
            fillOne(1, 0, n, left, right);
        }

        void fillOne(int v, int l, int r, int left, int right) {
            if (r <= left || right <= l) {
                return;
            }
            if (left <= l && r <= right) {
                setOne(v);
                return;
            }
            relax(v);
            int mid = l + r >> 1;
            fillOne(v * 2, l, mid, left, right);
            fillOne(v * 2 + 1, mid, r, left, right);
            update(v);
        }

        void fillZero(int left, int right) {
            fillZero(1, 0, n, left, right);
        }

        void fillZero(int v, int l, int r, int left, int right) {
            if (r <= left || right <= l) {
                return;
            }
            if (left <= l && r <= right) {
                setZero(v);
                return;
            }
            relax(v);
            int mid = l + r >> 1;
            fillZero(v * 2, l, mid, left, right);
            fillZero(v * 2 + 1, mid, r, left, right);
            update(v);
        }

        void setOne(int v) {
            if (v >= 2 * n) {
                return;
            }
            setOne[v] = true;
            setZero[v] = false;
        }

        void setZero(int v) {
            if (v >= 2 * n) {
                return;
            }
            setOne[v] = false;
            setZero[v] = true;
        }

        void relax(int v) {
            if (setZero[v]) {
                maxLeft[v] = maxRight[v] = maxSegment[v] = count[v];
                setZero[v] = false;
                setZero(v * 2);
                setZero(v * 2 + 1);
                return;
            }
            if (setOne[v]) {
                maxLeft[v] = maxRight[v] = maxSegment[v] = 0;
                setOne[v] = false;
                setOne(v * 2);
                setOne(v * 2 + 1);
                return;
            }
        }

        int getRight(int v) {
            return setZero[v] ? count[v] : setOne[v] ? 0 : maxRight[v];
        }

        int getLeft(int v) {
            return setZero[v] ? count[v] : setOne[v] ? 0 : maxLeft[v];
        }

        int getSegment(int v) {
            return setZero[v] ? count[v] : setOne[v] ? 0 : maxSegment[v];
        }

        void update(int v) {
            int right1 = getRight(v * 2);
            int right2 = getRight(v * 2 + 1);
            int left2 = getLeft(v * 2 + 1);
            int left1 = getLeft(v * 2);
            int cnt = count[v * 2];
            maxSegment[v] = Math.max(Math.max(getSegment(v * 2), getSegment(v * 2 + 1)), right1 + left2);
            maxLeft[v] = left1 == cnt ? cnt + left2 : left1;
            maxRight[v] = right2 == cnt ? cnt + right1 : right2;
        }

        int getFirst(int p) {
            return getFirst(1, 0, n, 0, p);
        }

        int getFirst(int v, int left, int right, int fromRight, int p) {
            relax(v);
            if (maxSegment[v] < p && maxLeft[v] + fromRight < p) {
                return -1;
            }
            if (maxLeft[v] + fromRight >= p) {
                return left - fromRight;
            }
            int mid = left + right >> 1;
            int got = getFirst(v * 2, left, mid, fromRight, p);
            if (got >= 0) {
                return got;
            }
            return getFirst(v * 2 + 1, mid, right, getRight(v * 2), p);
        }
    }

//    static class Tree2 {
//        boolean[] set;
//        int n;
//
//        Tree2(int n) {
//            this.n = n;
//            set = new boolean[n];
//        }
//
//        int getFirst(int p) {
//            for (int i = 0; i < n; ) {
//                if (set[i]) {
//                    ++i;
//                    continue;
//                }
//                int j = i;
//                while (j < n && !set[j]) {
//                    ++j;
//                }
//                if (j - i >= p) {
//                    return i;
//                }
//                i = j;
//            }
//            return -1;
//        }
//
//        void fillZero(int left, int right) {
//            for (int i = left; i < right; i++) {
//                set[i] = false;
//            }
//        }
//
//        void fillOne(int left, int right) {
//            for (int i = left; i < right; i++) {
//                set[i] = true;
//            }
//        }
//    }
//
//    void stress() {
//        int n = 2000;
//        Random rand = new Random(58L);
//        Tree tree = new Tree(n);
//        tree.fillOne(0, tree.n);
//        tree.fillZero(0, n);
//        Tree2 tree2 = new Tree2(n);
//        while (true) {
//            int type = rand.nextInt(3);
//            if (type == 0) {
//                int p = rand.nextInt(n) + 1;
//                int got1 = tree.getFirst(p);
//                int got2 = tree2.getFirst(p);
//                if (got1 != got2) {
//                    System.err.println("getFirst(" + p + ")");
//                    System.err.println(got1 + " " + got2);
//                    throw new AssertionError();
//                }
//            } else {
//                int left = rand.nextInt(n);
//                int right = rand.nextInt(n - left) + left + 1;
//                if (type == 1) {
//                    tree.fillOne(left, right);
//                    tree2.fillOne(left, right);
//                    System.err.println("fill1(" + left + ", " + right + ")");
//                } else {
//                    tree.fillZero(left, right);
//                    tree2.fillZero(left, right);
//                    System.err.println("fill0(" + left + ", " + right + ")");
//                }
//            }
//            System.err.println("OK");
//        }
//    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        Tree tree = new Tree(n);
        tree.fillOne(0, tree.n);
        tree.fillZero(0, n);
        int answer = 0;
        for (int i = 0; i < m; i++) {
            String op = in.next();
            if (op.equals("A")) {
                int p = in.nextInt();
                int got = tree.getFirst(p);
                if (got < 0) {
                    answer++;
                } else {
                    tree.fillOne(got, got + p);
                }
            } else {
                int left = in.nextInt() - 1;
                int right = in.nextInt();
                tree.fillZero(left, right);
            }
        }
        out.println(answer);
    }
}
