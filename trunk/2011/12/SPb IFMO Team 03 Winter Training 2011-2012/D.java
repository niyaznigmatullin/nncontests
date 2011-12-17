import static java.util.Arrays.fill;

import java.io.*;
import java.util.*;

public class D {

	static void solve() throws IOException {
		int t = nextInt();
		for (int i = 0; i < t; i++) {
			List<String> program = new ArrayList<String>();
			while (true) {
				String s = br.readLine().trim();
				if (s.equals("end")) {
					break;
				}
				program.add(s);
			}
			out.println("PROGRAM #" + (i + 1) + ":");
			out.println(solve(program));
		}
	}

	static String solve(List<String> program) {
		StringBuilder sb = new StringBuilder();
		for (String e : program) {
			int p = e.indexOf('%');
			if (p >= 0) {
				e = e.substring(0, p);
			}
			for (char c : e.toCharArray()) {
				if ("<>+-.[]".indexOf(c) < 0) {
					continue;
				}
				sb.append(c);
			}
		}
		String s = sb.toString();
		int balance = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '[') {
				balance++;
			} else if (s.charAt(i) == ']') {
				balance--;
			}
			if (balance < 0) {
				return "COMPILE ERROR";
			}
		}
		if (balance != 0) {
			return "COMPILE ERROR";
		}
		int[] pair = new int[s.length()];
		fill(pair, -1);
		Stack<Integer> has = new Stack<Integer>();
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '[') {
				has.add(i);
			} else if (s.charAt(i) == ']') {
				pair[i] = has.pop();
			}
		}
		for (int i = 0; i < s.length(); i++) {
			if (pair[i] >= 0) {
				pair[pair[i]] = i;
			}
		}
		int pointer = 0;
		int[] bytes = new int[POINTERS];
		sb.setLength(0);
		for (int pos = 0; pos < s.length();) {
			char c = s.charAt(pos);
			if (c == '[') {
				if (bytes[pointer] == 0) {
					pos = pair[pos] + 1;
				} else {
					pos++;
				}
			} else if (c == ']') {
				if (bytes[pointer] != 0) {
					pos = pair[pos];
				} else {
					pos++;
				}
			} else if (c == '+') {
				bytes[pointer] = (bytes[pointer] + 1) & 255;
				pos++;
			} else if (c == '-') {
				bytes[pointer] = (bytes[pointer] - 1) & 255;
				pos++;
			} else if (c == '>') {
				pointer = (pointer + 1) & (POINTERS - 1);
				pos++;
			} else if (c == '<') {
				pointer = (pointer - 1) & (POINTERS - 1);
				pos++;
			} else if (c == '.') {
				sb.appendCodePoint(bytes[pointer]);
				pos++;
			} else {
				throw new AssertionError();
			}
		}
		return sb.toString();
	}

	final static int POINTERS = (1 << 15);

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new FileReader("brainfck.in"));
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