import java.util.*;
import java.util.Map.Entry;
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
    private String to_precision(double x, int kol) {
        if (kol == 0) {
            return Long.toString((long)Math.floor(x));
        }
        String ret = Long.toString((long)Math.floor(x));
        x -= Math.floor(x);
        for (int i=0; i<kol; i++) {
            x *= 10;
        }
        ret += "." + Long.toString((long)Math.floor(x));
        if ((long)Math.floor(x) == 0) {
            ret += "0";
        }
        return ret;
    }
    private static double EPS = 1e-9;
    String INFILE = "bar.in", OUTFILE = "bar.out";    
    double[][] p;
    int[] a, b;
/*    HashMap<Integer, Double>[] dict;
    private void go(int x) {
    	if (dict[x] != null) return;
    	go(a[x]);
    	go(b[x]);
    	dict[x] = new HashMap<Integer, Double>();
    	for (Iterator it = dict[a[x]].entrySet().iterator();it.hasNext();) {
    		Map.Entry<Integer, Double> en = (Map.Entry<Integer, Double>) it.next();
    		for (Iterator it2 = dict[b[x]].entrySet().iterator(); it2.hasNext();) {
    			Map.Entry<Integer, Double> en2 = (Map.Entry<Integer, Double>)it2.next();
    			double y1, y2;
    			if (dict[x].containsKey(en.getKey())) y1 = dict[x].get(en.getKey()); else y1 = 0;
    			if (dict[x].containsKey(en2.getKey())) y2 = dict[x].get(en2.getKey()); else y2 = 0;
    			dict[x].put(en.getKey(), y1 + en.getValue() * (en2.getValue()) * p[en.getKey()][en2.getKey()]);
    			dict[x].put(en2.getKey(), y2 + en.getValue() * (en2.getValue()) * p[en2.getKey()][en.getKey()]);
    		}
		}
    }*/
    double[][] dict2;
    boolean[] was;
    int n;
    private void go2(int x) {
    	was[x] = true;
    	if (!was[a[x]]) go2(a[x]);
    	if (!was[b[x]]) go2(b[x]);
    	for (int i = 1; i <= n; i++) {
    		for (int j = 1; j <= n; j++) dict2[x][i] += dict2[b[x]][i] * p[i][j] * dict2[a[x]][j] + dict2[a[x]][i] * p[i][j] * dict2[b[x]][j];
    	}
    }
    private void solve() {    	
    	while (true) {
    		n = nextInt();
    		if (n == 0) break;
    		p = new double[n + 1][n + 1];
    		for (int i = 1; i <= n; i++)
    			for (int j = 1; j <= n; j++) p[i][j] = nextDouble();
    		a = new int[2 * n];
    		b = new int[2 * n];
    		for (int i = n + 1; i < 2 * n; i++) {
    			a[i] = nextInt();
    			b[i] = nextInt();
    		}    		
//    		dict = new HashMap[2 * n];
    		dict2 = new double[2 * n][n + 1];
    		was = new boolean[2 * n];
    		double ans = 0;
    		for (int i = 1; i <= n; i++) {
//    			dict[i] = new HashMap<Integer, Double>();
//    			dict[i].put(i, 1.0);
    			dict2[i][i] = 1.0;
    			was[i] = true;
    		}
/*    		for (int i = n + 1; i < 2 * n; i++) if (dict[i] == null) {
    			go(i);    			
    			if (dict[i].containsKey(1)) {
    				ans = dict[i].get(1);
    			} else ans = 0.0;
    		}*/
    		for (int i = n + 1; i < 2 * n; i++) if (!was[i]) {
    			go2(i);
    			ans = dict2[i][1];
    		}
    		out.println(String.format("%.10f", ans));
    	}
    }
    
    public void run() {
    	Locale.setDefault(Locale.US);
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
