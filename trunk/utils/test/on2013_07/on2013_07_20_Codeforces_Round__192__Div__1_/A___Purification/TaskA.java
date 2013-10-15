package lib.test.on2013_07.on2013_07_20_Codeforces_Round__192__Div__1_.A___Purification;



import ru.ifmo.niyaz.graphalgorithms.KuhnMatchingGraph;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        char[][] c = in.readCharacterFieldTokens(n, n);
        boolean[] row = new boolean[n];
        boolean[] col = new boolean[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (c[i][j] == '.') {
                    row[i] = true;
                    col[j] = true;
                }
            }
        }
        boolean rowOk = true;
        for (int i = 0; i < n; i++) {
            if (!row[i]) {
                rowOk = false;
                break;
            }
        }
        if (rowOk) {
            for (int i = 0; i < n; i++) {
                int id = -1;
                for (int j = 0; j < n; j++) {
                    if (c[i][j] == '.') {
                        id = j;
                        break;
                    }
                }
                out.println((i + 1) + " " + (id + 1));
            }
            return;
        }
        boolean colOk = true;
        for (int i = 0; i < n; i++) {
            if (!col[i]) {
                colOk = false;
                break;
            }
        }
        if (colOk) {
            for (int i = 0; i < n; i++) {
                int id = -1;
                for (int j = 0; j < n; j++) {
                    if (c[j][i] == '.') {
                        id = j;
                        break;
                    }
                }
                out.println((id + 1) + " " + (i + 1));
            }
            return;
        }
        out.println(-1);
    }
}
