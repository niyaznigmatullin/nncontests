package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        String name1 = in.next();
        String name2 = in.next();
        int n = in.nextInt();
        int[][] status = new int[2][100];
        for (int i = 0; i < n; i++) {
            int t = in.nextInt();
            int home = in.next().equals("h") ? 0 : 1;
            int number = in.nextInt();
            boolean yellow = in.next().equals("y");
            if (status[home][number] == 2) {
                continue;
            }
            if (!yellow || status[home][number] == 1) {
                status[home][number] = 2;
                out.println((home == 0 ? name1 : name2) + " " + number + " " + t);
            } else {
                status[home][number] = 1;
            }
        }
    }
}
