package mypackage;

import math.MathUtils;
import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Prime {
    static final int MAXN = 200;

    static long[] masks;

    static {
        List<Integer> primes = new ArrayList<Integer>();
        for (int i = 2; i < MAXN; i = BigInteger.valueOf(i).nextProbablePrime().intValue()) {
            primes.add(i);
        }
        masks = new long[MAXN];
        for (int i = 2; i < MAXN; i++) {
            int number = i;
            long curMask = 0;
            for (int j = 0; j < primes.size(); j++) {
                int prime = primes.get(j);
                if (number % prime == 0) {
                    curMask |= 1L << j;
                }
            }
            masks[i] = curMask;
        }
        int[][] dp = new int[101][101];
        Arrays.fill(dp[0], 1);
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                dp[i][j] = dp[i][j - 1];
                if (i >= j) {
                    dp[i][j] += dp[i - j][j];
                }
            }
        }
        System.err.println(dp[100][100]);
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        answer = 0;
        int n = in.nextInt();
        go(n, n, 0);
        out.println(answer);
    }

    static long answer;

    static void go(int left, int last, long mask) {
        answer++;
        for (int i = Math.min(left, last); i > 1; i--) {
            if ((masks[i] & mask) == 0) {
                go(left - i, i - 1, masks[i] | mask);
            }
        }
    }

    static void goStupid(int left, int last, List<Integer> numbers) {
        answer++;
        all:
        for (int i = Math.min(left, last); i > 1; i--) {
            for (int j : numbers) {
                if (MathUtils.gcd(i, j) != 1) {
                    continue all;
                }
            }
            numbers.add(i);
            goStupid(left - i, i, numbers);
            numbers.remove(numbers.size() - 1);
        }
    }

    private void printAnswers(FastPrinter out) {
        long[][] dp = new long[2][MAXN];
        dp[0][0] = 1;
        List<Integer> primes = new ArrayList<Integer>();
        int[] id = new int[MAXN];
        for (int i = 2, j = 0; i <= MAXN / 3; i = BigInteger.valueOf(i).nextProbablePrime().intValue()) {
            id[i] = j++;
            primes.add(i);
        }
        int countPrimes = primes.size();
        for (int i = MAXN / 3; i < MAXN; i += 2) {
            if ((i & 1) == 0) {
                ++i;
            }
            int number = i;
            for (int j = 0; j < countPrimes; j++) {
                int prime = primes.get(j);
                while (number % prime == 0) {
                    number /= prime;
                }
            }
            if (number == 1) {
                continue;
            }
            long[][] next = new long[2][];
            next[0] = dp[0].clone();
            next[1] = dp[1].clone();
            for (int j = 0; j + i < MAXN; j++) {
                next[0][j + i] += dp[0][j];
                next[1][j + i] += dp[1][j];
            }
            for (int j = 0; j + 2 * i < MAXN; j++) {
                next[1][j + 2 * i] += dp[0][j];
            }
            dp = next;
        }
        long[][] dp2 = new long[MAXN][1 << countPrimes];
        for (int i = 0; i < MAXN; i++) {
            dp2[i][0] = 1;
        }
        for (int i = 2; i < MAXN; i++) {
            int curMask = 0;
            int number = i;
            for (int j = 0; j < countPrimes; j++) {
                int prime = primes.get(j);
                if (number % prime == 0) {
                    curMask |= 1 << j;
                }
                while (number % prime == 0) {
                    number /= prime;
                }
            }
            if (number > 1) {
                continue;
            }
            long[][] next = new long[MAXN][];
            for (int k = 0; k < MAXN; k++) {
                next[k] = dp2[k].clone();
            }
            int other = ((1 << countPrimes) - 1) & ~curMask;
            for (int mask = other; ; mask = (mask - 1) & other) {
                for (int got = 0; i + got < MAXN; got++) {
                    next[got + i][mask | curMask] += dp2[got][mask];
                }
                if (mask == 0) {
                    break;
                }
            }
            dp2 = next;
        }
        long[][] res = new long[2][MAXN];
        for (int i = 0; i < MAXN; i++) {
            for (int j = 0; j < 1 << countPrimes; j++) {
                res[j & 1][i] += dp2[i][j];
            }
        }
        for (int n = 1; n < MAXN; n++) {
//        int n = in.nextInt();
            long answer = 0;
            for (int i = 0; i <= n; i++) {
                long s1 = res[0][i] + res[1][i];
                long s2 = dp[0][n - i] + dp[1][n - i];
                answer += s1 * s2 - (res[1][i] * dp[1][n - i]);
                System.err.println(n + " " + i + " " + answer + " " + s1 + " " + s2);
            }
            out.println(answer + ",");
        }
    }
}
