import java.util.*;
import java.math.*;
import java.io.*;

import static java.math.BigInteger.*;

public class Main implements Runnable {
	public static void main(String[] args) {
		new Thread(new Main()).start();
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

	final double EPS = 1e-9, EPS2 = 1e-7, EPS3 = 1e-7;

	int[] a, b, c;
	Player[] players;
	int n;

	final boolean DEBUG = false;

	class Table implements Comparable<Table> {
		int[] abc;

		Table(double t) {
			for (int i = 0; i < n; i++) {
				players[i].n = i;
				players[i].pnt = a[i] * t * t + b[i] * t + c[i];
			}
			Arrays.sort(players);
			if (DEBUG)
				System.err.println(t + " " + Arrays.toString(players));
			abc = new int[n];
			double last = -1000000000D;
			for (int i = 0; i < n; i++) {
				if (Math.abs(players[i].pnt - last) < EPS3) {
					abc[players[i].n] = abc[players[i - 1].n];
				} else {
					abc[players[i].n] = i;
				}
				last = players[i].pnt;
			}
		}

		public String toString() {
			return "Table [abc=" + Arrays.toString(abc) + "]";
		}

		public int compareTo(Table o) {
			for (int i = 0; i < n; i++) {
				if (abc[i] < o.abc[i]) {
					return -1;
				}
				if (abc[i] > o.abc[i]) {
					return 1;
				}
			}
			return 0;
		}

		public boolean equals(Object o) {
			Table t = (Table) o;
			for (int i = 0; i < n; i++) {
				if (abc[i] != t.abc[i])
					return false;
			}
			return true;
		}

	}

	class Player implements Comparable<Player> {
		double pnt;
		int n;

		public int compareTo(Player o) {
			return Double.compare(o.pnt, pnt);
		}

		public String toString() {
			return "Player [n=" + n + ", pnt=" + pnt + "]";
		}
	}

	void solve() {
		n = nextInt();
		a = new int[n];
		b = new int[n];
		c = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = nextInt();
			b[i] = nextInt();
			c[i] = nextInt();
		}
		players = new Player[n];
		for (int i = 0; i < n; i++) {
			players[i] = new Player();
		}
		ArrayList<Double> events = new ArrayList<Double>();
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				double dA = a[i] - a[j];
				double dB = b[i] - b[j];
				double dC = c[i] - c[j];
				double dD = dB * dB - 4 * dA * dC;
				if (dD < -EPS) {
					continue;
				}
				if (Math.abs(dD) < EPS) {
					double sol = -dB / (2 * dA);
					if (sol < EPS || sol > 1 + EPS) {
						continue;
					}
					events.add(sol);
				}
				double sol1 = (-dB + Math.sqrt(dD)) / (2 * dA);
				double sol2 = (-dB - Math.sqrt(dD)) / (2 * dA);
				if (DEBUG)
					System.err.println(sol1 + " " + sol2 + " " + a[i] + " "
							+ b[i] + " " + c[i] + " " + a[j] + " " + b[j] + " "
							+ c[j]);
				if (sol1 > EPS && sol1 < 1 + EPS) {
					events.add(sol1);
				}
				if (sol2 > EPS && sol2 < 1 + EPS) {
					events.add(sol2);
				}
			}
		}
		Collections.sort(events);
		ArrayList<Double> ev = new ArrayList<Double>();
		double last = -100;
		for (double e : events) {
			if (Math.abs(e - last) < EPS) {
				continue;
			}
			ev.add(e);
			last = e;
		}
		if (ev.isEmpty()) {
			out.println(1);
			return;
		}
		ArrayList<Table> table = new ArrayList<Table>(ev.size() * 2 + 4);
		table.add(new Table(EPS2));
		for (double event : ev) {
			table.add(new Table(event));
			table.add(new Table(event + EPS2));
		}
		if (DEBUG)
			System.err.println(table);
		Collections.sort(table);
		int ans = 1;
		for (int i = 1; i < table.size(); i++) {
			if (!table.get(i).equals(table.get(i - 1))) {
				ans++;
			}
		}
		out.println(ans);
	}

	public void run() {
		// long time = System.currentTimeMillis();
		try {
			br = new BufferedReader(new FileReader(new File("input.txt")));
			out = new PrintWriter(new File("output.txt"));
			solve();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(111);
		}
		// if (DEBUG) System.err.println(System.currentTimeMillis() - time);
		if (std)
			out.flush();
		else
			out.close();
	}
}