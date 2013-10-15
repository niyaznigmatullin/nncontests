package lib.test.on2013_08.on2013_08_31_.Parallel;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import sun.reflect.generics.tree.ArrayTypeSignature;

import java.math.BigInteger;
import java.util.Arrays;

public class Parallel {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        int p = in.nextInt();
        boolean[][] independence = new boolean[m][m];
        for (int i = 0; i < p; i++) {
            String s = in.next();
            int c1 = s.charAt(0) - 'A';
            int c2 = s.charAt(1) - 'A';
            independence[c1][c2] = true;
            independence[c2][c1] = true;
        }
        char[] c = in.next().toCharArray();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = c[i] - 'A';
        BigInteger[] ways = new BigInteger[n + 1];
        BigInteger[] ways2 = new BigInteger[n + 1];
        int[] steps = new int[n + 1];
        Arrays.fill(steps, Integer.MAX_VALUE);
        steps[0] = 0;
        ways[0] = BigInteger.ONE;
        ways2[0] = BigInteger.ONE;
        BigInteger[][] C = new BigInteger[k + 1][k + 1];
        for (BigInteger[] d : C) {
            Arrays.fill(d, BigInteger.ZERO);
        }
        for (int i = 0; i <= k; i++) {
            C[i][0] = BigInteger.ONE;
            for (int j = 1; j <= i; j++) {
                C[i][j] = C[i - 1][j - 1].add(C[i - 1][j]);
            }
        }
        BigInteger[] fact = new BigInteger[k + 1];
        fact[0] = BigInteger.ONE;
        for (int i = 1; i <= k; i++) {
            fact[i] = fact[i - 1].multiply(BigInteger.valueOf(i));
        }
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n && j < i + k; j++) {
                boolean ok = true;
                for (int j2 = i; j2 < j; j2++) {
                    if (!independence[a[j2]][a[j]]) {
                        ok = false;
                        break;
                    }
                }
                if (!ok) break;
                if (steps[j + 1] > steps[i] + 1) {
                    steps[j + 1] = steps[i] + 1;
                    ways[j + 1] = ways[i];
                    ways2[j + 1] = ways2[i].multiply(C[k][j - i + 1]).multiply(fact[j - i + 1]);
                } else if (steps[j + 1] == steps[i] + 1) {
                    ways[j + 1] = ways[j + 1].add(ways[i]);
                    ways2[j + 1] = ways2[j + 1].add(ways2[i].multiply(C[k][j - i + 1]).multiply(fact[j - i + 1]));
                }
            }
        }
        out.println(steps[n]);
        out.println(ways[n]);
        out.println(ways2[n]);
    }
}
