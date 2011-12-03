import java.io.*;
import java.util.*;
import java.math.*;

public class nenokku_nn implements Runnable {
	public static void main(String[] args) {
		new nenokku_nn().run();
	}

	class FastScanner {
		BufferedReader br;
		StringTokenizer st;
		boolean eof;
		String buf;

		public FastScanner(String fileName) throws FileNotFoundException {
			br = new BufferedReader(new FileReader(fileName));
			nextToken();
		}

		public FastScanner(InputStream stream) {
			br = new BufferedReader(new InputStreamReader(stream));
			nextToken();
		}

		String nextToken() {
			while (st == null || !st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
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

		void close() {
			try {
				br.close();
			} catch (Exception e) {

			}
		}

		boolean isEOF() {
			return eof;
		}
	}

	FastScanner sc;
	PrintWriter out;
	BufferedReader br;

	public void run() {
		Locale.setDefault(Locale.US);
		try {
			// sc = new FastScanner("nenokku.in");
			br = new BufferedReader(new FileReader("nenokku.in"));
			out = new PrintWriter("nenokku.out");
			solve();
			// sc.close();
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

	final int ALPHABET = 26;

	class Node {
		Node[] link;
		Node sufLink;
		int length;

		public Node(int len) {
			length = len;
			link = new Node[ALPHABET];
		}
	}

	void append(int c) {
		Node newNode = new Node(last.length + 1);
		while (last != null && last.link[c] == null) {
			last.link[c] = newNode;
			last = last.sufLink;
		}
		if (last == null) {
			newNode.sufLink = head;
			last = newNode;
			return;
		}
		Node q = last.link[c];
		if (q.length == last.length + 1) {
			newNode.sufLink = q;
			last = newNode;
			return;
		}
		Node copy = new Node(last.length + 1);
		System.arraycopy(q.link, 0, copy.link, 0, ALPHABET);
		copy.sufLink = q.sufLink;
		while (last != null && last.link[c] == q) {
			last.link[c] = copy;
			last = last.sufLink;
		}
		newNode.sufLink = q.sufLink = copy;
		last = newNode;
	}

	Node head;
	Node last;

	void solve() throws IOException {
		last = head = new Node(0);
		while (true) {
			String s = br.readLine();
			if (s == null) {
				break;
			}
			char e = s.charAt(0);
			if (e == 'A') {
				for (int i = 1; i < s.length(); i++) {
					if (Character.isLetter(s.charAt(i))) {
						int ch = Character.toLowerCase(s.charAt(i)) - 'a';
						append(ch);
					}
				}
			} else {
				Node v = head;
				boolean ok = true;
				for (int i = 1; i < s.length(); i++) {
					if (Character.isLetter(s.charAt(i))) {
						int ch = Character.toLowerCase(s.charAt(i)) - 'a';
						if (v.link[ch] == null) {
							ok = false;
							break;
						}
						v = v.link[ch];
					}
				}
				if (ok) {
					out.println("YES");
				} else {
					out.println("NO");
				}
			}
		}
	}
}