package lib.test.on2013_04.on2013_04_15_Croc_Champ_2013___Round_1.B___Network_Topology;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] deg = new int[n];
        for (int i = 0; i < m; i++) {
            int x = in.nextInt() - 1;
            int y = in.nextInt() - 1;
            deg[x]++;
            deg[y]++;
        }
        if (isCycle(deg)) {
            out.println("ring topology");
            return;
        }
        if (isStar(deg)) {
            out.println("star topology");
            return;
        }
        if (isBus(deg)) {
            out.println("bus topology");
            return;
        }
        out.println("unknown topology");
    }

    private boolean isBus(int[] deg) {
        int cnt = 0;
        for (int i = 0; i < deg.length; i++) {
            if (deg[i] == 1) {
                ++cnt;
                if (cnt > 2) {
                    return false;
                }
            } else if (deg[i] != 2) {
                return false;
            }
        }
        return cnt == 2;
    }

    private boolean isStar(int[] deg) {
        int best = -1;
        for (int i = 0; i < deg.length; i++) {
            if (best < 0 || deg[i] > deg[best]) {
                best = i;
            }
        }
        for (int i = 0; i < deg.length; i++) {
            if (i != best && deg[i] != 1) {
                return false;
            }
        }
        return deg[best] == deg.length - 1;
    }

    private boolean isCycle(int[] deg) {
        for (int i : deg) {
            if (i != 2) return false;
        }
        return true;
    }

}
