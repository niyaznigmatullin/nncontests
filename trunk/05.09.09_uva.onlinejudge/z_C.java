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
        ret += ".";
        String ss = Long.toString((long)Math.floor(x));
        for (int i = 0; i < kol - ss.length(); i++) ret += "0";
        ret += ss;
        return ret;
    }
    private void solve() {
        while (true) {
            int n = nextInt(), m = nextInt() - 1;
            if (n == 0 && m == -1) break;
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = (int)Math.round(nextDouble() * 100);
            }
            if (a[m] > 5000) {
                out.println("100.00");
                continue;
            }
            boolean[] dp = new boolean[10001];
            Arrays.fill(dp, false);
            dp[0] = true;
            for (int i = 0; i < n; i++) if (i != m) {
                for (int j = 10000; j >= 0; j--) if (j - a[i] >= 0) {
                    dp[j] |= dp[j - a[i]];
                }
            }
            int toget = 5001 - a[m];
            while (!dp[toget]) toget++;
            double ans = 100. * a[m] / (toget + a[m]);
            long anss = Math.round(ans * 100);
            String mod = anss % 100 + "";
            if (mod.length() < 2) mod = "0" + mod;
            out.println(anss / 100 + "." + mod);
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