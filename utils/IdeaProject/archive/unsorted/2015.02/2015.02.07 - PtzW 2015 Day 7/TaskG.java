package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskG {

    static char[] s;
    static int current;

    static String min(String s, String t) {
        if (s.length() > t.length()) return t;
        if (s.length() < t.length()) return s;
        if (s.compareTo(t) < 0) return s;
        return t;
    }

    static String parse() {
        String ans = parseConcat();
        while (current < s.length && s[current] == '|') {
            ++current;
            String cur = parseConcat();
            ans = min(ans, cur);
        }
        return ans;
    }

    static String parseConcat() {
        String ans = parseCleany();
        while (current < s.length && (Character.isLetter(s[current]) || s[current] == '$' || s[current] == '('))  {
            String cur = parseCleany();
            ans += cur;
        }
        return ans;
    }

    static String parseCleany() {
        String ans = parseTerm();
        while (current < s.length && s[current] == '*') {
            ans = "";
            ++current;
        }
        return ans;
    }

    static String parseTerm() {
        if (s[current] == '(') {
            ++current;
            String ret = parse();
            ++current;
            return ret;
        }
        if (s[current] == '$') {
            ++current;
            return "";
        }
        return s[current++] + "";
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int t = Integer.parseInt(in.readLine());
        for (int i = 0; i < t; i++) {
            String line = in.readLine();
            s = line.toCharArray();
            current = 0;
            String ans = parse();
            if (ans.isEmpty()) ans = "$";
            out.println(ans);
        }
    }
}
