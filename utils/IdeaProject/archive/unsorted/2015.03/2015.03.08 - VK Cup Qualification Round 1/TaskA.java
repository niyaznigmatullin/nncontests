package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.HashMap;
import java.util.Map;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        Map<String, Integer> depth = new HashMap<>();
        depth.put("polycarp", 1);
        for (int i = 0; i < n; i++) {
            String w = in.next().toLowerCase();
            in.next();
            String h = in.next().toLowerCase();
            depth.put(w, depth.get(h) + 1);
        }
        int ans = 0;
        for (int i : depth.values()) ans = Math.max(ans, i);
        out.println(ans);
    }
}
