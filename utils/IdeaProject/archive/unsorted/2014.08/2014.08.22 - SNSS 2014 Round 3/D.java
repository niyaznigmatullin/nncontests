import java.io.*;
import java.util.*;

public class D {

	static void solve() throws IOException {
		int t = nextInt();
		for (int i = 0; i < t; i++) {
			int n = nextInt();
			int[][] a = new int[n][];
			for (int j = 0; j < n; j++) {
				int k = nextInt();
				a[j] = new int[k];
				for (int e = 0; e < k; e++) {
					a[j][e] = nextInt();
				}
			}
			out.println(solve(a) ? "Yes" : "No");
		}
	}

	static boolean solve(int[][] a) {
		int all = 0;
		for (int[] e : a) {
			all += e.length;
		}
		int[] b = new int[all];
		for (int i = 0, k = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				b[k++] = a[i][j];
			}
		}
		b = sortAndUnique(b);
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				a[i][j] = Arrays.binarySearch(b, a[i][j]);
			}
		}
		List<Integer>[] edges = new List[b.length];
		for (int i = 0; i < edges.length; i++) {
			edges[i] = new ArrayList<>();
		}
		int[] deg = new int[b.length];
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j + 1 < a[i].length; j++) {
				edges[a[i][j]].add(a[i][j + 1]);
				deg[a[i][j + 1]]++;
			}
		}
		int[] q = new int[b.length];
		int head = 0;
		int tail = 0;
		for (int i = 0; i < b.length; i++) {
			if (deg[i] == 0) {
				q[tail++] = i;
			}
		}
		while (head < tail) {
			int v = q[head++];
			for (int i : edges[v]) {
				--deg[i];
				if (deg[i] == 0) {
					q[tail++] = i;
				}
			}
		}
		return tail == b.length;
	}

	static public int[] sortAndUnique(int[] a) {
		int[] ret = a.clone();
		sort(ret);
		if (ret.length == 0) {
			return ret;
		}
		int last = ret[0];
		int j = 1;
		for (int i = 1; i < ret.length; i++) {
			if (last != ret[i]) {
				ret[j++] = ret[i];
				last = ret[i];
			}
		}
		return Arrays.copyOf(ret, j);
	}

	public static void sort(int[] a) {
		shuffle(a);
		Arrays.sort(a);
	}

	static final Random rand = new Random();

	public static void shuffle(int[] a) {
		for (int i = 0; i < a.length; i++) {
			int j = rand.nextInt(i + 1);
			int t = a[i];
			a[i] = a[j];
			a[j] = t;
		}
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
