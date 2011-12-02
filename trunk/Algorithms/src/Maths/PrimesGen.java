package Maths;
public class PrimesGen {
	static boolean[] np;
	static int[] primes;
	static int MAXP = 1000000;
	static {
		np = new boolean[MAXP];
		for (int i = 2; i * i < MAXP; i++) {
			if (!np[i]) {
				for (int j = i * i; j < MAXP; j += i) {
					np[j] = true;
				}
			}
		}
		int count = 0;
		for (int i = 2; i < MAXP; i++) {
			if (!np[i]) {
				count++;
			}
		}
		primes = new int[count];
		for (int i = 2, j = 0; i < MAXP; i++) {
			if (!np[i]) {
				primes[j++] = i;
			}
		}
	}
}
