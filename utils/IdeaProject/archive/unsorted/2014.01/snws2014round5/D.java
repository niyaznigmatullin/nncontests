import java.io.*;
import java.util.*;
import java.math.*;

public class D implements Runnable {

	public static void main(String[] args) {
		new Thread(new D()).start();
	}

	BufferedReader br;
	StringTokenizer st;
	PrintWriter out;
	boolean eof = false;
	Random rand = new Random(25566);

	@Override
	public void run() {
		Locale.setDefault(Locale.US);
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			out = new PrintWriter(System.out);
			solve();
			out.close();
		} catch (Throwable e) {
			e.printStackTrace();
			System.exit(566);
		}
	}

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

	int nextInt() {
		return Integer.parseInt(nextToken());
	}

	long nextLong() {
		return Long.parseLong(nextToken());
	}

	double nextDouble() {
		return Double.parseDouble(nextToken());
	}

	int bsearch(long[] v, long x) {
		int l = 0;
		int r = v.length;
		while (r - l > 1) {
			int m = (r + l) / 2;
			if (v[m] < x) {
				l = m;
			} else {
				r = m;
			}
		}
		if (v[l] >= x)
			return l - 1;
		return l;
	}

	private void solve() throws IOException {
		int n = nextInt();
    P = nextInt();  
		long[] a = new long[n];
		for (int i = 0; i < n; i++) {
			a[i] = nextInt();
		}
		int[] l = new int[n];
		long[] b = new long[n + 1];
		Arrays.fill(b, Long.MAX_VALUE);
		b[0] = Long.MIN_VALUE;
		int max = 1;
		for (int i = 0; i < n; i++) {
			long x = a[i];
			int k = bsearch(b, x);
			b[k + 1] = Math.min(b[k + 1], x);
			l[i] = k;
			max = Math.max(max, k + 1);
		}

		int[] cnt = new int[max];
		for (int i : l) {
			++cnt[i];
		}

		long[][] tab = new long[max][];
		for (int i = 0; i < max; i++) {
			tab[i] = new long[cnt[i]];
		}
		Arrays.fill(cnt, 0);
		for (int i = 0; i < n; i++) {
			tab[l[i]][cnt[l[i]]++] = a[i];
		}
		for (int i = 0; i < max; i++) {
			Arrays.sort(tab[i]);
		}

		long[][] ft = new long[max][];
		for (int i = 0; i < max; i++) {
			ft[i] = new long[cnt[i]];
		}

		for (int i = 0; i < n; i++) {
			long x = a[i];
			int len = l[i];
			int pos = bsearch(tab[len], x) + 1;
			if (len == 0) {
				add(ft[len], pos, 1);
			} else {
				int j = bsearch(tab[len - 1], x);
				long sum = sum(ft[len - 1], j);
				add(ft[len], pos, sum);
			}
		}
		out.println(sum(ft[max - 1], ft[max - 1].length - 1));
	}

	long P = 1000000007L;

	long sum(long[] v, int x) {
		long ans = 0;
		while (x >= 0) {
			ans = (ans + v[x]) % P;
			x = ((x + 1) & x) - 1;
		}
		return ans;
	}

	void add(long[] v, int x, long val) {
		while (x < v.length) {
			v[x] = (v[x] + val) % P;
			x = (x + 1) | x;
		}
	}

}
