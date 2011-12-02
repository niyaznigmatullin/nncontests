package Maths;
import java.math.BigInteger;
import java.util.HashMap;

public class FastFourierTransofrm {

	static int MAXPOW = 1 << 21;
	static int ROOT;
	static int INVROOT;
	static int MOD;

	static {
		for (int i = 333;; i++) {
			if (testMOD(i * MAXPOW + 1, MAXPOW)) {
				MOD = i * MAXPOW + 1;
				break;
			}
		}
		ROOT = getRoot(1, MAXPOW, MOD);
		INVROOT = modPow(ROOT, MOD - 2, MOD);
		System.err.println("static int MOD = " + MOD + ";");
		System.err.println("static int ROOT = " + ROOT + ";");
		System.err.println("static int INVROOT = " + INVROOT + ";");
	}

	static boolean testMOD(int p, int n) {
		if (!BigInteger.valueOf(p).isProbablePrime(50)) {
			return false;
		}
		int r = getRoot(1, n, p);
		int cur = r;
		for (int i = 1; i < n; i++) {
			if (cur == 1) {
				return false;
			}
			cur = (int) ((long) cur * r % p);
		}
		return true;
	}

	static int getRoot(int x, int n, int p) {
		int g = getGenerator(p);
		int a = modPow(g, n, p);
		int k = log(x, a, p);
		return modPow(g, k, p);
	}

	static int log(int b, int a, int p) {
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		int d = 1;
		int m = (int) Math.sqrt(p) + 2;
		for (int i = 0; i < m; i++) {
			hm.put((int) ((long) d * b % p), i);
			d = (int) ((long) d * a % p);
		}
		int e = d;
		for (int i = 1; i < m; i++) {
			if (hm.containsKey(e)) {
				return i * m - hm.get(e);
			}
			e = (int) ((long) e * d % p);
		}
		return Integer.MIN_VALUE;
	}

	static int getGenerator(int p) {
		loop: for (int i = 2;; i++) {
			for (int j = 2; j * j < p; j++) {
				if (j % (p - 1) != 0) {
					continue;
				}
				if (modPow(i, (p - 1) / j, p) == 1) {
					continue loop;
				}
				if (modPow(i, j, p) == 1) {
					continue loop;
				}
			}
			return i;
		}
	}

	static int rev(int x, int n) {
		return Integer.reverse(x) >>> (32 - n);
	}

	static int modPow(int a, long b, int mod) {
		int ret = 1;
		while (b > 0) {
			if ((b & 1) == 1) {
				ret = (int) ((long) ret * a % mod);
			}
			a = (int) ((long) a * a % mod);
			b >>= 1;
		}
		return ret;
	}

	static void fft(int[] a, int n, boolean inv) {
		int bits = Integer.numberOfTrailingZeros(n);
		for (int i = 0; i < n; i++) {
			int j = rev(i, bits);
			if (i < j) {
				int t = a[i];
				a[i] = a[j];
				a[j] = t;
			}
		}
		for (int len = 2; len <= n; len <<= 1) {
			int m = (len >> 1);
			int fw = modPow(inv ? INVROOT : ROOT, MAXPOW / len, MOD);
			for (int i = 0; i < n; i += len) {
				int w = 1;
				for (int j = 0; j < m; j++) {
					int wm = (int) ((long) w * a[i + j + m] % MOD);
					int sm = a[i + j];
					a[i + j] = sm + wm;
					a[i + j + m] = sm - wm;
					if (a[i + j] >= MOD) {
						a[i + j] -= MOD;
					}
					if (a[i + j + m] < 0) {
						a[i + j + m] += MOD;
					}
					w = (int) ((long) w * fw % MOD);
				}
			}
		}
		if (inv) {
			int invn = modPow(n, MOD - 2, MOD);
			for (int i = 0; i < n; i++) {
				a[i] = (int) ((long) a[i] * invn % MOD);
			}
		}
	}

	public static void main(String[] args) {

	}
}
