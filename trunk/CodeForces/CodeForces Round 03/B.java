import java.io.*;
import java.util.*;

public class B implements Runnable {
	public static void main(String[] args) {
		new Thread(new B()).start();
	}

	BufferedReader br;
	PrintWriter out;
	StringTokenizer st;
	boolean eof;

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

	public void run() {
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			out = new PrintWriter(System.out);
			solve();
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
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

	class W implements Comparable<W> {
		int x, num;

		public W(int x, int num) {
			this.x = x;
			this.num = num;
		}

		public int compareTo(W o) {
			if (x > o.x) {
				return -1;
			}
			if (x < o.x) {
				return 1;
			}
			return 0;
		}
	}

	void solve() {
		int n = nextInt();
		int v = nextInt();
		ArrayList<W> a = new ArrayList<W>();
		ArrayList<W> b = new ArrayList<W>();
		for (int i = 0; i < n; i++) {
			int x = nextInt();
			int y = nextInt();
			if (x == 1) {
				a.add(new W(y, i + 1));
			} else {
				b.add(new W(y, i + 1));
			}
		}
		Collections.sort(a);
		Collections.sort(b);
		if (a.size() + b.size() * 2 <= v) {
			long ans = 0;
			for (W w : a) {
				ans += w.x;
			}
			for (W w : b) {
				ans += w.x;
			}
			out.println(ans);
			for (int i = 1; i <= a.size() + b.size(); i++) {
				out.print(i + " ");
			}
			return;
		}
		TreeSet<Integer> ansSet = new TreeSet<Integer>();
		long ans = 0;
		if (b.size() == 0) {
			for (int i = 0; i < v; i++) {
				ans += a.get(i).x;
				ansSet.add(a.get(i).num);
			}
			out.println(ans);
			for (int e : ansSet) {
				out.print(e + " ");
			}
			return;
		}
		if (v % 2 == 0) {
			int i = 0, j = 0;
			while (v > 0) {
				if (j >= b.size()
						|| (i < a.size() - 1 && a.get(i).x + a.get(i + 1).x > b
								.get(j).x)) {
					ans += a.get(i).x + a.get(i + 1).x;
					ansSet.add(a.get(i).num);
					ansSet.add(a.get(i + 1).num);
					i += 2;
				} else {
					ans += b.get(j).x;
					ansSet.add(b.get(j).num);
					j++;
				}
				v -= 2;
			}
			out.println(ans);
			for (int e : ansSet) {
				out.print(e + " ");
			}
		} else {
			int i = 0, j = 0;
			if (a.size() % 2 != 0) {
				while (v > 1) {
					if (j >= b.size()
							|| (i < a.size() - 1 && a.get(i).x + a.get(i + 1).x > b
									.get(j).x)) {
						ans += a.get(i).x + a.get(i + 1).x;
						ansSet.add(a.get(i).num);
						ansSet.add(a.get(i + 1).num);
						i += 2;
					} else {
						ans += b.get(j).x;
						ansSet.add(b.get(j).num);
						j++;
					}
					v -= 2;
				}
				ans += a.get(i).x;
				ansSet.add(a.get(i).num);
				out.println(ans);
				for (int e : ansSet) {
					out.print(e + " ");
				}
			} else {
				while (v > 3) {
					if (j >= b.size()
							|| (i < a.size() - 2 && a.get(i).x + a.get(i + 1).x > b
									.get(j).x)) {
						ans += a.get(i).x + a.get(i + 1).x;
						ansSet.add(a.get(i).num);
						ansSet.add(a.get(i + 1).num);
						i += 2;
					} else {
						ans += b.get(j).x;
						ansSet.add(b.get(j).num);
						j++;
					}
					v -= 2;
				}
				if (j >= b.size()) {
					while (v > 0) {
						ans += a.get(i).x;
						ansSet.add(a.get(i).num);
						i++;
						v--;
					}
					out.println(ans);
					for (int e : ansSet) {
						out.print(e + " ");
					}
					return;
				}
				if (i >= a.size()) {
					while (v > 1) {
						ans += b.get(j).x;
						ansSet.add(b.get(j).num);
						j++;
						v -= 2;
					}
					out.println(ans);
					for (int e : ansSet) {
						out.print(e + " ");
					}
					return;
				}
				if (i < a.size() - 3) {
					if (b.get(j).x + a.get(i).x > a.get(i).x + a.get(i + 1).x
							+ a.get(i + 2).x) {
						ans += a.get(i).x + b.get(j).x;
						ansSet.add(a.get(i).num);
						ansSet.add(b.get(j).num);
						v -= 3;
						i++;
						j++;
					} else {
						ans += a.get(i).x + a.get(i + 1).x + a.get(i + 2).x;
						ansSet.add(a.get(i).num);
						ansSet.add(a.get(i + 1).num);
						ansSet.add(a.get(i + 2).num);
						v -= 3;
						i++;
						j++;
					}
				} else {
					if (b.get(j).x + a.get(i).x > a.get(i).x + a.get(i + 1).x) {
						ans += a.get(i).x + b.get(j).x;
						ansSet.add(a.get(i).num);
						ansSet.add(b.get(j).num);
						v -= 3;
						i++;
						j++;
					} else {
						ans += a.get(i).x + a.get(i + 1).x;
						ansSet.add(a.get(i).num);
						ansSet.add(a.get(i + 1).num);
						i += 2;
						v -= 2;
					}
					if (v == 1 && i < a.size()) {
						ans += a.get(i).x;
						ansSet.add(a.get(i).num);
					}
				}
				out.println(ans);
				for (int e : ansSet) {
					out.print(e + " ");
				}
			}
		}
	}
}
