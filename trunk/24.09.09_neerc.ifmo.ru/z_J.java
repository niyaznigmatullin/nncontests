import java.util.*;
import java.math.*;
import java.io.*;

import javax.sound.midi.SysexMessage;
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
    String INFILE = "toponyms.in", OUTFILE = "toponyms.out";
    int[] head, next, link, dp;
    char[] cc;
    String s;
    int cnodes = 0, kol = 1;
    private void add(int node, int x) {
    	dp[node]++;    	
    	if (x == s.length()) return;
    	for (int e = head[node]; e != 0; e = next[e]) {
    		if (cc[e] == s.charAt(x)) {
    			add(link[e], x + 1);
    			return;
    		}
    	}
    	cnodes++;
    	next[cnodes] = head[node];
    	head[node] = cnodes;
    	cc[cnodes] = s.charAt(x);
    	kol++;
    	link[cnodes] = kol;
    	head[kol] = 0;
    	dp[kol] = 0;
    	add(kol, x + 1);
    }
    int maxN = 5000000;
    private void get(int node, int k) {
    	ans = Math.max(ans, (long)k * dp[node]);    	
    	for (int e = head[node]; e != 0; e = next[e]) {
    		get(link[e], k + 1);
    	}
    }
    long ans = 0;
    private void solve() {
    	int n = nextInt();
    	head = new int[maxN];
    	next = new int[maxN];
    	cc = new char[maxN];
    	link = new int[maxN];
    	dp = new int[maxN];
    	for (int i = 0; i < n; i++) {
    		s = nextLine();
    		add(1, 0);
    	}
    	get(1, 0);
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