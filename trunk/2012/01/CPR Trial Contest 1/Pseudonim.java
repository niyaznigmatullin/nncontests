package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.Arrays;

public class Pseudonim {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int last = in.nextInt();
        out.println(solveSmart(n, k, last));
//        System.err.println(solveStupid(n, k, last));
//        int n = 140;
//        for (int k = 5; k <= 5; k += 2) {
//            boolean[][] win = new boolean[n + 1][k + 1];
//            for (int i = 1; i <= n; i++) {
//                for (int j = 1; j <= k; j++) {
//                    for (int t = 1; t <= k && t <= i; t++) {
//                        if (j == t) {
//                            continue;
//                        }
//                        win[i][j] |= !win[i - t][t];
//                    }
//                }
//            }
//            System.out.println(n + " " + k);
//            for (int i = 0; i <= n; i++) {
//                System.out.print("n = " + i + "\t");
//                for (int j = 0; j <= k; j++) {
//                    System.out.print(" " + (win[i][j] ? 1 : 0));
//                }
//                System.out.println();
//            }
//            System.out.println();
//        }
    }

    static int solveStupid(int n, int k, int last) {
        boolean[][] win = new boolean[n + 1][k + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                for (int t = 1; t <= k && t <= i; t++) {
                    if (j == t) {
                        continue;
                    }
                    win[i][j] |= !win[i - t][t];
                }
            }
        }
        int answer = 0;
        for (int i = 1; i <= n && i <= k; i++) {
            if (i == last) {
                continue;
            }
            if (!win[n - i][i]) {
                answer = i;
                break;
            }
        }
        return answer;
    }

    static int[] dpSmart;
    static boolean[][] win;

    static int solveSmart(int n, int k, int last) {
        win = new boolean[k + 5][k + 1];
        for (int i = 1; i < k + 5; i++) {
            for (int j = 1; j <= k; j++) {
                for (int t = 1; t <= k && t <= i; t++) {
                    if (j == t) {
                        continue;
                    }
                    win[i][j] |= !win[i - t][t];
                }
            }
        }
        if ((k & 1) == 0) {
            boolean[] myWin = new boolean[n + 1];
            for (int i = 1; i <= n; i++) {
                if (n % (k + 1) == 0) {
                    continue;
                }
                int next = i + i % (k + 1);
                if (!myWin[i] && next <= n) {
                    myWin[next] = true;
                }
            }
//            System.err.println(Arrays.toString(myWin));
            int answer = 0;
            for (int i = 1; i <= n && i <= k; i++) {
                if (i == last) {
                    continue;
                }
                if (!getIt(n - i, k, i, myWin)) {
                    answer = i;
                    break;
                }
            }
            return answer;
        } else {
            int[] inCurrent = new int[n + 1];
            boolean[] myWin = new boolean[n + 1];
            for (int i = 0; i <= n; ) {
                for (int j = 1; i + j <= n && j <= k; j++) {
                    int next = (i + j) + j;
                    if (next <= n && !myWin[i + j]) {
                        myWin[next] = true;
                    }
                    inCurrent[i + j] = j;
                }
                int nextBegin = i + k + 1;
                if (nextBegin <= n && !myWin[i + (k + 1) / 2]) {
                    inCurrent[nextBegin] = k + 1;
                    int nextnext = nextBegin + (k + 1) / 2;
                    if (nextnext <= n) {
                        myWin[nextnext] = true;
                    }
                    nextBegin++;
                }
                i = nextBegin;
            }
            int answer = 0;
            for (int i = 1; i <= n && i <= k; i++) {
                if (i == last) {
                    continue;
                }
                if (!getIt2(n - i, k, i, myWin, inCurrent)) {
                    answer = i;
                    break;
                }
            }
//            for (int i = 0; i <= n; i++) {
//                System.out.println("n = " + i + " inC = " + inCurrent[i] + " myWin = " + myWin[i]);
//            }
            return answer;
        }
    }

    static boolean getIt2(int n, int k, int last, boolean[] myWin, int[] inCurrent) {
        if (inCurrent[n] == 0) {
            return false;
        }
        if (inCurrent[n] == k + 1) {
            return last != (k + 1) / 2;
        }
        if (inCurrent[n] == last) {
            return myWin[n];
        } else {
            return true;
        }
    }

    static boolean getIt(int n, int k, int last, boolean[] myWin) {
        if (last != n % (k + 1)) {
            return n % (k + 1) != 0;
        }
        return myWin[n];
    }

    static boolean go(int n, int k, int last) {
        if (n < win.length) {
            return win[n][last];
        }
        if ((k & 1) == 0) {
            if (last != n % (k + 1)) {
                return n % (k + 1) != 0;
            }
        } else {
            if (last != n % (k + 1) - 1) {
                return n % (k + 1) != 1;
            }
        }
        if (dpSmart[n] >= 0) {
            return dpSmart[n] == 1;
        }
        boolean ret = false;
        for (int i = 1; i <= n && i <= k; i++) {
            if (i == last) {
                continue;
            }
            ret |= !go(n - i, k, i);
        }
        dpSmart[n] = ret ? 1 : 0;
        return ret;
    }
}
