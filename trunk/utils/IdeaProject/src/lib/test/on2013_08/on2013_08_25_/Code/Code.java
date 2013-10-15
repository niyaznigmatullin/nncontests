package lib.test.on2013_08.on2013_08_25_.Code;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class Code {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] ret = getYellow(n);
        for (int z : ret) {
            for (int i = 0; i < n; i++) {
                out.print(z & 1);
                z >>= 1;
            }
            out.println();
        }
    }

    static boolean[] was;
    static int[] ret;

    static boolean go(int x, int n) {
        if (x == 1 << n) {
            if (Integer.bitCount(ret[x - 1]) * 2 < n) return false;
            return true;
        }
        for (int i = 0; i < (1 << n - 1); i++) {
            int j = i;
            if (Integer.bitCount(j) * 2 < n) {
                j = j ^ ((1 << n) - 1);
            }
            ret[x] = ret[x - 1] ^ j;
            if (was[ret[x]]) continue;
            was[ret[x]] = true;
            if (go(x + 1, n)) return true;
            was[ret[x]] = false;
        }
        return false;
    }

    static int[] getYellow(int n) {
        was = new boolean[1 << n];
        ret = new int[1 << n];
        was[0] = true;
        ret[0] = 0;
        if (!go(1, n)) throw new AssertionError();
//        return ret;
//        int[] ret = new int[1 << n];
//        if ((n & 1) == 1) {
//            for (int i = 0; i < 1 << n; i++) {
//                int z = i ^ (i / 2) ^ ((1 << (n - n / 2)) - 1);
////                int z = i ^ (i / 2);
//                ret[i] = z;
//            }
//        } else {
//            int[] got = getYellow(n - 1);
//            int[] todo = new int[got.length];
//            for (int i = 0; i < got.length; i++) {
//                ret[i] = got[i];
//                todo[ret[i]] = 1 - (i & 1);
//                ret[i] = ret[i] * 2 + (i & 1);
//                ret[ret.length - i - 1] = got[i] ^ ((1 << n - 1) - 1);
//            }
//            for (int i = got.length; i < ret.length; i++) {
//                ret[i] = ret[i] * 2 + todo[ret[i]];
//            }
//        }
//        int[] ret = new int[1 << n];
//        for (int i = 1; i <= n; i++) {
//            for (int j = 0, t = 0; j < 1 << n; j += 1 << i, t ^= 1) {
//                for (int k = 0; k < 1 << i; k++) {
//                    ret[j + k] |= (1 << (i - 1)) * (t ^ (k & 1));
//                }
//            }
//        }
        for (int i = 0; i < 1 << n; i++) {
            int z = ret[i] ^ ret[(i + 1) % (1 << n)];
            if (Integer.bitCount(z) < n / 2) throw new AssertionError();
        }
        return ret;
    }
}
