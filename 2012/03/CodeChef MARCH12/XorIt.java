package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.Arrays;
import java.util.Random;

public class XorIt {

    static final int MAXBITS = 31;

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        Arrays.sort(a);
        int[] count = new int[MAXBITS * n];
        int[] t0 = new int[count.length];
        int[] t1 = new int[count.length];
        int[] lef = new int[count.length];
        int[] rig = new int[count.length];
        Arrays.fill(t0, -1);
        Arrays.fill(t1, -1);
        Arrays.fill(lef, Integer.MAX_VALUE);
        int free = 1;
        for (int i = 0; i < n; i++) {
            int cur = 0;
            int x = a[i];
            for (int j = MAXBITS - 1; j >= 0; j--) {
                int bit = (x >> j) & 1;
                int[] t = bit == 0 ? t0 : t1;
                int go = t[cur];
                if (go < 0) {
                    go = t[cur] = free++;
                }
                cur = go;
                count[cur]++;
                lef[cur] = Math.min(lef[cur], i);
                rig[cur] = Math.max(rig[cur], i);
            }
        }
        int[] w = new int[n];
        int maximal = 0;
        int left = 2 * k + n;
        int answerCount = 0;
        int[] answer = new int[k];
        for (int j = MAXBITS - 1; j >= 0; j--) {
            long got = 0;
            for (int i = 0; i < n; i++) {
                if (w[i] < 0) {
                    continue;
                }
                int x = a[i];
                boolean bit = ((x >> j) & 1) == 1;
                int go = (bit ? t1 : t0)[w[i]];
                got += go < 0 ? 0 : count[go];
            }
            boolean mBit = false;
            if (got <= left) {
                maximal ^= 1 << j;
                mBit = true;
                left -= got;
                for (int i = 0; i < n; i++) {
                    if (w[i] < 0) {
                        continue;
                    }
                    boolean bit = ((a[i] >> j) & 1) == 1;
                    int z = (bit ? t1 : t0)[w[i]];
                    if (z < 0) {
                        continue;
                    }
                    int l = lef[z];
                    if (l == Integer.MAX_VALUE) {
                        continue;
                    }
                    int r = rig[z];
                    l = Math.max(l, i + 1);
                    for (int t = l; t <= r; t++) {
                        answer[answerCount++] = a[i] ^ a[t];
                    }
                }
            }
            for (int i = 0; i < n; i++) {
                if (w[i] < 0) {
                    continue;
                }
                boolean bit = ((a[i] >> j) & 1) == 1;
                w[i] = (bit == mBit ? t0 : t1)[w[i]];
            }
        }
        left >>= 1;
        while (left > 0) {
            answer[answerCount++] = maximal;
            left--;
        }
        Arrays.sort(answer);
        for (int i : answer) {
            out.print(i);
            out.print(' ');
        }
    }
}
