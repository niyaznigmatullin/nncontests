package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TaskC {

    static boolean win(long a, long b) {
        if (a > b) {
            long t = a;
            a = b;
            b = t;
        }
        if (a == 0) {
            return false;
        }
        if (b % a == 0) {
            return true;
        }
        long c = b % a;
        boolean next = win(b % a, a);
        if (!next) {
            return true;
        }
        long z = (b - c) / a;
        z %= a + 1;
        return (z & 1) == 0;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            long a = in.nextLong();
            long b = in.nextLong();
            if (win(a, b)) {
                out.println("First");
            } else {
                out.println("Second");
            }
        }
    }
}
