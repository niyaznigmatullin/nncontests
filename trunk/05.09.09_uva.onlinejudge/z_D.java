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
    int[] a, b;
    ArrayList<Integer>[] G;
    int[] was;
    int n, m;
    boolean[] has;
    int ans;
    private void go(int x) {
        if (x > n) {
            int ret = 0;
            for (int i = 1; i <= n; i++) ret += (has[i]) ? 1 : 0;
            if (ans < ret) ans = ret;
            return;
        }
        if (was[x] > 0 && !has[x]) {
            go(x + 1);
            return;
        }
        has[x] = true;
        boolean ok = true;
        for (Integer e : G[x]) {
            if (e > 0) {
                if (was[e] > 0 && !has[e]) {
                    ok = false;
                } else has[e] = true;
                was[e]++;
            }  else {
                if (was[-e] > 0 && has[-e]) ok = false; else
                has[-e] = false;
                was[-e]++;
            }            
        }         
        if (ok) {
            was[x]++;
            go(x + 1);
            was[x]--;
        }
        for (Integer e : G[x]) {
            was[Math.abs(e)]--;
        }
        if (was[x] > 0) return;
        has[x] = false;
        was[x]++;
        go(x + 1);
        was[x]--;
    }
    private void solve() {
        while (true) {
            n = nextInt();
            m = nextInt();
            if (n == 0 && m == 0) break;
            a = new int[m];
            b = new int[m];
            G = new ArrayList[n + 1];
            ans = 0;
            for (int i = 0; i <= n; i++) G[i] = new ArrayList<Integer>();
            for (int i = 0; i < m; i++) {
                a[i] = nextInt();
                b[i] = nextInt();
                G[a[i]].add(b[i]);
            }
/*            int ans = 0;
            for (int i = (1 << n) - 1; i >= 0; i--) {
                boolean ok = true;
                for (int j = 0; j < m; j++) {
                    if (((i >> (a[j] - 1)) & 1) == 1 && ((b[j] > 0 && ((i >> (b[j] - 1)) & 1) == 0) || (b[j] < 0 && ((i >> (-b[j] - 1)) & 1) == 1))) {
                        ok = false;
                        break;
                    }
                }
                if (ok) ans = Math.max(ans, Integer.bitCount(i));
            }*/
            has = new boolean[n + 1];
            was = new int[n + 1];
            Arrays.fill(has, false);
            Arrays.fill(was, 0);
            go(1);
            out.println(ans);
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