package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.Arrays;

public class TaskB {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        String s = in.next();
        String t = in.next();
        int[][] next = new int[26][t.length()];
        for (int[] d : next) {
            Arrays.fill(d, Integer.MAX_VALUE);
        }
        next[t.charAt(t.length() - 1) - 'a'][t.length() - 1] = t.length() - 1;
        for (int i = t.length() - 2; i >= 0; i--) {
            for (int j = 0; j < 26; j++) {
                next[j][i] = next[j][i + 1];
            }
            next[t.charAt(i) - 'a'][i] = i;
        }
        int[] pref = new int[s.length() + 1];
        pref[0] = 0;
        for (int i = 1; i <= s.length(); i++) {
            int k = pref[i - 1];
            pref[i] = k;
            if (k < t.length() && t.charAt(k) == s.charAt(i - 1)) {
                pref[i]++;
            }
        }
        int[] suf = new int[s.length() + 1];
        suf[s.length()] = t.length();
        for (int i = s.length() - 1; i >= 0; i--) {
            int k = suf[i + 1];
            suf[i] = k;
            if (k > 0 && t.charAt(k - 1) == s.charAt(i)) {
                suf[i]--;
            }
        }
        for (int i = 0; i < s.length(); i++) {
            int left = suf[i + 1] - 1;
            int right = pref[i];
            left = Math.max(left, 0);
            if (next[s.charAt(i) - 'a'][left] > right) {
                out.println("No");
                return;
            }
        }
        out.println("Yes");
	}

    static boolean stupid(String s, String t) {
        all: for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < t.length(); j++) {
                if (s.charAt(i) != t.charAt(j)) {
                    continue;
                }
                int id = j - 1;
                for (int k = i - 1; k >= 0; k--) {
                    if (id >= 0 && s.charAt(k) == t.charAt(id)) {
                        --id;
                    }
                }
                if (id >= 0) {
                    continue;
                }
                id = j + 1;
                for (int k = i + 1; k < s.length(); k++) {
                    if (id < t.length() && s.charAt(k) == t.charAt(id)) {
                        ++id;
                    }
                }
                if (id < t.length()) {
                    continue;
                }
                continue all;
            }
            return false;
        }
        return true;
    }
}
