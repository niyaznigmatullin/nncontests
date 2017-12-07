package coding;

public class RangeEncoding {
    public int minRanges(int[] a) {
        int ans = 0;
        for (int i = 0; i < a.length; ) {
            int j = i + 1;
            while (j < a.length && a[j] == a[i] + j - i) ++j;
            ans++;
            i = j;
        }
        return ans;
    }
}
