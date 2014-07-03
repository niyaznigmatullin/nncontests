package coding;

public class WinterAndPresents {
    public long getNumber(int[] apple, int[] orange) {
        int maximal = Integer.MAX_VALUE;
        int n = apple.length;
        for (int i = 0; i < n; i++) {
            maximal = Math.min(maximal, apple[i] + orange[i]);
        }
        long ans = 0;
        for (int x = 1; x <= maximal; x++) {
            int left = 0;
            int right = 0;
            for (int j = 0; j < n; j++) {
                int cr = Math.min(apple[j], x);
                int cl = Math.max(0, x - Math.min(orange[j], x));
                left += cl;
                right += cr;
            }
            if (right >= left)
                ans += right - left + 1;
        }
        return ans;
    }
}
