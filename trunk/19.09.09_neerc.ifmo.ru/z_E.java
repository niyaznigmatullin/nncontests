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
    private static double EPS = 1e-12;
    String INFILE = "race.in", OUTFILE = "race.out";
    ArrayList<Integer>[] G;
    class Point {
    	public Point(int xx, int yy) {
    		x = xx;
    		y = yy;
		}    	
		int x, y;
		public int dist(Point p) {
			return (p.x - x) * (p.x - x) + (p.y - y) * (p.y - y);			
		}
		public long vect_mul(Point p1, Point p2) {
			return (p1.x - x) * (p2.y - p1.y) - (p2.x - p1.x) *(p1.y - y);
		}
		public int scalar_mul(Point p1, Point p2) {
//			long t = (p1.x - x) * (p2.x - p1.x) + (p1.y - y) * (p2.y - p1.y);
//			t = t * t;
//			return (int)(t / dist(p1) / p1.dist(p2));
			double t = (p1.x - x) * (p2.x - p1.x) + (p1.y - y) * (p2.y - p1.y);
//			System.err.println(t / Math.sqrt(dist(p1)) / Math.sqrt(p1.dist(p2)));
			if (t / Math.sqrt(dist(p1)) / Math.sqrt(p1.dist(p2)) + 1 < EPS) {
				return -1;
			} else return 0;
		}
    }
    public class comp implements Comparator<Integer> {
    	public int compare(Integer i1, Integer i2) {
    		return dp[i2] - dp[i1];
    	}
    }
//    @SuppressWarnings("unchecked")
    private int to_hash(int x, int y) {
    	return (x - 1) * 2000 + y - 1;
    }
    private int[] from_hash(int x) {
    	int[] ret = new int[2];
    	ret[0] = x / 2000 + 1;
    	ret[1] = x % 2000 + 1;
    	return ret;
    }
    int[] dp;
	private void solve() {    	  
    	int n = nextInt(), m = nextInt();
    	Point[] p = new Point[n + 1];
    	for (int i = 1; i <= n; i++) p[i] = new Point(nextInt(), nextInt());
    	G = new ArrayList[n + 1];
    	for (int i = 1; i <= n; i++) G[i] = new ArrayList<Integer>();
    	for (int i = 0; i < m; i++) {
    		int x = nextInt(), y = nextInt();
    		G[x].add(y);
    		G[y].add(x);
    	}
    	dp = new int[4000000];    	
    	Arrays.fill(dp, Integer.MAX_VALUE);
    	PriorityQueue<Integer> que = new PriorityQueue<Integer>(1, new comp());
    	int[] pp = new int[4000000];
    	for (Integer e: G[1]) {
    		int y = to_hash(1, e);
    		dp[y] = p[1].dist(p[e]);
    		que.add(y);
    		pp[y] = -1;
    	}    	
    	int ans = 0;
    	int ansS = -1;
    	while (!que.isEmpty()) {
    		int hash = que.poll();
    		int[] e = from_hash(hash);
//    		System.err.println(dp[hash]); 
    		int xx = e[0], yy = e[1];
    		if (yy == 1) {
    			if (ans < dp[hash]) {
    				ans = dp[hash];
    				ansS = hash;
    			}
    		}
    		for (Integer w : G[yy]){
    			int r = to_hash(yy, w);
    			if (dp[r] != Integer.MAX_VALUE) continue;
    			if (p[xx].vect_mul(p[yy], p[w]) < 0 || p[xx].scalar_mul(p[yy], p[w]) == -1) continue;    			
    			dp[r] = Math.min(dp[hash], p[yy].dist(p[w]));
    			que.add(r);
    			pp[r] = hash;
    		}
    	}    	
    	if (ans == 0) {
    		System.err.println("###");
    		System.exit(11);
    	} else {
    		ArrayList<Integer> ret = new ArrayList<Integer>();
    		for (int state = ansS; state != -1; state = pp[state]) {
    			ret.add(from_hash(state)[0]);
    		}
//    		System.err.println(ans);
    		StringBuilder sb = new StringBuilder();
    		out.println(ret.size() + 1);
    		for (int i = ret.size() - 1; i >= 0; i--) {
    			sb.append(ret.get(i) + " ");    			
    		}
    		sb.append("1");
    		out.println(sb);
    	}
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