package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

public class TaskA {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        int[] c = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt() - 1;
            b[i] = in.nextInt() - 1;
            c[i] = in.nextInt();
        }
        int[] path = new int[n];
        path[0] = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] == 0 || b[i] == 0) {
                path[1] = a[i] + b[i];
                break;
            }
        }
        all: for (int i = 2; i < n; i++) {
            int last = path[i - 1];
            int preLast = path[i - 2];
            for (int j = 0; j < n; j++) {
                if (last == a[j] || last == b[j]) {
                    if (preLast != (a[j] ^ b[j] ^ last)) {
                        path[i] = a[j] ^ b[j] ^ last;
                        continue all;
                    }
                }
            }
        }
        int[] pos = new int[n];
        for (int i = 0; i < n; i++) {
            pos[path[i]] = i;
        }
        int cost1 = 0;
        int cost2 = 0;
        for (int i = 0; i < n; i++) {
            int dif = pos[b[i]] - pos[a[i]];
            if (dif < 0) {
                dif += n;
            }
            if (dif == 1) {
                cost1 += c[i];
            } else {
                cost2 += c[i];
            }
        }
        out.println(Math.min(cost1, cost2));
	}
}
