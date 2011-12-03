import static java.lang.Math.abs;
import static java.lang.System.exit;
import static java.util.Arrays.copyOf;
import static java.util.Arrays.deepToString;
import static java.util.Arrays.sort;

import java.io.*;
import java.util.*;

public class D {

	static char[] C = { 'I', 'V', 'X', 'L', 'C', 'D', 'M' };
	static int[] V = { 1, 5, 10, 50, 100, 500, 1000 };
	static String S = new String(C);

	static Map<Character, Integer> map;
	static Map<Integer, Character> revMap;
	static {
		map = new HashMap<Character, Integer>();
		revMap = new HashMap<Integer, Character>();
		for (int i = 0; i < C.length; i++) {
			map.put(C[i], V[i]);
			revMap.put(V[i], C[i]);
		}
	}

	static void solve() throws Exception {
		int n = nextInt();
		String str = next();
		int[] a = new int[str.length()];
		for (int i = 0; i < a.length; i++) {
			a[i] = map.get(str.charAt(i));
		}
		sort(a);
		StringBuilder plus = new StringBuilder();
		StringBuilder minus = new StringBuilder();
		{
			int i = a.length - 1;
			while (i > 0 && a[i] == a[a.length - 1]) {
				plus.append(revMap.get(a[i]));
				n -= a[i];
				i--;
			}
			a = copyOf(a, i + 1);
		}
		int s = 0;
		for (int i : a) {
			s += i;
		}
		if (abs(n) > abs(s) || (n & 1) != (s & 1)) {
			out.println("NO");
			return;
		}
		n = (s + n) / 2;
		boolean[] can = new boolean[n + 1];
		int[] last = new int[n + 1];
		can[0] = true;
		for (int i : a) {
			for (int j = can.length - i - 1; j >= 0; j--) {
				if (!can[j] || can[i + j]) {
					continue;
				}
				can[i + j] = true;
				last[i + j] = i;
			}
		}
		if (!can[n]) {
			out.println("NO");
			return;
		}
		for (int i = n; i > 0; i -= last[i]) {
			plus.append(revMap.get(last[i]));
		}
		int[] count = new int[C.length];
		for (char c : str.toCharArray()) {
			count[S.indexOf(c)]++;
		}
		for (char c : plus.toString().toCharArray()) {
			count[S.indexOf(c)]--;
		}
		for (int i = 0; i < count.length; i++) {
			for (int j = 0; j < count[i]; j++) {
				minus.append(C[i]);
			}
		}
		minus.reverse();
		String answer = minus + "" + plus;
		out.println(answer);
	}

	static int getSum(String s) {
		if (s.equals("")) {
			return 0;
		}
		int best = -1;
		for (int i = 0; i < s.length(); i++) {
			if (best < 0 || S.indexOf(s.charAt(best)) < S.indexOf(s.charAt(i))) {
				best = i;
			}
		}
		return map.get(s.charAt(best)) + getSum(s.substring(best + 1))
				- getSum(s.substring(0, best));
	}

	public static void main(String[] args) {
		try {
			File file = new File("input.txt");
			InputStream input = System.in;
			PrintStream output = System.out;
			if (System.getProperty("ONLINE_JUDGE") == null && file.canRead()) {
				input = new FileInputStream(file);
				output = new PrintStream(new File("output.txt"));
			}
			br = new BufferedReader(new InputStreamReader(input));
			out = new PrintWriter(output);
			solve();
			out.close();
		} catch (Throwable e) {
			e.printStackTrace();
			exit(239);
		}
	}

	static void debug(Object... a) {
		System.err.println(deepToString(a));
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	static String next() throws IOException {
		while (st == null || !st.hasMoreTokens()) {
			String line = br.readLine();
			if (line == null) {
				return null;
			}
			st = new StringTokenizer(line);
		}
		return st.nextToken();
	}

	static long nextLong() throws IOException {
		return Long.parseLong(next());
	}

	static int nextInt() throws IOException {
		return Integer.parseInt(next());
	}

	static double nextDouble() throws IOException {
		return Double.parseDouble(next());
	}
}
