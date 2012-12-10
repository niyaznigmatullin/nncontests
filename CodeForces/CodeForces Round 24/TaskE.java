package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

public class TaskE {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] x = new int[n];
        int[] v = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = in.nextInt();
            v[i] = in.nextInt();
        }
        int[] stack = new int[n];
        int[] timeNum = new int[n];
        int[] timeDen = new int[n];
        int cnt = 0;
        double answer = Double.POSITIVE_INFINITY;
        for (int i = 0; i < n; i++) {
            if (v[i] > 0) {
                int l = -1;
                int r = cnt;
                while (l < r - 1) {
                    int mid = l + r >> 1;
                    int cur = stack[mid];
                    int num;
                    int den;
                    if (v[i] >= v[cur]) {
                        num = 1;
                        den = 0;
                    } else {
                        num = x[i] - x[cur];
                        den = v[cur] - v[i];
                    }
                    if (den == 0 || (long) num * timeDen[mid] >= (long) den * timeNum[mid]) {
                        r = mid;
                    } else {
                        l = mid;
                    }
                }
                stack[l + 1] = i;
                cnt = l + 2;
                if (l < 0) {
                    timeNum[l + 1] = 1;
                    timeDen[l + 1] = 0;
                } else {
                    timeNum[l + 1] = x[i] - x[stack[l]];
                    timeDen[l + 1] = v[stack[l]] - v[i];
                }
            } else {
                int l = -1;
                int r = cnt;
                while (l < r - 1) {
                    int mid = l + r >> 1;
                    int cur = stack[mid];
                    int num;
                    int den;
                    num = x[i] - x[cur];
                    den = v[cur] - v[i];
                    if ((long) num * timeDen[mid] >= (long) den * timeNum[mid]) {
                        r = mid;
                    } else {
                        l = mid;
                    }
                }
                if (l >= 0) {
                    answer = Math.min(answer, 1. * (x[i] - x[stack[l]]) / (v[stack[l]] - v[i]));
                }
            }
        }
        out.println(Double.isInfinite(answer) ? -1 : answer);
    }
}
