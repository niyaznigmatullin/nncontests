package Maths;
public class BigNumber {
	static final long BASE = 1000000;

	static long[] add(long[] a, long[] b) {
		int n = Math.max(a.length, b.length);
		long[] ret = new long[n + 1];
		for (int i = 0; i < n; i++) {
			long t = a[i] + b[i];
			if (t >= BASE) {
				ret[i + 1]++;
				t -= BASE;
			}
			ret[i] = t;
		}
		return ret;
	}

	static void add(long[] ret, long[] a, long[] b) {
		int n = Math.max(a.length, b.length);
		if (ret.length < n + 1) {
			throw new RuntimeException();
		}
		for (int i = 0; i < n; i++) {
			long t = a[i] + b[i];
			if (t >= BASE) {
				ret[i + 1]++;
				t -= BASE;
			}
			ret[i] = t;
		}
	}

	static int compare(long[] a, long[] b) {
		int i = a.length - 1;
		int j = b.length - 1;
		while (i > 0 && a[i] == 0) {
			i--;
		}
		while (j > 0 && b[j] == 0) {
			j--;
		}
		if (i != j) {
			return i - j;
		}
		for (; i >= 0; i--, j--) {
			if (a[i] != a[j]) {
				return a[i] < a[j] ? -1 : 1;
			}
		}
		return 0;
	}

	static long[] mul(long[] a, long[] b) {
		long[] ret = new long[a.length + b.length];
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < b.length; j++) {
				ret[i + j] += a[i] * b[j];
			}
		}
		for (int i = 0; 1 + i < ret.length; i++) {
			if (ret[i] >= BASE) {
				ret[i + 1] += ret[i] / BASE;
				ret[i] %= BASE;
			}
		}
		return ret;
	}

}
