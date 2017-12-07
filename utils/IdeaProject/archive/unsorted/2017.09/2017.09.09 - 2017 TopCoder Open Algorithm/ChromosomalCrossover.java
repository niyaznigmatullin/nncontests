package coding;

public class ChromosomalCrossover {
    public int maximalLength(String A, String B) {
        int n = A.length();
        int ans = 0;
        for (int start = 0; start < n; start++) {
            for (int shift = 0; shift + start < n; shift++) {
                if (shift == 0) {
                    int i = start;
                    while (i < n && A.charAt(i) == B.charAt(i)) ++i;
                    ans = Math.max(ans, i - start);
                    continue;
                }
                int mx = n;
                for (int i = 0; i < shift; i++) {
                    int curBest = 0;
                    char c = A.charAt(i + start);
                    char d = B.charAt(i + start);
                    for (int it = 0; it < 2; it++) {
                        char curLetter = c;
                        int j = start + i;
                        while (j + shift < n) {
                            if (A.charAt(j + shift) != curLetter && B.charAt(j + shift) != curLetter) {
                                break;
                            }
                            curLetter = (char) (A.charAt(j + shift) ^ B.charAt(j + shift) ^ curLetter);
                            j += shift;
                        }
                        curBest = Math.max(curBest, j + shift);
                        char t = c;
                        c = d;
                        d = t;
                    }
                    mx = Math.min(mx, curBest);
                }
                ans = Math.max(ans, mx - start - shift);
            }
        }
        return ans;
    }
}
