import static java.lang.Math.max;
import static java.lang.System.exit;

import java.io.*;
import java.util.*;

public class A {

	static void solve() throws Exception {
		int t = nextInt();
		Map<String, Integer> map = new HashMap<String, Integer>();
		int best = 0;
		for (int i = 0; i < t; i++) {
			int n = nextInt();
			for (int j = 0; j < n; j++) {
				int k = nextInt();
				String s = next();
				if (!map.containsKey(s)) {
					map.put(s, 0);
				}
				int value = map.get(s) + k;
				best = max(best, value);
				map.put(s, value);
			} 
		}
		List<String> answer = new ArrayList<String>();
		for (String e : map.keySet()) {
			if (map.get(e).intValue() == best) {
				answer.add(e);
			}			
		}
		Collections.sort(answer);
		for (String e : answer) {
			out.println(e);
		}
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	static int nextInt() throws IOException {
		return Integer.parseInt(next());
	}

	static long nextLong() throws IOException {
		return Long.parseLong(next());
	}

	static double nextDouble() throws IOException {
		return Double.parseDouble(next());
	}

	static String next() throws IOException {
		while (st == null || !st.hasMoreTokens()) {
			String s = br.readLine();
			if (s == null) {
				return null;
			}
			st = new StringTokenizer(s);
		}
		return st.nextToken();
	}

	public static void main(String[] args) {
		try {
			File file = new File("input.txt");
			if (file.canRead()) {
				System.setIn(new FileInputStream(file));
				System.setOut(new PrintStream(new File("output.txt")));
			}
			br = new BufferedReader(new InputStreamReader(System.in));
			out = new PrintWriter(System.out);
			solve();
			br.close();
			out.close();
		} catch (OutOfMemoryError e) {
			e.printStackTrace();
			List<Void> list = new ArrayList<Void>();
			while (true) {
				list.add(null);
			}
		} catch (Throwable e) {
			e.printStackTrace();
			exit(1);
		}
	}
}
