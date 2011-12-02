package DataStructures;
import java.math.BigInteger;
import java.util.*;

public class HashTables {
	static final Random rand = new Random();

	static class LongHashMap {
		final static int A = BigInteger.probablePrime(20, rand).intValue();
		final static int B = BigInteger.probablePrime(17, rand).intValue();
		long[] map;
		int[] value;

		public LongHashMap() {
			map = new long[B];
			value = new int[B];
			clear();
		}

		void clear() {
			Arrays.fill(map, Long.MIN_VALUE);
		}

		int put(long v, int val) {
			int key = hash(v);
			while (map[key] != Long.MIN_VALUE && map[key] != v) {
				key++;
				if (key == B) {
					key = 0;
				}
			}
			map[key] = v;
			value[key] = val;
			return key;
		}

		boolean containsKey(long v) {
			int key = hash(v);
			while (map[key] != Long.MIN_VALUE) {
				if (map[key] == v) {
					return true;
				}
				key++;
				if (key == B) {
					key = 0;
				}
			}
			return false;
		}

		int get(long v) {
			int key = hash(v);
			while (map[key] != Long.MIN_VALUE) {
				if (map[key] == v) {
					return value[key];
				}
				key++;
				if (key == B) {
					key = 0;
				}
			}
			return Integer.MIN_VALUE;
		}

		int hash(long v) {
			return (int) (((v * A) % B + B) % B);
		}
	}

	static class IntHashMap {
		final static int A = BigInteger.probablePrime(20, rand).intValue();
		final static int B = BigInteger.probablePrime(17, rand).intValue();
		int[] map;
		int[] value;

		public IntHashMap() {
			map = new int[B];
			value = new int[B];
			clear();
		}

		void clear() {
			Arrays.fill(map, Integer.MIN_VALUE);
		}

		int put(int v, int val) {
			int key = hash(v);
			while (map[key] != Integer.MIN_VALUE && map[key] != v) {
				key++;
				if (key == B) {
					key = 0;
				}
			}
			map[key] = v;
			value[key] = val;
			return key;
		}

		boolean containsKey(int v) {
			int key = hash(v);
			while (map[key] != Integer.MIN_VALUE) {
				if (map[key] == v) {
					return true;
				}
				key++;
				if (key == B) {
					key = 0;
				}
			}
			return false;
		}

		int get(int v) {
			int key = hash(v);
			while (map[key] != Integer.MIN_VALUE) {
				if (map[key] == v) {
					return value[key];
				}
				key++;
				if (key == B) {
					key = 0;
				}
			}
			return Integer.MIN_VALUE;
		}

		int hash(int v) {
			return (Math.abs(v * A) % B);
		}
	}

}
