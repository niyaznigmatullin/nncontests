package lib.test.on2013_03.on2013_03_26_Game_Theory_Lab_2.HackenTree;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.Rational;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class HackenTree {

    static class Edge {
        int from;
        int to;
        int color;

        Edge(int from, int to, int color) {
            this.from = from;
            this.to = to;
            this.color = color;
        }
    }

    static List<Edge>[] edges;

    static Rational dfs(int v, int p) {
        Rational answer = new Rational(BigInteger.ZERO);
        for (Edge e : edges[v]) {
            if (e.to == p) continue;
            Rational got = dfs(e.to, v);
            if (got.signum() == 0 || got.signum() == e.color) {
                answer = answer.add(got.add(new Rational(BigInteger.valueOf(e.color))));
            } else {
                BigInteger a = got.getNum().abs();
                BigInteger b = got.getDen();
                BigInteger k = a.divide(b).add(BigInteger.ONE);
                BigInteger ap = a.multiply(k).add(b.subtract(a).multiply(k.add(BigInteger.ONE)));
                if (ap.signum() == got.signum()) ap = ap.negate();
                BigInteger bp = b.shiftLeft(k.intValue());
                answer = answer.add(new Rational(ap, bp));
            }
        }
        return answer;
    }


    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        edges = new List[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            int color = 1 - in.nextInt() * 2;
            edges[from].add(new Edge(from, to, color));
            edges[to].add(new Edge(to, from, color));
        }
        Rational ans = dfs(0, -1);
        out.println(ans.getNum() + " " + ans.getDen());
    }
}
