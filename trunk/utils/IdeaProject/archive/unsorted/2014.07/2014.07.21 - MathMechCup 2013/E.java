import java.io.*;
import java.util.*;

public class E {

	static int[] DX = { 1, 0, -1, -1, 0, 1 };
	static int[] DY = { 0, 1, 1, 0, -1, -1 };

	static void solve() throws IOException {
		String s = next();
		List<Integer> dirs = new ArrayList<>();
		List<Point> cp = new ArrayList<>();
		for (int dir = 0, i = 0, cx = 0, cy = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (ch == 'F') {
				dirs.add(dir);
				cx += DX[dir];
				cy += DY[dir];
				cp.add(new Point(cx, cy));
			} else if (ch == 'R') {
				dir = (dir + 1) % 6;
			} else {
				dir = (dir + 5) % 6;
			}
		}
		int ans = 0;
		for (int i = 0; i + 1 < dirs.size(); i++) {
			for (int j = i + 1; j + 1 < dirs.size(); j++) {
				if (!cp.get(i).equals(cp.get(j))) {
					continue;
				}
				int[] f = new int[] { (dirs.get(i) + 3) % 6, dirs.get(i + 1),
						(dirs.get(j) + 3) % 6, dirs.get(j + 1) };
				int[] z = new int[] { 1, 1, 2, 2 };
				for (int x = 0; x < 4; x++) {
					for (int y = x + 1; y < 4; y++) {
						if (f[x] > f[y]) {
							int t = f[x];
							f[x] = f[y];
							f[y] = t;
							t = z[x];
							z[x] = z[y];
							z[y] = t;
						}
					}
				}
				if (f[0] == f[1] || f[1] == f[2] || f[2] == f[3]
						|| z[0] == z[1] || z[1] == z[2] || z[2] == z[3]) {
					continue;
				}
				++ans;
			}
		}
		out.println(ans);
	}

	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Point other = (Point) obj;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}

	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		InputStream input = System.in;
		PrintStream output = System.out;
		File file = new File("archelon.in");
		if (file.exists() && file.canRead()) {
			input = new FileInputStream(file);
			output = new PrintStream("archelon.out");
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
