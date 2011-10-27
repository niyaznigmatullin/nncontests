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

	void solve() {
		int n = nextInt();
		int m = nextInt();
		TreeMap<Integer, Integer>[] edges = new TreeMap[n];
		for (int i = 0; i < n; i++) {
			edges[i] = new TreeMap<Integer, Integer>();
		}
		for (int i = 0; i < m; i++) {
			int u = nextInt() - 1;
			int v = nextInt() - 1;
			int c = nextInt();
			edges[u].put(c, v);
			edges[v].put(c, u);
		}
		int k = nextInt();
		int cur = 0;
		for (int i = 0; i < k; i++) {
			int x = nextInt();
			if (edges[cur].containsKey(x)) {
				cur = edges[cur].get(x);
			} else {
				out.println("INCORRECT");
				return;
			}
		}
		out.println(cur + 1);
	}
}