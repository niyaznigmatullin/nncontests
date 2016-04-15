package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.MathUtils;

import java.util.Arrays;

public class TaskD {


    static final int MOD = 1_000_000_007;

    static int mul(int a, int b) {
        return (int) ((long) a * b % MOD);
    }
    static class Tree {
        int[] a;
        int n;

        Tree(int size) {
            n = Integer.highestOneBit(size) * 2;
            a = new int[2 * n];
            Arrays.fill(a, 1);
        }

        void makeMul(int x, int y) {
            x += n;
            while (x > 0) {
                a[x] = mul(a[x], y);
                x >>= 1;
            }
        }

        int get(int l, int r) {
            l += n;
            r += n;
            int ret = 1;
            while (l <= r) {
                if ((l & 1) == 1) {
                    ret = mul(ret, a[l++]);
                }
                if ((r & 1) == 0) {
                    ret = mul(ret, a[r--]);
                }
                l >>= 1;
                r >>= 1;
            }
            return ret;
        }
    }

    static int modInverse(int x) {
        return MathUtils.modPow(x, MOD - 2, MOD);
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = in.readIntArray(n);
        int m = in.nextInt();
        int[] ql = new int[m];
        int[] qr = new int[m];
        int[] headQ = new int[n];
        Arrays.fill(headQ, -1);
        int[] nextQ = new int[m];
        for (int i = 0; i < m; i++) {
            ql[i] = in.nextInt() - 1;
            qr[i] = in.nextInt() - 1;
            nextQ[i] = headQ[qr[i]];
            headQ[qr[i]] = i;
        }
        int maxA = 0;
        for (int i : a) maxA = Math.max(maxA, i);
        maxA++;
        int[] p = MathUtils.getFactoringSieve(maxA);
        int[] lastPrime = new int[maxA];
        Arrays.fill(lastPrime, -1);
        Tree tree = new Tree(n);
        int[] answer = new int[m];
        for (int i = 0; i < n; i++) {
            int x = a[i];
            while (x > 1) {
                int cp = p[x];
                if (lastPrime[cp] < i) {
                    int id = lastPrime[cp];
                    if (id >= 0) {
                        tree.makeMul(id, mul(cp, modInverse(cp - 1)));
                    }
                    lastPrime[cp] = i;
                    tree.makeMul(i, cp - 1);
                } else {
                    tree.makeMul(i, cp);
                }
                x /= cp;
            }
            for (int e = headQ[i]; e >= 0; e = nextQ[e]) {
                answer[e] = tree.get(ql[e], qr[e]);
            }
        }
        for (int i : answer) {
            out.println(i);
        }
    }
}
