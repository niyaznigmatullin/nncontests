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
    boolean eof = false, in_out = false;
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
    String INFILE = "bear.in", OUTFILE = "bear.out";
    String[] s, tt;
    boolean[] was;
    private void done() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 2 * n; i++) {
            sb.append(tt[i]);
            sb.append("\n");
            if (i == n - 1) sb.append("\n");
        }
        out.print(sb.toString());
        out.close();
        System.exit(0);
    }
    private void go(int x, int k) {
        tt[k - 1] = s[x];
        if (k == 2 * n) {
            done();
            return;
        }
        int yy = k % n;
        if (k >= n) {
            for (int i = 0; i < 2 * n; i++) if (!was[i]) {
                boolean ok = true;
                for (int j = 0; j < yy; j++) {
                    if (s[i].charAt(j) != tt[j + n].charAt(yy)) {
                        ok = false;
                        break;
                    }
                }
                if (!ok) continue;
                was[i] = true;
                go(i, k + 1);
                was[i] = false;
            }
        } else {
            for (int i = 0; i < 2 * n; i++) if (!was[i]) {
                boolean ok = true;
                for (int j = 0; j < yy; j++) {
                    if (s[i].charAt(j) != tt[j].charAt(yy)) {
                        ok = false;
                        break;
                    }
                }
                if (!ok) continue;
                was[i] = true;
                go(i, k + 1);
                was[i] = false;
            }
        }
    }
    int n;
    private void solve() {
        n = nextInt();
        s = new String[2 * n];
        tt = new String[2 * n];
        was = new boolean[2 * n];
        for (int i = 0; i < 2 * n; i++) s[i] = nextLine().trim();
        for (int i = 0; i < 2 * n; i++) {
            was[i] = true;
            go(i, 1);
            was[i] = false;
        }
    }
    public void run() {
        try {
            br = new BufferedReader(new FileReader(new File((in_out) ?  INFILE  : "input.txt")));
            out = new PrintWriter(new File((in_out) ? OUTFILE : "output.txt"));
            //br = new BufferedReader(new FileReader(new File("input.txt")));
            //out = new PrintWriter(new File("output.txt"));
        } catch (Exception e) {
            System.exit(111);
        }
        solve();
        out.close();
    }
}
