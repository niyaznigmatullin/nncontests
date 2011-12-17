import static java.lang.Math.max;
import static java.math.BigInteger.probablePrime;

import java.io.*;
import java.util.*;

public class C {

	static final Random rand = new Random(1238123L);

	static class HashString {
		static final long PRIME = probablePrime(22, rand).longValue();
		static long[] POWS = null;
		int[] str;
		long[] hash;
		int length;

		public HashString(int[] a) {
			length = a.length;
			str = a;
			hash = new long[str.length];
			long curHash = 0;
			for (int i = 0; i < hash.length; i++) {
				curHash = curHash * PRIME + str[i];
				hash[i] = curHash;
			}
			if (POWS == null || POWS.length <= str.length) {
				POWS = new long[str.length + 1];
				POWS[0] = 1;
				for (int i = 1; i < POWS.length; i++) {
					POWS[i] = POWS[i - 1] * PRIME;
				}
			}
		}

		long get(int l, int r) {
			if (l >= r) {
				return 0;
			}
			long ret = hash[r - 1];
			if (l > 0) {
				ret -= hash[l - 1] * POWS[r - l];
			}
			return ret;
		}
	}

	static void solve() throws IOException {
		int n = nextInt();
		String[] names = new String[n];
		HashString[] c = new HashString[n];
		map = new HashMap<String, Integer>();
		for (int i = 0; i < n; i++) {
			names[i] = br.readLine().trim();
			c[i] = new HashString(readSource());
		}
		HashString d = new HashString(readSource());
		int max = 0;
		List<String> answers = new ArrayList<String>();
		for (int i = 0; i < n; i++) {
			int got = longestCommonSubstring(c[i], d);
			if (got > max) {
				answers.clear();
				max = got;
			}
			if (got == max) {
				answers.add(names[i]);
			}
		}
		out.print(max);
		if (max != 0) {
			for (String e : answers) {
				out.print(" " + e);
			}
		}
		out.println();
	}

	static int longestCommonSubstring(HashString a, HashString b) {
		int l = -1;
		int r = Math.min(a.str.length, b.str.length) + 1;
		set = new HashSet<Long>();
		while (l < r - 1) {
			int mid = l + r >> 1;
			if (hasCommonSubstring(a, b, mid)) {
				l = mid;
			} else {
				r = mid;
			}
		}
		return l;
	}

	static boolean hasCommonSubstring(HashString a, HashString b, int len) {
		set.clear();
		for (int i = 0; i + len <= a.length; i++) {
			set.add(a.get(i, i + len));
		}
		for (int i = 0; i + len <= b.length; i++) {
			if (set.contains(b.get(i, i + len))) {
				return true;
			}
		}
		return false;
	}

	static Set<Long> set;
	static Map<String, Integer> map;

	static int[] readSource() throws IOException {
		List<Integer> list = new ArrayList<Integer>();
		while (true) {
			String s = br.readLine();
			if (s.equals("***END***")) {
				break;
			}
			s = s.trim();
			if (s.isEmpty()) {
				continue;
			}
			StringTokenizer st = new StringTokenizer(s);
			StringBuilder sb = new StringBuilder();
			while (st.hasMoreTokens()) {
				sb.append(st.nextToken()).append(' ');
			}
			s = sb.toString().trim();
			if (!map.containsKey(s)) {
				map.put(s, map.size());
			}
			list.add(map.get(s));
		}
		int[] ret = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			ret[i] = list.get(i);
		}
		return ret;
	}

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		solve();
		out.close();
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	static String nextToken() throws IOException {
		while (st == null || !st.hasMoreTokens()) {
			String line = br.readLine();
			if (line == null) {
				return null;
			}
			st = new StringTokenizer(line);
		}
		return st.nextToken();
	}

	static int nextInt() throws IOException {
		return Integer.parseInt(nextToken());
	}

	static long nextLong() throws IOException {
		return Long.parseLong(nextToken());
	}

	static double nextDouble() throws IOException {
		return Double.parseDouble(nextToken());
	}
}