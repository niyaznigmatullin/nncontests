package coding;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ModModMod {
    public long findSum(int[] m, int R) {
        List<Integer> a = new ArrayList<>();
        a.add(R + 1);
        for (int i : m) {
            if (a.get(a.size() - 1) > i) {
                a.add(i);
            }
        }
        int[] z = new int[R + 1];
        int last = a.get(a.size() - 1);
        for (int i = 0; i < last; i++) {
            z[i] = i;
        }
        for (int i = a.size() - 2; i >= 0; i--) {
            int cur = a.get(i);
            int next = a.get(i + 1);
            for (int j = next; j < cur; j++) {
                z[j] = z[j % next];
            }
        }
        long ans = 0;
        for (int i = 1; i <= R; i++) ans += z[i];
        return ans;
    }
}
