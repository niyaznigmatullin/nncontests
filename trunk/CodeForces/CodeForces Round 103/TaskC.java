package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.Arrays;

public class TaskC {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        String s = in.next();
        String t = in.next();
        int[] a = new int[26];
        for (char c : t.toCharArray()) {
            a[c - 'a']++;
        }
        if (s.length() < t.length()) {
            out.println(0);
            return;
        }
        int negatives = 0;
        for (int i = 0; i < t.length(); i++) {
            char c = s.charAt(i);
            if (c == '?') {
                continue;
            }
            if (a[c - 'a'] == 0) {
                negatives++;
            }
            a[c - 'a']--;
        }
        int ans = 0;
        if (negatives == 0) {
            ans++;
        }
        for (int i = t.length(); i < s.length(); i++) {
            char add = s.charAt(i - t.length());
            if (add != '?') {
                if (a[add - 'a'] == -1) {
                    negatives--;
                }
                a[add - 'a']++;
            }
            char remove = s.charAt(i);
            if (remove != '?') {
                if (a[remove - 'a'] == 0) {
                    negatives++;
                }
                a[remove - 'a']--;
            }
            if (negatives == 0) {
                ans++;
            }
        }
        out.println(ans);
	}
}
