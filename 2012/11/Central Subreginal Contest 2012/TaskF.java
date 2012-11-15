package mypackage;

import math.Factor;
import math.MathUtils;
import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.math.BigInteger;
import java.util.*;

public class TaskF {
//    public void solve(int testNumber, FastScanner in, FastPrinter out) {
//        int n = in.nextInt();
//        int[][] g = new int[n][n];
//        for (int i = 0; i < n; i++) {
//            for (int j = i + 1; j < n; j++) {
//                g[i][j] = g[j][i] = in.nextInt();
//            }
//        }
//        BigInteger[] number = new BigInteger[n];
//        Arrays.fill(number, BigInteger.ONE);
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                if (i != j) {
//                    number[i] = BigInteger.valueOf(MathUtils.lcm(number[i].longValue(), g[i][j]));
//                }
//            }
//        }
////        for (int i = 0; i < n; i++) {
////            for (int j = 0; j < n; j++) {
////                if (i == j) {
////                    continue;
////                }
////                if (MathUtils.gcd(number[i], number[j]) != g[i][j]) {
////                    throw new AssertionError();
////                }
////            }
////        }
//        Set<Integer> z = new HashSet<Integer>();
//        for (int i = 0; i < n; i++) {
//            for (Factor e : MathUtils.factorize(number[i].longValue())) {
//                z.add((int) e.prime);
//            }
//        }
//        BigInteger nextProbablePrime = BigInteger.valueOf(2);
//        while (nextProbablePrime.bitLength() < 30 && z.contains(nextProbablePrime.intValue())) {
//            nextProbablePrime = nextProbablePrime.nextProbablePrime();
//        }
//        for (int i = 1; i < n; i++) {
//            while (number[i].compareTo(number[i - 1]) <= 0) {
//                number[i] = number[i].multiply(nextProbablePrime);
//                nextProbablePrime = nextProbablePrime.nextProbablePrime();
//                while (nextProbablePrime.bitLength() < 30 && z.contains(nextProbablePrime.intValue())) {
//                    nextProbablePrime = nextProbablePrime.nextProbablePrime();
//                }
//            }
//        }
////        for (int i = 0; i < n; i++) {
////            for (int j = 0; j < n; j++) {
////                if (i == j) {
////                    continue;
////                }
////                if (MathUtils.gcd(number[i], number[j]) != g[i][j]) {
////                    throw new AssertionError();
////                }
////            }
////        }
//        for (int i = 0; i < n; i++) {
//            if (i > 0) {
//                out.print(' ');
//            }
//            out.print(number[i]);
//        }
//        out.println();
//    }


    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[][] g = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                g[i][j] = g[j][i] = in.nextInt();
            }
        }
        long[] number = new long[n];
        Arrays.fill(number, 1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    number[i] = MathUtils.lcm(number[i], g[i][j]);
                }
            }
        }
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                if (i == j) {
//                    continue;
//                }
//                if (MathUtils.gcd(number[i], number[j]) != g[i][j]) {
//                    throw new AssertionError();
//                }
//            }
//        }
        boolean[] isPrime = MathUtils.genPrimesBoolean(20000000);
        for (int i = 0; i < n; i++) {
            for (Factor e : MathUtils.factorize(number[i])) {
                isPrime[((int) e.prime)] = false;
            }
        }
        int curPrime = 2;
        while (!isPrime[curPrime]) {
            ++curPrime;
        }
        BigInteger[] answer = new BigInteger[n];
        for (int i = 0; i < n; i++) {
            answer[i] = BigInteger.valueOf(number[i]);
        }
        for (int i = 1; i < n; i++) {
            if (answer[i].compareTo(answer[i - 1]) <= 0) {
                while (answer[i].compareTo(answer[i - 1]) <= 0) {
                    answer[i] = answer[i].multiply(BigInteger.valueOf(curPrime));
                }
                ++curPrime;
                while (!isPrime[curPrime]) {
                    ++curPrime;
                }
            }
        }
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                if (i == j) {
//                    continue;
//                }
//                if (MathUtils.gcd(number[i], number[j]) != g[i][j]) {
//                    throw new AssertionError();
//                }
//            }
//        }
        for (int i = 0; i < n; i++) {
            if (i > 0) {
                out.print(' ');
            }
            out.print(answer[i]);
        }
        out.println();
    }
}
