package mypackage;

import math.MathUtils;
import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.math.BigInteger;
import java.util.*;

public class ProblemC {
    static int[] primes = MathUtils.genPrimes(1000000);

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextLong();
        }
        Arrays.sort(a);
        if (a[0] == a[n - 1]) {
            out.println(1);
            return;
        }
        if (a[0] < 0 && a[n - 1] > 0) {
            boolean allEqAbs = true;
            for (int i = 1; i < n; i++) {
                if (Math.abs(a[i]) != Math.abs(a[0])) {
                    allEqAbs = false;
                    break;
                }
            }
            if (allEqAbs) {
                out.println(-1);
                return;
            }
            Integer[] id = new Integer[n];
            for (int i = 0; i < n; i++) {
                id[i] = i;
            }
            {
                final long[] c = a;
                Arrays.sort(id, new Comparator<Integer>() {
                    public int compare(Integer o1, Integer o2) {
                        return Long.signum(Math.abs(c[o1]) - Math.abs(c[o2]));
                    }
                });
            }
            {
                long[] b = new long[n];
                for (int i = 0; i < n; i++) {
                    b[i] = a[id[i]];
                }
                a = b;
            }
            long allNum = 0;
            long allDen = 0;
            for (int i = 1; i < n; i++) {
                long num = Math.abs(a[i]);
                long den = Math.abs(a[i - 1]);
                long g = MathUtils.gcd(num, den);
                num /= g;
                den /= g;
                if (Long.signum(a[i]) == Long.signum(a[i - 1])) {
                    long numSq = sqrt(num);
                    long denSq = sqrt(den);
                    if (denSq * denSq != den || numSq * numSq != num) {
                        out.println(0);
                        return;
                    }
                    num = numSq;
                    den = denSq;
                }
                allDen = MathUtils.gcd(den, allDen);
                allNum = MathUtils.gcd(num, allNum);
            }
            if (allNum <= allDen) {
                out.println(0);
                return;
            }
            long numSq = sqrt(allNum);
            long denSq = sqrt(allDen);
            int pow = 1;
            if (numSq * numSq == allNum && denSq * denSq == allDen) {
                pow = 2;
            }
            pow = Math.max(pow, MathUtils.gcd(getIt(allNum), getIt(allDen)));
            denSq = root(allDen, pow);
            numSq = root(allNum, pow);
            Map<Long, Integer> powsDen = new HashMap<Long, Integer>();
            Map<Long, Integer> powsNum = new HashMap<Long, Integer>();
            BigInteger denBig = BigInteger.valueOf(denSq);
            BigInteger numBig = BigInteger.valueOf(numSq);
            {
                BigInteger i = denBig;
                BigInteger j = numBig;
                int count = 0;
                BigInteger maxVal = BigInteger.valueOf(Long.MAX_VALUE);
                for (; i.compareTo(maxVal) < 0 && j.compareTo(maxVal) < 0; i = i.multiply(denBig), j = j.multiply(numBig)) {
                    ++count;
                    powsDen.put(i.longValue(), count);
                    powsNum.put(j.longValue(), count);
                }
            }
            int answerPow = 0;
            for (int i = 1; i < n; i++) {
                long num = Math.abs(a[i]);
                long den = Math.abs(a[i - 1]);
                long g = MathUtils.gcd(num, den);
                num /= g;
                den /= g;
                if (Long.signum(a[i]) == Long.signum(a[i - 1])) {
                    long numSq1 = sqrt(num);
                    long denSq1 = sqrt(den);
                    if (denSq1 * denSq1 != den || numSq1 * numSq1 != num) {
                        out.println(0);
                        return;
                    }
                    num = numSq1;
                    den = denSq1;
                }
                if (!powsDen.containsKey(den) || !powsNum.containsKey(num)) {
                    out.println(0);
                    return;
                }
                if (denSq != 1 && powsDen.get(den).intValue() != powsNum.get(num).intValue()) {
                    out.println(0);
                    return;
                }
                answerPow = MathUtils.gcd(answerPow, powsNum.get(num));
            }

            for (int i = 1; i < n; i++) {
                long num = Math.abs(a[i]);
                long den = Math.abs(a[i - 1]);
                long g = MathUtils.gcd(num, den);
                num /= g;
                den /= g;
                int got = powsNum.get(num);
                if ((Long.signum(a[i]) == Long.signum(a[i - 1])) != (got % 2 == 0)) {
                    out.println(0);
                    return;
                }
            }
            out.println(-1. * numBig.pow(answerPow).doubleValue() / denBig.pow(answerPow).doubleValue());
            return;
        }
        if (a[n - 1] < 0) {
            for (int i = 0; i < n; i++) {
                a[i] = -a[i];
            }
            Arrays.sort(a);
        }
        long allNum = 0;
        long allDen = 0;
        for (int i = 1; i < n; i++) {
            long num = a[i];
            long den = a[i - 1];
            long g = MathUtils.gcd(num, den);
            num /= g;
            den /= g;
            allDen = MathUtils.gcd(den, allDen);
            allNum = MathUtils.gcd(num, allNum);
        }
        if (allNum <= allDen) {
            out.println(0);
            return;
        }
        long numSq = sqrt(allNum);
        long denSq = sqrt(allDen);
        int pow = 1;
        if (numSq * numSq == allNum && denSq * denSq == allDen) {
            pow = 2;
        }
        pow = Math.max(pow, MathUtils.gcd(getIt(allNum), getIt(allDen)));
        denSq = root(allDen, pow);
        numSq = root(allNum, pow);
        Map<Long, Integer> powsDen = new HashMap<Long, Integer>();
        Map<Long, Integer> powsNum = new HashMap<Long, Integer>();
        BigInteger denBig = BigInteger.valueOf(denSq);
        BigInteger numBig = BigInteger.valueOf(numSq);
        {
            BigInteger i = denBig;
            BigInteger j = numBig;
            int count = 0;
            BigInteger maxVal = BigInteger.valueOf(Long.MAX_VALUE);
            for (; i.compareTo(maxVal) < 0 && j.compareTo(maxVal) < 0; i = i.multiply(denBig), j = j.multiply(numBig)) {
                ++count;
                powsDen.put(i.longValue(), count);
                powsNum.put(j.longValue(), count);
            }
        }
        int answerPow = 0;
        for (int i = 1; i < n; i++) {
            long num = a[i];
            long den = a[i - 1];
            long g = MathUtils.gcd(num, den);
            num /= g;
            den /= g;
            if (!powsDen.containsKey(den) || !powsNum.containsKey(num)) {
                out.println(0);
                return;
            }
            if (denSq != 1 && powsDen.get(den).intValue() != powsNum.get(num).intValue()) {
                out.println(0);
                return;
            }
            answerPow = MathUtils.gcd(answerPow, powsNum.get(num));
        }
        out.println(1. * numBig.pow(answerPow).doubleValue() / denBig.pow(answerPow).doubleValue());
    }

    static long root(long n, int pow) {
        if (pow == 1) {
            return n;
        }
        long z = (long) Math.pow(n, 1. / pow);
        while (BigInteger.valueOf(z).pow(pow).compareTo(BigInteger.valueOf(n)) < 0) {
            ++z;
        }
        while (BigInteger.valueOf(z).pow(pow).compareTo(BigInteger.valueOf(n)) > 0) {
            --z;
        }
        return z;
    }

    static int getIt(long n) {
        if (n == 1) {
            return 0;
        }
        int answer = 0;
        for (int i : primes) {
            if ((long) i * i * i > n) {
                break;
            }
            int count = 0;
            while (n % i == 0) {
                n /= i;
                ++count;
            }
            answer = MathUtils.gcd(answer, count);
        }
        if (n > 1) {
            return 1;
        }
        return answer;
    }

    static long sqrt(long n) {
        return root(n, 2);
    }
}
