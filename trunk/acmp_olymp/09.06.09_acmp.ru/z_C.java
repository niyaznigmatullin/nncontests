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
    String INFILE = "hadron.in", OUTFILE = "hadron.out";
    boolean[] was;
    int n;
    boolean[][] g;
    ArrayList <Integer> f = new ArrayList<Integer>();
    private int shl(int x) {
        return (int)(Math.pow(3, x));
    }
    private boolean has(int mask, int x) {
        while (x > 0) { mask /= 3; x--; }
        return mask % 3 != 0;
    }
    private boolean has2(int mask, int x) {
        while (x > 0) { mask /= 3; x--; }
        return mask % 3 == 2;
    }
    private void rec(int mask) {
        if (was[mask]) return;
        was[mask] = true;
        boolean ok = false;
        for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++) if ((i != j && has(mask, i) && has(mask, j)) || (i == j && has2(mask, i))) if (g[i][j] && g[j][i]) {
            rec(mask - shl(i) - shl(j));
            ok = true;
        } else if (g[i][j]) {
            rec(mask - shl(j));
            ok = true;
        } else if (g[j][i]) {
            rec(mask - shl(i));
            ok = true;
        }
        if (!ok) f.add(mask);
    }
    private void solve() {
        n = nextInt();
        g = new boolean[n][n];
        int[] a = new int[n];
        was = new boolean[(int)Math.pow(3, n)];
        Arrays.fill(was, false);
        was[0] = true;
        for (int i = 0; i < n; i++) a[i] = nextInt();
        int mask = 0;
        for (int i = n - 1; i >= 0; i--) {
            mask = mask * 3 + a[i];
        }
        for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++) {
            int x= nextInt();
            g[i][j] = x == 1;
        }
        rec(mask);
        out.println(f.size());
        for (Integer e : f) {
            for (int i = 0; i < n; i++) {
                out.print((e % 3) + " ");
                e /= 3;
            }
            out.println();
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
