import java.util.*;
import java.math.*;
import java.io.*;

public class F implements Runnable {
	public static void main(String[] args) {
		new Thread(new F()).start();
	}

	StringTokenizer st;
	PrintWriter out;
	BufferedReader br;
	boolean eof = false, in_out = false, std = false;

	String nextToken() {
		while (st == null || !st.hasMoreTokens()) {
			try {
				st = new StringTokenizer(br.readLine());
			} catch (Exception e) {
				eof = true;
				return "0";
			}
		}
		return st.nextToken();
	}

	String nextLine() {
		String ret = "";
		try {
			ret = br.readLine();
		} catch (Exception e) {
			ret = "";
		}
		if (ret == null) {
			eof = true;
			return "$";
		}
		return ret;
	}

	String nextString() {
		return nextToken();
	}

	int nextInt() {
		return Integer.parseInt(nextToken());
	}

	long nextLong() {
		return Long.parseLong(nextToken());
	}

	double nextDouble() {
		return Double.parseDouble(nextToken());
	}

	BigInteger nextBigInteger() {
		return new BigInteger(nextToken());
	}

	public void run() {
		// long time = System.currentTimeMillis();
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			out = new PrintWriter(System.out);
			solve();
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(111);
		}
		// System.err.println(System.currentTimeMillis() - time);
	}

	void solve() {
		int n = nextInt();
		for (int i = 0; i < n; i++) {
			solve2();
		}
	}

	class Point {
		long x, y;

		public Point(long x, long y) {
			this.x = x;
			this.y = y;
		}

		Point subtract(Point p) {
			return new Point(x - p.x, y - p.y);
		}

		@Override
		public String toString() {
			return x + " " + y;
		}

		long vectMul(Point p) {
			return x * p.y - y * p.x;
		}
	}

	void reverse(Point[] p) {
		for (int i = 0; i < p.length - i - 1; i++) {
			Point temp = p[i];
			p[i] = p[p.length - i - 1];
			p[p.length - i - 1] = temp;
		}
	}

	void solve2() {
		int dataSet = nextInt();
		int n = nextInt();
		long minY = Integer.MAX_VALUE;
		long maxY = Integer.MIN_VALUE;
		Point[] p = new Point[n];
		int id = -1;
		for (int i = 0; i < n; i++) {
			int x = nextInt();
			int y = nextInt();
			p[i] = new Point(x, y);
		}
		if (p[2].subtract(p[1]).vectMul(p[1].subtract(p[0])) > 0) {
			reverse(p);
		}
		for (int i = 0; i < n; i++) {
			if (p[i].y < minY) {
				minY = p[i].y;
				id = i;
			}
			maxY = Math.max(maxY, p[i].y);
		}
		if (DEBUG)
			System.err.println(Arrays.toString(p));
		ArrayList<Segment> ans = new ArrayList<Segment>();
		int jd = id;
		long d1 = p[id].x;
		long d2 = p[id].x;
		for (long i = minY + 1; i < maxY; i++) {
			while (p[id].y < i) {
				id--;
				if (id == -1) {
					id = p.length - 1;
				}
			}
			while (p[jd].y < i) {
				jd = (jd + 1) % p.length;
			}
			Point last1 = p[(id + 1) % p.length];
			Point last2 = p[(jd + p.length - 1) % p.length];
			while (true) {
				long dx = d1 - last1.x;
				long dy = i - last1.y;
				long ex = p[id].x - last1.x;
				long ey = p[id].y - last1.y;
				if (i == 1) {
					if (DEBUG)
						System.err.println("===========================");
					if (DEBUG)
						System.err.println(dx + " " + dy + " " + ex + " " + ey);
					if (DEBUG)
						System.err.println("===========================");
				}
				if (ey * dx < ex * dy) {
					break;
				}
				d1--;
			}
			while (true) {
				long dx = d2 - last2.x;
				long dy = i - last2.y;
				long ex = p[jd].x - last2.x;
				long ey = p[jd].y - last2.y;
				if (dx * ey > dy * ex) {
					break;
				}
				d2++;
			}
			while (true) {
				long dx = d1 - last1.x;
				long dy = i - last1.y;
				long ex = p[id].x - last1.x;
				long ey = p[id].y - last1.y;
				if (ey * dx > ex * dy) {
					break;
				}
				d1++;
			}
			while (true) {
				long dx = d2 - last2.x;
				long dy = i - last2.y;
				long ex = p[jd].x - last2.x;
				long ey = p[jd].y - last2.y;
				if (dx * ey < dy * ex) {
					break;
				}
				d2--;
			}
			if (DEBUG)
				System.err.println(i + " " + d1 + " " + d2);
			if (d2 >= d1) {
				ans.add(new Segment(i, d1, d2));
			}
		}
		Collections.reverse(ans);
		out.println(dataSet + " " + ans.size());
		for (Segment e : ans) {
			out.println(e.y + " " + e.x1 + " " + e.x2);
		}
	}

	final boolean DEBUG = false;

	class Segment {
		long y, x1, x2;

		public Segment(long y, long x1, long x2) {
			this.y = y;
			this.x1 = x1;
			this.x2 = x2;
		}

	}
}