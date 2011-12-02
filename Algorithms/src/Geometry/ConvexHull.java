package Geometry;
import java.util.*;

public class ConvexHull {

	static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		long vect(Point p1, Point p2) {
			return (p1.x - x) * (p2.y - y) - (p1.y - y) * (p2.x - x);
		}

		long dist2(Point p) {
			long dx = x - p.x;
			long dy = y - p.y;
			return dx * dx + dy * dy;
		}
	}

	static class ByAngle implements Comparator<Point> {
		Point p;

		public ByAngle(Point p) {
			super();
			this.p = p;
		}

		@Override
		public int compare(Point o1, Point o2) {
			long d = p.vect(o1, o2);
			long d1 = p.dist2(o1);
			long d2 = p.dist2(o2);
			return d > 0 ? -1 : d < 0 ? 1 : d1 < d2 ? -1 : d1 > d2 ? 1 : 0;
		}
	}

	static Point[] convexHull(Point[] p) {
		if (p.length < 3) {
			throw new AssertionError("not enough points");
		}
		Stack<Point> stack = new Stack<Point>();
		for (int i = 1; i < p.length; i++) {
			if (p[i].x < p[0].x || (p[i].x == p[0].x && p[i].y < p[0].y)) {
				Point t = p[0];
				p[0] = p[i];
				p[i] = t;
			}
		}
		ByAngle c = new ByAngle(p[0]);
		Arrays.sort(p, 1, p.length, c);
		stack.add(p[0]);
		stack.add(p[1]);
		for (int i = 1; i < p.length; i++) {
			while (true) {
				Point last2 = stack.pop();
				Point last = stack.peek();
				if (last.vect(last2, p[i]) >= 0) {
					stack.add(last2);
					break;
				}
			}
			stack.add(p[i]);
		}
		Point[] hull = new Point[stack.size()];
		int cnt = 0;
		for (Point e : stack) {
			hull[cnt++] = e;
		}
		return hull;
	}

}
