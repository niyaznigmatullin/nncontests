package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TaskA {

    static String solve(int[][] a) {
        StringBuilder sb = new StringBuilder();
        if (a[0][1] == a[1][0]) {
            if (a[0][1] > 0) {
                sb.append('0');
                for (int i = 0; i < a[0][0]; i++) sb.append('0');
                sb.append('1');
                for (int i = 0; i < a[1][1]; i++) sb.append('1');
                sb.append('0');
                for (int i = 1; i < a[0][1]; i++) {
                    sb.append("10");
                }
            } else {
                if (a[1][1] == 0) {
                    sb.append('0');
                    for (int i = 0; i < a[0][0]; i++) sb.append('0');
                } else if (a[0][0] == 0) {
                    sb.append('1');
                    for (int i = 0; i < a[1][1]; i++) sb.append('1');
                } else {
                    return null;
                }
            }
        } else if (a[0][1] == a[1][0] - 1) {
            sb.append('1');
            for (int i = 0; i < a[1][1]; i++) sb.append('1');
            sb.append('0');
            for (int i = 0; i < a[0][0]; i++) sb.append('0');
            if (a[1][0] > 1) {
                sb.append('1');
                for (int i = 2; i < a[1][0]; i++) {
                    sb.append("01");
                }
                sb.append('0');
            }
        } else if (a[1][0] == a[0][1] - 1) {
            sb.append('0');
            for (int i = 0; i < a[0][0]; i++) sb.append('0');
            sb.append('1');
            for (int i = 0; i < a[1][1]; i++) sb.append('1');
            if (a[0][1] > 1) {
                sb.append('0');
                for (int i = 2; i < a[0][1]; i++) {
                    sb.append("10");
                }
                sb.append('1');
            }
        } else {
            return null;
        }
        return sb.toString();
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int[][] a = new int[2][2];
        a[0][0] = in.nextInt();
        a[0][1] = in.nextInt();
        a[1][0] = in.nextInt();
        a[1][1] = in.nextInt();
        String ans = solve(a);
        if (ans == null) ans = "impossible";
        out.println(ans);
    }

}
