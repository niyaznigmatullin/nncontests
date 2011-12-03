import java.util.*;
import java.math.*;
import java.io.*;

public class C implements Runnable {
	public static void main(String[] args) {
		new Thread(new C()).start();
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
			br = new BufferedReader(new FileReader(new File("dull.in")));
			out = new PrintWriter("dull.out");
			solve();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(111);
		}
		// System.err.println(System.currentTimeMillis() - time);
		out.close();
	}

	void solve() {
		while (solve2())
			;
	}

	class Program {
		int mem;
		String dulls;

		public Program(int mem, String dulls) {
			this.mem = mem;
			this.dulls = dulls;
		}

	}

	Program[] a;
	int[] dull;
	int memory;
	int[] usedDull;
	int[] usedProgram;

	boolean solve2() {
		int n = nextInt();
		if (n == 0)
			return false;
		int p = nextInt();
		int s = nextInt();
		dull = new int[n];
		for (int i = 0; i < n; i++) {
			dull[i] = nextInt();
		}
		usedDull = new int[n];
		usedProgram = new int[p];
		memory = 0;
		a = new Program[p];
		for (int i = 0; i < a.length; i++) {
			a[i] = new Program(nextInt(), nextToken());
		}
		int ans = 0;
		for (int i = 0; i < s; i++) {
			int prog = nextInt();
			if (prog > 0) {
				load(prog - 1);
			} else {
				unload(-prog - 1);
			}
			ans = Math.max(ans, memory);
		}
		out.println(ans);
		return true;
	}

	void load(int p) {
		if (usedProgram[p] > 0) {
			usedProgram[p]++;
			memory += a[p].mem;
			return;
		} else {
			for (int i = 0; i < a[p].dulls.length(); i++) {
				loadDull(a[p].dulls.charAt(i) - 'A');
			}
			memory += a[p].mem;
			usedProgram[p]++;
		}
	}

	void loadDull(int x) {
		if (usedDull[x] > 0) {
			usedDull[x]++;
		} else {
			usedDull[x]++;
			memory += dull[x];
		}
	}

	void unload(int p) {
		if (usedProgram[p] == 0)
			return;
		usedProgram[p]--;
		memory -= a[p].mem;
		if (usedProgram[p] == 0) {
			for (int i = 0; i < a[p].dulls.length(); i++) {
				unloadDull(a[p].dulls.charAt(i) - 'A');
			}
		}
	}

	void unloadDull(int x) {
		if (usedDull[x] == 0)
			throw new AssertionError();
		usedDull[x]--;
		if (usedDull[x] == 0) {
			memory -= dull[x];
		}
	}
}