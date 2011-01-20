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
    String chars = "";
    boolean eof = false;
    private String nextToken() {
        while (st == null || !st.hasMoreTokens()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (Exception e) {
                eof = true;
                return "";
            }
        }
        return st.nextToken();
    }
    private String nextLine() {
        String ret = "";
        try {
            ret = br.readLine();
        } catch (Exception e) {
            ret = "$";
            eof = true;
        }
        if (ret == null) {
            eof = true;
            return "";
        }
        return ret;
    }
    private String nextString() {
        return nextToken();
    }
    private long nextLong() {
        return Long.parseLong(nextToken());
    }
    private int nextInt() {
        return Integer.parseInt(nextToken());
    }
    private double nextDouble() {
        return Double.parseDouble(nextToken());
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
        ret += ".";
        String ss = Long.toString((long)Math.floor(x));
        for (int i = 0; i < kol - ss.length(); i++) ret += "0";
        ret += ss;
        return ret;
    }
    private void solve() {
        int T = nextInt(), MOD = 10000007;
        for (int www = 1; www <= T; www++) {
            int n = nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = nextInt();
            TreeSet<Integer> w = new TreeSet<Integer>();
            for (int i = 0; i < n; i++)
                for (int j = i + 1; j < n; j++) w.add(a[j] - a[i]);
            int[] dif = new int[w.size()];
            int k = 0;
            for (Integer e : w) {
                dif[k++] = e;
            }
            TreeMap<Integer, Integer> q = new TreeMap<Integer, Integer>();
            for (int i = 0; i < k; i++) q.put(dif[i], i);
            int[][] dp = new int[n][k];
            for (int i = 0; i < n; i++) Arrays.fill(dp[i], 1);
            for (int i = 1; i < n; i++)
                for (int j = i - 1; j >= 0; j--) if (q.containsKey(a[i] - a[j])) {
                    int y = q.get(a[i] - a[j]);
                    dp[i][y] = (dp[i][y] + dp[j][y]) % MOD;
                }
            int ans = 0;
            for (int i = 0; i < n; i++)  {
                for (int j = 0; j < k; j++) ans = (ans + dp[i][j] - 1) % MOD;
                ans = (ans + 1) % MOD;
            }
            out.println("Case " + www + ": " + ans);
        }
    }
    public void run() {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(new OutputStreamWriter(System.out));
        solve();
        try {

        } catch (Exception e) {
            System.exit(111);
        }
        out.flush();
    }
}