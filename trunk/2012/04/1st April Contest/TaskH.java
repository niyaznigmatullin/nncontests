package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

public class TaskH {

    static long x;
    static long y;

    static void check2(int a, long num) {
        long x = 0;
        long y = 0;
        long mul = 1;
        boolean vert = true;
        while (num > 0) {
            int cur = (int) (num % 4);
            if (vert) {
                if (cur == 1 || cur == 2) {
                    long tmp = x;
                    x = y + mul;
                    y = tmp;
                    if (cur == 2) {
                        y += mul;
                    }
                } else if (cur == 3) {
                    x = mul - x - 1;
                    y = 2 * mul - y - 1;
                }
            } else {
                if (cur == 1 || cur == 2) {
                    long tmp = y;
                    y = x + mul;
                    x = tmp;
                    if (cur == 2) {
                        x += mul;
                    }
                } else if (cur == 3) {
                    y = mul - y - 1;
                    x = 2 * mul - x - 1;
                }
            }
            num /= 4;
            mul *= 2;
//				long tmp = x;
//				x = y;
//				y = tmp;
            vert = !vert;
        }
        if (a % 2 == 1) {
            long tmp = x;
            x = y;
            y = tmp;
        }
        if (x != TaskH.x || y != TaskH.y) {
            throw new AssertionError();
        }
    }

    static void go(int n, long m) {
        if (n == 0) {
            if (m != 0) {
                throw new AssertionError();
            }
            x = 0;
            y = 0;
            return;
        }
        long fourth = (1L << 2 * n - 2);
        long c = m / fourth;
        go(n - 1, m - c * fourth);
        long half = 1L << n - 1;
        if (c == 0) {
        } else if (c == 1) {
            long t = x;
            x = y;
            y = t;
            if ((n & 1) == 0) {
                y += half;
            } else {
                x += half;
            }
        } else if (c == 2) {
            long t = x;
            x = y;
            y = t;
            y += half;
            x += half;
        } else if (c == 3) {
            x = half - x - 1;
            y = half - y - 1;
            if ((n & 1) == 0) {
                x += half;
            } else {
                y += half;
            }
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
//        int n = 12;
//        for (int i = 0; i < (1 << 2 * n); i++) {
//            go(n, i);
//            check2(n, i);
//        }
        int n = in.nextInt();
        long m = in.nextLong();
        go(n, m);
        out.println(x + " " + y);
    }
}
