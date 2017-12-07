package coding;

import ru.ifmo.niyaz.arrayutils.ArrayUtils;

import java.util.Arrays;

public class CanidsSeesaw {
    public int[] construct(int[] wolf, int[] fox, int k) {
        int n = wolf.length;
        for (int i = 1; i < n; i++) {
            wolf[i] += wolf[i - 1];
        }
        boolean[] was = new boolean[n];
        int[] current = fox.clone();
        Arrays.sort(current);
        if (calc(wolf, current) > k) return new int[0];
        if (calc(wolf, ArrayUtils.reversed(current)) < k) return new int[0];
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int choose = -1;
            for (int put = 0; put < n; put++) {
                if (was[put]) continue;
                current[i] = fox[put];
                for (int j = 0, pos = i + 1; j < n; j++) {
                    if (j == put || was[j]) continue;
                    current[pos++] = fox[j];
                }
                Arrays.sort(current, i + 1, n);
                if (calc(wolf, current) > k) {
                    continue;
                }
                if (choose < 0 || fox[choose] < fox[put]) {
                    choose = put;
                }
            }
            was[choose] = true;
            current[i] = fox[choose];
            ans[i] = choose;
        }
        if (calc(wolf, current) != k) throw new AssertionError();
        return ans;
    }

    static int calc(int[] wolf, int[] fox) {
        int n = wolf.length;
        int cur = 0;
        int got = 0;
        for (int i = 0; i < n; i++) {
            cur += fox[i];
            if (cur > wolf[i]) ++got;
        }
        return got;
    }
}
