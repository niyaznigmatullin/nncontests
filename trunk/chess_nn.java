import static java.util.Arrays.fill;

import java.io.*;
import java.util.*;

public class chess_nn {

	static final String COLORS = "WB";

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("chess.in"));
		int n = in.nextInt();
		int m = in.nextInt();
		char[][] c = new char[n][m];
		for (int i = 0; i < n; i++) {
			c[i] = in.next().toCharArray();
		}
		Answer best = null;
		for (int color = 0; color < 2; color++) {
			Answer ans = solve(color, n, m, c);
			if (best == null || ans.count < best.count) {
				best = ans;
			}
		}
		PrintWriter out = new PrintWriter("chess.out");
		out.println(best.count);
		for (String e : best.answers) {
			out.println(e);
		}
		out.close();
	}

	static Answer solve(int color, int n, int m, char[][] c) {
		int[] p1 = new int[n + m - 1];
		int[] p2 = new int[n + m - 1];
		fill(p1, -1);
		fill(p2, -1);
		boolean[] was = new boolean[n + m - 1];
		int ans = 0;
		for (int i = 0; i < n + m - 1; i++) {
			fill(was, false);
			if (dfs(i, c, color, p1, p2, was)) {
				++ans;
			}
		}
		String[] answer = new String[ans];
		fill(was, false);
		int count = 0;
		for (int i = 0; i < n + m - 1; i++) {
			if (p1[i] < 0) {
				for (int j = 0; j < n + m - 1; j++) {
					if (!hasEdge(i, j, c, color)) {
						continue;
					}
					answer[count++] = getDiagonal2(j, n, m, color);
					was[p2[j]] = true;
				}
			}
		}
		for (int i = 0; i < n + m - 1; i++) {
			if (p1[i] >= 0 && !was[i]) {
				answer[count++] = getDiagonal1(i, n, m, color);
			}
		}
		return new Answer(ans, answer);
	}

	static String getDiagonal1(int sum, int n, int m, int color) {
		int x = Math.min(sum, n - 1);
		int y = sum - x;
		return "1 " + (x + 1) + " " + (y + 1) + " "
				+ COLORS.charAt((color ^ x ^ y) & 1);
	}

	static String getDiagonal2(int dif, int n, int m, int color) {
		dif -= m - 1;
		int x = Math.max(dif, 0);
		int y = x - dif;
		return "2 " + (x + 1) + " " + (y + 1) + " "
				+ COLORS.charAt((color ^ x ^ y) & 1);
	}

	static boolean hasEdge(int sum, int dif, char[][] c, int color) {
		dif -= c[0].length - 1;
		if ((sum & 1) != (dif & 1)) {
			return false;
		}
		int x = (sum + dif) / 2;
		int y = (sum - dif) / 2;
		if (x < 0 || x >= c.length || y < 0 || y >= c[x].length) {
			return false;
		}
		return COLORS.indexOf(c[x][y]) != ((x ^ y ^ color) & 1);
	}

	static boolean dfs(int v, char[][] c, int color, int[] p1, int[] p2,
			boolean[] was) {
		if (was[v]) {
			return false;
		}
		was[v] = true;
		for (int i = 0; i < c.length + c[0].length; i++) {
			if (!hasEdge(v, i, c, color)) {
				continue;
			}
			if (p2[i] < 0 || dfs(p2[i], c, color, p1, p2, was)) {
				p2[i] = v;
				p1[v] = i;
				return true;
			}
		}
		return false;
	}

	static class Answer {
		int count;
		String[] answers;

		public Answer(int count, String[] answers) {
			this.count = count;
			this.answers = answers;
		}

	}
}
