package mypackage;

import math.MathUtils;
import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.Arrays;

public class TaskE {

    static int[] currentTenPow = {1, 10, 100, 1000, 10000};
    static int[][] primesD;
    static int[] where;

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int[] primes = MathUtils.genPrimes(100000);
        where = new int[100001];
        Arrays.fill(where, -1);
        where[where.length - 1] = primes.length;
        for (int i = 0; i < primes.length; i++) {
            where[primes[i]] = i;
        }
        for (int i = where.length - 2; i >= 0; i--) {
            if (where[i] < 0) {
                where[i] = where[i + 1];
            }
        }
        primesD = new int[primes.length][5];
        for (int i = 0; i < primes.length; i++) {
            for (int p = primes[i], j = 4; j >= 0; j--, p /= 10) {
                primesD[i][j] = p % 10;
            }
        }
        int testsCount = in.nextInt();
        for (int t = 0; t < testsCount; t++) {
            int firstLine = in.nextInt();
            int n = countD(firstLine);
            currentTable = new int[n][n];
            shift = 5 - n;
            int id = Arrays.binarySearch(primes, firstLine);
            for (int i = shift; i < 5; i++) {
                currentTable[0][i - shift] = primesD[id][i];
            }
            out.println(go(1, n));
        }
    }

    static int[][] currentTable;

    static int shift = 5;

    static int go(int x, int n) {
        if (x == n) {
            return 1;
        }
        int number1 = 0;
        for (int i = 0; i < x; i++) {
            number1 = number1 * 10 + currentTable[i][x];
        }
        int number2 = number1 + 1;
        for (int i = x; i < n; i++) {
            number1 *= 10;
            number2 *= 10;
        }
        number1 = where[number1];
        number2 = where[number2];
        int ret = 0;
        for (int i = number1; i < number2; i++) {
            for (int j = shift; j < 5; j++) {
                currentTable[x][j - shift] = primesD[i][j];
            }
            ret += go(x + 1, n);
            for (int j = shift; j < 5; j++) {
                currentTable[x][j - shift] = 0;
            }
        }
        return ret;
    }

    static int countD(int x) {
        return x == 0 ? 0 : countD(x / 10) + 1;
    }
}
