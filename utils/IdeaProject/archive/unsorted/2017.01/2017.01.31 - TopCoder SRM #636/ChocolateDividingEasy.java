package coding;

public class ChocolateDividingEasy {
    public int findBest(String[] chocolate) {
        int n = chocolate.length;
        int m = chocolate[0].length();
        int l = 0;
        int r = 50 * 50 * 10 + 1;
        while (l < r - 1) {
            boolean found = false;
            int mid = (l + r) >>> 1;
            all: for (int i = 0; i < n; i++) {
                for (int j = i + 1; j + 1 < n; j++) {
                    int sum1 = 0;
                    int sum2 = 0;
                    int sum3 = 0;
                    int parts = 0;
                    for (int k = 0; k < m; k++) {
                        for (int e = 0; e < n; e++) {
                            int val = chocolate[e].charAt(k) - '0';
                            if (e <= i) {
                                sum1 += val;
                            } else if (e <= j) {
                                sum2 += val;
                            } else {
                                sum3 += val;
                            }
                        }
                        if (sum1 >= mid && sum2 >= mid && sum3 >= mid) {
                            sum1 = sum2 = sum3 = 0;
                            ++parts;
                        }
                    }
                    if (parts >= 3) {
                        found = true;
                        break all;
                    }
                }
            }
            if (found) l = mid; else r = mid;
        }
        return l;
    }
}
