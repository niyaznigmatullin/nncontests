package coding;

public class DistinctGrid {
    public int[] findGrid(int n, int k) {
        int[] ret = new int[n * n];
        int cur = 0;
        for (int shift = 0; shift + 1 < k; shift++) {
            for (int i = 0; i < n; i++) {
                int j = (i + shift) % n;
                ret[i * n + j] = ++cur;
            }
        }
        return ret;
    }
}
