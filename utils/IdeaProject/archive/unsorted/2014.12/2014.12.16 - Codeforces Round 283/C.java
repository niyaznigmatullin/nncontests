import java.io.*;
import java.util.*;

public class C {

	static class Segment {
		int a;
		int b;
		int id;

		public Segment(int a, int b, int id) {
			this.a = a;
			this.b = b;
			this.id = id;
		}

	}

	static class Event {
		int type;
		int x;
		int id;

		public Event(int type, int x, int id) {
			this.type = type;
			this.x = x;
			this.id = id;
		}

	}

	static void solve() throws IOException {
		int n = nextInt();
		Segment[] a = new Segment[n];
		for (int i = 0; i < n; i++) {
			a[i] = new Segment(nextInt(), nextInt(), i);
		}
		int m = nextInt();
		final Segment[] b = new Segment[m];
		int[] left = new int[m];
		for (int i = 0; i < m; i++) {
			b[i] = new Segment(nextInt(), nextInt(), i);
			left[i] = nextInt();
		}
		Event[] events = new Event[n + m];
		for (int i = 0; i < n; i++) {
			events[i] = new Event(0, a[i].a, i);
		}
		for (int i = 0; i < m; i++) {
			events[i + n] = new Event(1, b[i].a, i);
		}
		Arrays.sort(events, new Comparator<Event>() {
			@Override
			public int compare(Event o1, Event o2) {
				int c = Integer.compare(o1.x, o2.x);
				if (c != 0)
					return c;
				return Integer.compare(o2.type, o1.type);
			}
		});
		NavigableSet<Segment> set = new TreeSet<Segment>(
				new Comparator<Segment>() {
					@Override
					public int compare(Segment o1, Segment o2) {
						if (o1.b != o2.b)
							return Integer.compare(o1.b, o2.b);
						return Integer.compare(o1.id, o2.id);
					}
				});
		int[] ans = new int[n];
		for (Event e : events) {
			if (e.type == 0) {
				Segment f = new Segment(a[e.id].a, a[e.id].b, -1);
				while (true) {
					Segment g = set.higher(f);
					if (g == null) {
						out.println("NO");
						return;
					}
					if (left[g.id] == 0) {
						set.remove(g);
						continue;
					}
					--left[g.id];
					ans[e.id] = g.id;
					break;
				}
			} else {
				set.add(b[e.id]);
			}
		}
		out.println("YES");
		for (int i = 0; i < n; i++) {
			if (i > 0) {
				out.print(' ');
			}
			out.print(ans[i] + 1);
		}
		out.println();
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		InputStream input = System.in;
		PrintStream output = System.out;
		File file = new File("c.in");
		if (file.exists() && file.canRead()) {
			input = new FileInputStream(file);
			output = new PrintStream("c.out");
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
