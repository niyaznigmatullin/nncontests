package coding;

import ru.ifmo.niyaz.DataStructures.Fenwick;
import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.*;

public class TaskE {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] all = new int[2 * n];
        int[] from = new int[n];
        int[] to = new int[n];
        for (int i = 0; i < n; i++) {
            from[i] = in.nextInt();
            to[i] = in.nextInt();
            all[2 * i] = from[i];
            all[2 * i + 1] = to[i];
        }
        Map<Integer, Integer> z = new HashMap<>();
        all = ArrayUtils.sortAndUnique(all);
        List<Segment> d = new ArrayList<>();
        for (int i = 0; i < all.length; i++) {
            if (i > 0 && all[i] > all[i - 1] + 1) {
                d.add(new Segment(all[i - 1] + 1, all[i] - (all[i - 1] + 1), d.size()));
            }
            d.add(new Segment(all[i], 1, d.size()));
            z.put(all[i], d.size() - 1);
        }
        for (int i = 0; i < n; i++) {
            Segment t = d.get(z.get(from[i]));
            d.set(z.get(from[i]), d.get(z.get(to[i])));
            d.set(z.get(to[i]), t);
        }
        Fenwick f = new Fenwick(d.size());
        long ans = 0;
        for (int i = d.size() - 1; i >= 0; i--) {
            ans += (long) d.get(i).weight * f.getSum(d.get(i).id);
            f.add(d.get(i).id, d.get(i).weight);
        }
        out.println(ans);
    }

    static class Segment {
        int x;
        int weight;
        int id;

        Segment(int x, int weight, int id) {
            this.x = x;
            this.weight = weight;
            this.id = id;
        }
    }
}
