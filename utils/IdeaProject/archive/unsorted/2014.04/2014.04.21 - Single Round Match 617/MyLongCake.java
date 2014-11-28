package coding;

public class MyLongCake {
    public int cut(int n) {
        boolean[] cut = new boolean[n];
        for (int i = 1; i < n; i++) {
            if (n % i != 0) continue;
            int d = n / i;
            for (int j = d; j < n; j += d) {
                cut[j - 1] = true;
            }
        }
        int ans = 0;
        for (boolean e : cut) {
            if (e) ++ans;
        }
        return ans + 1;
    }
}
