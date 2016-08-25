package coding;

public class MultiplicationTable3 {
    public int[] construct(int x) {
        x++;
        int f = Integer.highestOneBit(x);
        int[][] ans = new int[20][20];
        int cn = 0;
        while (f > 1) {
            f >>= 1;
            for (int i = 0; i < cn; i++) {
                ans[i][cn] = cn;
                ans[cn][i] = cn;
            }
            ans[cn][cn] = cn;
            ++cn;
            if ((x & f) != 0) {
                for (int i = 0; i <= cn; i++) {
                    ans[i][cn] = (i + 1) % (cn + 1);
                    ans[cn][i] = (i + 1) % (cn + 1);
                }
                ++cn;
            }
        }
        int[] ret = new int[cn * cn];
        for (int i = 0; i < cn; i++) {
            for (int j = 0; j < cn; j++) {
                ret[i * cn + j] = ans[i][j];
            }
        }
        return ret;
    }
}
