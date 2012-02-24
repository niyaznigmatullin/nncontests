package mypackage;

import math.MathUtils;
import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] p = MathUtils.getFactoringSieve(n + 1);
        int[] hasForPrime = new int[n + 1];
        Set<Integer> set = new HashSet<Integer>();
        all:
        for (int i = 0; i < m; i++) {
            String op = in.next();
            int x = in.nextInt();
            if (op.equals("+")) {
                if (set.contains(x)) {
                    out.println("Already on");
                    continue;
                }
                for (int z = x; z > 1; z /= p[z]) {
                    if (hasForPrime[p[z]] > 0) {
                        out.println("Conflict with " + hasForPrime[p[z]]);
                        continue all;
                    }
                }
                for (int z = x; z > 1; z /= p[z]) {
                    hasForPrime[p[z]] = x;
                }
                set.add(x);
                out.println("Success");
            } else {
                if (!set.contains(x)) {
                    out.println("Already off");
                    continue;
                }
                for (int z = x; z > 1; z /= p[z]) {
                    hasForPrime[p[z]] = 0;
                }
                set.remove(x);
                out.println("Success");
            }
        }
    }
}
