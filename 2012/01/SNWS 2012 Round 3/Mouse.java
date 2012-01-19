package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

public class Mouse {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int rowCount = in.nextInt();
        int columnCount = in.nextInt();
        int n = in.nextInt();
        int[] x1 = new int[n];
        int[] x2 = new int[n];
        int[] y1 = new int[n];
        int[] y2 = new int[n];
        for (int i = 0; i < n; i++) {
            x1[i] = in.nextInt();
            y2[i] = in.nextInt();
            x2[i] = in.nextInt();
            y1[i] = in.nextInt();
        }
        int m = in.nextInt();
        int[] c = new int[n];
        for (int i = 0; i < n; i++) {
            c[i] = n - i - 1;
        }
        for (int i = 0; i < m; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            int best = -1;
            for (int j = 0; j < n; j++) {
                int k = c[j];
                if (x >= x1[k] && x <= x2[k] && y >= y1[k] && y <= y2[k]) {
                    best = j;
                    break;
                }
            }
            if (best < 0) {
                out.println(0);
                continue;
            }
            int ans = c[best];
            for (int j = best; j > 0; j--) {
                c[j] = c[j - 1];
            }
            c[0] = ans;
            out.println(ans + 1);
        }
	}
}
