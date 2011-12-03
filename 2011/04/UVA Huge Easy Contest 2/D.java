import java.io.*;
import java.util.*;
import java.math.*;

public class D implements Runnable {

	static final Random rand = new Random();

	void stress() {
		int q = 0;
		while (true) {
			if (++q == 100) {
				System.err.println("ASDS");
				q = 0;
			}
			StringBuilder sb = new StringBuilder(getTerm());
			int n = rand.nextInt(100);
			for (int i = 0; i < n; i++) {
				sb.append(' ').append(getBinary()).append(' ')
						.append(getTerm());
			}
			solve(sb.toString());
		}
	}

	static String getTerm() {
		int m = rand.nextInt(10);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < m; i++) {
			sb.append(getUnary()).append(' ');
		}
		sb.append(getNumber());
		return sb.toString();
	}

	static String getUnary() {
		int p = rand.nextInt(3);
		if (p == 0) {
			return "not";
		} else if (p == 1) {
			return "shr";
		} else {
			return "shl";
		}
	}

	static String getNumber() {
		int m = rand.nextInt(10) + 1;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < m; i++) {
			sb.append((char) ('0' + rand.nextInt(2)));
		}
		return sb.toString();
	}

	static String getBinary() {
		int p = rand.nextInt(3);
		if (p == 0) {
			return "and";
		} else if (p == 1) {
			return "or";
		} else {
			return "xor";
		}
	}

	void solve() {
		int t = nextInt();
		for (int i = 0; i < t; i++) {
			String s = sc.nextLine();
			out.println("Case " + (i + 1) + ": " + solve(s));
		}
	}

	static String solve(String str) {
		StringTokenizer st = new StringTokenizer(str);
		ArrayList<String> as = new ArrayList<String>();
		while (st.hasMoreTokens()) {
			as.add(st.nextToken());
		}
		String[] s = as.toArray(new String[as.size()]);
		int n = s.length;
		ArrayList<String> stack = new ArrayList<String>();
		for (int i = 0; i < n; i++) {
			if (s[i].equals("shl") || s[i].equals("shr") || s[i].equals("not")) {
				stack.add(s[i]);
				continue;
			}
			if (s[i].equals("xor") || s[i].equals("or") || s[i].equals("and")) {
				stack.add(s[i]);
				continue;
			}
			String e = s[i];
			while (!stack.isEmpty()) {
				String op = stack.get(stack.size() - 1);
				if (op.equals("shl") || op.equals("shr") || op.equals("not")) {
					e = removeLeadingZeros(e);
					if (op.equals("shl")) {
						e += "0";
					} else if (op.equals("shr")) {
						if (e.equals("1")) {
							e = "0";
						} else if (!e.equals("0")) {							
							e = e.substring(0, e.length() - 1);
						}
					} else {
						e = not(e);
					}
				} else if (op.equals("xor") || op.equals("or")
						|| op.equals("and")) {
					stack.remove(stack.size() - 1);
					String num = stack.get(stack.size() - 1);
					if (num.length() < e.length()) {
						num = addLeadingZeros(num, e.length());
					} else if (num.length() > e.length()) {
						e = addLeadingZeros(e, num.length());
					}
					if (op.equals("xor")) {
						e = xor(num, e);
					} else if (op.equals("or")) {
						e = or(num, e);
					} else {
						e = and(num, e);
					}
					stack.remove(stack.size() - 1);
					break;
				}
				stack.remove(stack.size() - 1);
			}
			stack.add(e);
		}
		return removeLeadingZeros(stack.get(stack.size() - 1));
	}

	static String xor(String a, String b) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < a.length(); i++) {
			sb.append((char) ('0' + ((a.charAt(i) - '0') ^ (b.charAt(i) - '0'))));
		}
		return sb.toString();
	}

	static String or(String a, String b) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < a.length(); i++) {
			sb.append((char) ('0' + ((a.charAt(i) - '0') | (b.charAt(i) - '0'))));
		}
		return sb.toString();
	}

	static String and(String a, String b) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < a.length(); i++) {
			sb.append((char) ('0' + ((a.charAt(i) - '0') & (b.charAt(i) - '0'))));
		}
		return sb.toString();
	}

	static String addLeadingZeros(String e, int length) {
		StringBuilder sb = new StringBuilder();
		for (int i = e.length(); i < length; i++) {
			sb.append('0');
		}
		sb.append(e);
		return sb.toString();
	}

	static String not(String s) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			sb.append(s.charAt(i) == '0' ? '1' : '0');
		}
		return sb.toString();
	}

	static String removeLeadingZeros(String e) {
		int i = 0;
		while (i + 1 < e.length() && e.charAt(i) == '0') {
			i++;
		}
		return e.substring(i);
	}

	FastScanner sc;
	PrintWriter out;

	public void run() {
		try {
			sc = new FastScanner(System.in);
			out = new PrintWriter(System.out);
			solve();
			sc.close();
			out.close();
		} catch (Throwable e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	int nextInt() {
		return sc.nextInt();
	}

	String nextToken() {
		return sc.nextToken();
	}

	long nextLong() {
		return sc.nextLong();
	}

	double nextDouble() {
		return sc.nextDouble();
	}

	BigInteger nextBigInteger() {
		return sc.nextBigInteger();
	}

	class FastScanner extends BufferedReader {
		StringTokenizer st;
		boolean eof;
		String buf;
		String curLine;
		boolean createST;

		public FastScanner(String fileName) throws FileNotFoundException {
			this(fileName, true);
		}

		public FastScanner(String fileName, boolean createST)
				throws FileNotFoundException {
			super(new FileReader(fileName));
			this.createST = createST;
			nextToken();
		}

		public FastScanner(InputStream stream) {
			this(stream, true);
		}

		public FastScanner(InputStream stream, boolean createST) {
			super(new InputStreamReader(stream));
			this.createST = createST;
			nextToken();
		}

		String nextLine() {
			String ret = curLine;
			if (createST) {
				st = null;
			}
			nextToken();
			return ret;
		}

		String nextToken() {
			if (!createST) {
				try {
					curLine = readLine();
				} catch (Exception e) {
					eof = true;
				}
				return null;
			}
			while (st == null || !st.hasMoreTokens()) {
				try {
					curLine = readLine();
					st = new StringTokenizer(curLine);
				} catch (Exception e) {
					eof = true;
					break;
				}
			}
			String ret = buf;
			buf = eof ? "-1" : st.nextToken();
			return ret;
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

		public void close() {
			try {
				buf = null;
				st = null;
				curLine = null;
				super.close();
			} catch (Exception e) {

			}
		}

		boolean isEOF() {
			return eof;
		}
	}

	public static void main(String[] args) {
		new D().run();
	}
}