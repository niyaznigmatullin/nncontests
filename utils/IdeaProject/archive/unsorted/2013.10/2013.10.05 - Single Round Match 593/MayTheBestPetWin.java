package coding;

public class MayTheBestPetWin {
    public int calc(int[] a, int[] b) {
        int n = a.length;
        int sum = 0;
        int sumB = 0;
        for (int i = 0; i < n; i++) {
            sum += b[i] + a[i];
            sumB += b[i];
        }
        boolean[] can = new boolean[sum + 1];
        can[0] = true;
        for (int i = 0; i < n; i++) {
            int x = b[i] + a[i];
            for (int j = sum; j >= x; j--) {
                can[j] |= can[j - x];
            }
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i <= sum; i++) {
            if (!can[i]) continue;
            int minGet = Math.min(i, sum - i);
//            System.out.println(sumB + " " + minGet);
            res = Math.min(res, sumB - minGet);
        }
        return Math.max(res, 0);
    }
}
