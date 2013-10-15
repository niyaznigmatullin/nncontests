package lib.test.on2013_04.on2013_04_13_Russian_Code_Cup_2013_Qualification.TaskA;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int[][] a = new int[3][2];
        for (int i = 0; i < 3; i++) {
            a[i][0] = in.nextInt();
            if (a[i][0] == 0) {
                throw new UnknownError();
            }
            a[i][1] = in.nextInt();
            if (a[i][0] > a[i][1]) {
                int t = a[i][0];
                a[i][0] = a[i][1];
                a[i][1] = t;
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = i + 1; j < 3; j++) {
                if (a[i][0] < a[j][0]) {
                    int t = a[i][0];
                    a[i][0] = a[j][0];
                    a[j][0] = t;
                    t = a[i][1];
                    a[i][1] = a[j][1];
                    a[j][1] = t;
                }
            }
        }
        int ans = a[0][0] * a[0][1];
        a[1][1] -= a[0][1];
        if (a[1][1] < 0) a[1][1] = 0;
        a[2][1] -= a[0][1];
        if (a[2][1] < 0) a[2][1] = 0;
        ans += a[1][0] * a[1][1];
        a[2][1] -= a[1][1];
        if (a[2][1] < 0) a[2][1] = 0;
        ans += a[2][0] * a[2][1];
        out.println(ans);
    }
}
