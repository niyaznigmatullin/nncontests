package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.*;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        List<Integer>[] where = new List[201];
        for (int i = 0; i <= 200; i++) {
            where[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < n * (n - 1) / 2; i++) {
            int k = in.nextInt();
            for (int j = 0; j < k; j++) {
                int x = in.nextInt();
                where[x].add(i);
            }
        }
        for (List<Integer> e : where) {
            Collections.sort(e);
        }
        Map<List<Integer>, List<Integer>> map = new HashMap<List<Integer>, List<Integer>>();
        for (int i = 0; i <= 200; i++) {
            if (where[i].isEmpty()) {
                continue;
            }
            List<Integer> e = map.get(where[i]);
            if (e == null) {
                e = new ArrayList<Integer>();
            }
            e.add(i);
            map.put(where[i], e);
        }
        if (map.size() == 1) {
            for (List<Integer> z : map.values()) {
                out.print(z.size() - 1);
                Collections.sort(z);
                int first = -1;
                boolean firs = true;
                for (int i : z) {
                    if (firs) {
                        first = i;
                        firs = false;
                    } else {
                        out.print(" " + i);
                    }
                }
                out.println();
                out.println(1 + " " + first);
            }
            return;
        }
        for (List<Integer> z : map.values()) {
            out.print(z.size());
            Collections.sort(z);
            for (int i : z) {
                out.print(" " + i);
            }
            out.println();
        }
    }
}
