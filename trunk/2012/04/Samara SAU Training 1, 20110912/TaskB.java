package mypackage;

import math.MathUtils;
import niyazio.FastScanner;
import niyazio.FastPrinter;

public class TaskB {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        long n = in.nextLong();
        int[] primes = MathUtils.genPrimes(100000);
        int answer = 0;
        for (int i : primes) {
            if (1L * i * i * i * i > n) {
                break;
            }
            answer++;
        }
        out.println(answer);
	}
}
