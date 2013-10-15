package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Task1965 {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        int[][] ans;
        ans = solve0(a);
        if (ans != null) {
            print(out, ans);
            return;
        }
        ans = solve1(a);
        if (ans != null) {
            print(out, ans);
            return;
        }
        ans = solve2(a);
        if (ans != null) {
            print(out, ans);
            return;
        }
        ans = solve3(a);
        if (ans != null) {
            print(out, ans);
            return;
        }
        out.println("Fail");
    }

    static void print(FastPrinter out, int[][] ans) {
        out.println(ans[0].length + " " + ans[1].length);
        for (int[] d : ans) {
            out.printArray(d);
        }
    }

    static int[][] solve0(int[] a) {
        boolean ok1 = true;
        boolean ok2 = true;
        for (int i = 2; i < a.length; i++) {
            if (a[i] < a[i - 1]) {
                ok1 = false;
            }
            if (a[i] > a[i - 1]) {
                ok2 = false;
            }
        }
        if (ok1 || ok2) {
            int[][] ret = new int[2][];
            ret[0] = new int[]{a[0]};
            ret[1] = Arrays.copyOfRange(a, 1, a.length);
            return ret;
        } else return null;
    }

    static int[][] solve3(int[] a) {
        int n = a.length;
        int[][] dp = new int[2][n];
        dp[0][0] = Integer.MAX_VALUE;
        dp[1][0] = Integer.MIN_VALUE;
        for (int i = 1; i < n; i++) {
            {
                int best = Integer.MIN_VALUE;
                if (a[i - 1] < a[i]) {
                    best = Math.max(dp[0][i - 1], best);
                }
                if (a[i] > dp[1][i - 1]) {
                    best = Math.max(a[i - 1], best);
                }
                dp[0][i] = best;
            }
            {
                int best = Integer.MAX_VALUE;
                if (a[i - 1] > a[i]) {
                    best = Math.min(dp[1][i - 1], best);
                }
                if (a[i] < dp[0][i - 1]) {
                    best = Math.min(a[i - 1], best);
                }
                dp[1][i] = best;
            }
        }
        int bestI = -1;
        if (dp[0][n - 1] != Integer.MIN_VALUE) {
            bestI = 0;
        } else if (dp[1][n - 1] != Integer.MAX_VALUE) {
            bestI = 1;
        } else return null;
        int[] ans1 = new int[n];
        int[] ans2 = new int[n];
        int cn1 = 0;
        int cn2 = 0;
        for (int i = bestI, j = n - 1; j >= 0; j--) {
            if (i == 0) {
                ans1[cn1++] = a[j];
            } else {
                ans2[cn2++] = a[j];
            }
            if (j == 0) break;
            if (i == 0) {
                if (a[j - 1] < a[j] && dp[0][j] == dp[0][j - 1]) {

                } else {
                    i = 1;
                }
            } else {
                if (a[j - 1] > a[j] && dp[1][j] == dp[1][j - 1]) {

                } else {
                    i = 0;
                }
            }
        }
        for (int i = 0, j = cn1 - 1; i < j; i++, j--) {
            int t = ans1[i];
            ans1[i] = ans1[j];
            ans1[j] = t;
        }
        for (int i = 0, j = cn2 - 1; i < j; i++, j--) {
            int t = ans2[i];
            ans2[i] = ans2[j];
            ans2[j] = t;
        }
        return new int[][]{Arrays.copyOf(ans1, cn1), Arrays.copyOf(ans2, cn2)};
    }

    static int[][] solve2(int[] a) {
        int[] b = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            b[i] = a[a.length - i - 1];
        }
        int[][] ret = solve1(b);
        if (ret != null) {
            for (int[] d : ret) {
                for (int i = 0, j = d.length - 1; i < j; i++, j--) {
                    int t = d[i];
                    d[i] = d[j];
                    d[j] = t;
                }
            }
        }
        return ret;
    }

    static int[][] solve1(int[] a) {
        int n = a.length;
        int[][] ret = new int[2][n + 1];
        int[] kg = new int[2];
        kg[0] = kg[1] = 1;
        ret[0][0] = -1;
        ret[1][0] = -1;
        for (int i : a) {
            int best = -1;
            int minimal = Integer.MAX_VALUE;
            for (int j = 0; j < 2; j++) {
                int last1 = ret[j][kg[j] - 1];
                if (last1 < i) {
                    int last2 = ret[j ^ 1][kg[j ^ 1] - 1];
                    if (last2 < minimal) {
                        minimal = last2;
                        best = j;
                    }
                }
            }
            if (best < 0) return null;
            ret[best][kg[best]++] = i;
        }
        return new int[][]{Arrays.copyOfRange(ret[0], 1, kg[0]), Arrays.copyOfRange(ret[1], 1, kg[1])};
    }
}
