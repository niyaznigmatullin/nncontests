import java.io.*;
import java.util.*;

public class D {

	static void solve() throws IOException {
		int n = nextInt();
		int[] color = new int[n];
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < n; i++) {
			color[i] = nextInt();
			add(map, color[i]);
		}
		if (map.size() == 1) {
			out.println(0);
			return;
		}
		int m = nextInt();
		for (int i = 0; i < m; i++) {
			int x = nextInt() - 1;
			int y = nextInt();
			remove(map, color[x]);
			color[x] = y;
			add(map, color[x]);
			if (map.size() == 1) {
				out.println(i + 1);
				return;
			}
		}
		out.println(-1);
	}

	static <K> void add(Map<K, Integer> map, K key) {
		Integer z = map.get(key);
		if (z == null)
			z = 0;
		map.put(key, z + 1);
	}

	static <K> void remove(Map<K, Integer> map, K key) {
		Integer z = map.get(key);
		if (z == 1)
			map.remove(key);
		else
			map.put(key, z - 1);
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		InputStream input = System.in;
		PrintStream output = System.out;
		File file = new File("d.in");
		if (file.exists() && file.canRead()) {
			input = new FileInputStream(file);
			output = new PrintStream("d.out");
		}
		br = new BufferedReader(new InputStreamReader(input));
		out = new PrintWriter(output);
		solve();
		out.close();
		br.close();
	}

	static boolean hasNext() throws IOException {
		while (st == null || !st.hasMoreTokens()) {
			String line = br.readLine();
			if (line == null) {
				return false;
			}
			st = new StringTokenizer(line);
		}
		return true;
	}

	static String next() throws IOException {
		return hasNext() ? st.nextToken() : null;
	}

	static int nextInt() throws IOException {
		return Integer.parseInt(next());
	}

	static double nextDouble() throws IOException {
		return Double.parseDouble(next());
	}

	static long nextLong() throws IOException {
		return Long.parseLong(next());
	}
}
