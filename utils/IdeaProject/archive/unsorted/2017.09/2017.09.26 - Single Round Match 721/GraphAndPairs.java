package coding;

import java.util.ArrayList;
import java.util.List;

public class GraphAndPairs {

    int current;

    public int[] construct(int d, int k) {
        List<int[]> ret = new ArrayList<>();
        current = 0;
        if (d == 2) {
            while (k > 0) {
                int x = 0;
                while (x * (x + 1) / 2 <= k) ++x;
                ret.addAll(generate2(x));
                k -= x * (x - 1) / 2;
            }
        } else {
            while (k > 0) {
                int x = 0;
                while ((x + 1) * (x + 1) <= k) x++;
                ret.addAll(generate(x, d));
                k -= x * x;
            }
        }
        int[] ans = new int[ret.size() * 2 + 1];
        int ac = 0;
        ans[ac++] = current;
        for (int[] e : ret) {
            ans[ac++] = e[0];
            ans[ac++] = e[1];
        }
        return ans;
    }

    List<int[]> generate(int x, int d) {
        List<int[]> ret = new ArrayList<>();
        for (int i = 0; i < x; i++) {
            ret.add(new int[]{current + i, current + x});
        }
        current += x;
        for (int i = 0; i < d - 2; i++) {
            ret.add(new int[]{current, current + 1});
            current++;
        }
        for (int i = 0; i < x; i++) {
            ret.add(new int[]{current, current + i + 1});
        }
        current += x + 1;
        return ret;
    }

    List<int[]> generate2(int x) {
        List<int[]> ret = new ArrayList<>();
        for (int i = 1; i <= x; i++) {
            ret.add(new int[]{current, current + i});
        }
        current += x + 1;
        return ret;
    }
}
