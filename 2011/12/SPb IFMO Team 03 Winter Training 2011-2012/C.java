import static java.util.Arrays.fill;

import java.io.*;
import java.util.*;

public class C {

	static void solve() throws IOException {
		int tc = nextInt();
		while (tc-- > 0) {
			nextToken();
			int girls = nextInt();
			int boys = nextInt();
			out.println(solve(girls, boys));
			// solve(girls, boys);
		}
	}

	// static void solve(int girls, int boys) {
	// if (boys == 0) {
	// for (int i = 0; i < girls; i++) {
	// out.print('S');
	// }
	// }
	// for (int boy = 0; boy < boys; boy++) {
	// if (boy > 0) {
	// for (int i = 0; i < girls; i++) {
	// out.print('s');
	// }
	// }
	// out.print('s');
	// for (int i = 0; i < girls; i++) {
	// out.print("Hs");
	// }
	// }
	// out.println();
	// }

	static String solve(int girls, int boys) {
		C.girls = girls;
		C.boys = boys;
		moves = new char[girls * boys + girls + boys];
		whoIsHacoc = new int[girls + boys + 1];
		fill(whoIsHacoc, 0, girls, -1);
		fill(whoIsHacoc, girls + 1, girls + boys + 1, 1);
		try {
			go(0, girls);
		} catch (FuckYeaException fuckyea) {
			return fuckyea.answer;
		}
		throw new AssertionError();
	}

	static char[] moves;
	static int[] whoIsHacoc;
	static int boys, girls;

	static class FuckYeaException extends Exception {
		final String answer;

		private FuckYeaException(String answer) {
			this.answer = answer;
		}
	}

	static boolean isOk(int free) {
		if (free != boys) {
			return false;
		}
		for (int i = 0; i < free; i++) {
			if (whoIsHacoc[i] != 1) {
				return false;
			}
		}
		return true;
	}

	static void go(int move, int free) throws FuckYeaException {
		if (isOk(free)) {
			throw new FuckYeaException(new String(moves, 0, move));
		}
		if (move >= moves.length) {
			return;
		}
		if (whoIsHacoc.length >= free + 2 && whoIsHacoc[free + 1] == 1) {
			moves[move] = 's';
			whoIsHacoc[free] = 1;
			go(move + 1, free + 1);
			whoIsHacoc[free + 1] = 1;
		}
		if (whoIsHacoc.length >= free + 3 && whoIsHacoc[free + 2] == 1) {
			moves[move] = 'h';
			whoIsHacoc[free] = 1;
			go(move + 1, free + 2);
			whoIsHacoc[free + 2] = 1;
		}
		if (free >= 1 && whoIsHacoc[free - 1] == -1) {
			moves[move] = 'S';
			whoIsHacoc[free] = -1;
			go(move + 1, free - 1);
			whoIsHacoc[free - 1] = -1;
		}
		if (free >= 2 && whoIsHacoc[free - 2] == -1) {
			moves[move] = 'H';
			whoIsHacoc[free] = -1;
			go(move + 1, free - 2);
			whoIsHacoc[free - 2] = -1;
		}
		return;
	}

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new FileReader("kindergarten.in"));
		out = new PrintWriter(System.out);
		solve();
		out.close();
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	static String nextToken() throws IOException {
		while (st == null || !st.hasMoreTokens()) {
			String line = br.readLine();
			if (line == null) {
				return null;
			}
			st = new StringTokenizer(line);
		}
		return st.nextToken();
	}

	static int nextInt() throws IOException {
		return Integer.parseInt(nextToken());
	}

	static long nextLong() throws IOException {
		return Long.parseLong(nextToken());
	}

	static double nextDouble() throws IOException {
		return Double.parseDouble(nextToken());
	}
}