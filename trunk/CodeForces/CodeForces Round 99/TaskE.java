package mypackage;

import niyazio.FastScanner;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskE {
    static class Rule {
        char c;
        int minCount;
        int maxCount;

        Rule(char c, int minCount, int maxCount) {
            this.c = c;
            this.minCount = minCount;
            this.maxCount = maxCount;
        }
    }

    public void solve(int testNumber, FastScanner in, PrintWriter out) {
        char[] c = in.next().toCharArray();
        int k = in.nextInt();
        int leftSatisfied = in.nextInt();
        int rightSatisfied = in.nextInt();
        Rule[] r = new Rule[k];
        for (int i = 0; i < k; i++) {
            r[i] = new Rule(in.next().charAt(0), in.nextInt(), in.nextInt());
        }
        List<Integer>[] where = new List[26];
        for (int i = 0; i < 26; i++) {
            where[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < c.length; i++) {
            where[c[i] - 'a'].add(i);
        }
        int[] countLettersWas = new int[26];
        int[] countSatisfied = new int[k + 1];
        int[] begin = new int[k];
        int[] end = new int[k];
        long ans = 0;
        for (int i = 0; i < c.length; i++) {
            Arrays.fill(countSatisfied, 0);
            for (int j = 0; j < r.length; j++) {
                int ch = r[j].c - 'a';
                int curLetter = countLettersWas[ch];
                int left = r[j].minCount + curLetter - 1;
                int right = r[j].maxCount + curLetter;
                if (left < 0) {
                    left = i - 1;
                } else if (where[ch].size() <= left) {
                    left = c.length;
                } else {
                    left = where[ch].get(left);
                }
                if (right < 0) {
                    right = i - 1;
                } else if (where[ch].size() <= right) {
                    right = c.length;
                } else {
                    right = where[ch].get(right);
                }
                begin[j] = left;
                end[j] = right;
            }
            Arrays.sort(begin);
            Arrays.sort(end);
            int curCount = 0;
            int last = i;
            for (int j = 0, t = 0; j < k || t < k; ) {
                if (t >= k || j < k && begin[j] <= end[t]) {
                    countSatisfied[curCount] += begin[j] - last;
                    curCount++;
                    last = begin[j++];
                } else {
                    countSatisfied[curCount] += end[t] - last;
                    curCount--;
                    last = end[t++];
                }
            }
//            System.err.println("Cs = " + Arrays.toString(countSatisfied));
            for (int j = leftSatisfied; j <= rightSatisfied; j++) {
                ans += countSatisfied[j];
            }
            countLettersWas[c[i] - 'a']++;
        }
        out.println(ans);
    }
}
