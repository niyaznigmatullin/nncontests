import java.io.*;
import java.util.*;

public class B {

	static void solve() throws IOException {
		int t = nextInt();
		for (int i = 0; i < t; i++) {
			out.println("Test " + (i + 1));
			solveCase();
		}
	}

	static void solveCase() throws IOException {
		int a = Integer.parseInt(next(), 2);
		int b = Integer.parseInt(next(), 2);
		long c = (long) a * b;
		String sa = Integer.toBinaryString(a);
		String sb = Integer.toBinaryString(b);
		String sc = Long.toBinaryString(c);
		int mx = Math.max(sa.length() + sb.length() - 1, sc.length());
		for (int i = sa.length(); i < mx; i++)
			out.print(' ');
		out.println(sa);
		for (int i = sb.length(); i < mx; i++)
			out.print(' ');
		out.println(sb);
		for (int i = Math.max(sa.length(), sb.length()); i < mx; i++)
			out.print(' ');
		for (int i = 0; i < Math.max(sa.length(), sb.length()); i++)
			out.print('-');
		out.println();
		for (int i = 0; i < sb.length(); i++) {
			for (int j = sa.length() + i; j < mx; j++)
				out.print(' ');
			if (sb.charAt(sb.length() - i - 1) == '1')
				out.print(sa);
			else {
				for (int j = 0; j < sa.length(); j++)
					out.print('0');
			}
			out.println();
		}
		for (int i = 0; i < mx; i++)
			out.print('-');
		out.println();
		for (int i = sc.length(); i < mx; i++)
			out.print(' ');
		out.println(sc);
		out.println();
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		InputStream input = System.in;
		PrintStream output = System.out;
		File file = new File("b.in");
		if (file.exists() && file.canRead()) {
			input = new FileInputStream(file);
			output = new PrintStream("b.out");
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
