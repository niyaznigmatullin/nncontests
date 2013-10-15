package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;
import java.util.Comparator;

public class Grlex {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        long n = in.nextLong() + 1;
        long k = in.nextLong() - 1;
//        Integer[] id = new Integer[(int) n - 1];
//        for (int i = 0; i < id.length; i++) {
//            id[i] = i + 1;
//        }
//        Arrays.sort(id, new Comparator<Integer>() {
//
//            int sum(int x) {
//                return x == 0 ? 0 : sum(x / 10) + x % 10;
//            }
//
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                int c = sum(o1) - sum(o2);
//                if (c != 0) return c;
//                return (o1 + "").compareTo(o2 + "");
//            }
//        });
//        System.out.println(Arrays.toString(id));
        long kk = k;
        int allDigits = (n + "").length();
        long[][] dp = new long[allDigits * 9 + 1][allDigits + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= allDigits; i++) {
            for (int j = 0; j <= 9 * i; j++) {
                for (int firstD = 0; firstD < 10; firstD++) {
                    if (j - firstD >= 0) {
                        dp[j][i] += dp[j - firstD][i - 1];
                    }
                }
            }
        }
        int digitSum = Integer.MIN_VALUE;
        for (int i = 1; i <= allDigits * 9; i++) {
            long curCount = getCountBySum(dp, n, i);
            if (curCount <= k) {
                k -= curCount;
            } else {
                digitSum = i;
                break;
            }
//            System.out.println(curCount + " " + k);
        }
        String s = "";
        while (k > 0 || digitSum > 0) {
            if (digitSum == 0) {
                --k;
            }
            for (int d = 0; d < 10; d++) {
                if (s.equals("") && d == 0) continue;
                long count = getCount(dp, s + d, n, digitSum - d);
                if (count <= k) {
                    k -= count;
                } else {
                    s += d;
                    digitSum -= d;
                    break;
                }
            }
        }
        String answerS = s;
        k = kk + 1;
        int[] numberK = getArray(k);
        int curSum = 0;
        for (int i : numberK) {
            curSum += i;
        }
        long ret = 0;
        for (int sum = 1; sum < curSum; sum++) {
            ret += getCountBySum(dp, n, sum);
        }
        s = "";
        for (int i = 0; i < numberK.length; i++) {
            for (int j = 0; j < numberK[i]; j++) {
                if (j == 0 && i == 0) {
                    continue;
                }
                ret += getCount(dp, s + j, n, curSum - j);
            }
            s += numberK[i];
            curSum -= numberK[i];
            if (curSum == 0) ++ret;
        }
        out.println(ret);
        out.println(answerS);
    }

    static int[] getArray(long n) {
        int[] numberN = new int[(n + "").length()];
        {
            long nn = n;
            for (int i = 0; i < numberN.length; i++) {
                numberN[numberN.length - i - 1] = (int) (nn % 10);
                nn /= 10;
            }
        }
        return numberN;
    }

    static long getCountBySum(long[][] dp, long n, int sum) {
        int[] numberN = getArray(n);
        int curSum = 0;
        long curCount = 0;
        for (int j = 0; curSum <= sum && j < numberN.length; j++) {
            for (int d = 0; d < numberN[j] && d + curSum <= sum; d++) {
                curCount += dp[sum - curSum - d][numberN.length - j - 1];
            }
            curSum += numberN[j];
//            System.out.println(j + " " + curSum + " " + curCount);
        }
        return curCount;
    }

    static long getCount(long[][] dp, String s, long n, int digitSum) {
        if (digitSum < 0) return 0;
        String z = n + "";
        long ret = 0;
        for (int len = 0; len + s.length() < z.length(); len++) {
            ret += dp[digitSum][len];
        }
        if (s.compareTo(z) > 0) {
            return ret;
        }
        if (z.startsWith(s)) {
            long ss = Long.parseLong(s);
            for (int i = s.length(); i < z.length(); i++) ss *= 10;
            long nn = n - ss;
            return ret + getCountBySum(dp, nn, digitSum);
        } else {
            return ret + dp[digitSum][z.length() - s.length()];
        }
    }
}
