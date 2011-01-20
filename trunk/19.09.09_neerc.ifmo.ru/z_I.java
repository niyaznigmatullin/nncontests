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
    String INFILE = "two.in", OUTFILE = "two.out";
    private void solve() {    	  
    	int n = nextInt();    	
    	int[] w = new int[n], a = new int[n], d = new int[n + 1];
    	for (int i = 0; i < n; i++) {
    		w[i] = nextInt();
    		a[i] = nextInt();
    		d[i + 1] = d[i] + a[i];
    	}
    	int end = d[n], ans = 0;
    	int[] allw = new int[n + 1];
    	allw[0] = w[0];
    	for (int i = 1; i < n; i++) {
    		allw[i] = allw[i - 1] + w[i];
    	}
    	for (int i = 0; i < n; i++) {
    		ans += w[i] * (end - d[i]);
    	}
    	int cur = ans;
    	for (int i = 0; i < n; i++) {
    		int cur2 = cur;
    		cur2 -= w[i] * (end - d[i]);
    		if (i != 0) {
    			cur2 += a[i - 1] * allw[i - 1];  
    		}
    		cur = cur2;    		
    		for (int j = i + 1; j < n; j++) {    			
    			cur2 -= w[j] * (end - d[j]);
    			cur2 += (allw[j - 1] - allw[i]) * a[j - 1];
    			if (cur2 < ans) {
    				ans = cur2;
    			}
//    			System.err.println(cur2);
    		}
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