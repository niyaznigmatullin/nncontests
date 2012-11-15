package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.Arrays;

public class TaskE {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int[] count = new int[10];
        for (int i = 0; i < 10; i++) {
            count[i] = in.nextInt();
        }
        for (int i = 0; i < 10000; i++) {
            char[] d = String.format("%04d", i).toCharArray();
            for (char c : d) {
                count[c - '0']--;
            }
            boolean ok = true;
            for (int j : count) {
                if (j < 0) {
                    ok = false;
                }
            }
            if (ok) {
                int ans = check(i, count);
                if (ans >= 0) {
                    out.println(i);
                    out.println(ans);
                    return;
                }
            }
            for (char c : d) {
                count[c - '0']++;
            }
        }
        out.println(-1);
        out.println(-1);
    }

    static int check(int first, int[] count) {
        return go(0, 0, 0, first, count);
    }

    static int[] TEN = {1, 10, 100, 1000, 10000, 100000, 1000000};

    static int go(int index, int result, int second, int first, int[] count) {
        if (index == 4) {
            if (result >= 10000000) {
                return -1;
            }
            boolean ok = true;
            for (int i = 0; i < 4; i++) {
                result /= 10;
            }
            for (int i = 0, j = result; i < 3; i++, j /= 10) {
                count[j % 10]--;
            }
            for (int i = 0; i < 10; i++) {
                if (count[i] < 0) {
                    ok = false;
                    break;
                }
            }
            for (int i = 0, j = result; i < 3; i++, j /= 10) {
                count[j % 10]++;
            }
            return ok ? second : -1;
        }
        for (int i = 0; i < 10; i++) {
            if (count[i] == 0 || first * i >= 10000) {
                continue;
            }
            count[i]--;
            second += i * TEN[index];
            int newResult = first * second;
            int digit = newResult / TEN[index] % 10;
            if (count[digit] != 0) {
                count[digit]--;
                for (int j = 0, k = first * i; j < 4; j++, k /= 10) {
                    count[k % 10]--;
                }
                boolean ok = true;
                for (int j = 0; j < 10; j++) {
                    if (count[j] < 0) {
                        ok = false;
                        break;
                    }
                }
                if (ok) {
                    int ans = go(index + 1, newResult, second, first, count);
                    if (ans >= 0) {
                        return ans;
                    }
                }
                for (int j = 0, k = first * i; j < 4; j++, k /= 10) {
                    count[k % 10]++;
                }
                count[digit]++;
            }
            second -= i * TEN[index];
            count[i]++;
        }
        return -1;
    }
}
