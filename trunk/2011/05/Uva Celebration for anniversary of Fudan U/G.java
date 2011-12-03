import java.io.*;
import java.math.*;
import java.util.*;

class Main {

	static class Page implements Comparable<Page> {
		String name;
		int relevance;

		public Page(String name, int relevance) {
			this.name = name;
			this.relevance = relevance;
		}

		@Override
		public int compareTo(Page o) {
			return o.relevance - relevance;
		}

	}

	void solve() {
		int t = nextInt();
		for (int i = 0; i < t; i++) {
			Page[] pages = new Page[10];
			for (int j = 0; j < 10; j++) {
				pages[j] = new Page(sc.next(), nextInt());
			}
			String[] output = solve(pages);
			out.println("Case #" + (i + 1) + ":");
			for (String e : output) {
				out.println(e);
			}
		}
	}

	static String[] solve(Page[] pages) {
		Arrays.sort(pages);
		int i = 0;
		while (i < pages.length && pages[i].relevance == pages[0].relevance) {
			i++;
		}
		String[] ret = new String[i];
		for (int j = 0; j < i; j++) {
			ret[j] = pages[j].name;
		}
		return ret;
	}

	InputReader sc;
	PrintWriter out;

	public void run() {
		try {
			File inputFile = new File("input.txt");
			if (inputFile.canRead()) {
				System.setIn(new FileInputStream(inputFile));
				System.setOut(new PrintStream("output.txt"));
			}
			sc = new StreamInputReader(System.in);
			out = new PrintWriter(System.out);
			solve();
			sc.close();
			out.close();
		} catch (Throwable e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	double nextDouble() {
		return Double.parseDouble(sc.next());
	}

	int nextInt() {
		return sc.nextInt();
	}

	static abstract class InputReader {
		private boolean finished = false;

		public abstract int read();

		public int nextInt() {
			int c = read();
			while (c <= 32 && c >= 0) {
				c = read();
			}
			if (c == -1) {
				finished = true;
				return Integer.MIN_VALUE;
			}
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = read();
			}
			if (c < '0' || c > '9') {
				throw new NumberFormatException("digit expected " + (char) c
						+ " found");
			}
			int ret = 0;
			while (c >= '0' && c <= '9') {
				ret = ret * 10 + c - '0';
				c = read();
			}
			if (c > 32) {
				throw new NumberFormatException("space character expected "
						+ (char) c + " found");
			}
			return ret * sgn;
		}

		public String next() {
			int c = read();
			while (c <= 32 && c >= 0) {
				c = read();
			}
			if (c == -1) {
				finished = true;
				return null;
			}
			StringBuilder res = new StringBuilder();
			while (c > 32) {
				res.appendCodePoint(c);
				c = read();
			}
			return res.toString();
		}

		public boolean isFinished() {
			return finished;
		}

		public void setFinished(boolean finished) {
			this.finished = finished;
		}

		public abstract void close();
	}

	static class StreamInputReader extends InputReader {
		private InputStream stream;
		private byte[] buf;
		private int current, numOfChars;

		public StreamInputReader(InputStream stream) {
			this(stream, 1024);
		}

		public StreamInputReader(InputStream stream, int bufSize) {
			this.stream = stream;
			buf = new byte[bufSize];
		}

		public int read() {
			if (numOfChars == -1) {
				return -1;
			}
			if (current >= numOfChars) {
				current = 0;
				try {
					numOfChars = stream.read(buf);
				} catch (IOException e) {
					return -1;
				}
				if (numOfChars <= 0) {
					return -1;
				}
			}
			return buf[current++];
		}

		@Override
		public void close() {
			try {
				stream.close();
			} catch (IOException ignored) {

			}
		}
	}

	public static void main(String[] args) {
		new Main().run();
	}
}

public class G {
	public static void main(String[] args) {
		Main.main(args);
	}
}