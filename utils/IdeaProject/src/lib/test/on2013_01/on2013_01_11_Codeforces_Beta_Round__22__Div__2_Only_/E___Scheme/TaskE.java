package lib.test.on2013_01.on2013_01_11_Codeforces_Beta_Round__22__Div__2_Only_.E___Scheme;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskE {

    static boolean[] was;
    static int[] a;
    static int[] colors;
    static int cntColors;
    static int[] forColor;

    static int dfs(int v) {
        if (was[v]) {
            if (colors[v] < 0) {
                forColor[cntColors] = v;
                colors[v] = cntColors++;
            }
            return colors[v];
        }
        was[v] = true;
        int got = dfs(a[v]);
        return colors[v] = got;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt() - 1;
        }
        boolean[] comes = new boolean[n];
        for (int i = 0; i < n; i++) {
            comes[a[i]] = true;
        }
        was = new boolean[n];
        colors = new int[n];
        cntColors = 0;
        forColor = new int[n];
        Arrays.fill(colors, -1);
        for (int i = 0; i < n; i++) {
            if (was[i]) {
                continue;
            }
            dfs(i);
        }
        List<Integer> ans = new ArrayList<Integer>();
        boolean[] has = new boolean[cntColors];
        for (int i = 0; i < n; i++) {
            if (comes[i]) {
                continue;
            }
            ans.add(forColor[(colors[i] - 1 + cntColors) % cntColors] + 1);
            ans.add(i + 1);
            has[colors[i]] = true;
        }
        for (int i = 0; i < cntColors; i++) {
            if (!has[i]) {
                if (cntColors == 1) {
                    break;
                }
                ans.add(forColor[(i - 1 + cntColors) % cntColors] + 1);
                ans.add(forColor[i] + 1);
            }
        }
        out.println(ans.size() / 2);
        for (int i = 0; i < ans.size(); i += 2) {
            out.println(ans.get(i) + " " + ans.get(i + 1));
        }
    }
}
