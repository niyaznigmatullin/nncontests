package lib.test.on2013_02.on2013_02_02_Facebook_Hacker_Cup_2013_Round_1.Dead_Pixels;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class Dead_Pixels {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        System.err.println(testNumber);
        out.print("Case #" + testNumber + ": ");
        int w = in.nextInt();
        int h = in.nextInt();
        int p = in.nextInt();
        int q = in.nextInt();
        int n = in.nextInt();
        int x = in.nextInt();
        int y = in.nextInt();
        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();
        int d = in.nextInt();
        int[][] cnt = new int[w][h];
        cnt[x][y]++;
        for (int i = 1; i < n; i++) {
            int nx = (x * a + y * b + 1) % w;
            int ny = (x * c + y * d + 1) % h;
            x = nx;
            y = ny;
            cnt[x][y]++;
        }
        for (int i = 0; i < w; i++) {
            int[] cnti = cnt[i];
            for (int j = 1; j < h; j++) {
                cnti[j] += cnti[j - 1];
            }
        }
        for (int i = 1; i < w; i++) {
            int[] cnti = cnt[i];
            int[] cnti1 = cnt[i - 1];
            for (int j = 0; j < h; j++) {
                cnti[j] += cnti1[j];
            }
        }
        int answer = 0;
        for (int i = p - 1; i < w; i++) {
            int[] cnti = cnt[i];
            int[] cntip = i >= p ? cnt[i - p] : null;
            for (int j = q - 1; j < h; j++) {
                int count = cnti[j];
                if (i >= p) {
                    count -= cntip[j];
                }
                if (j >= q) {
                    count -= cnti[j - q];
                }
                if (i >= p && j >= q) {
                    count += cntip[j - q];
                }
                if (count == 0) {
                    ++answer;
                }
            }
        }
        out.println(answer);
    }
}
