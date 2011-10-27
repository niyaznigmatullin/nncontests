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
    String INFILE = "ministry.in", OUTFILE = "ministry.out";
    int cl = 0, id = 0, maxH = 0;
    HashMap<Long, Integer> w;
    String s;
    int[] ans;
    private int[] rec(int h) {
    	maxH = Math.max(maxH, h);
    	int[] y = new int[3];
    	int r = 0, height = -1;
    	while (true) {
    		id++;
    		if (s.charAt(id) == '(') {
    			int[] e = rec(h + 1);
    			height = Math.max(height, e[1]);
    			y[r++] = e[0];
    		} else {
    			break;
    		}
    	}
    	++height;
    	Arrays.sort(y);
    	long state = y[0] * 1000000000000L + y[1] * 1000000 + y[2];
    	if (!w.containsKey(state)) {
    		w.put(state, ++cl);
    		ans[height]++;
    	}    	
    	return new int[]{w.get(state), height};
    }
    private void solve() {
    	s = nextLine();
    	w = new HashMap<Long, Integer>();
    	ans = new int[1000000];
    	rec(0);
    	for (int i = 0; i <= maxH; i++) out.println(ans[i]);
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