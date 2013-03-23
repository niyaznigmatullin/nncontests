package coding;

import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Task1337 {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int week = in.nextInt();
        int[] a = new int[week];
        Arrays.fill(a, -1);
        for (int i = 0; i < n; i++) {
            a[(in.nextInt() - 1)] = i;
        }
        List<Integer>[] edges = new List[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
        }
        int[] deg = new int[n];
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            while (x != 0) {
                deg[i]++;
                edges[x - 1].add(i);
                x = in.nextInt();
            }
        }
        int currentDay = in.nextInt() - 1;
        boolean[] have = new boolean[n];
        {
            int x = in.nextInt();
            while (x != 0) {
                if (!have[x - 1]) {
                    have[x - 1] = true;
                    for (int i : edges[x - 1]) {
                        deg[i]--;
                    }
                }
                x = in.nextInt();
            }
        }
        boolean[] toGet = new boolean[n];
        int need = 0;
        {
            int x = in.nextInt();
            while (x != 0) {
                toGet[x - 1] = true;
                if (!have[x - 1]) {
                    need++;
                }
                x = in.nextInt();
            }
        }
        int answer = 0;
        if (need == 0) {
            ++answer;
        }
        List<Integer>  ans = new ArrayList<>();
        while (need > 0) {
            if (answer > 100500) {
                out.println("No Solution");
                return;
            }
            ++answer;
            int w = a[currentDay];
            if (w >= 0 && !have[w] && deg[w] == 0) {
                ans.add(w);
                have[w] = true;
                if (toGet[w]) {
                    need--;
                }
                for (int i : edges[w]) {
                    deg[i]--;
                }
            }
            currentDay = (currentDay + 1) % week;
        }
        out.println(answer - 1);
        int[] z = ArrayUtils.toPrimitiveArrayInteger(ans);
        for (int i = 0; i < z.length; i++) {
            z[i]++;
        }
        out.printArray(z);
    }
}
