import java.util.*;
import java.math.*;
import java.io.*;

public class Main implements Runnable{
    public static void main(String[] args) {
        new Thread(new Main()).start();
    }
    StringTokenizer st;
    PrintWriter out;
    BufferedReader br;
    boolean eof = false, in_out = true, std = false;
    private String nextToken() {
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
    private String nextLine() {
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
    private String nextString() {
        return nextToken();
    }
    private int nextInt() {
        return Integer.parseInt(nextToken());
    }
    private long nextLong() {
        return Long.parseLong(nextToken());
    }
    private double nextDouble() {
        return Double.parseDouble(nextToken());
    }
    private BigInteger nextBigInteger() {
        return new BigInteger(nextToken());
    }    
    private static double EPS = 1e-9;
    String INFILE = "team.in", OUTFILE = "team.out";
    int maxN = (1 << 20) - 1;
    private void update(int x, int y) {
    	x += maxN;
    	tr[x] = y;
    	x >>= 1;
    	for (;x > 0; x >>= 1) tr[x] = Math.min(tr[x << 1], tr[x << 1 | 1]);
    }
    private int getmin(int l, int r) {
    	l += maxN;
    	r += maxN;
    	int ret = Integer.MAX_VALUE;
    	while (l <= r) {
    		if ((l & 1) == 1) {
    			ret = Math.min(ret, tr[l++]);
    		}
    		if ((r & 1) == 0) {
    			ret = Math.min(ret, tr[r--]);
    		}
    		l >>= 1;
    		r >>= 1;
    	}
    	return ret;
    }
    int[] tr;
    private void solve() {    	  
    	int n = nextInt();
    	int[] a = new int[n + 1], b = new int[n + 1], c = new int[n + 1];
    	tr = new int[2 * maxN + 4];
    	Arrays.fill(tr, Integer.MAX_VALUE);
    	for (int i = 0; i < n; i++) {
    		a[i] = nextInt();
    	}
    	for (int i = 0; i < n; i++) {
    		int t = nextInt();
    		b[t] = i + 1;
    	}
    	for (int i = 0; i < n; i++) {
    		int t = nextInt();
    		c[t] = i + 1;
    	}
    	int ans = n;
    	for (int i = 0; i < n; i++) {
    		int y = getmin(1, c[a[i]] - 1);
    		if (y < b[a[i]]) {
    			ans--;
//    			System.err.println(i);
    		}
    		update(c[a[i]], b[a[i]]);
    	}
    	out.println(ans);
    }    
	public void run() {
        try {
            br = std ? new BufferedReader(new InputStreamReader(System.in)) : new BufferedReader(new FileReader(new File((in_out) ?  INFILE  : "input.txt")));
            out = std ? new PrintWriter(new OutputStreamWriter(System.out)) : new PrintWriter(new File((in_out) ? OUTFILE : "output.txt"));         
        } catch (Exception e) {
            System.exit(111);
        }
        solve();
        out.close();
    }
}