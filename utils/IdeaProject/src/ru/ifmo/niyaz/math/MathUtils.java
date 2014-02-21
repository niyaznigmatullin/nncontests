package ru.ifmo.niyaz.math;

import ru.ifmo.niyaz.math.Factor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: niyaznigmatul
 * Date: 14.01.12
 * Time: 3:09
 * To change this template use File | Settings | File Templates.
 */
public class MathUtils {

    public static int gcd(int a, int b) {
        if (a < 0) a = -a;
        if (b < 0) b = -b;
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    public static Factor[] factorize(long n) {
        List<Factor> ret = new ArrayList<Factor>();
        for (long i = 2; i * i <= n; i++) {
            if (n % i != 0) {
                continue;
            }
            int count = 0;
            while (n % i == 0) {
                n /= i;
                count++;
            }
            ret.add(new Factor(i, count));
        }
        if (n > 1) {
            ret.add(new Factor(n, 1));
        }
        return ret.toArray(new Factor[ret.size()]);
    }


    public static long gcd(long a, long b) {
        if (a < 0) a = -a;
        if (b < 0) b = -b;
        while (b != 0) {
            long t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    public static long lcm(int a, int b) {
        return a / gcd(a, b) * (long) b;
    }

    public static long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    public static int modPow(int a, int b, int mod) {
        int ret = 1;
        a = (a % mod + mod) % mod;
        while (b > 0) {
            if ((b & 1) == 1) {
                ret = (int) ((long) ret * a % mod);
            }
            a = (int) ((long) a * a % mod);
            b >>= 1;
        }
        return ret;
    }

    public static int powerStupid(int a, int b) {
        int ret = 1;
        for (int i = 0; i < b; i++) {
            ret *= a;
        }
        return ret;
    }

    public static int[] genPrimes(int n) {
        boolean[] isPrime = genPrimesBoolean(n);
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (isPrime[i]) {
                count++;
            }
        }
        int[] primes = new int[count];
        for (int i = 0, j = 0; i < n; i++) {
            if (isPrime[i]) {
                primes[j++] = i;
            }
        }
        return primes;
    }

    public static boolean[] genPrimesBoolean(int n) {
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i * i < n; i++) {
            if (!isPrime[i]) {
                continue;
            }
            for (int j = i * i; j < n; j += i) {
                isPrime[j] = false;
            }
        }
        return isPrime;
    }

    public static int[] getFactoringSieve(int n) {
        int[] p = new int[n];
        for (int i = 2; i < n; i++) {
            if (p[i] > 0) {
                continue;
            }
            for (int j = i; j < n; j += i) {
                p[j] = i;
            }
        }
        return p;

    }

    public static int modPow(int a, long b, int mod) {
        int ret = 1;
        a = (a % mod + mod) % mod;
        while (b > 0) {
            if ((b & 1) == 1) {
                ret = (int) ((long) ret * a % mod);
            }
            a = (int) ((long) a * a % mod);
            b >>= 1;
        }
        return ret;
    }
}
