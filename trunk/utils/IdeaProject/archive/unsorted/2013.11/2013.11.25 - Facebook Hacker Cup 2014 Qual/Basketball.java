package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;
import java.util.Comparator;

public class Basketball {

    static class Player {
        String name;
        int a;
        int b;

        Player(String name, int a, int b) {
            this.name = name;
            this.a = a;
            this.b = b;
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        out.print("Case #" + testNumber + ":");
        int n = in.nextInt();
        int m = in.nextInt();
        int p = in.nextInt();
        Player[] all = new Player[n];
        for (int i = 0; i < n; i++) {
            all[i] = new Player(in.next(), in.nextInt(), in.nextInt());
        }
        Arrays.sort(all, new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                if (o1.a != o2.a) return -Integer.compare(o1.a, o2.a);
                return -Integer.compare(o1.b, o2.b);
            }
        });
        Player[] a = new Player[(n + 1) / 2];
        Player[] b = new Player[n / 2];
        for (int i = 0; i < n; i++) {
            ((i & 1) == 1 ? b : a)[i / 2] = all[i];
        }
        String[] ans = solveForTeam(m, p, a);
        String[] ans2 = solveForTeam(m, p, b);
        int pos = ans.length;
        ans = Arrays.copyOf(ans, ans.length + ans2.length);
        System.arraycopy(ans2, 0, ans, pos, ans2.length);
        Arrays.sort(ans);
        for (String e : ans) {
            out.print(" " + e);
        }
        out.println();
    }

    private String[] solveForTeam(int m, int p, Player[] a) {
        int n = a.length;
        int[] count = new int[n];
        boolean[] playing = new boolean[n];
        for (int i = 0; i < p; i++) {
            playing[i] = true;
        }
        for (int it = 0; it < m; it++) {
            for (int i = 0; i < n; i++) {
                if (playing[i]) ++count[i];
            }
            if (n <= p) continue;
            int leave = -1;
            for (int i = n - 1; i >= 0; i--) {
                if (!playing[i]) {
                    continue;
                }
                if (leave < 0 || count[leave] < count[i]) {
                    leave = i;
                }
            }
            int come = -1;
            for (int i = 0; i < n; i++) {
                if (playing[i]) continue;
                if (come < 0 || count[come] > count[i]) {
                    come = i;
                }
            }
            playing[come] = true;
            playing[leave] = false;
        }
        String[] ans = new String[p];
        int ac = 0;
        for (int i = 0; i < n; i++) {
            if (playing[i]) {
                ans[ac++] = a[i].name;
            }
        }
        return ans;
    }
}
