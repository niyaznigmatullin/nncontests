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
				st = new StringTokenizer(br.readLine(), " :");
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
		try {
			br = new BufferedReader(new FileReader(new File("input.txt")));
			out = new PrintWriter(new File("output.txt"));
			solve();
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(456);
		}
	}

	boolean check(char[][] c1, char[][] c2) {
		int[] a = new int[256];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < c1[i].length; j++) {
				a[c1[i][j]]++;
			}
		}
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < c2[i].length; j++) {
				a[c2[i][j]]--;
			}
		}
		for (int i = 0; i < a.length; i++) {
			if (a[i] != 0)
				return false;
		}
		return true;
	}

	HashMap<Character, Integer> d;

	class State {
		int h;
		int steps;

		State(char[][] c, int steps) {
			h = 0;
			for (int i = 0; i < c.length; i++) {
				for (int j = 0; j < c[i].length; j++) {
					h = h * 10 + d.get(c[i][j]);
				}
			}
			this.steps = steps;
		}
	}

	void toCharArray(State state) {
		int e = state.h;
		for (int i = 1; i >= 0; i--) {
			for (int j = 3; j >= 0; j--) {
				cGot[i][j] = charS[e % 10];
				e /= 10;
			}
		}
	}

	char[][] cGot;
	char[] charS;

	void solve() {
		char[][] c1 = new char[2][];
		char[][] c2 = new char[2][];
		cGot = new char[2][4];
		c1[0] = nextToken().toCharArray();
		c1[1] = nextToken().toCharArray();
		c2[0] = nextToken().toCharArray();
		c2[1] = nextToken().toCharArray();
		if (!check(c1, c2)) {
			out.println("-1");
			return;
		}
		int num = 1;
		d = new HashMap<Character, Integer>();
		d.put('#', 0);
		charS = new char[8];
		charS[0] = '#';
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 4; j++) {
				if (!d.containsKey(c1[i][j])) {
					charS[num] = c1[i][j];
					d.put(c1[i][j], num++);
				}
			}
		}
		State start = new State(c1, 0);
		State finish = new State(c2, -1);
		Queue<State> que = new LinkedList<State>();
		que.add(start);
		HashSet<Integer> was = new HashSet<Integer>();
		was.add(start.h);
		while (!que.isEmpty()) {
			State state = que.poll();
			toCharArray(state);
			int id = -1;
			int jd = -1;
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 4; j++) {
					if (cGot[i][j] == '#') {
						id = i;
						jd = j;
					}
				}
			}
			for (int dir = 0; dir < 4; dir++) {
				int x = id + dx[dir];
				int y = jd + dy[dir];
				if (x < 0 || x >= 2 || y < 0 || y >= 4) {
					continue;
				}
				{
					char temp = cGot[id][jd];
					cGot[id][jd] = cGot[x][y];
					cGot[x][y] = temp;
				}
				State cur = new State(cGot, state.steps + 1);
				if (cur.h == finish.h) {
					out.println(state.steps + 1);
					return;
				}
				if (!was.contains(cur.h)) {
					was.add(cur.h);
					que.add(cur);
				}
				{
					char temp = cGot[id][jd];
					cGot[id][jd] = cGot[x][y];
					cGot[x][y] = temp;
				}
			}
		}
		out.println("-1");
	}

	final int[] dx = { 1, 0, -1, 0 };
	final int[] dy = { 0, 1, 0, -1 };
}