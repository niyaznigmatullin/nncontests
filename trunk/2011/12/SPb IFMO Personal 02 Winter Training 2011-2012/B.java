import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.System.exit;
import static java.util.Arrays.asList;
import static java.util.Arrays.sort;

import java.io.*;
import java.util.*;

public class B {

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

		@Override
		public String toString() {
			return x + " " + y;
		}
	}

	static class Rectangle {
		Point p1;
		Point p2;

		public Rectangle(Point p1, Point p2) {
			super();
			this.p1 = p1;
			this.p2 = p2;
		}

		Rectangle intersect(Rectangle a) {
			Point z1 = new Point(max(p1.x, a.p1.x), max(p1.y, a.p1.y));
			Point z2 = new Point(min(p2.x, a.p2.x), min(p2.y, a.p2.y));
			return new Rectangle(z1, z2);
		}

		long intersectArea(Rectangle a) {
			int x1 = max(p1.x, a.p1.x);
			int y1 = max(p1.y, a.p1.y);
			int x2 = min(p2.x, a.p2.x);
			int y2 = min(p2.y, a.p2.y);
			if (x1 >= x2 || y1 >= y2) {
				return 0;
			}
			return (long) (x2 - x1) * (y2 - y1);
		}

		long area() {
			if (p1.x >= p2.x || p1.y >= p2.y) {
				return 0;
			}
			return (long) (p2.x - p1.x) * (p2.y - p1.y);
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((p1 == null) ? 0 : p1.hashCode());
			result = prime * result + ((p2 == null) ? 0 : p2.hashCode());
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
			Rectangle other = (Rectangle) obj;
			if (p1 == null) {
				if (other.p1 != null)
					return false;
			} else if (!p1.equals(other.p1))
				return false;
			if (p2 == null) {
				if (other.p2 != null)
					return false;
			} else if (!p2.equals(other.p2))
				return false;
			return true;
		}

		Point[] getAll() {
			return new Point[] { p1, new Point(p1.x, p2.y), p2,
					new Point(p2.x, p1.y) };
		}

		@Override
		public String toString() {
			return p1.toString() + " " + p2.toString();
		}
	}

	static class Answer {
		Rectangle[] r;

		public Answer(Rectangle[] r) {
			super();
			this.r = r;
		}

	}

	static void print(Answer ans) {
		out.println(ans.r.length);
		for (Rectangle e : ans.r) {
			out.println(e);
		}
	}

	static List<Rectangle> rects;
	static Rectangle[][] pair;
	static Point[] all;

	static Answer can(int x, int n, long neededArea, Rectangle outer,
			Rectangle bad) {
		if (neededArea < 0) {
			return null;
		}
		if (x == n) {
			return neededArea == 0 ? new Answer(rects
					.toArray(new Rectangle[rects.size()])) : null;
		}
		for (int i = 0; i < all.length; i++) {
			Point p1 = all[i];
			all: for (int j = i + 1; j < all.length; j++) {
				Point p2 = all[j];
				if (p1.x >= p2.x || p1.y >= p2.y) {
					continue;
				}
				Rectangle cur = pair[i][j];
				if (cur.intersectArea(outer) != cur.area()
						|| cur.intersectArea(bad) > 0) {
					continue;
				}
				for (Rectangle z : rects) {
					if (cur.intersectArea(z) != 0) {
						continue all;
					}
				}
				rects.add(cur);
				Answer got = can(x + 1, n, neededArea - cur.area(), outer, bad);
				if (got != null) {
					return got;
				}
				rects.remove(rects.size() - 1);
			}
		}
		return null;
	}

	static void solve() throws Exception {
		xs = new HashSet<Integer>();
		ys = new HashSet<Integer>();
		Rectangle a = readRectangle();
		Rectangle b = readRectangle();
		Rectangle c = a.intersect(b);
		if (c.area() == 0) {
			out.println(1);
			out.println(a);
			return;
		}			
		if (c.equals(a)) {
			out.println(0);
			return;
		}
		all = new Point[xs.size() * ys.size()];
		int cur = 0;
		for (int i : xs) {
			for (int j : ys) {
				all[cur++] = new Point(i, j);
			}
		}
		sort(all, new Comparator<Point>() {
			@Override
			public int compare(Point o1, Point o2) {
				if (o1.x != o2.x) {
					return o1.x - o2.x;
				}
				return o1.y - o2.y;
			}
		});
		System.err.println(Arrays.toString(all));
		pair = new Rectangle[all.length][all.length];
		for (int i = 0; i < all.length; i++) {
			for (int j = 0; j < all.length; j++) {
				pair[i][j] = new Rectangle(all[i], all[j]);
			}
		}
		rects = new ArrayList<Rectangle>();
		long area = a.area() - c.area();
		for (int i = 1; i <= 4; i++) {
			Answer can1 = can(0, i, area, a, c);
			if (can1 != null) {
				print(can1);
				return;
			}
		}
		// if (c.area() == 0) {
		// out.println(0);
		// return;
		// }
		// if (c.equals(a)) {
		// out.println(1);
		// out.println(a);
		// return;
		// }
		// if (c.p1.equals(a.p1)) {
		// if (c.p2.x == a.p2.x) {
		// out.println(1);
		// Point p1 = new Point(c.p1.x, c.p2.y);
		// Point p2 = a.p2;
		// out.println(new Rectangle(p1, p2));
		// } else if (c.p2.y == a.p2.y) {
		// out.println(1);
		// Point p1 = new Point(c.p2.x, c.p1.y);
		// Point p2 = a.p2;
		// out.println(new Rectangle(p1, p2));
		// } else {
		// out.println(2);
		// Point p1 = new Point(c.p2.x, c.p1.y);
		// Point p2 = new Point(a.p2.x, c.p2.y);
		// Point p3 = new Point(c.p1.x, c.p2.y);
		// Point p4 = a.p2;
		// out.println(new Rectangle(p1, p2));
		// out.println(new Rectangle(p3, p4));
		// }
		// } else if (c.p2.equals(a.p2)) {
		// if (c.p1.x == a.p1.x) {
		// out.println(1);
		// out.println(new Rectangle(a.p1, new Point(a.p2.x, c.p1.y)));
		// } else if (c.p1.y == a.p1.y) {
		// out.println(1);
		// out.println(new Rectangle(a.p1, new Point(c.p1.x, a.p2.y)));
		// } else {
		// out.println(2);
		// out.println(new Rectangle(a.p1, new Point(c.p1.x, a.p2.y)));
		// out.println(new Rectangle(new Point(c.p1.x, a.p1.y), new Point(
		// a.p2.x, c.p1.y)));
		// }
		// } else {
		// if (c.p1.x != a.p1.x && c.p2.x != a.p2.x && c.p1.y != a.p1.y
		// && c.p2.y != a.p2.y) {
		// out.println(4);
		// out.println(new Rectangle(a.p1, new Point(c.p1.x, a.p2.y)));
		// out.println(new Rectangle(new Point(c.p2.x, a.p1.y), a.p2));
		// out.println(new Rectangle(new Point(c.p1.x, a.p1.y), new Point(
		// c.p2.x, c.p1.y)));
		// out.println(new Rectangle(new Point(c.p1.x, c.p2.y), new Point(
		// c.p2.x, a.p2.y)));
		// } else if ()
		// }
	}

	static Set<Integer> xs;
	static Set<Integer> ys;
	
	static Point getIntersection(Point s1, Point s2, Point t1, Point t2) {
		if ((s1.x == s2.x) == (t1.x == t2.x)) {
			return null;
		}
		if (s1.x != s2.x) {
			{
				Point tmp = t1;
				t1 = s1;
				s1 = tmp;
			}
			{
				Point tmp = t2;
				t2 = s2;
				s2 = tmp;
			}
		}
		if (s1.x < min(t1.x, t2.x) || s1.x > max(t1.x, t2.x)
				|| t1.y < min(s1.y, s2.y) || t2.y > max(s1.y, s2.y)) {
			return null;
		}
		return new Point(s1.x, t1.y);
	}

	static Rectangle readRectangle() throws IOException {
		return new Rectangle(readPoint(), readPoint());
	}

	static Point readPoint() throws IOException {
		int x = nextInt();
		int y = nextInt();
		xs.add(x);
		ys.add(y);
		return new Point(x, y);
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
