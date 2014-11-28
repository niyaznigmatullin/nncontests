package coding;

public class IncrementAndDoubling {
    public int getMin(int[] a) {
        int ans = 0;
        while (true) {
            boolean allZero = true;
            for (int i : a) {
                allZero &= i == 0;
            }
            if (allZero) break;
            for (int i = 0; i < a.length; i++) {
                if ((a[i] & 1) == 1) {
                    --a[i];
                    ++ans;
                }
            }
            allZero = true;
            for (int i : a) {
                allZero &= i == 0;
            }
            if (allZero) break;
            ++ans;
            for (int i = 0; i < a.length; i++) {
                a[i] /= 2;
            }
        }
        return ans;
    }
}
