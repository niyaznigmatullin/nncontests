package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Task7 {
    static final int MAXN = 100001;
    static int[] g;

    static {
        int n = MAXN;
        int[] firstG = new int[n];
        int[] can = new int[n];
        g = new int[n];
        for (int i = 2; i < n; i++) {
            for (int j = 2; j * (j + 1) <= (i << 1); j++) {
                int number = i / j;
                int mod = i - number * j;
                if ((j & 1) == 1) {
                    if (mod == 0) {
                        int left = number - (j >> 1);
                        int right = number + (j >> 1);
                        int v = firstG[right] ^ firstG[left - 1];
                        can[v] = i;
                    }
                } else {
                    if ((mod << 1) == j) {
                        int left = number - (j >> 1) + 1;
                        int right = number + (j >> 1);
                        int v = firstG[right] ^ firstG[left - 1];
                        can[v] = i;
                    }
                }
            }
            for (int k = 0; ; k++) {
                if (can[k] != i) {
                    g[i] = k;
                    firstG[i] = firstG[i - 1] ^ k;
                    break;
                }
            }
        }
    }


    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        try {
            int n = in.indianNextInt();
            int ans = 0;
            for (int i = 0; i < n; i++) {
                int v = in.indianNextInt();
                ans ^= g[v];
            }
            out.println(ans == 0 ? "RON" : "HARRY");
        } catch (Throwable e) {
            out.println("RON");
        }
    }
}
