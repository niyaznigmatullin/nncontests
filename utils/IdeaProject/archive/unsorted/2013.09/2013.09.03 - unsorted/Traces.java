package coding;

import ru.ifmo.niyaz.DataStructures.Fenwick;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;
import java.util.Queue;

public class Traces {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int alphabet = in.nextInt();
        int m = in.nextInt();
        boolean[][] independent = new boolean[alphabet][alphabet];
        for (int i = 0; i < m; i++) {
            String s = in.next();
            independent[s.charAt(0) - 'a'][s.charAt(1) - 'a'] = true;
            independent[s.charAt(1) - 'a'][s.charAt(0) - 'a'] = true;
        }
        String s = in.next();
        String t = in.next();
        if (s.length() != t.length()) {
            out.println("NO");
            return;
        }
        for (int j = 0; j < 10; j++) {
            int count1 = 0;
            int count2 = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) - 'a' == j) ++count1;
                if (t.charAt(i) - 'a' == j) ++count2;
            }
            if (count1 != count2) {
                out.println("NO");
                return;
            }
        }
        int n = s.length();
        int[] head = new int[alphabet];
        Arrays.fill(head, -1);
        int[] next = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            int c = t.charAt(i) - 'a';
            next[i] = head[c];
            head[c] = i;
        }
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            int first = head[c];
            if (first < 0) {
                out.println("NO");
                return;
            }
            head[c] = next[first];
            for (int j = 0; j < alphabet; j++) {
                if (j == c || independent[j][c]) continue;
                if (head[j] >= 0 && head[j] < first) {
                    out.println("NO");
                    return;
                }
            }
        }
        out.println("YES");
    }
}
