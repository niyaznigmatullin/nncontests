package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskD {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int f = in.nextInt();
        char[] c = in.next().toCharArray();
        int[] p = new int[n];
        p[0] = -1;
        int k = -1;
        int K = 20;
        int[][] pp = new int[K][n];
        char[] ans = new char[n];
        for (int i = 0; i < n; i++) {
            if (i > 0) {
                while (k > -1 && c[k + 1] != c[i]) {
                    k = p[k];
                }
                if (c[k + 1] == c[i]) ++k;
                p[i] = k;
            }
            pp[0][i] = p[i];
            for (int j = 1; j < K; j++) {
                int go = pp[j - 1][i];
                pp[j][i] = go >= 0 ? pp[j - 1][go] : -1;
            }
            int len = i + 1;
            int cur = p[i];
            for (int j = K - 1; cur >= 0 && j >= 0; j--) {
                int go = pp[j][cur];
                int period = (len - go - 1);
                int nf = len / period;
                if (nf >= f + 1) {
                    cur = go;
                }
            }
            boolean ok = false;
            for (int d = 0; d < 3; d++) {
                int period = len - cur - 1;
                if (len % period == 0 && len / period == f + 1 || len / period == f) {
                    ok = true;
                    break;
                }
                if (cur < 0) break;
                cur = p[cur];
            }
            ans[i] = ok ? '1' : '0';
//            int period = len - cur - 1;
//            System.out.println(i + " " + len + " " + period);
//            if (len % period == 0 && len / period == f + 1 || len / (len - cur - 1) == f) {
//                ans[i] = '1';
//                continue;
//            } else {
//                if (cur >= 0) {
//                    cur = p[cur];
//                    period = len - cur - 1;
//                    if (len / period == f) {
//                        ans[i] = '1';
//                        continue;
//                    }
//                }
//            }
//            ans[i] = '0';
        }
        out.println(ans);
    }
}
