package coding;

public class RemoveCharacters {
    public int minimalDistinct(String A, String B) {
        int[] f = new int[26];
        for (char c = 'a'; c <= 'z'; c++) {
            for (char d = c; d <= 'z'; d++) {
                String a = "";
                String b = "";
                for (char e : A.toCharArray()) {
                    if (e == c || e == d) {
                        a += e;
                    }
                }
                for (char e : B.toCharArray()) {
                    if (e == c || e == d) {
                        b += e;
                    }
                }
                if (!a.equals(b)) {
                    f[c - 'a'] |= 1 << (d - 'a');
                    f[d - 'a'] |= 1 << (c - 'a');
                }
            }
        }
        int ans = 0;
        all: for (int i = 0; i < 1 << 26; i++) {
            if (Integer.bitCount(i) <= ans) continue;
            for (int j = 0; j < 26; j++) {
                if (((i >> j) & 1) == 0) continue;
                if ((f[j] & i) != 0) {
                    continue all;
                }
            }
            ans = Integer.bitCount(i);
        }
        return 26 - ans;
    }
}
