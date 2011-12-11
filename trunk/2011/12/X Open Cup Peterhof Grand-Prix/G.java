import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.util.Arrays.fill;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class G {
	
	static BufferedReader in;
	static PrintWriter out;
	static StringTokenizer tok;
	
	static final long MOD = 1000000000000000000L;
	
//	static long get(Map<Integer, Long> dyn, int mask) {
//		Long ans = dyn.get(mask);
//		return ans == null ? 0 : ans;
//	}
//	
//	static void increment(Map<Integer, Long> dyn, int mask, long ans) {
//		ans += get(dyn, mask);
//		if (ans >= MOD) {
//			ans -= MOD;
//		}
//		dyn.put(mask, ans);
//	}
	
//	static void dump(int m, Map<Integer, Long> dyn) {
//		System.err.println("STATE DUMP");
//		for (Entry<Integer, Long> e: dyn.entrySet()) {
//			int key = e.getKey();
//			for (int i = 0; i < m; i++) {
//				System.err.print("WX?B".charAt((key >> (i << 1)) & 3));
//			}
//			System.err.println(": " + e.getValue());
//		}
//	}
	
	static int index(int map[], int key) {
		int index = key;
		index ^= index * 1000000009 >> 16;
		index ^= index * 1000000007 >> 24;
		int mask = map.length - 1;
		for (index &= mask; ; index = (index + 1) & mask) {
			if (map[index] == key) {
				return index;
			} else if (map[index] == -1) {
				return ~index;
			}
		}
	}
	
	static long get(int map[], long vals[], int key) {
		int index = index(map, key);
		return index < 0 ? 0 : vals[key];
	}
	
	static void increment(int map[], long vals[], int key, long ans) {
		int index = index(map, key);
		if (index < 0) {
			index = ~index;
			map[index] = key;
		}
		vals[index] += ans;
		if (vals[index] >= MOD) {
			vals[index] -= MOD;
		}
	}
	
	static final int SIZE = 1 << 19;
	
	static long solve(boolean arr[][]) {
		int n = arr.length;
		int m = arr[0].length;
//		Map<Integer, Long> dyn = new HashMap<Integer, Long>();
		int dynMap[] = new int[SIZE];
		long dynVals[] = new long[SIZE];
		fill(dynMap, -1);
		increment(dynMap, dynVals, 0, 1);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
//				Map<Integer, Long> dynNext = new HashMap<Integer, Long>();
				int dynNextMap[] = new int[SIZE];
				long dynNextVals[] = new long[SIZE];
				fill(dynNextMap, -1);
				for (int pos = 0; pos < SIZE; pos++) {
					int mask = dynMap[pos];
					if (mask == -1) {
						continue;
					}
					long val = dynVals[pos];
					int neigh1 = j > 0 ? (mask >> ((j - 1) << 1)) & 3 : 0;
					int neigh2 = (mask >> (j << 1)) & 3;
					if (!arr[i][j] && (neigh1 & neigh2 & 2) == 0) {
						int maskNext = mask & ~(3 << (j << 1));
						if (((neigh1 | neigh2) & 2) != 0) {
							maskNext |= 1 << (j << 1);
						}
						increment(dynNextMap, dynNextVals, maskNext, val);
					}
					if (neigh1 != 1 && neigh2 != 1) {
						int maskNext = mask | (3 << (j << 1));
						if (j > 0) {
							maskNext |= 1 << ((j - 1) << 1);
						}
						increment(dynNextMap, dynNextVals, maskNext, val);
					}
				}
				dynMap = dynNextMap;
				dynVals = dynNextVals;
//				dump(m, dyn);
			}
		}
		long ans = 0;
		for (long val: dynVals) {
			ans += val;
			if (ans >= MOD) {
				ans -= MOD;
			}
		}
		return ans;
	}
	
//	static final int dx[] = {1, 0, -1, 0};
//	static final int dy[] = {0, 1, 0, -1};
//	
//	static long solveDumb(boolean arr[][]) {
//		int n = arr.length;
//		int m = arr[0].length;
//		boolean arrCur[][] = new boolean[n][m];
//		for (int i = 0; i < n; i++) {
//			fill(arrCur[i], true);
//		}
//		long ans = 0;
//		comb: while (true) {
//			tryans: {
//				for (int i = 0; i < n; i++) {
//					for (int j = 0; j < m; j++) {
//						if (arrCur[i][j]) {
//							continue;
//						}
//						boolean have = false;
//						for (int d = 0; d < 4; d++) {
//							int ii = i + dx[d];
//							int jj = j + dy[d];
//							if (ii >= 0 && ii < n && jj >= 0 && jj < m && arrCur[ii][jj]) {
//								if (!have) {
//									have = true;
//								} else {
//									break tryans;
//								}
//							}
//						}
//					}
//				}
//				++ans;
//				if (ans >= MOD) {
//					ans -= MOD;
//				}
//			}
//			for (int i = n - 1; ; i--) {
//				if (i < 0) {
//					break comb;
//				}
//				for (int j = m - 1; j >= 0; j--) {
//					if (arrCur[i][j] && !arr[i][j]) {
//						arrCur[i][j] = false;
//						for (j++; j < m; j++) {
//							arrCur[i][j] = true;
//						}
//						for (i++; i < n; i++) {
//							for (j = 0; j < m; j++) {
//								arrCur[i][j] = true;
//							}
//						}
//						continue comb;
//					}
//				}
//			}
//		}
//		return ans;
//	}
	
//	static final Random rng = new Random(42);
	
//	static void onetest() {
//		int n;
//		int m;
//		do {
//			n = rng.nextInt(6) + 1;
//			m = rng.nextInt(6) + 1;
//		} while (n * m > 28);
//		boolean arr[][] = new boolean[n][m];
//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < m; j++) {
//				if (rng.nextInt(3) == 0) {
//					arr[i][j] = true;
//				}
//			}
//		}
//		long ans = solve(arr);
//		long ansDumb = solveDumb(arr);
//		if (ans != ansDumb) {
//			System.err.println(m + " " + n);
//			for (int i = 0; i < n; i++) {
//				for (int j = 0; j < m; j++) {
//					System.err.print(arr[i][j] ? 'B' : '.');
//				}
//				System.err.println();
//			}
//			System.err.println("Expected: " + ansDumb + ", actual: " + ans);
//			exit(1);
//		}
//	}
//	
//	static void strest() {
//		long time = currentTimeMillis() / 1000;
//		int tests = 0;
//		while (true) {
//			onetest();
//			++tests;
//			long newTime = currentTimeMillis() / 1000;
//			if (newTime != time) {
//				System.err.println(tests + " tests passed");
//				time = newTime;
//			}
//		}
//	}
	
	static void solve() throws Exception {
		int m = nextInt();
		int n = nextInt();
		boolean arr[][] = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			String l = next();
			for (int j = 0; j < m; j++) {
				switch (l.charAt(j)) {
				case 'B':
					arr[i][j] = true;
				case '.':
					break;
				default:
					throw new AssertionError();
				}
			}
		}
		out.print(solve(arr));
	}
	
	public static void main(String[] args) throws Exception {
//		strest();
		in = new BufferedReader(new InputStreamReader(new FileInputStream("life.in")));
		out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream("life.out"))));
		solve();
		in.close();
		out.close();
	}
	
	static String next() throws IOException {
		while (tok == null || !tok.hasMoreTokens()) {
			tok = new StringTokenizer(in.readLine());
		}
		return tok.nextToken();
	}
	
	static char nextChar() throws IOException {
		String token = next();
		if (token.length() != 1) {
			throw new IllegalArgumentException("String \"" + token + "\" is not a single character");
		}
		return token.charAt(0);
	}
	
	static int nextInt() throws IOException {
		return parseInt(next());
	}
	
	static long nextLong() throws IOException {
		return parseLong(next());
	}
	
	static double nextDouble() throws IOException {
		return parseDouble(next());
	}
	
	static BigInteger nextBigInt() throws IOException {
		return new BigInteger(next());
	}
}