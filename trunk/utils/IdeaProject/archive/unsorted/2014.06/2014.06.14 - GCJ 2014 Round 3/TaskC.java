package coding;

import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        System.err.println("[" + testNumber + "]");
        out.print("Case #" + testNumber + ": ");
        int n = in.nextInt();
        boolean[] type = new boolean[n];
        int[] id = new int[n];
        int[] all = new int[n];
        int cn = 0;
        for (int i = 0; i < n; i++) {
            type[i] = in.next().equals("E");
            id[i] = in.nextInt() - 1;
            if (id[i] >= 0) {
                all[cn++] = id[i];
            }
        }
        all = Arrays.copyOf(all, cn);
        all = ArrayUtils.sortAndUnique(all);
        for (int i = 0; i < n; i++) {
            if (id[i] >= 0) {
                id[i] = Arrays.binarySearch(all, id[i]);
            }
        }
        cn = all.length;
        int[] state = new int[cn];
        Arrays.fill(state, -1);
        boolean[] was = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (id[i] >= 0) {
                if (type[i]) {
                    if (state[id[i]] >= 0) {
                        boolean found = false;
                        for (int j = state[id[i]] + 1; j < i; j++) {
                            if (was[j]) continue;
                            if (!type[j] && id[j] < 0) {
                                was[j] = true;
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            out.println("CRIME TIME");
                            return;
                        }
                    }
                    state[id[i]] = i;
                } else {
                    state[id[i]] = -1;
                }
            }
        }
        Arrays.fill(state, -1);
        for (int i = n - 1; i >= 0; i--) {
            if (id[i] < 0) continue;
            if (!type[i]) {
                if (state[id[i]] >= 0) {
                    boolean found = false;
                    for (int j = state[id[i]] - 1; j > i; j--) {
                        if (was[j]) continue;
                        if (type[j] && id[j] < 0) {
                            was[j] = true;
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        out.println("CRIME TIME");
                        return;
                    }
                }
                state[id[i]] = i;
            } else {
                state[id[i]] = -1;
            }
        }
        Arrays.fill(state, -1);
        for (int i = 0; i < n; i++) {
            if (id[i] < 0) continue;
            if (state[id[i]] >= 0) continue;
            if (!type[i]) {
                for (int j = i - 1; j >= 0; j--) {
                    if (id[j] >= 0 || was[j]) continue;
                    if (type[j]) {
                        was[j] = true;
                        break;
                    }
                }
            }
            state[id[i]] = 1;
        }
        Arrays.fill(state, -1);
        int ans = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (id[i] < 0) continue;
            if (state[id[i]] >= 0) continue;
            if (type[i]) {
                boolean found = false;
                for (int j = i + 1; j < n; j++) {
                    if (id[j] >= 0 || was[j]) continue;
                    if (!type[j]) {
                        was[j] = true;
                        found = true;
                        break;
                    }
                }
                if (!found) ++ans;
            }
            state[id[i]] = 1;
        }
        int balance = 0;
        for (int i = 0; i < n; i++) {
            if (id[i] >= 0) continue;
            if (was[i]) continue;
            if (type[i]) ++balance;
            else balance = Math.max(balance - 1, 0);
        }
        ans += balance;
        out.println(ans);
    }
}
