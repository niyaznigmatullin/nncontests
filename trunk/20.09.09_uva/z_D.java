import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.math.*;
import java.io.*;

public class Main implements Runnable{
    public static void main(String[] args) {
        new Thread(new Main()).start();
    }
    StringTokenizer st;
    PrintWriter out;
    BufferedReader br;
    boolean eof = false, in_out = false, std = true;
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
    String INFILE = "bar.in", OUTFILE = "bar.out";
    ArrayList<Integer>[] G;
    boolean[] was;
    private void dfs(int x) {
    	was[x] = true;
    	for (Integer e: G[x]) if (!was[e]) {
    		dfs(e);
    	}
    }
    private void solve() {    	
    	while (true) {
    		int B = nextInt(), N = nextInt();
    		if (B == 0 && N == 0) break;
    		int[] R = new int[B + 1], a = new int[N], b = new int[N], c = new int[N];;
    		for (int i = 1; i <= B; i++) R[i] = nextInt();
    		G = new ArrayList[B + 1];
    		for (int i = 1; i <= B; i++) G[i] = new ArrayList<Integer>();
    		for (int i = 0; i < N; i++) {
    			a[i] = nextInt();
    			b[i] = nextInt();
    			c[i] = nextInt();
    			G[a[i]].add(b[i]);
    		}    		
    		was = new boolean[B + 1];
    		for (int i = 1; i <= B; i++) if (!was[i] && R[i] > 0) dfs(i);
/*    		int[] R2 = new int[B + 1];
    		boolean[] dp = new boolean[1 << N];
    		dp[0] = true;
    		for (int i = 0; i < (1 << N); i++) if (dp[i]) {
    			System.arraycopy(R, 0, R2, 0, B + 1);
    			for (int j = 0; j < N; j++) if (((i >> j) & 1) != 0){
    				R2[a[j]] = R2[a[j]] - c[j];
    				R2[b[j]] = R2[b[j]] + c[j];    				
    			}
    			for (int k = 0; k < N; k++) if (((i >> k) & 1) == 0) {
    				if (R2[a[k]] >= c[k]) {
    					dp[i | (1 << k)] = true;
    				}
    			}
    		}
    		if (dp[(1 << N) - 1]) out.println("S"); else out.println("N");*/    		
    		for (int i = 0; i < N; i++) {
    			R[a[i]] -= c[i];
    			R[b[i]] += c[i];    			
    		}    		
    		boolean ans = true;    		
    		for (int i = 1; i <= B; i++) {
    			if (R[i] < 0) {
    				ans = false;    				
    				break;
    			}
    		}
    		out.println((ans ? "S" : "N"));
    	}
    }
    
    public void run() {
        try {
            br = std ? new BufferedReader(new InputStreamReader(System.in)) : new BufferedReader(new FileReader(new File((in_out) ?  INFILE  : "input.txt")));
            out = std ? new PrintWriter(new OutputStreamWriter(System.out)) : new PrintWriter(new File((in_out) ? OUTFILE : "output.txt"));
            //br = new BufferedReader(new FileReader(new File("input.txt")));
            //out = new PrintWriter(new File("output.txt"));
        } catch (Exception e) {
            System.exit(111);
        }
        solve();
        out.close();
    }
}
