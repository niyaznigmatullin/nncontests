package coding;

public class BearPasswordLexic {
    public String findPassword(int[] x) {
        x = x.clone();
        int n = x.length;
        for (int i = n - 2; i >= 0; i--) {
            int len = i + 1;
            for (int j = i + 1; j < n; j++) {
                int len2 = j + 1;
                x[i] -= x[j] * (len2 - len + 1);
            }
            if (x[i] < 0) {
                return "";
            }
        }
        int all = 0;
        for (int i = 0; i < n; i++) {
            all += (i + 1) * x[i];
        }
        if (all != n) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int cur = 0;
        all: while (sb.length() < n) {
            if (cur == 0) {
                for (int i = n - 1; i >= 0; i--) {
                    if (x[i] > 0) {
                        --x[i];
                        for (int j = 0; j <= i; j++) {
                            sb.append('a');
                        }
                        cur ^= 1;
                        continue all;
                    }
                }
            } else {
                for (int i = 0; i < n; i++) {
                    if (x[i] > 0) {
                        --x[i];
                        for (int j = 0; j <= i; j++) {
                            sb.append('b');
                        }
                        cur ^= 1;
                        continue all;
                    }
                }
            }
        }
        return sb.toString();
    }
}
