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

	int to_num(int[][] a) {
		int ret = 0;
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				ret = ret * 10 + a[i][j];
			}
		}
		return ret;
	}

	int[][] to_state(int n) {
		int[][] a = new int[2][4];
		for (int i = a.length - 1; i >= 0; i--) {
			for (int j = a[i].length - 1; j >= 0; j--) {
				a[i][j] = n % 10;
				n /= 10;
			}
		}
		return a;
	}

	void solve() {
		char[][] c = new char[2][];
		c[0] = nextString().toCharArray();
		c[1] = nextString().toCharArray();
		int cur = 1;
		HashMap<Character, Integer> w = new HashMap<Character, Integer>();
		w.put('#', 0);
		int[][] a = new int[2][4];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 4; j++) {
				if (w.containsKey(c[i][j]))
					a[i][j] = w.get(c[i][j]);
				else {
					w.put(c[i][j], cur++);
					a[i][j] = cur - 1;
				}
			}
		}
		int fin = to_num(a);
		c[0] = nextString().toCharArray();
		c[1] = nextString().toCharArray();
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 4; j++) {
				if (!w.containsKey(c[i][j]))
					throw new AssertionError();
				a[i][j] = w.get(c[i][j]);
			}
		}
		Queue<Integer> que = new LinkedList<Integer>();
		que.add(to_num(a));
		HashMap<Integer, Integer> dict = new HashMap<Integer, Integer>();
		dict.put(to_num(a), 0);
		while (!que.isEmpty()) {
			int x = que.poll();
			int z = dict.get(x);
			int[][] b = to_state(x);
			int i1 = -1, j1 = -1;
			for (int i = 0; i < b.length; i++)
				for (int j = 0; j < b[i].length; j++)
					if (b[i][j] == 0) {
						i1 = i;
						j1 = j;
					}
			if (i1 > 0) {
				{
					int temp = b[i1][j1];
					b[i1][j1] = b[i1 - 1][j1];
					b[i1 - 1][j1] = temp;
				}
				int st = to_num(b);
				if (!dict.containsKey(st)) {
					dict.put(st, z + 1);
					que.add(st);
				}
				{
					int temp = b[i1][j1];
					b[i1][j1] = b[i1 - 1][j1];
					b[i1 - 1][j1] = temp;
				}
			}
			if (j1 > 0) {
				{
					int temp = b[i1][j1];
					b[i1][j1] = b[i1][j1 - 1];
					b[i1][j1 - 1] = temp;
				}
				int st = to_num(b);
				if (!dict.containsKey(st)) {
					dict.put(st, z + 1);
					que.add(st);
				}
				{
					int temp = b[i1][j1];
					b[i1][j1] = b[i1][j1 - 1];
					b[i1][j1 - 1] = temp;
				}
			}
			if (i1 == 0) {
				{
					int temp = b[i1][j1];
					b[i1][j1] = b[i1 + 1][j1];
					b[i1 + 1][j1] = temp;
				}
				int st = to_num(b);
				if (!dict.containsKey(st)) {
					dict.put(st, z + 1);
					que.add(st);
				}
				{
					int temp = b[i1][j1];
					b[i1][j1] = b[i1 + 1][j1];
					b[i1 + 1][j1] = temp;
				}
			}
			if (j1 < 3) {
				{
					int temp = b[i1][j1];
					b[i1][j1] = b[i1][j1 + 1];
					b[i1][j1 + 1] = temp;
				}
				int st = to_num(b);
				if (!dict.containsKey(st)) {
					dict.put(st, z + 1);
					que.add(st);
				}
				{
					int temp = b[i1][j1];
					b[i1][j1] = b[i1][j1 + 1];
					b[i1][j1 + 1] = temp;
				}
			}
		}
		if (dict.containsKey(fin)) {
			out.println(dict.get(fin));
		} else
			out.println(-1);
	}
}