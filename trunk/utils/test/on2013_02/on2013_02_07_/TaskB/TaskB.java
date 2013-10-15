package lib.test.on2013_02.on2013_02_07_.TaskB;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.math.BigInteger;

public class TaskB {
    final int PLUS = ~1;
    final int MINUS = ~2;
    final int MUL = ~3;
    final int DIV = ~4;
    final int POW = ~5;
    final int VAR = ~6;
    final int NEG = ~7;

    final int N = 1111111;
    long[] type = new long[N];
    int[] lef = new int[N], righ = new int[N];
    char[] s;
    int len, fr;
    int cur;

    int pE() {
        return pP();
    }

    int pP() {
        int d = pF();
        while (cur < len && (s[cur] == '+' || s[cur] == '-')) {
            int z = fr++;
            type[z] = s[cur] == '+' ? PLUS : MINUS;
            ++cur;
            lef[z] = d;
            righ[z] = pF();
            d = z;
        }
        return d;
    }

    int pF() {
        int d = pC();
        while (cur < len && s[cur] == '*') {
            int z = fr++;
            type[z] = MUL;
            ++cur;
            lef[z] = d;
            righ[z] = pC();
            d = z;
         }
        return d;
    }

    int pC() {
        int d = pZ();
        if (cur < len && s[cur] == '^') {
            int z = fr++;
            type[z] = POW;
            lef[z] = d;
            ++cur;
            righ[z] = pZ();
            d = z;
        }
        return d;
    }

    int pZ() {
        boolean neg = false;
        while (cur < len && (s[cur] == '-' || s[cur] == '+')) {
            if (s[cur] == '-') {
                neg = !neg;
            }
            ++cur;
        }
        int d = pV();
        if (neg) {
            int z = fr++;
            lef[z] = d;
            d = z;
            type[z] = NEG;
        }
        return d;
    }


    int pV() {
        if (s[cur] == '(') {
            ++cur;
            int d = pE();
            ++cur;
            return d;
        }
        if (s[cur] >= 'a' && s[cur] <= 'z') {
            int z = fr++;
            type[z] = VAR;
            ++cur;
            return z;
        }
        if (cur >= len || s[cur] < '0' || s[cur] > '9') throw new AssertionError();
        long num = 0;
        while (cur < len && s[cur] >= '0' && s[cur] <= '9') {
            num = num * 10 + s[cur] - '0';
            ++cur;
        }
        int d = fr++;
        type[d] = num;
        if (cur < len && s[cur] >= 'a' && s[cur] <= 'z') {
            int z = fr++;
            type[z] = MUL;
            lef[z] = d;
            int z2 = fr++;
            type[z2] = VAR;
            righ[z] = z2;
            ++cur;
            d = z;
        }
        return d;
    }

    BigInteger calc(int v, BigInteger val) {
        if (type[v] == PLUS) return calc(lef[v], val).add(calc(righ[v], val));
        if (type[v] == MINUS) return calc(lef[v], val).subtract(calc(righ[v], val));
        if (type[v] == MUL) return calc(lef[v], val).multiply(calc(righ[v], val));
        if (type[v] == POW) return calc(lef[v], val).pow(calc(righ[v], val).intValue());
        if (type[v] == NEG) return calc(lef[v], val).negate();
        if (type[v] == VAR) return val;
        return BigInteger.valueOf(type[v]);
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        StringBuilder sb = new StringBuilder();
        while (true) {
            int c = in.read();
            if (c < 0) {
                break;
            }
            if (Character.isDigit(c) || Character.isLetter(c) || "()+-*^=".indexOf(c) >= 0) {
                sb.appendCodePoint(c);
            }
        }
        String ss = sb.toString();
        int pos = 0;
        while (ss.charAt(pos) != '=') ++pos;
        char[] s1 = ss.substring(0, pos).toCharArray();
        char[] s2 = ss.substring(pos + 1).toCharArray();
        fr = 0;
        cur = 0;
        s = s1;
        len = s.length;
        int r1 = pE();
        if (cur != len) throw new AssertionError();
        cur = 0;
        s = s2;
        len = s.length;
        int r2 = pE();
        if (cur != len) throw new AssertionError();
        int ok = 1;
        int ans = 0;
        for (int i = -10000; i < 10000; i++) {
            if (calc(r1, BigInteger.valueOf(i)).equals(calc(r2, BigInteger.valueOf(i)))) {
                ++ans;
            } else {
                ok = 0;
            }
        }
        if (ok == 1) {
            out.println("inf");
        } else {
            out.println(ans);
        }
    }
}
