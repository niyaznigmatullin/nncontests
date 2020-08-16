package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class SubstringDeletion {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        String str = in.next();
        if (str == null) throw new UnknownError();
        char[] c = str.toCharArray();
        int[][] letters = new int[26][];
        int[] count = new int[26];
        for (char e : c) count[e - 'a']++;
        for (int i = 0; i < 26; i++) {
            letters[i] = new int[count[i]];
            count[i] = 0;
        }
        for (int i = 0; i < c.length; i++) {
            int letter = c[i] - 'a';
            letters[letter][count[letter]++] = i;
        }
        int[] mask = new int[c.length];
        for (int i = c.length - 1, curMask = 0; i >= 0; i--) {
            curMask |= 1 << (c[i] - 'a');
            mask[i] = curMask;
        }
        int allMask = mask[0];
        int curPos = 0;
        StringBuilder answer = new StringBuilder();
        all: while (allMask > 0) {
            for (int next = 0; next < 26; next++) {
                if (((allMask >>> next) & 1) == 0) continue;
                int nextPos = lowerBound(letters[next], curPos);
                if (nextPos >= letters[next].length) continue;
                nextPos = letters[next][nextPos];
                if ((mask[nextPos] & allMask) != allMask) continue;
                answer.append((char) ('a' + next));
                allMask &= ~(1 << next);
                curPos = nextPos + 1;
                continue all;
            }
            throw new AssertionError();
        }
        out.println(answer);
    }

    static int lowerBound(int[] a, int x) {
        int left = -1;
        int right = a.length;
        while (left < right - 1) {
            int mid = (left + right) >>> 1;
            if (a[mid] < x) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return right;
    }
}
