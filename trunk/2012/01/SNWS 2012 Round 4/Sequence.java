package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.ArrayList;
import java.util.List;

public class Sequence {
    static final long MAXN = 10000000000000000L;

    static List<Long> f = new ArrayList<Long>();

    static {
        f.add(1L);
        f.add(2L);
        while (f.get(f.size() - 1) < MAXN) {
            f.add(f.get(f.size() - 1) + f.get(f.size() - 2));
        }
    }

    static long[] dp = new long[f.size() + 2];
    static long[] count = new long[f.size() + 2];

    static {
        count[0] = 1;
        count[1] = 2;
        dp[1] = 1;
        for (int i = 2; i < dp.length; i++) {
            dp[i] = dp[i - 2] + dp[i - 1] + count[i - 2];
            count[i] = count[i - 2] + count[i - 1];
        }

    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        long n = in.nextLong();
        if (n <= 0) {
            out.println(0);
            return;
        }
//        System.err.println(Arrays.toString(dp));
//        System.err.println(Arrays.toString(count));
        long last = 0;
        int curDig = 0;
        long answer = 0;
        for (long e : f) {
            if (curDig == 0) {

            } else if ((e - last) * curDig <= n) {
                n -= (e - last) * curDig;
                if (curDig == 1) {
                    answer++;
                } else {
                    answer += dp[curDig - 2] + count[curDig - 2];
                }
            } else {
                long number = last + (n / curDig);
                int cur = (int) (n % curDig);
                answer += getFirst(f, number, cur);
                answer += getAll(f, last + n / curDig, curDig);
                break;
            }
            curDig++;
            last = e;
//            System.err.println(answer + " " + last + " " + n + " " + curDig);
        }
        out.println(answer);
    }

    static long getAll(List<Long> f, long number, int curDig) {
        int[] digs = new int[curDig];
        for (int i = curDig - 1; i >= 0; i--) {
            if (number >= f.get(i)) {
                number -= f.get(i);
                digs[curDig - i - 1] = 1;
            }
        }
        int ones = 1;
        long ret = 0;
        for (int i = 1; i < curDig; i++) {
            if (digs[i] == 0) {
                continue;
            }
            ret += dp[curDig - i - 1] + count[curDig - i - 1] * ones;
            ones++;
        }
        return ret;
    }

    static long getFirst(List<Long> f, long number, int cur) {
        int i = 0;
        while (f.get(i + 1) <= number) {
            i++;
        }
        int ret = 0;
        while (cur-- > 0) {
            if (number >= f.get(i)) {
                number -= f.get(i);
                ret++;
            }
            i--;
        }
        return ret;
    }
}
