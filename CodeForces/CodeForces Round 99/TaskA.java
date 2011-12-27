package mypackage;

import com.sun.tools.corba.se.idl.toJavaPortable.StructGen;
import niyazio.FastScanner;
import java.io.PrintWriter;

public class TaskA {
	public void solve(int testNumber, FastScanner in, PrintWriter out) {
        int rhyme = 15;
        int n = in.nextInt();
        int k = in.nextInt();
        for (int it = 0; it < n; it++) {
            String[] s = new String[4];
            for (int j = 0; j < 4; j++) {
                String t = in.next();
                StringBuilder sb = new StringBuilder();
                for (char c : t.toCharArray()) {
                    if (isVowel(c)) {
                        sb.append(c);
                    }
                }
                s[j] = sb.toString();
                if (s[j].length() > k) {
                    s[j] = s[j].substring(s[j].length() - k, s[j].length());
                }
            }
            boolean[][] eq = new boolean[4][4];
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    eq[i][j] = equalBySuffix(s[i], s[j], k);
                }
            }
            if (!(eq[0][1] && eq[0][2] && eq[0][3])) {
                rhyme &= ~1;
            }
            if (!(eq[0][1] && eq[2][3])) {
                rhyme &= ~2;
            }
            if (!(eq[0][2] && eq[1][3])) {
                rhyme &= ~4;
            }
            if (!(eq[0][3] && eq[1][2])) {
                rhyme &= ~8;
            }
        }
        if ((rhyme & 1) == 1) {
            out.println("aaaa");
        } else if ((rhyme & 2) == 2) {
            out.println("aabb");
        } else if ((rhyme & 4) == 4) {
            out.println("abab");
        } else if ((rhyme & 8) == 8) {
            out.println("abba");
        } else {
            out.println("NO");
        }
	}

    static boolean isVowel(char c) {
        return "aeiou".indexOf(c) >= 0;
    }
    static boolean equalBySuffix(String s, String t, int k) {
        if (s.length() < k || t.length() < k) {
            return false;
        }
        return s.equals(t);
    }
}
